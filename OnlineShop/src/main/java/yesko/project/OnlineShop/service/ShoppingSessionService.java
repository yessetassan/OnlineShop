package yesko.project.OnlineShop.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yesko.project.OnlineShop.entity.ShoppingSession;
import yesko.project.OnlineShop.repo.ShoppingSessionRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ShoppingSessionService {
    private final ShoppingSessionRepository shoppingSessionRepository;
    @Transactional
    public ShoppingSession saveShoppingSession(ShoppingSession shoppingSession) {
        return shoppingSessionRepository.save(shoppingSession);
    }
    public Optional<ShoppingSession> getShoppingSessionById(Long id) {
        return shoppingSessionRepository.findById(id);
    }
    public Optional<ShoppingSession> getShoppingSessionByUserId(Long userId) {
        return shoppingSessionRepository.findByUserId(userId);
    }
    public List<ShoppingSession> getAllShoppingSessions() {
        return shoppingSessionRepository.findAll();
    }
    @Transactional
    public void deleteShoppingSession(Long id) {
        shoppingSessionRepository.deleteById(id);
    }
}
