package structural.facade.sample;

public class InventoryService {
    public boolean checkStock(String item) {
        System.out.println("Checking stock for " + item);
        return true;
    }
}
