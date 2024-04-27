package yesko.project.OnlineShop.dto;

import lombok.*;
import yesko.project.OnlineShop.entity.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
