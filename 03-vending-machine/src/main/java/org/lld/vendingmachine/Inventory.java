package org.lld.vendingmachine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {

    private final Map<Product,Integer> products;

    public Inventory() {
        this.products = new ConcurrentHashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        products.put(product, quantity);
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    public void updateQuantity(Product product, int quantity) {
        products.put(product, products.get(product) + quantity);
    }

    public int getQuantity(Product product) {
        return products.get(product);
    }

    public boolean isAvailable(Product product) {
        return products.containsKey(product) && products.get(product) > 0;
    }
}
