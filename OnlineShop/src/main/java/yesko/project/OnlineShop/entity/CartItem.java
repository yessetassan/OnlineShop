package yesko.project.OnlineShop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "t_cart_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private ShoppingSession cart_shopSession;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product cart_product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;
}
