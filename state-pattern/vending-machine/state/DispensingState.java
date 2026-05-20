package state;

import model.Product;
import vending.VendingMachine;

public class DispensingState implements IVendingMachineState {

    private final VendingMachine machine;

    public DispensingState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney(int amount) {
        System.out.println("Please wait. Dispensing in progress");
    }

    @Override
    public void selectProduct(String productName) {
        System.out.println("Already dispensing a product");
    }

    @Override
    public void dispenseProduct() {
        Product product = machine.getSelectedProduct();

        System.out.println("Dispensing: " + product.getName());
        product.reduceQuantity();

        int remaining = machine.getInsertedMoney() - product.getPrice();
        if (remaining > 0) {
            System.out.println("Returning change: ₹" + remaining);
        }

        machine.resetMoney();
        machine.setSelectedProduct(null);
        machine.setState(new IdleState(machine));
    }
}
