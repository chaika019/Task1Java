package task1.by.chaika19.observer;

public interface CustomArrayObservable {
    void attach(CustomArrayObserver observer);

    void detach(CustomArrayObserver observer);

    void notifyObservers();
}