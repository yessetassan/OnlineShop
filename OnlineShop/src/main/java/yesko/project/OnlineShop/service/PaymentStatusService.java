package yesko.project.OnlineShop.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yesko.project.OnlineShop.entity.PaymentStatus;
import yesko.project.OnlineShop.repo.PaymentStatusRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentStatusService {

    private final PaymentStatusRepository paymentStatusRepository;
    public Optional<PaymentStatus> findById(Long id) {
        return paymentStatusRepository.findById(id);
    }

    public Optional<PaymentStatus> findByName(String name) {
        return paymentStatusRepository.findByName(name);
    }
    public List<PaymentStatus> findAll() {
        return paymentStatusRepository.findAll();
    }

    @Transactional
    public PaymentStatus save(PaymentStatus paymentStatus) {
        return paymentStatusRepository.save(paymentStatus);
    }

    @Transactional
    public void deleteById(Long id) {
        paymentStatusRepository.deleteById(id);
    }
}

