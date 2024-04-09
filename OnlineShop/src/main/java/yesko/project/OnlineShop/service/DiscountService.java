package yesko.project.OnlineShop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yesko.project.OnlineShop.dto.DiscountDto;
import yesko.project.OnlineShop.entity.Discount;
import yesko.project.OnlineShop.repo.DiscountRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;

    public List<Discount> all() {
        return discountRepository.findAll();
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
    public Discount updateDiscount(DiscountDto discountDto, Long id) {
        Discount existingDiscount = discountRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot update discount. Discount with id " + id + " does not exist."));
        existingDiscount.setActive(discountDto.isActive());
        existingDiscount.setModifiedAt(new Timestamp(System.currentTimeMillis()));
        existingDiscount.setDiscountPercent(discountDto.getDiscountPercent());

        return discountRepository.save(existingDiscount);
    }
    @Transactional
    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }
}
