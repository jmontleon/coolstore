package com.redhat.coolstore.persistence;

import io.quarkus.hibernate.orm.PersistenceUnit;
import io.quarkus.hibernate.orm.RuntimePanicException;
import io.quarkus.hibernate.orm.Session;
import io.quarkus.hibernate.orm.SessionFactory;
import javax.inject.Singleton;

@Singleton
public class Resources {

    @PersistenceUnit("default")
    private SessionFactory sessionFactory;

    public Session getEntityManager() {
        Session session = sessionFactory.getSession();
        if (session == null) {
            throw new RuntimePanicException("Unable to get a Hibernate Session.");
        }
        return session;
    }
}
