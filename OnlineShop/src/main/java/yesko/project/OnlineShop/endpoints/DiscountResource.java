//package yesko.project.OnlineShop.endpoints;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import yesko.project.OnlineShop.dto.DiscountDTO;
//import yesko.project.OnlineShop.dto.RequestDiscountDto;
//import yesko.project.OnlineShop.dto.ResponseStatusDto;
//import yesko.project.OnlineShop.entity.Discount;
//import yesko.project.OnlineShop.service.DiscountService;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static yesko.project.OnlineShop.utils.enums.ResponseStatus.*;
//
//@RestController
//@AllArgsConstructor
//@Slf4j
//@RequestMapping("/api/discount")
//public class DiscountResource {
//
//    private final DiscountService service;
//
//    @GetMapping
//    public ResponseEntity<List<DiscountDTO>> takeAll() {
//        log.info("Received GET request to retrieve all discounts");
//        List<DiscountDTO> discounts = service
//                .all()
//                .stream()
//                .map(DiscountService::fromEntity)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok().body(discounts);
//    }
//
//    @GetMapping("/byActive")
//    public ResponseEntity<List<DiscountDTO>> takeByActive() {
//        log.info("Retrieving active discounts from the service");
//        List<DiscountDTO> discounts = service
//                .all()
//                .stream()
//                .filter(discount -> discount.getActive().equals(true))
//                .map(DiscountService::fromEntity)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok().body(discounts);
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<DiscountDTO> create(
//            @RequestBody @Validated RequestDiscountDto discountDTO
//    ) {
//        log.info("Creating discount {}", discountDTO);
//        DiscountDTO createdDiscount = DiscountService.fromEntity(service.saveOrUpdateDiscount(discountDTO, discountDTO.v));
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiscount);
//    }
//
//    @PutMapping("/modify")
//    public ResponseEntity<DiscountDTO> modify(
//            @RequestBody DiscountDTO discountDTO
//    ) {
//        log.info("Update discount with {}", discountDTO);
//        DiscountDTO returnDiscountDto = DiscountService.fromEntity(service.saveOrUpdateDiscount(discountDTO, discountDTO.getId()));
//        return ResponseEntity.ok().body(returnDiscountDto);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
//        log.info("Deleting discount with ID: {}", id);
//        service.deleteDiscount(id);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
//    }
//
//}
