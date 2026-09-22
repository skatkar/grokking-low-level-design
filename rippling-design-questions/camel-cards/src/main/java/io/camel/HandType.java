package io.camel;

public enum HandType {
    HIGH_CARD(1),
    ONE_PAIR(2),
    THREE_OF_A_KIND(3),
    TWO_PAIR(4),
    FOUR_OF_A_KIND(5);

    private final int strength;

    HandType(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }
}
