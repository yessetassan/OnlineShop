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
    private BigDecimal discountPercent;
    private boolean active;
}