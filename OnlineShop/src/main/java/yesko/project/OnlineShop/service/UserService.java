package yesko.project.OnlineShop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import yesko.project.OnlineShop.dto.UserDto;
import yesko.project.OnlineShop.entity.User;
import yesko.project.OnlineShop.repo.UserRepository;
import yesko.project.OnlineShop.specification.UserSpecifications;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    public List<User> all() {
        return userRepository.findAll();
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
    @Transactional
    public User update(UserDto user, Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot update user. User with id " + id + " does not exist."));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setModifiedAt(LocalDateTime.now());

        return userRepository.save(existingUser);
    }
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
