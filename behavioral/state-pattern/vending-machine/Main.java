import model.Product;
import vending.VendingMachine;

public class Main {

    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        machine.addProduct(new Product("Coke", 40, 5));
        machine.addProduct(new Product("Pepsi", 35, 2));

        machine.insertMoney(50);
        machine.selectProduct("Coke");
    }
}
