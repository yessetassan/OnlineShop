package yesko.project.OnlineShop.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yesko.project.OnlineShop.dto.ProductCategoryDTO;
import yesko.project.OnlineShop.entity.ProductCategory;
import yesko.project.OnlineShop.repo.ProductCategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static yesko.project.OnlineShop.utils.Constants.formatter;

@Service
@AllArgsConstructor
public class ProductCategoryService {

    private ProductCategoryRepository productCategoryRepository;


    public static ProductCategoryDTO fromEntity(ProductCategory category) {
        return ProductCategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .createdAt(category.getCreatedAt() != null ? category.getCreatedAt().format(formatter) : null)
                .modifiedAt(category.getModifiedAt() != null ? category.getModifiedAt().format(formatter) : null)
                .deletedAt(category.getDeletedAt() != null ? category.getDeletedAt().format(formatter) : null)
                .build();
    }

    public Optional<ProductCategory> getProductCategoryById(Long id) {
        return productCategoryRepository.findById(id);
    }

    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    public void deleteProductCategory(Long id) {
        productCategoryRepository.deleteById(id);
    }

    public Optional<ProductCategory> findByName(String name) {
        return productCategoryRepository.findByName("%" + name + "%");
    }

}
