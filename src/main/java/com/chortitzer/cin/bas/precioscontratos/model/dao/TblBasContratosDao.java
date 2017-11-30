package com.chortitzer.cin.bas.precioscontratos.model.dao;

import com.chortitzer.cin.bas.precioscontratos.model.TblBasContratos;
import com.chortitzer.cin.bas.precioscontratos.model.Tblempresa;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class TblBasContratosDao extends AbstractDao<TblBasContratos>{


    public TblBasContratosDao() {
        super(TblBasContratos.class);
    }

    @Transactional
    public Optional<TblBasContratos> findById(Integer id) {
        List<TblBasContratos> list = (List<TblBasContratos>)getEntityManager().createQuery("select e from TblBasContratos e").getResultList();
        return list.stream().filter(contrato -> contrato.getId().equals(id)).findFirst();
    }


}
