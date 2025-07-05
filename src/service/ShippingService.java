package service;

import model.ShipmentLine;

import java.util.List;

public class ShippingService {
    public void shipItems(List<ShipmentLine> items) {
        System.out.println("ShippingService: " + items.size() + " items shipped");
    }
}