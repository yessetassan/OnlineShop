package yesko.project.OnlineShop.dto;

import lombok.*;
import yesko.project.OnlineShop.entity.User;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    public static UserDto fromEntity(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getCreatedAt(),
                user.getModifiedAt());
    }
}
