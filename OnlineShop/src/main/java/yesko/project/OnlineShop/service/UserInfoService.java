package yesko.project.OnlineShop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import yesko.project.OnlineShop.dto.UserDto;
import yesko.project.OnlineShop.entity.User;
import yesko.project.OnlineShop.entity.UserInfo;
import yesko.project.OnlineShop.repo.UserInfoRepo;
import yesko.project.OnlineShop.repo.UserRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepo userInfoRepo;

    public List<UserInfo> all() {
        return userInfoRepo.findAll();
    }
    public Optional<UserInfo> findByEmail(String email) {
        return userInfoRepo.findByEmail(email);
    }
    public Optional<UserInfo> findById(Integer id) {
        return userInfoRepo.findById(id);
    }
    @Transactional
    public UserInfo save(UserInfo userInfo) {
        return userInfoRepo.save(userInfo);
    }
    @Transactional
    public UserInfo update(UserInfo userInfo, Integer id) {
        UserInfo existingUserInfo = userInfoRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot update userInfo. UserInfo with id " + id + " does not exist."));
        existingUserInfo.setEmail(userInfo.getEmail());
        existingUserInfo.setAddressLine(userInfo.getAddressLine());
        existingUserInfo.setCity(userInfo.getCity());
        existingUserInfo.setCountry(userInfo.getCountry());
        existingUserInfo.setTelephone(userInfo.getTelephone());
        existingUserInfo.setPostalCode(userInfo.getPostalCode());

        return userInfoRepo.save(existingUserInfo);
    }
}
