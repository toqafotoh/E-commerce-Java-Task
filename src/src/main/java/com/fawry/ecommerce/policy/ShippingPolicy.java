package src.main.java.com.fawry.ecommerce.policy;

public interface ShippingPolicy {
    double getWeight();
    boolean isShippable();
}