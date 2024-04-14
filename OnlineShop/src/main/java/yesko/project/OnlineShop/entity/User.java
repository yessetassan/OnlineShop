package yesko.project.OnlineShop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User{

    public User(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 255)
    private String username;

    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @OneToOne(mappedBy = "user_userAuth", cascade = CascadeType.ALL)
    private UserAuth userAuth;

    @OneToOne(mappedBy = "user_userInfo", cascade = CascadeType.ALL)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "user_userPayment")
    private List<UserPayment> userPayments;

    @OneToOne(mappedBy = "user_shopSession")
    private ShoppingSession shoppingSession;

    @OneToMany(mappedBy = "orderDet_user")
    private List<OrderDetails> orderDetailsList;
}
