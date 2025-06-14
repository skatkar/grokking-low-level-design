package org.lld.trafficmgmt;

import java.util.List;

public class ObserverManagerImpl implements ObserverManager{
    private List<SignalObserver> observers;

    @Override
    public void notifyObservers(Signal signal) {
        for(SignalObserver observer : observers) {
            observer.changeSignal(signal);
        }
    }

    @Override
    public void registerObserver(SignalObserver newObserver) {
        if (newObserver != null && !observers.contains(newObserver)) {
            observers.add(newObserver);
        }
    }

    @Override
    public void removeObserver(SignalObserver observer) {
        observers.remove(observer);
    }
}
