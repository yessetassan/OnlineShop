package yesko.project.OnlineShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yesko.project.OnlineShop.entity.Product;
import yesko.project.OnlineShop.entity.Role;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
}

