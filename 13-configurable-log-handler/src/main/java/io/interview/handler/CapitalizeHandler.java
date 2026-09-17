package io.interview.handler;

import io.interview.HandlerResult;

public class CapitalizeHandler extends LogHandler{
    @Override
    public HandlerResult handle(String message) {
        return new HandlerResult(message.toUpperCase(), true);
    }
}
