package org.lld.vendingmachine.state;

import org.lld.vendingmachine.Bill;
import org.lld.vendingmachine.Coin;
import org.lld.vendingmachine.Product;

public interface VendingMachineState {
    // Product functions
    void selectProduct(Product product);
    void dispenseProduct();

    // Payment functions
    void insertCoins(Coin coin);
    void insertBill(Bill bill);
    void returnChange();
}
