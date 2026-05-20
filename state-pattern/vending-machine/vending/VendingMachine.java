package vending;

import java.util.HashMap;
import java.util.Map;
import model.Product;
import state.IVendingMachineState;
import state.IdleState;

public class VendingMachine {
    private IVendingMachineState currentState;
    private int insertedMoney;
    private final Map<String, Product> inventory;
    private Product selectedProduct;

    public VendingMachine() {
        this.currentState = new IdleState(this);
        this.insertedMoney = 0;
        this.inventory = new HashMap<>();
        this.selectedProduct = null;
    }

    // Public API — delegates to current state
    public void insertMoney(int amount) {
        currentState.insertMoney(amount);
    }

    public void selectProduct(String productName) {
        currentState.selectProduct(productName);
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }

    // State management
    public void setState(IVendingMachineState state) {
        this.currentState = state;
    }

    // Internal methods used by states
    public void addMoney(int amount) {
        this.insertedMoney += amount;
    }

    public int getInsertedMoney() {
        return this.insertedMoney;
    }

    public void resetMoney() {
        this.insertedMoney = 0;
    }

    public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
    }

    public Product getSelectedProduct() {
        return this.selectedProduct;
    }

    public void addProduct(Product product) {
        inventory.put(product.getName(), product);
    }

    public Product getProduct(String productName) {
        return inventory.get(productName);
    }
}
