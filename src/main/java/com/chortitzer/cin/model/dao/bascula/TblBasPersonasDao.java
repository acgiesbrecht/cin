package com.chortitzer.cin.model.dao.bascula;

import com.chortitzer.cin.model.bascula.TblBasPersonas;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class TblBasPersonasDao extends AbstractDaoBasculaImp<TblBasPersonas> {


    public TblBasPersonasDao() {
        super(TblBasPersonas.class);
    }

    @Transactional
    public Optional<TblBasPersonas> findById(Integer id) {
        List<TblBasPersonas> list = (List<TblBasPersonas>)getEntityManager().createQuery("select e from TblBasPersonas e").getResultList();
        return list.stream().filter(persona -> persona.getId().equals(id)).findFirst();
    }


}
