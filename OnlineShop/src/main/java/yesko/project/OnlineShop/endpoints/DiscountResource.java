package yesko.project.OnlineShop.endpoints;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yesko.project.OnlineShop.dto.DiscountDto;
import yesko.project.OnlineShop.dto.UserDto;
import yesko.project.OnlineShop.entity.Discount;
import yesko.project.OnlineShop.service.DiscountService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/open-api/discount")
public class DiscountResource {

    private final DiscountService service;

    @GetMapping
    public ResponseEntity<List<DiscountDto>> takeAll() {
        List<DiscountDto> discounts = service.all()
                .stream()
                .map(DiscountDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(discounts);
    }

    @GetMapping("/byActive")
    public ResponseEntity<List<DiscountDto>> takeByActive() {
        List<DiscountDto> discounts = service.all()
                .stream()
                .filter(discount -> discount.getActive().equals(true))
                .map(DiscountDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(discounts);
    }

    @PutMapping("/byId/modify")
    public ResponseEntity<DiscountDto> takeByActive(
            @RequestBody DiscountDto redDiscountDto
    ) {
        Discount discount = service.updateDiscount(redDiscountDto,redDiscountDto.getId());
        return ResponseEntity.ok().body(
                DiscountDto.fromEntity(discount));
    }

}
