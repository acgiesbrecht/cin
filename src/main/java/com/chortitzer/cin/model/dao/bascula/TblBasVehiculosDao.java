package com.chortitzer.cin.model.dao.bascula;

import com.chortitzer.cin.model.bascula.TblBasVehiculos;

public class TblBasVehiculosDao extends AbstractDaoBasculaImp<TblBasVehiculos> {

    public TblBasVehiculosDao() {
        super(TblBasVehiculos.class);
    }

    public String findRucByChapa(String chapa) {
        try {
            return getEntityManager().createQuery("select t.ruc from TblBasVehiculos t where t.chapa = '" + chapa + "'").getSingleResult().toString();
        } catch (Exception ex) {
            return "";
        }
    }
}
