package org.lld.vendingmachine.state;

import org.lld.vendingmachine.Bill;
import org.lld.vendingmachine.Coin;
import org.lld.vendingmachine.Product;
import org.lld.vendingmachine.VendingMachine;

public class ReturnChangeState implements VendingMachineState{
    private VendingMachine vendingMachine;

    public ReturnChangeState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("ReturnChangeState - can't do selectProduct.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("ReturnChangeState - can't do dispenseProduct.");
    }

    @Override
    public void insertCoins(Coin coin) {
        System.out.println("ReturnChangeState - can't do insertCoins.");
    }

    @Override
    public void insertBill(Bill bill) {
        System.out.println("ReturnChangeState - can't do insertBill.");
    }

    @Override
    public void returnChange() {
        double change = vendingMachine.getCurrTotalPayment() - vendingMachine.getSelectedProduct().getPrice();
        if(change > 0){
            System.out.println("change = " + change);
        }else{
            System.out.println("Nothing to return as user paid the right amount.");
        }

        vendingMachine.resetCurrAmount();
        vendingMachine.resetSelectedProduct();
        vendingMachine.setState(vendingMachine.getIdleState());
    }
}
