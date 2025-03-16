package org.lld.vendingmachine;

import org.lld.vendingmachine.state.*;

public class VendingMachine {
    private static VendingMachine instance;

    Inventory inventory;
    private VendingMachineState idleState;
    private VendingMachineState readyState;
    private VendingMachineState dispenseState;
    private VendingMachineState returnChangeState;
    private VendingMachineState currentState;
    private Product selectedProduct;

    // The total money collected so far
    private double totalPayment;

    // Money collected during this transaction only
    private double currTotalPayment;

    private VendingMachine() {
        idleState = new IdleState(this);
        readyState = new ReadyState(this);
        dispenseState = new DispenseState(this);
        returnChangeState = new ReturnChangeState(this);
        currentState = idleState;

        inventory = new Inventory();

        selectedProduct = null;
        totalPayment = 0.0;
        currTotalPayment = 0.0;
    }

    public static synchronized VendingMachine getInstance() {
        if (instance == null) {
            instance = new VendingMachine();
        }
        return instance;
    }

    public void setSelectProduct(Product product) {
        this.selectedProduct = product;
        currentState.selectProduct(product);
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }

    public void insertCoin(Coin coin) {
        currentState.insertCoins(coin);
    }

    public void insertBill(Bill bill) {
        currentState.insertBill(bill);
    }

    public void returnChange() {
        currentState.returnChange();
    }

    public void addCoin(Coin coin) {
        currTotalPayment += coin.getValue();
    }

    public void addBill(Bill bill) {
        currTotalPayment += bill.getValue();
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }

    public void resetSelectedProduct() {
        this.selectedProduct = null;
    }

    public void resetCurrAmount() {
        this.currTotalPayment = 0.0;
    }

    // This will increase the total money collected so far
    // Once the product is dispensed, increment the total amount
    public void increaseTotalAmount() {
        totalPayment += selectedProduct.getPrice();
    }

    /**
     * Getter methods
     **/

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public VendingMachineState getIdleState() {
        return idleState;
    }

    public VendingMachineState getReadyState() {
        return readyState;
    }

    public VendingMachineState getDispenseState() {
        return dispenseState;
    }

    public VendingMachineState getReturnChangeState() {
        return returnChangeState;
    }

    public VendingMachineState getCurrentState() {
        return currentState;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public double getCurrTotalPayment() {
        return currTotalPayment;
    }


}
