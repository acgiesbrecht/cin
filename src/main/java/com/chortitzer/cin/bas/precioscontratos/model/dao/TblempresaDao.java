package com.chortitzer.cin.bas.precioscontratos.model.dao;

import com.chortitzer.cin.bas.precioscontratos.model.Tblempresa;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class TblempresaDao extends AbstractDao<Tblempresa>{


    public TblempresaDao() {
        super(Tblempresa.class);
    }

    @Transactional
    public Optional<Tblempresa> findById(Integer id) {
        List<Tblempresa> list = (List<Tblempresa>)getEntityManager().createQuery("select e from Tblempresa e").getResultList();
        return list.stream().filter(empresa -> empresa.getId().equals(id)).findFirst();
    }


}
