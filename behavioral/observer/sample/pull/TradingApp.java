package behavioral.observer.sample.pull;

public class TradingApp implements IObserver {

    private final String name;

    public TradingApp(String name) {
        this.name = name;
    }

    @Override
    public void update(ISubject stock) {
        IStockSubject stockObj = (IStockSubject) stock;
        System.out.println(name + " received notification: New Price - " + stockObj.getStockPrice());
        System.out.println(name + " received notification: New Trend - " + stockObj.getStockTrend());
    }
}
