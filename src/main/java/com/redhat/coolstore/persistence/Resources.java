package com.redhat.coolstore.persistence;

import jakarta.enterprise.inject.Inject;
import jakarta.persistence.EntityManager;

public class Resources {

    @Inject
    private EntityManager em;

    @Named
    public EntityManager getEntityManager() {
        return em;
    }
}
