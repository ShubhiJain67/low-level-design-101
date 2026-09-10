package behavioral.observer.sample.pull;

public class TradingApp implements IObserver {

    private final String name;

    public TradingApp(String name) {
        this.name = name;
    }

    @Override
    public void update(ISubject stock) {
        Stock stockObj = (Stock) stock;
        System.out.println(name + " received notification: New Price - " + stockObj.getStockPrice());
        System.out.println(name + " received notification: New Trend - " + stockObj.getStockTrend());
    }
}
