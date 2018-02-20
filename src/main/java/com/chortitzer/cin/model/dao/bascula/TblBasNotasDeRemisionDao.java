package com.chortitzer.cin.model.dao.bascula;

import com.chortitzer.cin.model.bascula.TblBasFacturas;
import com.chortitzer.cin.model.bascula.TblBasNotasDeRemision;

import java.util.List;

public class TblBasNotasDeRemisionDao extends AbstractDaoBasculaImp<TblBasNotasDeRemision> {

    public TblBasNotasDeRemisionDao() {
        super(TblBasNotasDeRemision.class);
    }

    public List<String> getEmisoresRucList() {
        return (List<String>) getEntityManager().createQuery("select distinct t.rucEmisor from TblBasNotasDeRemision t").getResultList();
    }

    public List<String> getTransportadorasRucList() {
        return (List<String>) getEntityManager().createQuery("select distinct t.rucTransportadora from TblBasNotasDeRemision t").getResultList();
    }

    public List<TblBasNotasDeRemision> findAllByFacturaOrSinFactura(TblBasFacturas factura) {
        return (List<TblBasNotasDeRemision>) getEntityManager().createQuery("select t from TblBasNotasDeRemision t where t.idFactura = "
                + factura.getId().toString()
                + " or t.idFactura = null").getResultList();
    }


}
