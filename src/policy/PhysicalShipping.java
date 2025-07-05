package policy;

public class PhysicalShipping implements ShippingPolicy {
    private double weight;

    public PhysicalShipping(double weight) {
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isShippable() {
        return true;
    }
}