package yesko.project.OnlineShop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    public static DiscountDTO fromEntity(Discount discount) {
        DiscountDTO dto = new DiscountDTO();
        dto.setId(discount.getId());
        dto.setName(discount.getName());
        dto.setDescription(discount.getDescription());
        dto.setDiscountPercent(discount.getDiscountPercent());
        dto.setActive(discount.getActive());
        if (discount.getCreatedAt() != null) {
            dto.setCreatedAt(discount.getCreatedAt().format(formatter));
        }
        if (discount.getModifiedAt() != null) {
            dto.setModifiedAt(discount.getModifiedAt().format(formatter));
        }
        if (discount.getDeletedAt() != null) {
            dto.setDeletedAt(discount.getDeletedAt().format(formatter));
        }
        return dto;
    }

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
    public Discount saveDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    @Transactional
    public Discount saveDiscountDto(RequestDiscountDto requestDiscountDto) {
        Discount discount = new Discount();
        discount.setName(requestDiscountDto.getName());
        discount.setDescription(requestDiscountDto.getDescription());
        discount.setDiscountPercent(requestDiscountDto.getDiscountPercent());
        discount.setActive(false);
        discount.setCreatedAt(LocalDateTime.now());
        return discountRepository.save(discount);
    }
    @Transactional
    public Discount updateDiscount(DiscountDTO discountDto, Long id) {
        Discount existingDiscount = discountRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot update discount. Discount with id " + id + " does not exist."));
        existingDiscount.setActive(discountDto.isActive());
        existingDiscount.setModifiedAt(LocalDateTime.now());
        existingDiscount.setDiscountPercent(discountDto.getDiscountPercent());
        return discountRepository.save(existingDiscount);
    }
    @Transactional
    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }
}
