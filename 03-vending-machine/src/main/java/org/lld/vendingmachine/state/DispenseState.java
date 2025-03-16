package org.lld.vendingmachine.state;

import org.lld.vendingmachine.Bill;
import org.lld.vendingmachine.Coin;
import org.lld.vendingmachine.Product;
import org.lld.vendingmachine.VendingMachine;

public class DispenseState implements VendingMachineState{
    private VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("DispenseState - You already selected a product.");
    }

    @Override
    public void dispenseProduct() {
        Product product = vendingMachine.getSelectedProduct();
        vendingMachine.getInventory().updateQuantity(product, -1);
        vendingMachine.increaseTotalAmount();
        vendingMachine.setState(vendingMachine.getReturnChangeState());
        System.out.println("Dispensed product = " + product);
    }

    @Override
    public void insertCoins(Coin coin) {
        System.out.println("DispenseState - You already did a payment.");
    }

    @Override
    public void insertBill(Bill bill) {
        System.out.println("DispenseState - You already did a payment.");
    }

    @Override
    public void returnChange() {
        System.out.println("DispenseState - Can't return change here");
    }
}
