package behavioral.state.sample;

public class ProductSelectedState implements IVendingMachineState {
    private final VendingMachine machine;

    public ProductSelectedState(VendingMachine machine) {
        this.machine = machine;
    }

    public void moveToNextState() {
        IVendingMachineState newState = new NoMoneyState(this.machine);
        machine.setState(newState);
    }

    @Override
    public void insertMoney() {
        System.out.println("Money already inserted");
    }

    @Override
    public void selectProduct() {
        System.out.println("Product already selected");
    }

    @Override
    public void dispense() {
        System.out.println("Dispensing product");
        this.moveToNextState();
    }
}