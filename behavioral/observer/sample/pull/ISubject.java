package behavioral.observer.sample.pull;

public interface ISubject {
    void watch(IObserver observer);

    void unwatch(IObserver observer);

    void notifyObservers();
}

