package src.main.java.com.fawry.ecommerce.exception;

public class ExpiredProductException extends RuntimeException {
    public ExpiredProductException(String message) {
        super(message);
    }
}