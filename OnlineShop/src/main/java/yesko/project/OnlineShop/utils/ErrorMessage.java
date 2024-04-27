package yesko.project.OnlineShop.utils;

import lombok.Data;

@Data
public class ErrorMessage {
    public static final String PRODUCT_NOT_FOUND_ERROR = "Product with ID %d not found.";
    public static final String USER_PAYMENT_DETAILS_NOT_FOUND = "User payment details not found.";
    public static final String PAYMENT_STATUS_NOT_FOUND = "Payment status not found.";
    public static final String QUANTITY_EXCEEDS_STOCK = "Requested quantity exceeds available stock.";
    public static final String INSUFFICIENT_FUNDS = "User does not have enough money for this transaction.";
}
