package yesko.project.OnlineShop.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yesko.project.OnlineShop.dto.ProductDTO;
import yesko.project.OnlineShop.entity.Product;
import yesko.project.OnlineShop.repo.ProductRepository;

import java.util.List;
import java.util.Optional;

import static yesko.project.OnlineShop.utils.Constants.formatter;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public static ProductDTO fromEntity(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setSKU(product.getSku());
        if (product.getProduct_discount() != null)
            dto.setDiscountDTO(DiscountService.fromEntity(product.getProduct_discount()));
        if (product.getProduct_productCategory() != null)
            dto.setCategory(product.getProduct_productCategory().getName());
        if (product.getProduct_productInventory() != null)
            dto.setQuantity(product.getProduct_productInventory().getQuantity());
        return dto;
    }
    @Transactional
    public Product saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSku(productDTO.getSKU());
        product.setPrice(productDTO.getPrice());

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product not found for this id :: " + id));
        product.setName(productDetails.getName());
        product.setSku(productDetails.getSku());

        return productRepository.save(product);
    }
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> findProductByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findAllByCategory(String category) {
        return productRepository.findAllByCategory("%" + category + "%");
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
