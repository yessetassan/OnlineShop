package yesko.project.OnlineShop.controllers.error;

public class FormException extends RuntimeException{
    public FormException(String message) {
        super(message);
    }
}
