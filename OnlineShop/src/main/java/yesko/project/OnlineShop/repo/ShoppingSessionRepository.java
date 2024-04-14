package yesko.project.OnlineShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yesko.project.OnlineShop.entity.ShoppingSession;

import java.util.Optional;

@Repository
public interface ShoppingSessionRepository extends JpaRepository<ShoppingSession, Long> {
    @Query("SELECT S FROM ShoppingSession S WHERE S.user_shopSession.id = :user_id")
    Optional<ShoppingSession> findByUserId(@Param("user_id") Long user_id);
}
