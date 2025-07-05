package setup;

import model.*;
import policy.*;
import service.*;

import java.time.LocalDate;

public class StoreSimulator {

    private Customer customer;
    private Cart cart;
    private CheckoutService checkoutService;

    public void start() {
        setupStore();
        testSuccessfulCheckout();
        testExpiredProduct();
        testInsufficientBalance();
        testOutOfStock();
        testMixedItems();
    }

    private void setupStore() {
        customer = new Customer("Ahmed Hassan", 100000);
        cart = new Cart();
        ShippingService shippingService = new ShippingService();
        checkoutService = new CheckoutService(shippingService);
    }
    private void testSuccessfulCheckout() {
        System.out.println("===== TEST 1: SUCCESSFUL CHECKOUT =====");

        Product cheese = new Product("Cheese", 100, 10,
                new FixedExpiration(LocalDate.now().plusDays(30)),
                new PhysicalShipping(0.2));

        Product biscuits = new Product("Biscuits", 150, 5,
                new FixedExpiration(LocalDate.now().plusDays(20)),
                new PhysicalShipping(0.7));

        Product scratchCard = new Product("Scratch Card", 100, 100,
                new NoExpiration(),
                new NoShipping());

        cart.addItem(cheese, 2);
        cart.addItem(biscuits, 1);
        cart.addItem(scratchCard, 5);
        checkout();
    }

    private void testExpiredProduct() {
        System.out.println("===== TEST 2: EXPIRED PRODUCT =====");

        Product expiredMilk = new Product("Expired Milk", 50, 5,
                new FixedExpiration(LocalDate.now().minusDays(1)),
                new PhysicalShipping(1.0));

        cart.addItem(expiredMilk, 1);
        checkout();
    }
    private void testInsufficientBalance() {
        System.out.println("===== TEST 3: INSUFFICIENT BALANCE =====");

        Product tv = new Product("TV", 50000, 3,
                new NoExpiration(),
                new PhysicalShipping(15.0));

        cart.addItem(tv, 2);
        checkout();
    }

    private void testOutOfStock() {
        System.out.println("===== TEST 4: OUT OF STOCK =====");

        Product cheese = new Product("Cheese", 100, 10,
                new FixedExpiration(LocalDate.now().plusDays(30)),
                new PhysicalShipping(0.2));

        cart.addItem(cheese, 20);
        checkout();
    }

    private void testMixedItems() {
        System.out.println("===== TEST 5: MIXED SHIPPABLE/NON-SHIPPABLE =====");

        Product tv = new Product("TV", 50000, 1,
                new NoExpiration(),
                new PhysicalShipping(15.0));

        Product scratchCard = new Product("Scratch Card", 100, 100,
                new NoExpiration(),
                new NoShipping());

        cart.addItem(tv, 1);
        cart.addItem(scratchCard, 3);
        checkout();
    }

    private void checkout() {
        try {
            checkoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            cart.clear();
        }
    }
}
