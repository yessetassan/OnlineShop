package yesko.project.OnlineShop.dto;

import lombok.*;
import yesko.project.OnlineShop.entity.Product;
import yesko.project.OnlineShop.entity.ProductCategory;
import yesko.project.OnlineShop.entity.ProductInventory;

import java.math.BigDecimal;

import static yesko.project.OnlineShop.utils.Constants.formatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String SKU;
    private BigDecimal price;
    private DiscountDTO discountDTO;
    private ProductCategoryDTO productCategoryDTO;
    private ProductInventoryDTO productInventoryDTO;
    private String createdAt;
    private String modifiedAt;
    private String deletedAt;
    private Long categoryId;
    private Long inventoryId;
    private Long discountId;
}
