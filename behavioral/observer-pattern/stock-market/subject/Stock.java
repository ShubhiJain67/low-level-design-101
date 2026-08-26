package subject;
import async.NotificationExecutor;
import java.util.List;
import observer.IObserver;

public class Stock implements ISubject {
    public final String name;
    public double price;
    public List<IObserver> observers;

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
        this.observers = new java.util.ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    @Override
    public void subscribe(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(IObserver observer : observers) {
            // Thi is SYNC update
            // observer.update(this.name, this.price);

            // This is ASYNC update
            NotificationExecutor.getExecutorService().submit(() -> {
                observer.update(
                    this.name,
                    this.price
                );
            });
        }
    }
}