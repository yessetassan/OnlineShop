package yesko.project.OnlineShop.auth;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yesko.project.OnlineShop.config.JwtService;
import yesko.project.OnlineShop.controllers.error.AlreadyExistsException;
import yesko.project.OnlineShop.entity.Role;
import yesko.project.OnlineShop.entity.User;
import yesko.project.OnlineShop.entity.UserAuth;
import yesko.project.OnlineShop.entity.UserInfo;
import yesko.project.OnlineShop.repo.RoleRepository;
import yesko.project.OnlineShop.repo.UserAuthRepository;
import yesko.project.OnlineShop.repo.UserInfoRepository;
import yesko.project.OnlineShop.repo.UserRepository;
import yesko.project.OnlineShop.utils.enums.AuthorizationStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserAuthRepository userAuthRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserInfoRepository userInfoRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent())
            throw new AlreadyExistsException(String.format("User with username %s is already exists...", request.getUsername()));
        if (userInfoRepository.findByEmail(request.getEmail()).isPresent())
            throw new AlreadyExistsException(String.format("User with email %s is already exists...", request.getEmail()));

        User user = toUserEntity(request);
        log.info("User is saved {}", user);
        UserAuth userAuth = toUserAuthEntity(request, user);
        log.info("UserAuth is saved {}", userAuth);
        UserInfo userInfo = toUserInfoEntity(request, user);
        log.info("UserInfo is saved {}", userInfo);

        return AuthenticationResponse.builder()
                .token(userAuth.getToken())
                .build();
    }

    private UserInfo toUserInfoEntity(RegisterRequest request, User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_userInfo(user);
        userInfo.setEmail(request.getEmail());
        userInfo.setAddressLine(request.getAddressLine());
        userInfo.setCity(request.getCity());
        userInfo.setCountry(request.getCountry());
        userInfo.setTelephone(request.getTelephone());
        return userInfoRepository.save(userInfo);
    }

    private UserAuth toUserAuthEntity(RegisterRequest request, User user) {
        UserAuth userAuth = new UserAuth();
        userAuth.setPassword(passwordEncoder.encode(request.getPassword()));
        userAuth.setUser_userAuth(user);
        userAuth.setRole_userAuth(toUserRole());
        var jwt = jwtService.generateToken(userAuth);
        var expiredDate = convert(jwtService.extractExpiration(jwt));
        var isExpired = jwtService.isTokenExpired(jwt);
        userAuth.setToken(jwt);
        userAuth.setExpiredDate(expiredDate);
        userAuth.setIsExpired(isExpired);
        return userAuthRepository.save(userAuth);
    }

    public LocalDateTime convert(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
    private Role toUserRole() {
        final String role = AuthorizationStatus.USER.getRoleName();
        return roleRepository.findByName(role)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Error during loading for %s...", role)));
    }
    private User toUserEntity(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setCreatedAt(LocalDateTime.now());
        user.setModifiedAt(LocalDateTime.now());
        return userRepository.save(user);
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var userAuth = userAuthRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found...", request.getUsername())));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            var jwt = jwtService.generateToken(userAuth);
            ZoneId zoneId = ZoneId.of("Asia/Almaty");
            userAuth.setToken(jwt);
            userAuth.setExpiredDate(jwtService.extractExpiration(jwt).toInstant()
                    .atZone(zoneId)
                    .toLocalDateTime());
            userAuthRepository.save(userAuth);

            return AuthenticationResponse.builder()
                    .token(jwt)
                    .build();
        }catch (Exception e) {
            throw new UsernameNotFoundException("User with password not found...");
        }
    }
}
