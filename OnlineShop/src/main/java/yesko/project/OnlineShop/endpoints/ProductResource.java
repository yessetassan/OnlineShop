package yesko.project.OnlineShop.endpoints;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yesko.project.OnlineShop.dto.ProductDTO;
import yesko.project.OnlineShop.dto.UserDto;
import yesko.project.OnlineShop.entity.Product;
import yesko.project.OnlineShop.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/open-api/product")
public class ProductResource {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> takeAll() {
        List<ProductDTO> users = productService
                .findAllProducts()
                .stream()
                .map(ProductService::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(users);
    }


}
