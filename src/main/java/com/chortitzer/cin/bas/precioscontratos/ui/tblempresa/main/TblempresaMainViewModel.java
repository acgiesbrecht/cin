package com.chortitzer.cin.bas.precioscontratos.ui.tblempresa.main;

import com.chortitzer.cin.bas.precioscontratos.model.Tblempresa;
import com.chortitzer.cin.bas.precioscontratos.model.dao.TblempresaDao;
import com.chortitzer.cin.bas.precioscontratos.utils.Dialog;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public class TblempresaMainViewModel implements ViewModel {

    private final ObservableList<Tblempresa> empresasList = FXCollections.observableArrayList();
    private final ObjectProperty<Tblempresa> selectedEmpresa = new SimpleObjectProperty<>();
    private ModelWrapper<Tblempresa> empresaWrapper = new ModelWrapper<>();
    private final ObjectProperty<Tblempresa> selectedTableRow = new SimpleObjectProperty<>();
    private Consumer<Tblempresa> onSelect;

    @Inject
    Dialog dialog;

    @Inject
    TblempresaDao tblempresaDao;

    public void initialize() {
        updateEmpresaList();
        selectedEmpresaProperty().addListener(new ChangeListener<Tblempresa>() {
                                                  @Override
                                                  public void changed(ObservableValue<? extends Tblempresa> observable, Tblempresa oldValue, Tblempresa newValue) {
                                                      empresaWrapper.set(selectedEmpresaProperty().get());
                                                  }
                                              }
        );
    }

    private void updateEmpresaList() {

        final Integer selectedEmpresaId = (selectedTableRow.get() == null) ? null : selectedTableRow.get().getId();

        Set<Tblempresa> allEmpresas = new HashSet<>(tblempresaDao.findAll());

        empresasList.clear();
        empresasList.addAll(allEmpresas);

        if (selectedEmpresaId != null) {
            Optional<Tblempresa> selectedRow = empresasList.stream()
                    .filter(row -> row.getId().equals(selectedEmpresaId))
                    .findFirst();

            Optional.of(onSelect).ifPresent(consumer -> consumer.accept(selectedRow.orElse(null)));
        }
    }

    public StringProperty nombreProperty() { return empresaWrapper.field("nombre", Tblempresa::getNombre, Tblempresa::setNombre); }
    public StringProperty rucProperty() { return empresaWrapper.field("ruc", Tblempresa::getRuc, Tblempresa::setRuc); }
    public StringProperty ctacteProperty() { return empresaWrapper.field("ctacte", Tblempresa::getCuentacorriente, Tblempresa::setCuentacorriente); }

    public Tblempresa getSelectedEmpresa() { return selectedEmpresa.get(); }
    public void setSelectedEmpresa(Tblempresa selectedEmpresa){ this.selectedEmpresa.set(selectedEmpresa); }
    public ObjectProperty<Tblempresa> selectedEmpresaProperty() { return selectedEmpresa; }

    public ReadOnlyBooleanProperty differentProperty(){return empresaWrapper.differentProperty();}

    public ObservableList<Tblempresa> getEmpresas() {
        return empresasList;
    }
    public void reset(){
        try{
            empresaWrapper.reload();
        }catch (Exception ex){
            dialog.showAlert(ex.getMessage());
        }
    }

    public void setOnSelect(Consumer<Tblempresa> consumer) {
        onSelect = consumer;
    }

    public void add(){
        try{
            Tblempresa empresa = new Tblempresa();
            empresa.setNombre("");
            empresaWrapper.set(empresa);
            empresaWrapper.reset();
        }catch (Exception ex){
            dialog.showAlert(ex.getMessage());
        }
    }

    public void save(){
        try{
            empresaWrapper.commit();
            tblempresaDao.persist(empresaWrapper.get());
            updateEmpresaList();
        }catch (Exception ex){
            dialog.showAlert(ex.getMessage());
        }
    }

    public void delete(){
        try{
            if(dialog.showConfirmation("¿Realmente desea eliminar la empresa seleccionada?", false)){
                tblempresaDao.remove(empresaWrapper.get());
                updateEmpresaList();
            }
        }catch (Exception ex){
            dialog.showAlert(ex.getMessage());
        }
    }

}
