package yesko.project.OnlineShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yesko.project.OnlineShop.entity.OrderItem;
import yesko.project.OnlineShop.repo.OrderItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Transactional
    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("OrderItem with id " + id + " not found"));
        orderItem.setOrderItem_product(updatedOrderItem.getOrderItem_product());
        orderItem.setQuantity(updatedOrderItem.getQuantity());
        return orderItemRepository.save(orderItem);
    }

    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Transactional
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
