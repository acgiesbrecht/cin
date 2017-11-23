package com.chortitzer.cin.bas.precioscontratos.model.dao;

import com.chortitzer.cin.bas.precioscontratos.model.TblBasPrecios;

import java.util.List;

public class TblBasPreciosDao  extends AbstractDao<TblBasPrecios> {

    public TblBasPreciosDao() {
        super(TblBasPrecios.class);
    }

    public List<TblBasPrecios> getAll() {
        return getEntityManager().createQuery("select t from TblBasPrecios t").getResultList();
    }

}
