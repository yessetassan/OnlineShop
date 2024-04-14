package yesko.project.OnlineShop.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yesko.project.OnlineShop.dto.DiscountDTO;
import yesko.project.OnlineShop.dto.PaymentProcessDTO;
import yesko.project.OnlineShop.dto.PaymentProcessStatusDTO;
import yesko.project.OnlineShop.entity.OrderDetails;
import yesko.project.OnlineShop.entity.Product;

@Service
@AllArgsConstructor
public class PaymentProcessService {
    private final ProductService productService;
    public PaymentProcessStatusDTO paymentProcess(PaymentProcessDTO paymentProcessDTO) {
        Integer quantity = paymentProcessDTO.getQuantity();
        Long productId = paymentProcessDTO.getProductId();
        Product product = productService.findProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Product with id %d not found", productId)));
        return null;
    }
}
