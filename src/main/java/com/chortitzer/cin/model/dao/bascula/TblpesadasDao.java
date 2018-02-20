package com.chortitzer.cin.model.dao.bascula;

import com.chortitzer.cin.model.bascula.Tblempresa;
import com.chortitzer.cin.model.bascula.Tblpesadas;
import com.chortitzer.cin.model.bascula.Tblpesadas;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TblpesadasDao extends AbstractDaoBasculaImp<Tblpesadas> {


    public TblpesadasDao() {
        super(Tblpesadas.class);
    }

    @Override
    public List<Tblpesadas> findAll() {
        return (List<Tblpesadas>) getEntityManager().createQuery("select e from Tblpesadas e where e.fechahora >= '" +
                String.valueOf(LocalDate.now().minusYears(1).getYear()) +
                "-01-01 00:00:00' order by e.id desc").getResultList();
    }

}
