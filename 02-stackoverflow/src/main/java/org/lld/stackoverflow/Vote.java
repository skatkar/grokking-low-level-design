package org.lld.stackoverflow;

public class Vote {
    private User user;
    private int value;

    public Vote(User user, int value) {
        this.user = user;
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public int getValue() {
        return value;
    }
}
