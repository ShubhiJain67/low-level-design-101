package behavioral.observer.projects.stockmarket.observer;

public interface IObserver {
    void update(String stockName, double price);
}