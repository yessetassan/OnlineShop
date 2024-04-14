package yesko.project.OnlineShop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public Optional<OrderDetails> findByUserId(Long userId) {
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
}
