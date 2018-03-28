package com.chortitzer.cin.model.dao.bascula;

import com.chortitzer.cin.model.bascula.TblBasTimbradosRuc;

public class TblBasTimbradosRucDao extends AbstractDaoBasculaImp<TblBasTimbradosRuc> {

    public TblBasTimbradosRucDao() {
        super(TblBasTimbradosRuc.class);
    }

    public String findRucByTimbrado(Integer timbrado) {
        try {
            return getEntityManager().createQuery("select t.ruc from TblBasTimbradosRuc t where t.nroTimbrado = " + timbrado.toString()).getSingleResult().toString();
        } catch (Exception ex) {
            return "";
        }
    }
}
