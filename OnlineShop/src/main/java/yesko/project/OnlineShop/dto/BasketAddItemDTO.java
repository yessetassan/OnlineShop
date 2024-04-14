package yesko.project.OnlineShop.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketAddItemDTO {
    @NotNull(message = "productId should not be null")
    private Long productId;

    @NotNull(message = "quantity should not be null")
    @Min(value = 1, message = "quantity should be at least 1")
    private Integer quantity;
}
