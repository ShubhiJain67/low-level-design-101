package subject;

import observer.IObserver;

public interface ISubject {
    void subscribe(IObserver observer);

    void unsubscribe(IObserver observer);

    void notifyObservers();
}