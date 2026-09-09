package behavioral.state.sample;

public class MoneyInsertedState implements IVendingMachineState {
    private VendingMachine machine;

    public MoneyInsertedState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        System.out.println("Money already inserted");
    }

    @Override
    public void selectProduct() {
        System.out.println("Product selected");
        machine.setState(new ProductSelectedState(machine));
    }

    @Override
    public void dispense() {
        System.out.println("Please select a product first");
    }
}