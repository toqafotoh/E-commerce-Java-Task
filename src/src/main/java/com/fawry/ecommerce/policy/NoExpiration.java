package src.main.java.com.fawry.ecommerce.policy;
public class NoExpiration implements ExpirationPolicy {
    @Override
    public boolean isExpired() {
        return false;
    }
}