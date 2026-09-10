package behavioral.state.sample;

public class MoneyInsertedState implements IVendingMachineState {
    private final VendingMachine machine;

    public MoneyInsertedState(VendingMachine machine) {
        this.machine = machine;
    }

    public void moveToNextState() {
        IVendingMachineState newState = new ProductSelectedState(this.machine);
        this.machine.setState(newState);
    }

    @Override
    public void insertMoney() {
        System.out.println("Money already inserted");
    }

    @Override
    public void selectProduct() {
        System.out.println("Product selected");
        this.moveToNextState();
    }

    @Override
    public void dispense() {
        System.out.println("Please select a product first");
    }
}