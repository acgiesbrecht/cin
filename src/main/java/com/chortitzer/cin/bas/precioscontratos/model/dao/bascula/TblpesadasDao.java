package com.chortitzer.cin.bas.precioscontratos.model.dao.bascula;

import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblempresa;
import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblpesadas;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class TblpesadasDao extends AbstractDaoBasculaImp<Tblpesadas> {


    public TblpesadasDao() {
        super(Tblpesadas.class);
    }

    @Transactional
    public Optional<Tblempresa> findById(Integer id) {
        List<Tblempresa> list = (List<Tblempresa>)getEntityManager().createQuery("select e from Tblpesadas e").getResultList();
        return list.stream().filter(empresa -> empresa.getId().equals(id)).findFirst();
    }


}
