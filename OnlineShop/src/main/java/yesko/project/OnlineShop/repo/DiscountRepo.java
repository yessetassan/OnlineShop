package yesko.project.OnlineShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yesko.project.OnlineShop.entity.Discount;
import yesko.project.OnlineShop.entity.ProductInventory;

@Repository
public interface DiscountRepo  extends JpaRepository<Discount, Integer> {
}