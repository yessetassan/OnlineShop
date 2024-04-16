package yesko.project.OnlineShop.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yesko.project.OnlineShop.entity.UserInfo;
import yesko.project.OnlineShop.entity.UserPayment;
import yesko.project.OnlineShop.repo.UserInfoRepository;
import yesko.project.OnlineShop.repo.UserPaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Transactional(readOnly = true)
    public List<UserInfo> findAll() {
        return userInfoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<UserInfo> findByEmail(String email) {
        return userInfoRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
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
