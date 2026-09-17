package io.interview.logger;

import io.interview.HandlerResult;
import io.interview.handler.LogHandler;

import java.util.List;

public class Logger {

    private List<LogHandler> logHandlers;

    public Logger(List<LogHandler> logHandlers) {
        this.logHandlers = logHandlers;
    }

    public void log(String message) {
        boolean shouldPrint = true;

        for(LogHandler logHandler : logHandlers) {
            HandlerResult handlerResult = logHandler.handle(message);
            message = handlerResult.getMessage();
            shouldPrint = shouldPrint && handlerResult.isShouldPrint();
        }

        if(!shouldPrint){
            return;
        }

        System.out.println(message);
    }
}
