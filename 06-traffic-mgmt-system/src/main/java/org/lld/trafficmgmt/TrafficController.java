package org.lld.trafficmgmt;

import java.util.HashMap;
import java.util.Map;

public class TrafficController {
    private static TrafficController instance;
    private final Map<String, Road> roads;

    private TrafficController() {
        roads = new HashMap<>();
    }

    public static synchronized TrafficController getInstance() {
        if (instance == null) {
            instance = new TrafficController();
        }
        return instance;
    }

    public void addRoad(Road road) {
        roads.put(road.getId(), road);
    }

    public void removeRoad(String roadId) {
        roads.remove(roadId);
    }

    // This will start the signals for the roads that it is controlling
    public void startTrafficControl() {
        for (Road road : roads.values()) {
            TrafficLight trafficLight = road.getTrafficLight();
            System.out.println("Handling traffic control on = " + road.getId());
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(trafficLight.getRedDuration());
                        System.out.println("For = " + road.getId() + " changing from RED to GREEN");
                        trafficLight.changeSignal(Signal.GREEN);

                        Thread.sleep(trafficLight.getGreenDuration());
                        System.out.println("For = " + road.getId() + " changing from GREEN to YELLOW");
                        trafficLight.changeSignal(Signal.YELLOW);

                        Thread.sleep(trafficLight.getYellowDuration());
                        System.out.println("For = " + road.getId() + " changing from YELLOW to RED");
                        trafficLight.changeSignal(Signal.RED);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    // This will handle the emergency situation on a given road
    public void handleEmergency(String roadId) {
        Road road = roads.get(roadId);
        if (road != null) {
            TrafficLight trafficLight = road.getTrafficLight();
            trafficLight.changeSignal(Signal.GREEN);
            // Perform emergency handling logic
            // ...
        }
    }
}
