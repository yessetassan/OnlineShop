package yesko.project.OnlineShop.endpoints;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yesko.project.OnlineShop.dto.BasketAddItemDTO;
import yesko.project.OnlineShop.dto.BasketDTO;
import yesko.project.OnlineShop.dto.DiscountDTO;
import yesko.project.OnlineShop.service.BasketService;
import yesko.project.OnlineShop.service.DiscountService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/basket")
public class BasketResource {

    private final BasketService service;
    @GetMapping("/all")
    public ResponseEntity<BasketDTO> takeAll()
    {
        return ResponseEntity.ok().body(service.takeAll());
    }

    @PostMapping("/addItem")
    public ResponseEntity<BasketDTO> addItem(
            @RequestBody @Validated BasketAddItemDTO basketAddItemDTO
    ) {
        log.info("Adding item: {}", basketAddItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addItem(basketAddItemDTO));
    }

    @PostMapping("/deleteItem")
    public ResponseEntity<BasketDTO> deleteItem(
            @RequestBody @Validated BasketAddItemDTO basketAddItemDTO
    ) {
        service.deleteItem(basketAddItemDTO);
        log.info("Deleting item: {}", basketAddItemDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
