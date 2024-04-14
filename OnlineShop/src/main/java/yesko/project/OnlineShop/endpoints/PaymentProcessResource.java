package yesko.project.OnlineShop.endpoints;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yesko.project.OnlineShop.dto.DiscountDTO;
import yesko.project.OnlineShop.dto.PaymentProcessDTO;
import yesko.project.OnlineShop.dto.PaymentProcessStatusDTO;
import yesko.project.OnlineShop.dto.RequestDiscountDto;
import yesko.project.OnlineShop.service.DiscountService;
import yesko.project.OnlineShop.service.PaymentProcessService;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/payment_process")
public class PaymentProcessResource {
    private final PaymentProcessService paymentProcessService;
    @PostMapping
    public ResponseEntity<PaymentProcessStatusDTO> paymentProcess(
            @RequestBody @Validated PaymentProcessDTO paymentProcessDTO
    ) {
        log.info("PaymentProcess {}", paymentProcessDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentProcessService.paymentProcess(paymentProcessDTO));
    }
}
