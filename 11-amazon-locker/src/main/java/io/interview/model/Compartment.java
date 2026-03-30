package io.interview.model;

import java.util.UUID;

public class Compartment {
    private UUID id;
    private Size size;
    private boolean occupied;

    public Compartment(Size size) {
        this.id = UUID.randomUUID();
        this.size = size;
    }

    public void markOccupied() {
        occupied = true;
    }

    public void markFree() {
        occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Size getSize() {
        return size;
    }

    public void open() {
        occupied = false;
    }
}
