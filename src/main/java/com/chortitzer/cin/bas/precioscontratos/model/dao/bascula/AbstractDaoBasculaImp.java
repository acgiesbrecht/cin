package com.chortitzer.cin.bas.precioscontratos.model.dao.bascula;


import com.chortitzer.cin.bas.precioscontratos.model.dao.AbstractDao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;
import java.util.List;

public abstract class AbstractDaoBasculaImp<T> implements AbstractDao<T> {
    protected Class<T> entityClass;

    @Inject @PgBascula
    private EntityManager em;

    public AbstractDaoBasculaImp(Class<T> entityClass) {
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

    public void persist(T entity) {
        beginTransaction();
        getEntityManager().persist(entity);
        commitTransaction();
    }

    public void merge(T entity) {
        beginTransaction();
        getEntityManager().merge(entity);
        commitTransaction();
    }

    public void remove(T entity) {
    beginTransaction();
        getEntityManager().remove(getEntityManager().merge(entity));
        commitTransaction();
    }

    @Transactional
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
                .createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Transactional
    public List<T> getListFromQuery(String jpqlQuery){
        return  (List<T>)getEntityManager().createQuery(jpqlQuery).getResultList();
    }

    public void beginTransaction() {
        try {
            getEntityManager().getTransaction().begin();
        } catch (IllegalStateException e) {
            rollBackTransaction();
        }
    }

    public void commitTransaction() {
        try {
            getEntityManager().getTransaction().commit();
        } catch (IllegalStateException | RollbackException e) {
            rollBackTransaction();
        }
    }

    private void rollBackTransaction() {
        try {
            getEntityManager().getTransaction().rollback();
        } catch (IllegalStateException | PersistenceException e) {
            e.printStackTrace();
        }
    }

}
