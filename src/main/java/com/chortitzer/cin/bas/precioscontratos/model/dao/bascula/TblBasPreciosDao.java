package com.chortitzer.cin.bas.precioscontratos.model.dao.bascula;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.TblBasPrecios;

import java.util.List;

public class TblBasPreciosDao  extends AbstractDaoBasculaImp<TblBasPrecios> {

    public TblBasPreciosDao() {
        super(TblBasPrecios.class);
    }

    @Override
    public List<TblBasPrecios> findAll() {
        return getEntityManager().createQuery("select e from TblBasPrecios e").setMaxResults(500).getResultList();
    }

}
