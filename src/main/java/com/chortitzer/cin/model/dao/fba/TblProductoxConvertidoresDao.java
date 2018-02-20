package com.chortitzer.cin.model.dao.fba;

import com.chortitzer.cin.model.fba.TblEtiquetadoraContenido;
import com.chortitzer.cin.model.fba.TblProductoxConvertidores;
import com.chortitzer.cin.model.fba.TblProductoxConvertidores;

import java.util.List;

public class TblProductoxConvertidoresDao extends AbstractDaoBalanceadosImp<TblProductoxConvertidores> {

    public TblProductoxConvertidoresDao() {
        super(TblProductoxConvertidores.class);
    }

    public List<TblProductoxConvertidores> findByFormula(Integer idFormula) {
        return (List<TblProductoxConvertidores>) getEntityManager().createQuery("select p from TblProductoxConvertidores p, Datosx d where p.idProductox = d.idp and d.idf = " + String.valueOf(idFormula) + " and d.set > 0 order by d.set desc").getResultList();
    }

}
