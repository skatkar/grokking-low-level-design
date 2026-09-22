package io.interview;

import io.interview.handler.*;
import io.interview.logger.Logger;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        SearchableStoreHandler storeHandler =
                new SearchableStoreHandler();

        List<LogHandler> handlers = List.of(
                new RemoveStringHandler("password"),
                new TruncateHandler(30),
                new CapitalizeHandler(),
                storeHandler
        );

        Logger logger = new Logger(handlers);

        logger.log("User password is secret");

        System.out.println(storeHandler.search("SECRET"));
    }
}