package yesko.project.OnlineShop.dto;

import lombok.*;
import yesko.project.OnlineShop.entity.UserInfo;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    private Long id;
    private String email;
    private String addressLine;
    private String city;
    private String country;
    private String telephone;
    private String postalCode;

    public static UserInfoDto fromEntity(UserInfo userInfo) {
        return new UserInfoDto(
                userInfo.getId(),
                userInfo.getEmail(),
                userInfo.getAddressLine(),
                userInfo.getCity(),
                userInfo.getCountry(),
                userInfo.getTelephone(),
                userInfo.getPostalCode()
        );
    }
}
