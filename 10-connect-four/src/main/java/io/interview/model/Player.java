package io.interview.model;

import io.interview.DiscColor;

public class Player {
    private String name;
    private DiscColor color;

    public Player(String name, DiscColor color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public DiscColor getColor() {
        return color;
    }
}
