package yesko.project.OnlineShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yesko.project.OnlineShop.entity.Discount;
import yesko.project.OnlineShop.entity.Product;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    @Query("SELECT P FROM Product P WHERE P.product_productCategory.name like :category")
    List<Product> findAllByCategory(@Param("category") String category);
}
