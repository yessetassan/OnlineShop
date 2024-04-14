package yesko.project.OnlineShop.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import yesko.project.OnlineShop.entity.User;
import yesko.project.OnlineShop.entity.UserAuth;

import java.util.Optional;

@Component
public class SecurityUtil {
    public UserAuth getCurrentUserAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserAuth) {
            return (UserAuth) authentication.getPrincipal();
        }
        return null;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserAuth) {
            return ((UserAuth) authentication.getPrincipal()).getUser_userAuth();
        }
        return null;
    }

}
