package state;

import model.Product;
import vending.VendingMachine;

public class HasMoneyState implements IVendingMachineState {

    private final VendingMachine machine;

    public HasMoneyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney(int amount) {
        machine.addMoney(amount);
        System.out.println("Additional money inserted: ₹" + amount);
    }

    @Override
    public void selectProduct(String productName) {
        Product product = machine.getProduct(productName);

        if (product == null) {
            System.out.println("Invalid product selected");
            return;
        }

        if (product.getQuantity() <= 0) {
            System.out.println("Product out of stock");
            machine.setState(new OutOfStockState(machine));
            return;
        }

        if (machine.getInsertedMoney() < product.getPrice()) {
            System.out.println("Insufficient balance");
            return;
        }

        machine.setSelectedProduct(product);
        machine.setState(new DispensingState(machine));
        machine.dispenseProduct();
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please select a product first");
    }
}
