package yesko.project.OnlineShop.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartItemDTO {
    private String productName;
    private Integer quantity;
}
