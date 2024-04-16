package yesko.project.OnlineShop.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yesko.project.OnlineShop.entity.UserPayment;
import yesko.project.OnlineShop.repo.UserPaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserPaymentService {

    private final UserPaymentRepository userPaymentRepository;
    @Transactional(readOnly = true)
    public Optional<UserPayment> findById(Long id) {
        return userPaymentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<UserPayment> findByUserId(Long userId) {
        return userPaymentRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<UserPayment> findAll() {
        return userPaymentRepository.findAll();
    }

    @Transactional
    public UserPayment save(UserPayment userPayment) {
        return userPaymentRepository.save(userPayment);
    }
    @Transactional
    public void deleteById(Long id) {
        userPaymentRepository.deleteById(id);
    }
}
