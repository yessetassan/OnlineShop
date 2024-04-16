package yesko.project.OnlineShop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import yesko.project.OnlineShop.dto.DiscountDTO;
import yesko.project.OnlineShop.dto.RequestDiscountDto;
import yesko.project.OnlineShop.entity.Discount;
import yesko.project.OnlineShop.repo.DiscountRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static yesko.project.OnlineShop.utils.Constants.formatter;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;
    private static final Logger logger = LoggerFactory.getLogger(DiscountService.class);

    public List<Discount> all() {
        return discountRepository.findAll();
    }

    public List<Discount> findByDiscountPercentByAscendingOrder() {
        return discountRepository.findByDiscountPercentByAscendingOrder();
    }

    public List<Discount> findByDiscountPercentByDescendingOrder() {
        return discountRepository.findByDiscountPercentByDescendingOrder();
    }

    public Optional<Discount> getDiscountById(Long id) {
        return discountRepository.findById(id);
    }

    public List<Discount> getActiveDiscounts(boolean active) {
        return discountRepository.findByActive(active);
    }

    @Transactional
    public Discount saveOrUpdateDiscount(RequestDiscountDto requestDiscountDto, Long id) {
        Discount discount = id == null ? new Discount() : discountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discount with id " + id + " not found"));
        mapDiscountDtoToEntity(requestDiscountDto, discount);
        return discountRepository.save(discount);
    }

    @Transactional
    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
        logger.info("Deleted discount with ID: {}", id);
    }

    private void mapDiscountDtoToEntity(RequestDiscountDto dto, Discount discount) {
        discount.setName(dto.getName());
        discount.setDescription(dto.getDescription());
        discount.setDiscountPercent(dto.getDiscountPercent());
        discount.setActive(false);
        if (discount.getId() == null) {
            discount.setCreatedAt(LocalDateTime.now());
        } else {
            discount.setModifiedAt(LocalDateTime.now());
        }
    }

    public static DiscountDTO fromEntity(Discount discount) {
        DiscountDTO dto = new DiscountDTO();
        dto.setId(discount.getId());
        return dto;
    }
}

