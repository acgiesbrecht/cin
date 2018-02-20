package com.chortitzer.cin.model.dao.fba;

import com.chortitzer.cin.model.fba.TblEtiquetadoraContenido;
import com.chortitzer.cin.utils.InformationDialog;

import javax.inject.Inject;

public class TblEtiquetadoraContenidoDao extends AbstractDaoBalanceadosImp<TblEtiquetadoraContenido> {

    @Inject
    InformationDialog dialog;

    public TblEtiquetadoraContenidoDao() {
        super(TblEtiquetadoraContenido.class);
    }

    public TblEtiquetadoraContenido findByFormula(Integer idFormula) {
        try {
            return (TblEtiquetadoraContenido) getEntityManager().createQuery("select e from TblEtiquetadoraContenido e where e.idFormula = " + String.valueOf(idFormula)).getSingleResult();
        } catch (Exception ex) {
            dialog.showAlert("No hay datos para la etiqueta.");
            return null;
        }
    }
}
