package observer;

public class Trader implements IObserver {
    private final String name;

    public Trader(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println(
            String.format(
                "Trader %s notified. Stock: %s, Price: %.2f",
                name,
                stockName,
                price
            )
        );
    }
    
}
