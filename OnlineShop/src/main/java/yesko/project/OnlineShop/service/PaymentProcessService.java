package yesko.project.OnlineShop.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yesko.project.OnlineShop.dto.PaymentProcessDTO;
import yesko.project.OnlineShop.dto.PaymentProcessStatusDTO;
import yesko.project.OnlineShop.dto.UserPaymentHistoryDTO;
import yesko.project.OnlineShop.entity.*;
import yesko.project.OnlineShop.utils.SecurityUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static yesko.project.OnlineShop.utils.CheckPaymentProcess.limitsProductQuantity;
import static yesko.project.OnlineShop.utils.CheckPaymentProcess.userHasNotEnoughMoney;
import static yesko.project.OnlineShop.utils.Constants.PAID;
import static yesko.project.OnlineShop.utils.ErrorMessage.*;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentProcessService {
    private final ProductService productService;
    private final OrderDetailsService orderDetailsService;
    private final SecurityUtil securityUtil;
    private final OrderItemService orderItemService;
    private final PaymentDetailsService paymentDetailsService;
    private final UserPaymentService userPaymentService;
    private final PaymentStatusService paymentStatusService;
    public PaymentProcessStatusDTO paymentProcess(PaymentProcessDTO paymentProcessDTO) {
        User user = securityUtil.getCurrentUser();
        log.info("Processing payment for user: {}", user.getId());
        Integer quantity = paymentProcessDTO.getQuantity();
        Long productId = paymentProcessDTO.getProductId();
        Product product = productService.findProductById(productId)
                .orElseThrow(() -> new IllegalArgumentException(String.format(PRODUCT_NOT_FOUND_ERROR, productId)));
        BigDecimal totalSum = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        OrderDetails orderDetails = createOrderDetails(user, totalSum);
        PaymentDetails paymentDetails = createPaymentDetails(user,orderDetails,product,quantity);
        createOrderItem(orderDetails, product, quantity);

        return PaymentProcessStatusDTO.builder()
                .status(paymentDetails.getPaymentDet_paymentStatus().getName())
                .build();
    }

    private PaymentStatus returnPaymentStatus(Product product, Integer quantity) {
        if (limitsProductQuantity(product,quantity))
            throw new IllegalArgumentException(QUANTITY_EXCEEDS_STOCK);
        if (userHasNotEnoughMoney(product))
            throw new IllegalArgumentException(INSUFFICIENT_FUNDS);
        return paymentStatusService.findByName(PAID)
                .orElseThrow(() -> new IllegalArgumentException(PAYMENT_STATUS_NOT_FOUND));
    }

    private PaymentDetails createPaymentDetails(User user, OrderDetails orderDetails, Product product, Integer quantity) {
        UserPayment userPayment = userPaymentService.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException(USER_PAYMENT_DETAILS_NOT_FOUND));
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentDet_orderDet(orderDetails);
        paymentDetails.setProvider(userPayment.getProvider());
        paymentDetails.setPaymentDet_paymentStatus(returnPaymentStatus(product, quantity));
        return paymentDetailsService.saveOrUpdatePaymentDetails(paymentDetails);
    }
    private OrderItem createOrderItem(OrderDetails orderDetails, Product product, Integer quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItem_orderDet(orderDetails);
        orderItem.setOrderItem_product(product);
        orderItem.setQuantity(quantity);
        return orderItemService.saveOrderItem(orderItem);
    }
    private OrderDetails createOrderDetails(User user, BigDecimal totalSum) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setTotal(totalSum);
        orderDetails.setOrderDet_user(user);
        return orderDetailsService.save(orderDetails);
    }

    public List<UserPaymentHistoryDTO> allPaymentDetails() {
        User user = securityUtil.getCurrentUser();
        return orderDetailsService
                .findByUserId(user.getId())
                .stream()
                .map(OrderDetailsService::toUserPaymentDTO)
                .collect(Collectors.toList());
    }
}
