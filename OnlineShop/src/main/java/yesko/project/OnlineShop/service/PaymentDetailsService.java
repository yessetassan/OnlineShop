package yesko.project.OnlineShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yesko.project.OnlineShop.entity.PaymentDetails;
import yesko.project.OnlineShop.repo.PaymentDetailsRepository;

import java.util.Optional;

@Service
public class PaymentDetailsService {

    private final PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    public PaymentDetailsService(PaymentDetailsRepository paymentDetailsRepository) {
        this.paymentDetailsRepository = paymentDetailsRepository;
    }

    public Optional<PaymentDetails> findByOrderId(Long orderId) {
        return paymentDetailsRepository.findByOrderId(orderId);
    }

    public PaymentDetails saveOrUpdatePaymentDetails(PaymentDetails paymentDetails) {
        return paymentDetailsRepository.save(paymentDetails);
    }

    public void deletePaymentDetails(Long paymentDetailsId) {
        paymentDetailsRepository.deleteById(paymentDetailsId);
    }
}

