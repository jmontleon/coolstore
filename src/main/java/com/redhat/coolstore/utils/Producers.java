package com.redhat.coolstore.utils;

import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Named;
import java.util.logging.Logger;

public class Producers {

    Logger log = Logger.getLogger(Producers.class.getName());

    public Logger produceLog() {
        Instance<Logger> loggerInstance = CDI.current().select(Logger.class);
        return loggerInstance.get();
    }

}
