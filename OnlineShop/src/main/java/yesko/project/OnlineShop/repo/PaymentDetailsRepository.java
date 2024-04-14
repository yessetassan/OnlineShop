package yesko.project.OnlineShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yesko.project.OnlineShop.entity.PaymentDetails;

import java.util.Optional;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {
    @Query("SELECT P FROM PaymentDetails P WHERE P.paymentDet_orderDet.id = :order_id")
    Optional<PaymentDetails> findByOrderId(Long order_id);
}
