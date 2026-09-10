package behavioral.observer.sample.pull;

public interface IStockSubject extends ISubject {
    double getStockPrice();
    String getStockTrend();
}

