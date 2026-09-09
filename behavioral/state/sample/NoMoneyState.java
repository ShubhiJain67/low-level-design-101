package behavioral.state.sample;

public class NoMoneyState implements IVendingMachineState {
    private VendingMachine machine;

    public NoMoneyState(VendingMachine machine) {
        this.machine = machine;
    }
    
    @Override
    public void insertMoney() {
        System.out.println("Money inserted");
        machine.setState(
            new MoneyInsertedState(machine)
        );
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