package service;

import exception.*;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {
    private final ShippingService shippingService;

    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public void checkout(Customer customer, Cart cart) {
        validateCart(cart);
        validateStockAndExpiration(cart);

        double subtotal = cart.getSubtotal();
        double shippingFees = calculateShippingFees(cart);
        double total = subtotal + shippingFees;

        validateCustomerBalance(customer, total);
        deductStockAndBalance(customer, cart, total);

        List<ShipmentLine> shipmentLines = prepareShipmentLines(cart);

        printShipmentNotice(shipmentLines);
        printReceipt(cart, subtotal, shippingFees, total);
        System.out.printf("Customer current balance: %.0f%n%n", customer.getBalance());

        shippingService.shipItems(shipmentLines);
        cart.clear();
    }

    private void validateCart(Cart cart) {
        if (cart.isEmpty()) {
            throw new EmptyCartException("Cannot checkout empty cart");
        }
    }

    private void validateStockAndExpiration(Cart cart) {
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (product.isExpired()) {
                throw new ExpiredProductException("Product '" + product.getName() + "' is expired");
            }
            if (product.getQuantity() < item.getQuantity()) {
                throw new OutOfStockException("Only " + product.getQuantity() + " available for " + product.getName());
            }
        }
    }

    private void validateCustomerBalance(Customer customer, double total) {
        if (customer.getBalance() < total) {
            throw new InsufficientBalanceException("Insufficient balance. Required: " + total +
                    ", Available: " + customer.getBalance());
        }
    }

    private void deductStockAndBalance(Customer customer, Cart cart, double total) {
        customer.deductBalance(total);
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() - item.getQuantity());
        }
    }

    private List<ShipmentLine> prepareShipmentLines(Cart cart) {
        List<ShipmentLine> shipmentLines = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            if (item.isShippable()) {
                shipmentLines.add(new ShipmentLine(item));
            }
        }
        return shipmentLines;
    }

    private double calculateShippingFees(Cart cart) {
        double totalWeight = 0;
        for (CartItem item : cart.getItems()) {
            if (item.isShippable()) {
                totalWeight += item.getProduct().getWeight() * item.getQuantity();
            }
        }
        return totalWeight > 0 ? 30 : 0;
    }

    private void printShipmentNotice(List<ShipmentLine> shipmentLines) {
        if (shipmentLines.isEmpty()) return;

        System.out.println("** Shipment notice **");
        double totalWeight = 0;

        for (ShipmentLine line : shipmentLines) {
            double weightInGrams = line.getWeight() * 1000;
            System.out.printf("%dx %s    %.0fg%n",
                    line.getQuantity(), line.getName(), weightInGrams);
            totalWeight += line.getWeight();
        }

        System.out.printf("Total package weight %.1fkg%n%n", totalWeight);
    }

    private void printReceipt(Cart cart, double subtotal, double shippingFees, double total) {
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s    %.0f%n",
                    item.getQuantity(),
                    item.getProduct().getName(),
                    item.getTotalPrice());
        }
        System.out.println("---");
        System.out.printf("Subtotal    %.0f%n", subtotal);
        System.out.printf("Shipping    %.0f%n", shippingFees);
        System.out.printf("Amount    %.0f%n%n", total);
    }
}
