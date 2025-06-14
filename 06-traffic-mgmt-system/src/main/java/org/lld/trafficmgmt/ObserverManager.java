package org.lld.trafficmgmt;

public interface ObserverManager {
    void notifyObservers(Signal signal);
    void registerObserver(SignalObserver newObserver);
    void removeObserver(SignalObserver observer);
}
