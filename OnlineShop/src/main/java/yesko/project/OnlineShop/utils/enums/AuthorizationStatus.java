package yesko.project.OnlineShop.utils.enums;

import lombok.*;


@AllArgsConstructor
@Getter
public enum AuthorizationStatus {
    USER_ROLE("ROLE_USER"),
    DEVELOPER_ROLE("ROLE_DEVELOPER"),
    ADMIN_ROLE("ROLE_ADMIN");

    private final String roleName;
}
