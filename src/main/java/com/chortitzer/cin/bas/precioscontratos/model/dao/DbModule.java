package com.chortitzer.cin.bas.precioscontratos.model.dao;

import com.chortitzer.cin.bas.precioscontratos.JPAInitializer;
import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

public class DbModule extends AbstractModule {

    public void configure() {

    }

    /*
    private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE
            = new ThreadLocal<EntityManager>();

    public void configure() {
    }

    @Provides
    @Singleton
    public EntityManagerFactory provideEntityManagerFactory() {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.connection.url", "jdbc:postgresql://192.168.3.122:5432/industria");
        properties.put("hibernate.connection.username", "postgres");
        properties.put("hibernate.connection.password", "123456");
        properties.put("hibernate.connection.pool_size", "1");
        //properties.put("hibernate.dialect", "org.hibernate.dialect.");
        //properties.put("hibernate.hbm2ddl.auto", "create");
        return Persistence.createEntityManagerFactory("db-manager", properties);
    }

    @Provides
    public EntityManager provideEntityManager(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = ENTITY_MANAGER_CACHE.get();
        if (entityManager == null) {
            ENTITY_MANAGER_CACHE.set(entityManager = entityManagerFactory.createEntityManager());
        }
        return entityManager;
    }
*/
}