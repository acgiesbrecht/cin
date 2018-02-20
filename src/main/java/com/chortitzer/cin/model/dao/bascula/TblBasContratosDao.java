package com.chortitzer.cin.model.dao.bascula;

import com.chortitzer.cin.model.bascula.TblBasContratos;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class TblBasContratosDao extends AbstractDaoBasculaImp<TblBasContratos> {


    public TblBasContratosDao() {
        super(TblBasContratos.class);
    }

    @Transactional
    public Optional<TblBasContratos> findById(Integer id) {
        List<TblBasContratos> list = (List<TblBasContratos>)getEntityManager().createQuery("select e from TblBasContratos e").getResultList();
        return list.stream().filter(contrato -> contrato.getId().equals(id)).findFirst();
    }


}
