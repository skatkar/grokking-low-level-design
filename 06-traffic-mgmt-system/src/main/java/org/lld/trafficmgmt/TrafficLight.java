package org.lld.trafficmgmt;

public class TrafficLight{
    private final String id;
    private Signal currentSignal;
    private int redDuration;
    private int yellowDuration;
    private int greenDuration;
    private final ObserverManager observerManager;

    public TrafficLight(String id, int redDuration, int yellowDuration, int greenDuration) {
        this.id = id;
        this.redDuration = redDuration;
        this.yellowDuration = yellowDuration;
        this.greenDuration = greenDuration;
        this.currentSignal = Signal.RED;
        this.observerManager = new ObserverManagerImpl();
    }

    public synchronized void changeSignal(Signal newSignal) {
        if (this.currentSignal != newSignal) {
            this.currentSignal = newSignal;
            notifyObservers(newSignal);
        }
    }

    public Signal getCurrentSignal() {
        return currentSignal;
    }

    public int getRedDuration() {
        return redDuration;
    }

    public void setRedDuration(int redDuration) {
        this.redDuration = redDuration;
    }

    public int getYellowDuration() {
        return yellowDuration;
    }

    public void setYellowDuration(int yellowDuration) {
        this.yellowDuration = yellowDuration;
    }

    public int getGreenDuration() {
        return greenDuration;
    }

    public void setGreenDuration(int greenDuration) {
        this.greenDuration = greenDuration;
    }

    private void notifyObservers(Signal signal) {
        observerManager.notifyObservers(signal);
    }

    public void registerObserver(SignalObserver newObserver){
        observerManager.registerObserver(newObserver);
    }

    public void removeObserver(SignalObserver observer) {
        observerManager.removeObserver(observer);
    }
}
