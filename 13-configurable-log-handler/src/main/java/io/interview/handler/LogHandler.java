package io.interview.handler;

import io.interview.HandlerResult;

public abstract class LogHandler {
    public abstract HandlerResult handle(String message);

}
