package yesko.project.OnlineShop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import yesko.project.OnlineShop.entity.Discount;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static yesko.project.OnlineShop.utils.Constants.formatter;

@Getter
@Setter
@ToString
public class RequestDiscountDto {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "Discount percentage cannot be blank")
    private BigDecimal discountPercent;
}
