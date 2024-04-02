package yesko.project.OnlineShop.endpoints;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yesko.project.OnlineShop.dto.ProductDTO;
import yesko.project.OnlineShop.entity.Product;
import yesko.project.OnlineShop.repo.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController // Indicates that this class is a REST controller
@AllArgsConstructor // Generates a constructor with all arguments for dependency injection
@RequestMapping("/api/product") // Specifies the base URL for the endpoint
public class ProductResource {

    private final ProductRepo productRepo; // Autowired instance of ProductRepo to interact with the database

    // GET endpoint to retrieve all products
    @GetMapping
    public ResponseEntity<List<ProductDTO>> all() {
        System.out.println(productRepo.findAll().size());
        List<ProductDTO> productDTOS = productRepo.findAll().stream()
                .map(ProductDTO::toProductDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(productDTOS);
    }

    // POST endpoint to create Product(Book)
    @PostMapping("/create")
    public ResponseEntity<String> create(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setSKU(productDTO.getSKU());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setCreatedAt(productDTO.getCreatedAt());
        productRepo.save(product);

        return ResponseEntity.ok().body("CREATED_SUCCESSFULLY");
    }
}