package yesko.project.OnlineShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yesko.project.OnlineShop.entity.OrderDetails;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    @Query("SELECT O FROM OrderDetails O WHERE O.orderDet_user.id = :user_id")
    List<OrderDetails> findByUserId(Long user_id);
}
