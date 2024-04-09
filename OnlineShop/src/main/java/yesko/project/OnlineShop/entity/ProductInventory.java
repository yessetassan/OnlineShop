package yesko.project.OnlineShop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name = "t_product_inventory")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "modified_at", nullable = false)
    private Timestamp modifiedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @OneToOne(mappedBy = "product_productInventory") // Student owns the relationship (optional)
    private Product product;
}

