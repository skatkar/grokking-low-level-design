package io.interview.handler;

import io.interview.HandlerResult;

public class RemoveStringHandler extends io.interview.handler.LogHandler {

    private final String target;

    public RemoveStringHandler(String target) {
        this.target = target;
    }

    @Override
    public HandlerResult handle(String message) {
        return new HandlerResult(message.replace(target, ""), true);
    }
}
