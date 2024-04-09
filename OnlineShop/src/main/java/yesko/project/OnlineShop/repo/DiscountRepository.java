package yesko.project.OnlineShop.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yesko.project.OnlineShop.entity.Discount;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    List<Discount> findByActive(boolean active);
    @Query("SELECT D FROM Discount D ORDER BY D.discountPercent DESC")
    List<Discount> findByDiscountPercentByAscending();

}
