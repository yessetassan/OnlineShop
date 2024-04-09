package yesko.project.OnlineShop.entity;




import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "t_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "SKU")
    private String sku;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private ProductCategory product_productCategory;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id") // Foreign key in Student table
    private ProductInventory product_productInventory;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount product_discount;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @OneToMany(mappedBy = "cart_product", cascade = CascadeType.ALL)
    private List<CartItem> cartItemList;

    @OneToMany(mappedBy = "orderItem_product")
    private List<OrderItem> orderItemList;
}
