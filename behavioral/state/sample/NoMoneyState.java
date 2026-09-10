package behavioral.state.sample;

public class NoMoneyState implements IVendingMachineState {
    private final VendingMachine machine;

    public NoMoneyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        System.out.println("Inserting Money");
        IVendingMachineState newState = new MoneyInsertedState(this.machine);
        machine.setState(newState);
    }

    @Override
    public void selectProduct() {
        System.out.println("Please insert money first");
    }

    @Override
    public void dispense() {
        System.out.println("Please insert money first");
    }
}