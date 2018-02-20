package com.chortitzer.cin.ui.bascula.notasderemision;

import com.chortitzer.cin.model.bascula.TblBasNotasDeRemision;
import com.chortitzer.cin.model.bascula.Tblpesadas;
import com.chortitzer.cin.model.dao.bascula.TblBasNotasDeRemisionDao;
import com.chortitzer.cin.model.dao.bascula.TblContribuyentesDao;
import com.chortitzer.cin.ui.validators.RucValidator;
import com.chortitzer.cin.model.bascula.TblBasNotasDeRemision;
import com.chortitzer.cin.model.dao.bascula.TblBasNotasDeRemisionDao;
import com.chortitzer.cin.ui.validators.RucValidator;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import de.saxsys.mvvmfx.utils.validation.CompositeValidator;
import de.saxsys.mvvmfx.utils.validation.ValidationStatus;
import de.saxsys.mvvmfx.utils.validation.Validator;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import java.time.LocalDateTime;
import java.util.List;

public class TblBasNotasDeRemisionViewModel implements ViewModel {

    public ModelWrapper<TblBasNotasDeRemision> itemWrapper = new ModelWrapper<>();

    public enum CommitStatus {COMMIT, CANCEL, DELETE}

    public CommitStatus commitStatus;

    @Inject
    TblBasNotasDeRemisionDao tblBasNotasDeRemisionDao;
    @Inject
    TblContribuyentesDao tblContribuyentesDao;

    private final Validator rucEmisorValidator = new RucValidator(rucEmisorProperty());
    public ValidationStatus rucEmisorValidation(){return rucEmisorValidator.getValidationStatus();}
    private final Validator rucTransportadoraValidator = new RucValidator(rucTransportadoraProperty());
    public ValidationStatus rucTransportadoraValidation(){return rucTransportadoraValidator.getValidationStatus();}
    private final CompositeValidator formValidator = new CompositeValidator();
    public ValidationStatus formValidation(){return formValidator.getValidationStatus();}

    public void initialize() {
        formValidator.addValidators(
                rucEmisorValidator,
                rucTransportadoraValidator
        );
    }

    public StringProperty nroProperty() {
        return itemWrapper.field("nro", TblBasNotasDeRemision::getNro, TblBasNotasDeRemision::setNro);
    }

    public StringProperty nroTimbradoProperty() {
        return itemWrapper.field("nroRemision", TblBasNotasDeRemision::getNroTimbrado, TblBasNotasDeRemision::setNroTimbrado);
    }

    public StringProperty rucEmisorProperty() {
        return itemWrapper.field("rucEmisor", TblBasNotasDeRemision::getRucEmisor, TblBasNotasDeRemision::setRucEmisor);
    }

    public StringProperty razonSocialEmisorProperty() {
        return itemWrapper.field("razonSocialEmisor", TblBasNotasDeRemision::getRazonSocialEmisor, TblBasNotasDeRemision::setRazonSocialEmisor);
    }

    public StringProperty rucTransportadoraProperty() {
        return itemWrapper.field("rucTransportadora", TblBasNotasDeRemision::getRucTransportadora, TblBasNotasDeRemision::setRucTransportadora);
    }

    public StringProperty razonSocialTransportadoraProperty() {
        return itemWrapper.field("razonSocialTransportadora", TblBasNotasDeRemision::getRazonSocialTransportadora, TblBasNotasDeRemision::setRazonSocialTransportadora);
    }

    public LongProperty pesoNetoProperty() {
        return itemWrapper.field("pesoNeto", TblBasNotasDeRemision::getPesoNeto, TblBasNotasDeRemision::setPesoNeto);
    }

    public ObjectProperty<LocalDateTime> fechaEmisionProperty() {
        return itemWrapper.field("fechaEmision", TblBasNotasDeRemision::getFechaEmision, TblBasNotasDeRemision::setFechaEmision);
    }

    public ObservableList<String> getEmisoresRucList() {
        return FXCollections.observableArrayList(tblBasNotasDeRemisionDao.getEmisoresRucList());
    }

    public ObservableList<String> getTransportadorasRucList() {
        return FXCollections.observableArrayList(tblBasNotasDeRemisionDao.getTransportadorasRucList());
    }

    public void commit() {
        itemWrapper.commit();
        commitStatus = CommitStatus.COMMIT;
    }

    public void cancel() {
        commitStatus = CommitStatus.CANCEL;
    }

    public void delete() {
        commitStatus = CommitStatus.DELETE;
    }

    public String getContribuyenteRazonSocial(String ruc) {
        try {
            return tblContribuyentesDao.findByRuc(ruc).getRazonSocial();
        } catch (Exception ex) {
            return "";
        }
    }

}
