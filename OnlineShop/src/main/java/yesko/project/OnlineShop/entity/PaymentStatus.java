package yesko.project.OnlineShop.entity;


import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "t_payment_status")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "paymentDet_paymentStatus")
    private List<PaymentDetails> paymentDetailsList;
}