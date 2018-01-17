package com.chortitzer.cin.bas.precioscontratos.model.dao.bascula;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.TblBasNotasDeRemision;

import java.util.List;

public class TblBasNotasDeRemisionDao extends AbstractDaoBasculaImp<TblBasNotasDeRemision> {

    public TblBasNotasDeRemisionDao() {
        super(TblBasNotasDeRemision.class);
    }

    public List<String> getEmisoresList() {
        return (List<String>) getEntityManager().createQuery("select t.razonSocialEmisor from TblBasNotasDeRemision t").getResultList();
    }

    public List<String> getTransportadorasList() {
        return (List<String>) getEntityManager().createQuery("select t.razonSocialTransportadora from TblBasNotasDeRemision t").getResultList();
    }

}
