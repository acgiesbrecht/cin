package com.chortitzer.cin.bas.precioscontratos.model.dao.bascula;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@PgBascula
public class EntityManagerProducerBascula {

    @Produces @PgBascula
    public EntityManager createEntityManager() {
        return Persistence
                .createEntityManagerFactory("PU")
                .createEntityManager();
    }


    public void close(
            @Disposes @PgBascula EntityManager entityManager) {
        entityManager.close();
    }

}
