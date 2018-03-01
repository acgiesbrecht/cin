package com.chortitzer.cin.model.dao.bascula;

import com.chortitzer.cin.model.bascula.TblBasFacturasFlete;
import com.chortitzer.cin.model.bascula.TblBasFacturasMercaderia;
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

    public String getRucFromTimbrado(String timbrado) {
        return (String) getEntityManager().createQuery("select t.rucEmisor from TblBasNotasDeRemision t where t.nroTimbrado = " + timbrado).getSingleResult();
    }

    public List<TblBasNotasDeRemision> findAllByFacturaFleteOrSinFactura(TblBasFacturasFlete factura) {
        return (List<TblBasNotasDeRemision>) getEntityManager().createQuery("select t from TblBasNotasDeRemision t where t.idFacturaFlete.id = "
                + factura.getId().toString()
                + " or t.idFacturaFlete is null").getResultList();
    }

    public List<TblBasNotasDeRemision> findAllByFacturaMercaderiaOrSinFactura(TblBasFacturasMercaderia factura) {
        return (List<TblBasNotasDeRemision>) getEntityManager().createQuery("select t from TblBasNotasDeRemision t where t.idFacturaMercaderia.id = "
                + factura.getId().toString()
                + " or t.idFacturaMercaderia is null").getResultList();
    }


}
