package behavioral.observer.sample.pull;

public class User implements IObserver {

    private final String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(ISubject stock) {
        Stock stockObj = (Stock) stock;
        System.out.println(name + " received notification: New Price - " + stockObj.getStockPrice());
    }
}
