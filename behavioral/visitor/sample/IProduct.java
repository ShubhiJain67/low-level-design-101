package behavioral.visitor.sample;

public interface IProduct {
    void accept(IProductVisitor visitor);
}
