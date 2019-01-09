package com.chortitzer.cin.model.dao.admin;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@PgAdmin
public class EntityManagerProducerAdmin {

    @Produces
    @PgAdmin
    public EntityManager createEntityManager() {
        return Persistence
                .createEntityManagerFactory("PU_ADMIN")
                .createEntityManager();
    }

    public void close(
            @Disposes @PgAdmin EntityManager entityManager) {
        entityManager.close();
    }

}
