package behavioral.visitor.sample.products;

import behavioral.visitor.sample.IProduct;
import behavioral.visitor.sample.IProductVisitor;

public class Book implements IProduct {
    private double price;

    public Book(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void accept(IProductVisitor visitor) {
        visitor.visit(this);
    }
}