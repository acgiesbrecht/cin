package com.chortitzer.cin.bas.precioscontratos.ui.tblempresa.master;

import com.chortitzer.cin.bas.precioscontratos.model.Tblempresa;
import com.chortitzer.cin.bas.precioscontratos.model.dao.TblempresaDao;
import com.chortitzer.cin.bas.precioscontratos.ui.tblempresa.scopes.TblempresaMasterDetailScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public class TblempresaMasterViewModel implements ViewModel {

    private static final Logger LOG = LoggerFactory.getLogger(TblempresaMasterViewModel.class);

    private final ObservableList<Tblempresa> empresas = FXCollections.observableArrayList();
    private final ReadOnlyObjectWrapper<Tblempresa> selectedEmpresa = new ReadOnlyObjectWrapper<>();
    private final ObjectProperty<Tblempresa> selectedTableRow = new SimpleObjectProperty<>();
    private Consumer<Tblempresa> onSelect;

    @InjectScope
    TblempresaMasterDetailScope mdScope;

    @Inject
    TblempresaDao tblempresaDao;

    public void initialize() {
        updateEmpresaList();

        mdScope.selectedEmpresaProperty().bindBidirectional(selectedEmpresa);

        selectedEmpresa.bind(Bindings.createObjectBinding(() -> {
            if (selectedTableRow.get() == null) {
                return null;
            } else {
                return tblempresaDao.findById(selectedTableRow.get().getId()).orElse(null);
            }
        }, selectedTableRow));

    }

    private void updateEmpresaList() {
        LOG.debug("update empresa list");
        
        final Integer selectedEmpresaId = (selectedTableRow.get() == null) ? null : selectedTableRow.get().getId();

        Set<Tblempresa> allEmpresas = new HashSet<>(tblempresaDao.getAll());

        empresas.clear();
        empresas.addAll(allEmpresas);

        if (selectedEmpresaId != null) {
            Optional<Tblempresa> selectedRow = empresas.stream()
                    .filter(row -> row.getId().equals(selectedEmpresaId))
                    .findFirst();

            Optional.of(onSelect).ifPresent(consumer -> consumer.accept(selectedRow.orElse(null)));
        }
    }

    public void refresh() {
        mdScope.publish(mdScope.UPDATE);
    }

    public ObservableList<Tblempresa> getEmpresas() {
        return empresas;
    }
    public void setOnSelect(Consumer<Tblempresa> consumer) {
        onSelect = consumer;
    }
    public ObjectProperty<Tblempresa> selectedTableRowProperty() {
        return selectedTableRow;
    }

}
