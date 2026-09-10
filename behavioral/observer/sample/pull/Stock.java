package behavioral.observer.sample.pull;

import java.util.ArrayList;
import java.util.List;

public class Stock implements IStockSubject {
    private List<IObserver> watchers = new ArrayList<>();
    private String name;
    private double price;
    private String trend;

    public Stock(String name){
        this.name = name;
        this.price = 0;
        this.trend = "stable";
    }

    @Override
    public void watch(IObserver observer) {
        this.watchers.add(observer);
    }

    @Override
    public void unwatch(IObserver observer) {
        this.watchers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver watcher : this.watchers) {
            watcher.update(this);
        }
    }

    @Override
    public double getStockPrice(){
        return this.price;
    }

    @Override
    public String getStockTrend(){
        return this.trend;
    }

    public void updateDetails(int price) {
        if (this.price == price) {
            this.trend = "stable";
        } else if (this.price > price) {
            this.trend = "decreaing";
        } else {
            this.trend = "increasing";
        }
        this.price = price;
        System.out.println("Updated price for " + this.name + ": " + price);
        notifyObservers();
    }
}
