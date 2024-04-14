package yesko.project.OnlineShop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Repository;
import yesko.project.OnlineShop.entity.Discount;
import yesko.project.OnlineShop.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    @Query("SELECT P FROM ProductCategory P WHERE P.name LIKE :name")
    Optional<ProductCategory> findByName(String name);
}