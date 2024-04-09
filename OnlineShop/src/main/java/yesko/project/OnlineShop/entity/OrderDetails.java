package yesko.project.OnlineShop.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "t_order_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User orderDet_user;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    @OneToMany(mappedBy = "orderItem_orderDet")
    private List<OrderItem> orderItemList;

    @OneToOne(mappedBy = "paymentDet_orderDet")
    private PaymentDetails paymentDetails;
}
