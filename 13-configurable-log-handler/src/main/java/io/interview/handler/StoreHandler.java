package io.interview.handler;

import io.interview.HandlerResult;

import java.util.ArrayList;
import java.util.List;

public class StoreHandler extends LogHandler{
    protected List<String> messages = new ArrayList<>();
    @Override
    public HandlerResult handle(String message) {
        messages.add(message);
        return new HandlerResult(message, false);
    }

    public List<String> getMessages() {
        return messages;
    }
}
