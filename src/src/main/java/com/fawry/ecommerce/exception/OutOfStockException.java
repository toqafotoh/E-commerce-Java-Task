package src.main.java.com.fawry.ecommerce.exception;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String message) {
        super(message);
    }
}