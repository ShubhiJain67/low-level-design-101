package state;

import vending.VendingMachine;

public class OutOfStockState implements IVendingMachineState {

    private final VendingMachine machine;

    public OutOfStockState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney(int amount) {
        System.out.println("Product unavailable. Please wait.");
    }

    @Override
    public void selectProduct(String productName) {
        System.out.println("Selected product is out of stock");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Cannot dispense. Product out of stock.");
        int money = machine.getInsertedMoney();
        if (money > 0) {
            System.out.println("Refunding: ₹" + money);
            machine.resetMoney();
        }
        machine.setState(new IdleState(machine));
    }
}
