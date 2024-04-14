package yesko.project.OnlineShop.dto;

import lombok.*;
import yesko.project.OnlineShop.entity.ProductCategory;

import java.time.format.DateTimeFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDTO {

    private Long id;
    private String name;
    private String description;
    private String createdAt;
    private String modifiedAt;
    private String deletedAt;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


}
