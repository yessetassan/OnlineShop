package yesko.project.OnlineShop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yesko.project.OnlineShop.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private String SKU;
    private BigDecimal price;
    private Long categoryId;
    private Long inventoryId;
    private Long discountId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;

    public static ProductDTO toProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setSKU(product.getSKU());
        productDTO.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            productDTO.setCategoryId(product.getCategory().getId());
        }
        if (product.getInventory() != null) {
            productDTO.setInventoryId(product.getInventory().getId());
        }
        if (product.getDiscount() != null) {
            productDTO.setDiscountId(product.getDiscount().getId());
        }
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setModifiedAt(product.getModifiedAt());
        productDTO.setDeletedAt(product.getDeletedAt());
        return productDTO;
    }
}