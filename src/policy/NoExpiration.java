package policy;
public class NoExpiration implements ExpirationPolicy {
    @Override
    public boolean isExpired() {
        return false;
    }
}