package com.chortitzer.cin.bas.precioscontratos.model.dao.bascula;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblempresa;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class TblempresaDao extends AbstractDaoBasculaImp<Tblempresa> {


    public TblempresaDao() {
        super(Tblempresa.class);
    }

    @Transactional
    public Optional<Tblempresa> findById(Integer id) {
        List<Tblempresa> list = (List<Tblempresa>)getEntityManager().createQuery("select e from Tblempresa e").getResultList();
        return list.stream().filter(empresa -> empresa.getId().equals(id)).findFirst();
    }


}
