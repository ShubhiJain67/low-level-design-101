package behavioral.state.sample;

public class ProductSelectedState implements IVendingMachineState {
    private VendingMachine machine;

    public ProductSelectedState(VendingMachine machine) {
        this.machine = machine;
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
        machine.setState(
            new NoMoneyState(machine)
        );
    }
}