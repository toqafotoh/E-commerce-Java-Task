package model;

import policy.ExpirationPolicy;
import policy.ShippingPolicy;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private ExpirationPolicy expirationPolicy;
    private ShippingPolicy shippingPolicy;

    public Product(String name, double price, int quantity,
                   ExpirationPolicy expirationPolicy,
                   ShippingPolicy shippingPolicy) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirationPolicy = expirationPolicy;
        this.shippingPolicy = shippingPolicy;
    }

    public boolean isExpired() {
        return expirationPolicy.isExpired();
    }

    public boolean isShippable() {
        return shippingPolicy.isShippable();
    }

    public double getWeight() {
        return shippingPolicy.getWeight();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}