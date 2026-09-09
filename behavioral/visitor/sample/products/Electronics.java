package behavioral.visitor.sample.products;

import behavioral.visitor.sample.IProduct;
import behavioral.visitor.sample.IProductVisitor;

public class Electronics implements IProduct {
    private double price;

    public Electronics(double price) {
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