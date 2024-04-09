package yesko.project.OnlineShop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "t_payment_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id") // Foreign key in Student table
    private OrderDetails paymentDet_orderDet;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "provider")
    private String provider;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private PaymentStatus paymentDet_paymentStatus;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "modified_at", nullable = false)
    private Timestamp modifiedAt;
}
