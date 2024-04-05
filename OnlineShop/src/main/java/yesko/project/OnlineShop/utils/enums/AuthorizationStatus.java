package yesko.project.OnlineShop.utils.enums;

import lombok.*;


@AllArgsConstructor
@Getter
public enum AuthorizationStatus {
    USER("ROLE_USER"),
    DEVELOPER("ROLE_DEVELOPER"),
    ADMIN("ROLE_ADMIN");
    private final String roleName;
}
