package org.lld.trafficmgmt;

public class Road implements SignalObserver {
    private final String id;
    private final String name;
    private TrafficLight trafficLight;

    public Road(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }

    public void setTrafficLight(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
        trafficLight.registerObserver(this);
    }

    public String getId() {
        return id;
    }

    @Override
    public void changeSignal(Signal signal) {
        System.out.println("Road id = " + id + " name = " +  name + " Signal changed to = " + signal);
    }
}
