package yesko.project.OnlineShop.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketDTO {
    private BigDecimal total;
    private List<CartItemDTO> cartItemDTOList;
}
