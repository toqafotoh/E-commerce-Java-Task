package policy;

public class NoShipping implements ShippingPolicy {
    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public boolean isShippable() {
        return false;
    }
}