package yesko.project.OnlineShop.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yesko.project.OnlineShop.dto.ProductInventoryDTO;
import yesko.project.OnlineShop.entity.ProductInventory;
import yesko.project.OnlineShop.repo.ProductInventoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductInventoryService {

    private final ProductInventoryRepository productInventoryRepository;

    public Optional<ProductInventory> getProductInventoryById(Long id) {
        return productInventoryRepository.findById(id);
    }

    public List<ProductInventory> getAllProductInventories() {
        return productInventoryRepository.findAll();
    }

    public static ProductInventoryDTO fromEntity(ProductInventory productInventory) {
        if (productInventory == null) {
            return null; // or throw an IllegalArgumentException, based on your design decision
        }
        return new ProductInventoryDTO(
                productInventory.getId(),
                productInventory.getQuantity(),
                productInventory.getCreatedAt(),
                productInventory.getModifiedAt(),
                productInventory.getDeletedAt()
        );
    }

    public ProductInventory updateProductInventory(Long id, Integer minusQuantity) {
        ProductInventory productInventory = productInventoryRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        Integer quantity = productInventory.getQuantity();
        if (quantity < minusQuantity) throw new IllegalStateException("Not enough quantity for product...");
        productInventory.setQuantity(productInventory.getQuantity() - minusQuantity);
        return productInventoryRepository.save(productInventory);
    }

    public void deleteProductInventory(Long id) {
        productInventoryRepository.deleteById(id);
    }

}
