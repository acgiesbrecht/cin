package com.chortitzer.cin.model.dao.bascula;

import com.chortitzer.cin.model.bascula.TblContribuyentes;
import com.chortitzer.cin.model.bascula.TblContribuyentes;

import java.util.List;

public class TblContribuyentesDao extends AbstractDaoBasculaImp<TblContribuyentes> {

    public TblContribuyentesDao() {
        super(TblContribuyentes.class);
    }

    public List<TblContribuyentes> findAllOrdered() {
        return getListFromQuery("select e from TblContribuyentes e order by e.rucSinDv");
    }

    public TblContribuyentes findByRuc(String ruc) {
        return getEntityFromQuery("select e from TblContribuyentes e where e.rucSinDv = '" + ruc.split("-", 2)[0] + "'");
    }

}
