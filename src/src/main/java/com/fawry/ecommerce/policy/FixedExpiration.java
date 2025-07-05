package src.main.java.com.fawry.ecommerce.policy;

import java.time.LocalDate;

public class FixedExpiration implements ExpirationPolicy {
    private LocalDate expiryDate;

    public FixedExpiration(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
}