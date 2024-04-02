package yesko.project.OnlineShop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_user_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "address_line", length = 255)
    private String addressLine;

    @Column(name = "city", nullable = false, length = 255)
    private String city;

    @Column(name = "country", nullable = false, length = 255)
    private String country;

    @Column(name = "telephone", nullable = false, length = 255)
    private String telephone;

    @Column(name = "postal_code", nullable = false, length = 50)
    private String postalCode;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_userInfo;
}
