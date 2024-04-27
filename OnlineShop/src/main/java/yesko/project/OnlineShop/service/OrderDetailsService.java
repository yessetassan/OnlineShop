package yesko.project.OnlineShop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yesko.project.OnlineShop.dto.UserPaymentHistoryDTO;
import yesko.project.OnlineShop.entity.OrderDetails;
import yesko.project.OnlineShop.repo.OrderDetailsRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;
    @Transactional(readOnly = true)
    public Optional<OrderDetails> findById(Long id) {
        return orderDetailsRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<OrderDetails> findByUserId(Long userId) {
        return orderDetailsRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<OrderDetails> findAll() {
        return orderDetailsRepository.findAll();
    }

    @Transactional
    public OrderDetails save(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    @Transactional
    public void deleteById(Long id) {
        orderDetailsRepository.deleteById(id);
    }

    public static UserPaymentHistoryDTO toUserPaymentDTO(OrderDetails orderDetails) {
        UserPaymentHistoryDTO dto = new UserPaymentHistoryDTO();
        dto.setProductInfo(orderDetails.getOrderItem().getOrderItem_product().getName()); // Assuming OrderDetails has getId()
        dto.setQuantity(orderDetails.getOrderItem().getQuantity());
        dto.setPaymentStatus(orderDetails.getPaymentDetails().getPaymentDet_paymentStatus().getName());
        return dto;
    }
}
