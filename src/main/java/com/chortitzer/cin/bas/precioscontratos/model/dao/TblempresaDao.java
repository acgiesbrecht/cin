package com.chortitzer.cin.bas.precioscontratos.model.dao;

import com.chortitzer.cin.bas.precioscontratos.model.Tblempresa;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import java.util.List;
import java.util.Optional;

@Singleton
public class TblempresaDao extends AbstractDao<Tblempresa>{


    public TblempresaDao() {
        super(Tblempresa.class);
    }

    @Transactional
    public List<Tblempresa> getAll() {
        return getEntityManager().createQuery("select e from Tblempresa e").getResultList();
    }

    @Transactional
    public Optional<Tblempresa> findById(Integer id) {
        List<Tblempresa> list = (List<Tblempresa>)getEntityManager().createQuery("select e from Tblempresa e").getResultList();
        return list.stream().filter(empresa -> empresa.getId().equals(id)).findFirst();
    }

}
