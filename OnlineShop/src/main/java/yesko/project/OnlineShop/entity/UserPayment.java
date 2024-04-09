package yesko.project.OnlineShop.entity;


import jakarta.persistence.*;
import lombok.*;
import java.sql.*;

@Entity
@Table(name = "t_user_payment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user_userPayment;

    @Column(name = "payment_type", nullable = false)
    private String paymentType;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "account_no", nullable = false)
    private String accountNo;

    @Column(name = "expiry", nullable = false)
    private Date expiry;

}

