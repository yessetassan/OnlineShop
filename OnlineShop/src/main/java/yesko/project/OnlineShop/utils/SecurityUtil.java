package yesko.project.OnlineShop.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import yesko.project.OnlineShop.entity.UserAuth;

@Component
public class SecurityUtil {
    public UserAuth getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserAuth) {
            return (UserAuth) authentication.getPrincipal();
        }
        return null;
    }
}
