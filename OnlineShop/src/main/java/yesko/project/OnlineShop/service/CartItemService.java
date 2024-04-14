package yesko.project.OnlineShop.service;

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
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepo;

    public CartItem saveCartItem(CartItem cartItem) {
        return cartItemRepo.save(cartItem);
    }

    @Transactional
    public CartItem updateCartItem(CartItem cartItem, Integer quantity) {
        int preQuantity = cartItem.getQuantity() + quantity;
        if (preQuantity == 0) {
            deleteCartItem(cartItem.getId());
            return null;
        }
        if (preQuantity < 0)
            throw new IllegalArgumentException("Total Quantity can not be less than ZERO...");
        cartItem.setQuantity(preQuantity);
        return cartItemRepo.save(cartItem);
    }
    public Optional<CartItem> getCartItemById(Long id) {
        return cartItemRepo.findById(id);
    }
    public Optional<CartItem> getCartItemBySessionIdAndProductId(Long sessionId, Long productId) {
        return cartItemRepo.getCartItemBySessionIdAndProductId(sessionId,productId);
    }
    public List<CartItem> getAllCartItems() {
        return cartItemRepo.findAll();
    }
    public List<CartItem> getAllCartItemsBySessionId(Long sessionId) {
        return cartItemRepo.findAll();
    }
    public List<CartItem> findAllBySessionId(Long sessionId) {
        return cartItemRepo.findAllBySessionId(sessionId);
    }
    public static CartItemDTO fromEntity(CartItem cartItem) {
        if (cartItem == null || cartItem.getCart_product() == null) {
            return null; // or throw an exception depending on your design decision
        }
        CartItemDTO dto = new CartItemDTO();
        dto.setProductName(cartItem.getCart_product().getName()); // Assuming the product has a 'getName()' method
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }

    public void deleteCartItem(Long id) {
        cartItemRepo.deleteById(id);
    }
}

