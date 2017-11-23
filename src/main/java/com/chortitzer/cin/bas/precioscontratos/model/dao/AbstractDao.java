package com.chortitzer.cin.bas.precioscontratos.model.dao;

import com.chortitzer.cin.bas.precioscontratos.JPAInitializer;
import com.google.inject.*;
import com.google.inject.persist.Transactional;
import com.google.inject.persist.jpa.JpaPersistModule;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import java.util.List;

public abstract class AbstractDao<T> {
    protected Class<T> entityClass;

    //@Inject
    //private Provider<EntityManager> em;
    private EntityManager em;

    @Inject
    public AbstractDao(Class<T> entityClass) {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                install(new JpaPersistModule("PU"));
                bind(JPAInitializer.class).asEagerSingleton();
            }
        });

        this.em = injector.getInstance(EntityManager.class);
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        return this.em;
    }

    /**
     * Retrieves the meta-model for a certain entity.
     *
     * @return the meta-model of a certain entity.
     */
    protected EntityType<T> getMetaModel() {
        return getEntityManager().getMetamodel().entity(entityClass);
    }

    @Transactional
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    @Transactional
    public void update(T entity) {
        getEntityManager().merge(entity);
    }

    @Transactional
    public void remove(Long entityId) {
        T entity = find(entityId);

        if (entity != null)
            remove(entity);
    }

    @Transactional
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    @Transactional
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    @Transactional
    public List<T> findAll() {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
                .createQuery(entityClass);
        cq.select(cq.from(entityClass));

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Transactional
    public List<T> findRange(int[] range) {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
                .createQuery(entityClass);
        cq.select(cq.from(entityClass));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);

        return q.getResultList();
    }

    @Transactional
    public int count() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(entityClass);
        cq.select(cb.count(root));
        Long count = getEntityManager().createQuery(cq).getSingleResult();

        return count.intValue();
    }

}