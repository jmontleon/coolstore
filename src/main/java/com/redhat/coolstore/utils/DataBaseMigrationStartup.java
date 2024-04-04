package com.redhat.coolstore.utils;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.inject.Inject;
import jakarta.transaction.TransactionManagement;
import jakarta.transaction.TransactionManagementType;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by tqvarnst on 2017-04-04.
 */
@ApplicationScoped
@ApplicationScoped
@TransactionManagement(TransactionManagementType.BEAN)
public class DataBaseMigrationStartup {

    @Inject
    Logger logger;

    @Resource(mappedName = "java:jboss/datasources/CoolstoreDS")
    DataSource dataSource;

    @PostConstruct
    private void startup(@Initialized(ApplicationScoped.class) Iterable<Object> initParams) {


        try {
            logger.info("Initializing/migrating the database using FlyWay");
            Flyway flyway = new Flyway();
            flyway.setDataSource(dataSource);
            flyway.baseline();
            // Start the db.migration
            flyway.migrate();
        } catch (FlywayException e) {
            if(logger !=null)
                logger.log(Level.SEVERE,"FAILED TO INITIALIZE THE DATABASE: " + e.getMessage(),e);
            else
                System.out.println("FAILED TO INITIALIZE THE DATABASE: " + e.getMessage() + " and injection of logger doesn't work");

        }
    }



}
