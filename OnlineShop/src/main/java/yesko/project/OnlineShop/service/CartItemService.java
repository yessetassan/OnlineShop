package yesko.project.OnlineShop.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yesko.project.OnlineShop.dto.CartItemDTO;
import yesko.project.OnlineShop.entity.CartItem;
import yesko.project.OnlineShop.repo.CartItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CartItemService {

    private static final Logger logger = LoggerFactory.getLogger(CartItemService.class);
    private CartItemRepository cartItemRepo;

    public CartItem saveCartItem(CartItem cartItem) {
        validateCartItem(cartItem);
        return cartItemRepo.save(cartItem);
    }

    @Transactional
    public CartItem updateCartItem(CartItem cartItem, Integer quantity) {
        validateCartItem(cartItem);
        int preQuantity = cartItem.getQuantity() + quantity;
        if (preQuantity < 0) {
            throw new IllegalArgumentException("Total quantity cannot be less than zero...");
        } else if (preQuantity == 0) {
            deleteCartItem(cartItem.getId());
            return null;
        } else {
            cartItem.setQuantity(preQuantity);
            return cartItemRepo.save(cartItem);
        }
    }

    public Optional<CartItem> getCartItemById(Long id) {
        return cartItemRepo.findById(id);
    }

    public Optional<CartItem> getCartItemBySessionIdAndProductId(Long sessionId, Long productId) {
        return cartItemRepo.getCartItemBySessionIdAndProductId(sessionId, productId);
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepo.findAll();
    }

    public List<CartItem> getAllCartItemsBySessionId(Long sessionId) {
        return cartItemRepo.findAllBySessionId(sessionId);
    }

    public void deleteCartItem(Long id) {
        cartItemRepo.deleteById(id);
    }

    private void validateCartItem(CartItem cartItem) {
        if (cartItem == null || cartItem.getCart_product() == null) {
            logger.error("Invalid cart item or product reference");
            throw new IllegalArgumentException("Invalid cart item or product reference.");
        }
    }

    public static CartItemDTO fromEntity(CartItem cartItem) {
        if (cartItem == null || cartItem.getCart_product() == null) {
            return null;
        }
        CartItemDTO dto = new CartItemDTO();
        dto.setProductName(cartItem.getCart_product().getName());
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }
}
