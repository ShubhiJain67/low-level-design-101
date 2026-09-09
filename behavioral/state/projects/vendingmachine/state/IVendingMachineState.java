package behavioral.state.projects.vendingmachine.state;

public interface IVendingMachineState {
    void insertMoney(int amount);
    void selectProduct(String productName);
    void dispenseProduct();
}
