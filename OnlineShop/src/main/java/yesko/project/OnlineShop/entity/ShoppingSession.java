package yesko.project.OnlineShop.entity;


import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_shopping_session")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShoppingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id") // Foreign key in Student table
    private User user_shopSession;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "cart_shopSession", cascade = CascadeType.ALL)
    private List<CartItem> cartItemList;
}

