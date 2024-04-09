package yesko.project.OnlineShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yesko.project.OnlineShop.entity.User;
import yesko.project.OnlineShop.entity.UserAuth;

import java.util.Optional;

@Repository
public interface UserRepo  extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
