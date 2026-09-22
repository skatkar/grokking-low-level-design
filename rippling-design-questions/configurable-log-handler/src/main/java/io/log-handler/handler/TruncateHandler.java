package io.interview.handler;

import io.interview.HandlerResult;

public class TruncateHandler extends io.interview.handler.LogHandler {

    private final int maxChars;

    public TruncateHandler(int maxChars) {
        this.maxChars = maxChars;
    }

    @Override
    public HandlerResult handle(String message) {
        if(maxChars <= 0)
            throw new IllegalArgumentException("Invalid number of chars to retain");
        return new HandlerResult(message.substring(0, Math.min(maxChars, message.length())),
                true);
    }
}
