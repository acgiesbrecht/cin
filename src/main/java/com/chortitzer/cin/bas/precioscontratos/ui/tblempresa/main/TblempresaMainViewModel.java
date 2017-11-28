package com.chortitzer.cin.bas.precioscontratos.ui.tblempresa.main;

import com.chortitzer.cin.bas.precioscontratos.model.Tblempresa;
import com.chortitzer.cin.bas.precioscontratos.model.dao.TblempresaDao;
import com.chortitzer.cin.bas.precioscontratos.utils.Dialog;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import javafx.beans.property.*;
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
    private final ModelWrapper<Tblempresa> selectedEmpresa = new ModelWrapper<>();
    private final ObjectProperty<Tblempresa> selectedTableRow = new SimpleObjectProperty<>();
    private Consumer<Tblempresa> onSelect;

    @Inject
    Dialog dialog;

    @Inject
    TblempresaDao tblempresaDao;

    public void initialize() {
        updateEmpresaList();

    }

    private void updateEmpresaList() {

        final Integer selectedEmpresaId = (selectedTableRow.get() == null) ? null : selectedTableRow.get().getId();

        Set<Tblempresa> allEmpresas = new HashSet<>(tblempresaDao.getAll());

        empresasList.clear();
        empresasList.addAll(allEmpresas);

        if (selectedEmpresaId != null) {
            Optional<Tblempresa> selectedRow = empresasList.stream()
                    .filter(row -> row.getId().equals(selectedEmpresaId))
                    .findFirst();

            Optional.of(onSelect).ifPresent(consumer -> consumer.accept(selectedRow.orElse(null)));
        }
    }

    public StringProperty nombreProperty() {
        return selectedEmpresa.field("nombre", Tblempresa::getNombre, Tblempresa::setNombre);
    }

    public ObjectProperty<Tblempresa> selectedEmpresaProperty() {
        return selectedEmpresa.modelProperty();
    }

    public ReadOnlyBooleanProperty differentProperty(){return selectedEmpresa.differentProperty();}


    public ObservableList<Tblempresa> getEmpresas() {
        return empresasList;
    }
    public void setOnSelect(Consumer<Tblempresa> consumer) {
        onSelect = consumer;
    }

    public void save(){
        try{
            selectedEmpresa.commit();
            tblempresaDao.persist(selectedEmpresa.get());
            updateEmpresaList();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
