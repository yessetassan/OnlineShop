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
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/user/payment")
public class PaymentProcessResource {
    private final PaymentProcessService paymentProcessService;
    @GetMapping("")
    public CompletableFuture<ResponseEntity<List<UserPaymentHistoryDTO>>> allPaymentDetails(
    ) {
        log.info("Getting paymentDetails");
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok()
                .body(paymentProcessService.allPaymentDetails()));
    }
    @PostMapping("/pay")
    public CompletableFuture<ResponseEntity<PaymentProcessStatusDTO>> paymentProcess(
            @RequestBody @Validated PaymentProcessDTO paymentProcessDTO
    ) {
        log.info("PaymentProcess {}", paymentProcessDTO);
        return CompletableFuture.supplyAsync(() -> ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentProcessService.paymentProcess(paymentProcessDTO)));
    }
}
