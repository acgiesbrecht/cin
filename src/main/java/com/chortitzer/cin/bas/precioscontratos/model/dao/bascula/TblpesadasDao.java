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

    @Override
    public List<Tblpesadas> findAll() {
        return (List<Tblpesadas>) getEntityManager().createQuery("select e from Tblpesadas e order by e.id desc").getResultList();
    }

}
