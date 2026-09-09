package behavioral.observer.projects.stockmarket.subject;

import behavioral.observer.projects.stockmarket.observer.IObserver;

public interface ISubject {
    void subscribe(IObserver observer);

    void unsubscribe(IObserver observer);

    void notifyObservers();
}