package behavioral.visitor.sample;

import behavioral.visitor.sample.products.Book;
import behavioral.visitor.sample.products.Electronics;

public interface IProductVisitor {
    void visit(Book book);
    void visit(Electronics electronics);
}