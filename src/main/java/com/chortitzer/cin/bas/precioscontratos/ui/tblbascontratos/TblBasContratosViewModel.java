package com.chortitzer.cin.bas.precioscontratos.ui.tblbascontratos;

import com.chortitzer.cin.bas.precioscontratos.model.TblBasContratos;
import com.chortitzer.cin.bas.precioscontratos.model.Tblempresa;
import com.chortitzer.cin.bas.precioscontratos.model.Tblproductos;
import com.chortitzer.cin.bas.precioscontratos.model.dao.TblBasContratosDao;
import com.chortitzer.cin.bas.precioscontratos.model.dao.TblempresaDao;
import com.chortitzer.cin.bas.precioscontratos.utils.Dialog;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public class TblBasContratosViewModel implements ViewModel {

    private final ObservableList<TblBasContratos> contratosList = FXCollections.observableArrayList();
    private final ObjectProperty<TblBasContratos> selectedContrato = new SimpleObjectProperty<>();
    private ModelWrapper<TblBasContratos> contratoWrapper = new ModelWrapper<>();
    private final ObjectProperty<TblBasContratos> selectedTableRow = new SimpleObjectProperty<>();
    private Consumer<TblBasContratos> onSelect;

    @Inject
    Dialog dialog;

    @Inject
    TblBasContratosDao tblBasContratosDao;

    public void initialize() {
        updateEmpresaList();
        selectedContratoProperty().addListener(new ChangeListener<TblBasContratos>() {
                                                  @Override
                                                  public void changed(ObservableValue<? extends TblBasContratos> observable, TblBasContratos oldValue, TblBasContratos newValue) {
                                                      contratoWrapper.set(selectedContratoProperty().get());
                                                  }
                                              }
        );
    }

    private void updateEmpresaList() {
        final Integer selectedContratoId = (selectedTableRow.get() == null) ? null : selectedTableRow.get().getId();

        contratosList.clear();
        contratosList.addAll(new HashSet<>(tblBasContratosDao.findAll()));

        if (selectedContratoId != null) {
            Optional<TblBasContratos> selectedRow = contratosList.stream()
                    .filter(row -> row.getId().equals(selectedContratoId))
                    .findFirst();

            Optional.of(onSelect).ifPresent(consumer -> consumer.accept(selectedRow.orElse(null)));
        }
    }

    public ObjectProperty<LocalDateTime> fechaProperty() { return contratoWrapper.field("fechahora", TblBasContratos::getFecha, TblBasContratos::setFecha); }
    public ObjectProperty<Tblempresa> empresaProperty() { return contratoWrapper.field("empresa", TblBasContratos::getIdEmpresa, TblBasContratos::setIdEmpresa); }
    public ObjectProperty<Tblproductos> productoProperty() { return contratoWrapper.field("producto", TblBasContratos::getIdProducto, TblBasContratos::setIdProducto); }
    public IntegerProperty precioProperty() { return contratoWrapper.field("precioGsPorKg", TblBasContratos::getPrecioGsPorKg, TblBasContratos::setPrecioGsPorKg); }

    public TblBasContratos getSelectedContrato() { return selectedContrato.get(); }
    public void setSelectedContrato(TblBasContratos selectedContrato){ this.selectedContrato.set(selectedContrato); }
    public ObjectProperty<TblBasContratos> selectedContratoProperty() { return selectedContrato; }

    public ReadOnlyBooleanProperty differentProperty(){return contratoWrapper.differentProperty();}

    public ObservableList<TblBasContratos> getContratos() {
        return contratosList;
    }
    public void reset(){
        try{
            contratoWrapper.reload();
        }catch (Exception ex){
            dialog.showAlert(ex.getMessage());
        }
    }

    public void setOnSelect(Consumer<TblBasContratos> consumer) {
        onSelect = consumer;
    }

    public void add(){
        try{
            TblBasContratos contrato = new TblBasContratos();
            contrato.setFecha(LocalDateTime.now());
            contratoWrapper.set(contrato);
            contratoWrapper.reset();
        }catch (Exception ex){
            dialog.showAlert(ex.getMessage());
        }
    }

    public void save(){
        try{
            contratoWrapper.commit();
            tblBasContratosDao.persist(contratoWrapper.get());
            updateEmpresaList();
        }catch (Exception ex){
            dialog.showAlert(ex.getMessage());
        }
    }

    public void delete(){
        try{
            if(dialog.showConfirmation("¿Realmente desea eliminar el contrato seleccionada?", false)){
                tblBasContratosDao.remove(contratoWrapper.get());
                updateEmpresaList();
            }
        }catch (Exception ex){
            dialog.showAlert(ex.getMessage());
        }
    }

}
