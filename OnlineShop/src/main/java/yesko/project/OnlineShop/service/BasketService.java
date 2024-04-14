package yesko.project.OnlineShop.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yesko.project.OnlineShop.dto.BasketAddItemDTO;
import yesko.project.OnlineShop.dto.BasketDTO;
import yesko.project.OnlineShop.dto.CartItemDTO;
import yesko.project.OnlineShop.entity.*;
import yesko.project.OnlineShop.utils.SecurityUtil;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class BasketService {
    private final SecurityUtil securityUtil;
    private final ShoppingSessionService shoppingSessionService;
    private final CartItemService cartItemService;
    private final ProductService productService;
    public BasketDTO takeAll() {
        User user = securityUtil.getCurrentUser();
        ShoppingSession shoppingSession = Optional.ofNullable(user.getShoppingSession())
                .orElseGet(() -> returnNewGeneratedShoppingSession(user));
        return returnBasket(shoppingSession);
    }

    private BasketDTO returnBasket(ShoppingSession shoppingSession) {
        BasketDTO basketDTO = new BasketDTO();
        basketDTO.setTotal(shoppingSession.getTotal());
        basketDTO.setCartItemDTOList(
                cartItemService
                        .findAllBySessionId(shoppingSession.getId())
                        .stream()
                        .map(CartItemService::fromEntity)
                        .collect(Collectors.toList())
        );
        return basketDTO;
    }

    public BasketDTO addItem(BasketAddItemDTO basketAddItemDTO) {
        Integer quantity = basketAddItemDTO.getQuantity();
        Long productId = basketAddItemDTO.getProductId();
        User user = securityUtil.getCurrentUser();
        Product product = productService
                .findProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Product with id %d not found", productId)));
        ShoppingSession shoppingSession = Optional.ofNullable(user.getShoppingSession())
                .orElseGet(() -> returnNewGeneratedShoppingSession(user));

        addProductToBasket(shoppingSession, product, quantity);

        return returnBasket(shoppingSession);
    }

    private void addProductToBasket(ShoppingSession shoppingSession, Product product, Integer quantity) {
        BigDecimal newTotal = shoppingSession.getTotal().add(BigDecimal.valueOf(quantity).multiply(product.getPrice()));
        CartItem cartItem = cartItemService
                .getCartItemBySessionIdAndProductId(shoppingSession.getId(), product.getId())
                .orElseGet(() -> createCartItem(shoppingSession,product));
        cartItem = cartItemService.updateCartItem(cartItem, quantity);

        shoppingSession.setTotal(newTotal);
        shoppingSessionService.saveShoppingSession(shoppingSession);
    }

    private CartItem createCartItem(ShoppingSession shoppingSession, Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setCart_shopSession(shoppingSession);
        cartItem.setCart_product(product);
        cartItem.setQuantity(0);
        return cartItemService.saveCartItem(cartItem);
    }

    private ShoppingSession returnNewGeneratedShoppingSession(User user) {
        ShoppingSession shoppingSession = new ShoppingSession();
        shoppingSession.setUser_shopSession(user);
        shoppingSession.setTotal(BigDecimal.ZERO);
        return shoppingSessionService.saveShoppingSession(shoppingSession);
    }

    public void deleteItem(BasketAddItemDTO basketAddItemDTO) {
        Integer quantity = basketAddItemDTO.getQuantity();
        Long productId = basketAddItemDTO.getProductId();
        User user = securityUtil.getCurrentUser();
        Product product = productService
                .findProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Product with id %d not found", productId)));
        ShoppingSession shoppingSession = Optional.ofNullable(user.getShoppingSession())
                .orElseThrow(() -> new IllegalArgumentException("Session for User is not found"));

        deleteProductToBasket(shoppingSession, product, quantity);

        returnBasket(shoppingSession);
    }

    private void deleteProductToBasket(ShoppingSession shoppingSession, Product product, Integer quantity) {
        CartItem cartItem = cartItemService
                .getCartItemBySessionIdAndProductId(shoppingSession.getId(), product.getId())
                .orElseThrow(() -> new IllegalArgumentException("CartItem for this Product is not found"));
        BigDecimal newTotal = shoppingSession.getTotal().subtract(BigDecimal.valueOf(quantity).multiply(product.getPrice()));
        if (newTotal.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Total can not be less than ZERO...");
        }
        cartItem = cartItemService.updateCartItem(cartItem, quantity * (-1));
        shoppingSession.setTotal(newTotal);
        shoppingSessionService.saveShoppingSession(shoppingSession);
    }
}
