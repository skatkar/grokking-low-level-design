package io.interview;

public class HandlerResult {
    private String message;
    private boolean shouldPrint;


    public HandlerResult(String message, boolean shouldPrint) {
        this.message = message;
        this.shouldPrint = shouldPrint;
    }

    public String getMessage() {
        return message;
    }

    public boolean isShouldPrint() {
        return shouldPrint;
    }
}
