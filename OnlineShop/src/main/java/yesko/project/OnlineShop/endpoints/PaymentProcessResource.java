package yesko.project.OnlineShop.endpoints;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yesko.project.OnlineShop.dto.*;
import yesko.project.OnlineShop.service.PaymentProcessService;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/user/payment")
public class PaymentProcessResource {
    private final PaymentProcessService paymentProcessService;
    @GetMapping("")
    public ResponseEntity<List<UserPaymentHistoryDTO>> allPaymentDetails(
    ) {
        log.info("Getting paymentDetails");
        return ResponseEntity.ok()
                .body(paymentProcessService.allPaymentDetails());
    }
    @PostMapping("/pay")
    public ResponseEntity<PaymentProcessStatusDTO> paymentProcess(
            @RequestBody @Validated PaymentProcessDTO paymentProcessDTO
    ) {
        log.info("PaymentProcess {}", paymentProcessDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentProcessService.paymentProcess(paymentProcessDTO));
    }
}
