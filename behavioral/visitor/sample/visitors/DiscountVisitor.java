package behavioral.visitor.sample.visitors;

import behavioral.visitor.sample.products.Book;
import behavioral.visitor.sample.products.Electronics;
import behavioral.visitor.sample.IProductVisitor;

public class DiscountVisitor implements IProductVisitor {
    @Override
    public void visit(Book book) {
        System.out.println("Book discount: " + book.getPrice() * 0.10);
    }

    @Override
    public void visit(Electronics electronics) {
        System.out.println("Electronics discount: " + electronics.getPrice() * 0.20);
    }
}
