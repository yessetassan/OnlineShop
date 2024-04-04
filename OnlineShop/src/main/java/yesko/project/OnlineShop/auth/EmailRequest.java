package yesko.project.OnlineShop.auth;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmailRequest {
    @NotBlank(message = "Email must be not blank")
    @Email(message = "Email format is incorrect. Example: user123@mail.ru, user123@yandex.ru, user@gmail.com")
    private String email;
}
