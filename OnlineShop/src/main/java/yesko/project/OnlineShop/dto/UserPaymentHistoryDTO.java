package yesko.project.OnlineShop.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yesko.project.OnlineShop.entity.PaymentStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPaymentHistoryDTO {
    private String productInfo;
    private Integer quantity;
    private String paymentStatus;
}
