package com.chortitzer.cin.bas.precioscontratos.model.dao;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Qualifier;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class EntityManagerProducer {

    @Produces
    @PgSQLDatabase
    public EntityManager createEntityManager() {
        return Persistence
                .createEntityManagerFactory("my-persistence-unit")
                .createEntityManager();
    }

    public void close(
            @Disposes @PgSQLDatabase EntityManager entityManager) {
        entityManager.close();
    }

}
