package org.lld.vendingmachine.state;

import org.lld.vendingmachine.Bill;
import org.lld.vendingmachine.Coin;
import org.lld.vendingmachine.Product;
import org.lld.vendingmachine.VendingMachine;

public class ReadyState implements VendingMachineState{
    private VendingMachine vendingMachine;

    public ReadyState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("You already selected a product");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please complete the payment");
    }

    @Override
    public void insertCoins(Coin coin) {
        vendingMachine.addCoin(coin);
        checkPaymentStatus();
    }

    private void checkPaymentStatus() {
        // This has to be a different logic. But for the time being, this one is much simpler
        if (vendingMachine.getCurrTotalPayment() >= vendingMachine.getSelectedProduct().getPrice()){
            System.out.println("CurrTotalPayment = " + vendingMachine.getCurrTotalPayment());
            vendingMachine.setState(vendingMachine.getDispenseState());
        }
    }

    @Override
    public void insertBill(Bill bill) {
        vendingMachine.addBill(bill);
        checkPaymentStatus();
    }

    @Override
    public void returnChange() {
        System.out.println("Nothing to return");
    }

    
}
