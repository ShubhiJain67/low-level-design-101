package behavioral.visitor.sample;

import behavioral.visitor.sample.products.*;
import behavioral.visitor.sample.visitors.*;

public class Main {

    public static void main(String[] args) {
        IProduct book = new Book(1000);
        IProduct laptop = new Electronics(50000);

        IProductVisitor taxVisitor = new TaxVisitor();
        book.accept(taxVisitor);
        laptop.accept(taxVisitor);

        IProductVisitor discountVisitor = new DiscountVisitor();
        book.accept(discountVisitor);
        laptop.accept(discountVisitor);
    }
}