package com.chortitzer.cin.bas.precioscontratos.ui;

import com.chortitzer.cin.bas.precioscontratos.App;
import com.chortitzer.cin.bas.precioscontratos.model.dao.AbstractDao;
import com.chortitzer.cin.bas.precioscontratos.utils.Dialog;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressIndicator;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

public class AbstractViewModel<T> implements ViewModel {

    public final ObservableList<T> itemsList = FXCollections.observableArrayList();
    public final ObjectProperty<T> selectedItem = new SimpleObjectProperty<>();
    public ModelWrapper<T> itemWrapper = new ModelWrapper<>();
    public final ObjectProperty<T> selectedTableRow = new SimpleObjectProperty<>();
    public Consumer<T> onSelect;
    private BooleanProperty loadingInProgressProperty = new SimpleBooleanProperty();

    Class<T> t;
    private AbstractDao<T> dao;

    @Inject
    public Dialog dialog;

    public AbstractDao<T> getDao() {
        return dao;
    }

    public void setDao(AbstractDao<T> dao) {
        this.dao = dao;
    }

    public void updateItemsList() {
        itemsList.clear();
        Service<List<T>> service = new Service<List<T>>() {
            @Override
            protected Task<List<T>> createTask() {
                return new Task<List<T>>() {
                    @Override
                    protected List<T> call() throws Exception {
                        return dao.findAll();
                    }
                };
            }
        };
        loadingInProgressProperty().bind(service.runningProperty());
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                itemsList.addAll(service.getValue());   //here you get the return value of your service
            }
        });
        service.restart();
    }

    public void initializeAbstract() {
        updateItemsList();
        selectedItemProperty().addListener(new ChangeListener<T>() {
                                               @Override
                                               public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
                                                   itemWrapper.set(selectedItemProperty().get());
                                               }
                                           }
        );
    }

    public T getSelectedItem() {
        return selectedItem.get();
    }

    public void setSelectedItem(T selectedItem) {
        this.selectedItem.set(selectedItem);
    }

    public ObjectProperty<T> selectedItemProperty() {
        return selectedItem;
    }

    public ReadOnlyBooleanProperty differentProperty() {
        return itemWrapper.differentProperty();
    }

    public ObservableList<T> getItems() {
        return itemsList;
    }

    public void reset() {
        try {
            itemWrapper.reload();
        } catch (Exception ex) {
            dialog.showAlert(ex.getMessage());
        }
    }

    public void setOnSelect(Consumer<T> consumer) {
        onSelect = consumer;
    }

    public void add() {
        try {
            itemWrapper.set(t.newInstance());
            itemWrapper.reload();
        } catch (Exception ex) {
            dialog.showAlert(ex.getMessage());
        }
    }

    public void save() {
        itemWrapper.commit();
        dao.persist(itemWrapper.get());
        updateItemsList();
    }

    public void delete() {
        if (dialog.showConfirmation("¿Realmente desea eliminar el contrato seleccionada?", false)) {
            dao.remove(itemWrapper.get());
            updateItemsList();
        }
    }

    public BooleanProperty loadingInProgressProperty() { return this.loadingInProgressProperty; }
    public void setLoadingInProgress(Boolean loadingInProgress){ this.loadingInProgressProperty.set(loadingInProgress); }
    public Boolean getLoadingInProgress(){ return loadingInProgressProperty.get(); }

}
