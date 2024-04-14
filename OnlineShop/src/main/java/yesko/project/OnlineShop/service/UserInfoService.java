package yesko.project.OnlineShop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yesko.project.OnlineShop.entity.UserInfo;
import yesko.project.OnlineShop.repo.UserInfoRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    public List<UserInfo> all() {
        return userInfoRepository.findAll();
    }
    public Optional<UserInfo> findByEmail(String email) {
        return userInfoRepository.findByEmail(email);
    }
    public Optional<UserInfo> findById(Integer id) {
        return userInfoRepository.findById(id);
    }
    @Transactional
    public UserInfo save(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }
    @Transactional
    public UserInfo update(UserInfo userInfo, Integer id) {
        UserInfo existingUserInfo = userInfoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot update userInfo. UserInfo with id " + id + " does not exist."));
        existingUserInfo.setEmail(userInfo.getEmail());
        existingUserInfo.setAddressLine(userInfo.getAddressLine());
        existingUserInfo.setCity(userInfo.getCity());
        existingUserInfo.setCountry(userInfo.getCountry());
        existingUserInfo.setTelephone(userInfo.getTelephone());
        existingUserInfo.setPostalCode(userInfo.getPostalCode());

        return userInfoRepository.save(existingUserInfo);
    }
}
