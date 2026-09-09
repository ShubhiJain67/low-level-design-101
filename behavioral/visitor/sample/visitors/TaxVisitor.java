package behavioral.visitor.sample.visitors;

import behavioral.visitor.sample.IProductVisitor;
import behavioral.visitor.sample.products.Book;
import behavioral.visitor.sample.products.Electronics;

public class TaxVisitor implements IProductVisitor {
    @Override
    public void visit(Book book) {
        double tax = book.getPrice() * 0.05;
        System.out.println("Book tax: " + tax);
    }

    @Override
    public void visit(Electronics electronics) {
        double tax = electronics.getPrice() * 0.18;
        System.out.println("Electronics tax: " + tax);
    }
}
