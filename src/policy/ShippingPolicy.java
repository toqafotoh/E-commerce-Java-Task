package policy;

public interface ShippingPolicy {
    double getWeight();
    boolean isShippable();
}