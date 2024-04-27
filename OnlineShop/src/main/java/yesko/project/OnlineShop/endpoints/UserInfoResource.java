package yesko.project.OnlineShop.endpoints;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import yesko.project.OnlineShop.dto.UserAuthDto;
import yesko.project.OnlineShop.dto.UserDto;
import yesko.project.OnlineShop.entity.User;
import yesko.project.OnlineShop.entity.UserAuth;
import yesko.project.OnlineShop.service.UserService;
import yesko.project.OnlineShop.utils.SecurityUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserInfoResource {

    private final UserService service;
    private final SecurityUtil securityUtil;
    @GetMapping
    public ResponseEntity<List<UserDto>> takeAll() {
        return ResponseEntity.ok().body(service
                .all()
                .stream()
                .map(UserService::fromEntity)
                .toList()
        );
    }
    @GetMapping("/selfInfo")
    public ResponseEntity<UserAuthDto> info() {
        Optional<UserAuth> userAuth = Optional.ofNullable(securityUtil.getCurrentUserAuth());
        UserAuthDto userAuthDto = new UserAuthDto().toUserAuthDtoEntity(userAuth.get());
        return ResponseEntity.ok().body(userAuthDto);
    }
    @GetMapping("/byId/{id}")
    public ResponseEntity<UserDto> takeById(@PathVariable("id") Long id) {
        User user = service.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " not found..."));
        return ResponseEntity.ok().body(UserService.fromEntity(user));
    }

    @GetMapping("/byUsername/{username}")
    public ResponseEntity<UserDto> takeById(@PathVariable("username") String username) {
        User user = service.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with id " + username + " not found..."));
        return ResponseEntity.ok().body(UserService.fromEntity(user));
    }
    @PutMapping("/update")
    public ResponseEntity<UserDto> takeById(@RequestBody UserDto userDto) {
        User user = service.update(userDto, userDto.getId());
        return ResponseEntity.ok().body(UserService.fromEntity(user));
    }

}
