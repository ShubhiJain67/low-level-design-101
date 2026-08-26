package state;

import vending.VendingMachine;

public class IdleState implements IVendingMachineState {

    private final VendingMachine machine;

    public IdleState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney(int amount) {
        machine.addMoney(amount);
        System.out.println("Money inserted: ₹" + amount);
        machine.setState(new HasMoneyState(machine));
    }

    @Override
    public void selectProduct(String productName) {
        System.out.println("Please insert money first");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please select a product first");
    }
}
