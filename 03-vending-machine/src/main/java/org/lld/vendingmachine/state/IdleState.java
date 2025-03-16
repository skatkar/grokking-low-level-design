package org.lld.vendingmachine.state;

import org.lld.vendingmachine.Bill;
import org.lld.vendingmachine.Coin;
import org.lld.vendingmachine.Product;
import org.lld.vendingmachine.VendingMachine;

public class IdleState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        if(vendingMachine.getInventory().isAvailable(product)){
            vendingMachine.setState(vendingMachine.getReadyState());
            vendingMachine.setSelectProduct(product);
            System.out.println("Selected product - " + product);
        }else
            System.out.println("Selected product - " + product + " does not exist.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please select a product first.");
    }

    @Override
    public void insertCoins(Coin coin) {
        System.out.println("Please select a product first.");
    }

    @Override
    public void insertBill(Bill bill) {
        System.out.println("Please select a product first.");
    }

    @Override
    public void returnChange() {
        System.out.println("Please select a product first.");
    }
}
