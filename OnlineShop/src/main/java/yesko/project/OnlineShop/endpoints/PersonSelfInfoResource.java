package yesko.project.OnlineShop.endpoints;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yesko.project.OnlineShop.dto.UserAuthDto;
import yesko.project.OnlineShop.entity.User;
import yesko.project.OnlineShop.entity.UserAuth;
import yesko.project.OnlineShop.utils.SecurityUtil;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/personInfo")
public class PersonSelfInfoResource {

    private final SecurityUtil securityUtil;

    @GetMapping
    public ResponseEntity<UserAuthDto> info() {
        Optional<UserAuth> userAuth = Optional.ofNullable(securityUtil.getCurrentUser());
        UserAuthDto userAuthDto = new UserAuthDto().toUserAuthDtoEntity(userAuth.get());
        return ResponseEntity.ok().body(userAuthDto);
    }
}
