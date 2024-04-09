package yesko.project.OnlineShop.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yesko.project.OnlineShop.entity.UserAuth;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDto {

    private Long id;
    private String username; // Assuming you want to include the username
    private String role; // Assuming you want to include the role name
    private String token;
    private LocalDateTime expiredDate;
    private Boolean isExpired;

    public UserAuthDto toUserAuthDtoEntity(UserAuth userAuth) {
        UserAuthDto dto = new UserAuthDto();
        dto.setId(userAuth.getId());
        dto.setUsername(userAuth.getUser_userAuth().getUsername());
        dto.setRole(userAuth.getRole_userAuth().getName());
        dto.setToken(userAuth.getToken());
        dto.setExpiredDate(userAuth.getExpiredDate());
        dto.setIsExpired(userAuth.getIsExpired());
        return dto;
    }
}
