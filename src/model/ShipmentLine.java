package model;

public class ShipmentLine implements ShippingItem {
    private final String name;
    private final int quantity;
    private final double totalWeight;

    public ShipmentLine(CartItem item) {
        this.name = item.getProduct().getName();
        this.quantity = item.getQuantity();
        this.totalWeight = item.getProduct().getWeight() * item.getQuantity();
    }

    @Override
    public String getName() { return name; }

    @Override
    public double getWeight() { return totalWeight; }

    public int getQuantity() { return quantity; }
}