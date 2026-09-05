package creational.builder.sample.step;

public interface IOptionalStep {
    IOptionalStep addCheese();
    IOptionalStep addTopping(String topping);
    Pizza build();
}
