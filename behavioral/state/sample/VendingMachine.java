package behavioral.state.sample;

public class VendingMachine {

    private IVendingMachineState state;

    public VendingMachine() {
        this.state = new NoMoneyState(this);
    }

    public void setState(IVendingMachineState state) {
        this.state = state;
    }

    public void insertMoney() {
        state.insertMoney();
    }

    public void selectProduct() {
        state.selectProduct();
    }

    public void dispense() {
        state.dispense();
    }
}
