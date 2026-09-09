package behavioral.state.sample;

public class Main {

    public static void main(String[] args) {

        VendingMachine machine = new VendingMachine();

        machine.selectProduct();

        machine.insertMoney();

        machine.selectProduct();

        machine.dispense();
    }
}
