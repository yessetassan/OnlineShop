package yesko.project.OnlineShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yesko.project.OnlineShop.entity.UserAuth;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
    @Query("SELECT u FROM UserAuth u WHERE u.user_userAuth.username LIKE :username")
    Optional<UserAuth> findByUsername(@Param("username") String username);
}
