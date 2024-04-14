package yesko.project.OnlineShop.repo;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yesko.project.OnlineShop.entity.UserPayment;

import java.util.Optional;

@Repository
public interface UserPaymentRepository extends JpaRepository<UserPayment, Long> {
    @Query("SELECT U FROM UserPayment U WHERE U.user_userPayment.id = :user_id")
    Optional<UserPayment> findByUserId(Long user_id);
}
