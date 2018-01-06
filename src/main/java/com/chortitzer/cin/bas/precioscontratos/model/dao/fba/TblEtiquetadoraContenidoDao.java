package com.chortitzer.cin.bas.precioscontratos.model.dao.fba;

import com.chortitzer.cin.bas.precioscontratos.App;
import com.chortitzer.cin.bas.precioscontratos.model.bascula.Tblpesadas;
import com.chortitzer.cin.bas.precioscontratos.model.fba.Formulas;
import com.chortitzer.cin.bas.precioscontratos.model.fba.TblEtiquetadoraContenido;
import com.chortitzer.cin.bas.precioscontratos.utils.Dialog;
import javafx.scene.control.Alert;

import javax.inject.Inject;
import java.util.List;

public class TblEtiquetadoraContenidoDao extends AbstractDaoBalanceadosImp<TblEtiquetadoraContenido> {

    @Inject
    Dialog dialog;

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
