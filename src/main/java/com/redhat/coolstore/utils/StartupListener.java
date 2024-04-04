package com.redhat.coolstore.utils;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jboss.logging.Logger;

@Singleton
public class StartupListener {

    @Inject
    Logger log;

    public void onStartup(org.eclipse.microprofile.config.spi.ConfigSourceProvider configSourceProvider) {
        log.info("AppListener(postStart)");
    }

    public void onShutdown() {
        log.info("AppListener(preStop)");
    }

}
