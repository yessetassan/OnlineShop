package yesko.project.OnlineShop.utils;

import org.springframework.stereotype.Component;
import yesko.project.OnlineShop.entity.Product;

import java.math.BigDecimal;
import java.util.Random;

@Component
public class CheckPaymentProcess {
    private static final Random randomInteger = new Random();
    public static boolean userHasNotEnoughMoney(Product product) {
        Integer productQuantity = product.getProduct_productInventory().getQuantity();
        BigDecimal totalSum = product.getPrice().multiply(BigDecimal.valueOf(productQuantity));
        return totalSum.compareTo(BigDecimal.ONE) < 0;
    }

    public static boolean limitsProductQuantity(Product product, Integer quantity) {
        return randomInteger.nextBoolean();
    }
}
