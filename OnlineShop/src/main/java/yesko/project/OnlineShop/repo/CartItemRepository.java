package yesko.project.OnlineShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yesko.project.OnlineShop.entity.CartItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT C FROM CartItem C WHERE C.cart_shopSession.id = :session_id")
    List<CartItem> findAllBySessionId(@Param("session_id") Long session_id);
    @Query("SELECT C FROM CartItem C WHERE C.cart_shopSession.id = :sessionId " +
            "and C.cart_product.id = :productId")
    Optional<CartItem> getCartItemBySessionIdAndProductId(Long sessionId, Long productId);

}
