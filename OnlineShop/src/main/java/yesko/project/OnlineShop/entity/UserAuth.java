package yesko.project.OnlineShop.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "t_user_auth")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAuth implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user_userAuth;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role_userAuth;

    @Column(name = "token", columnDefinition = "TEXT")
    private String token;

    @Column(name = "expired_date", nullable = false)
    private LocalDateTime expiredDate;

    @Column(name = "is_expired", nullable = false)
    private Boolean isExpired;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role_userAuth.getName()));
    }
    @Override
    public String getUsername() {
        return this.user_userAuth.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}