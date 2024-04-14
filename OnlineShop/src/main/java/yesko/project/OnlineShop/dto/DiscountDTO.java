package yesko.project.OnlineShop.dto;

import lombok.*;
import yesko.project.OnlineShop.entity.Discount;

import java.math.BigDecimal;

import static yesko.project.OnlineShop.utils.Constants.formatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal discountPercent;
    private boolean active;
    private String createdAt;
    private String modifiedAt;
    private String deletedAt;
}