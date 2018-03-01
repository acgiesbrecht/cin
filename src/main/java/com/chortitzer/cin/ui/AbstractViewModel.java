package com.chortitzer.cin.ui;

import com.chortitzer.cin.model.dao.AbstractDao;
import com.chortitzer.cin.model.dao.bascula.TblContribuyentesDao;
import com.chortitzer.cin.utils.InformationDialog;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import javax.inject.Inject;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class AbstractViewModel<T> implements ViewModel {

    public final ObservableList<T> itemsList = FXCollections.observableArrayList();
    public final ObjectProperty<T> selectedItem = new SimpleObjectProperty<>();
    public ModelWrapper<T> itemWrapper = new ModelWrapper<>();
    public final ObjectProperty<T> selectedTableRow = new SimpleObjectProperty<>();
    public Consumer<T> onSelect;
    private BooleanProperty loadingInProgressProperty = new SimpleBooleanProperty();

    @Inject
    TblContribuyentesDao tblContribuyentesDao;
    //Class<T> t;
    private AbstractDao<T> dao;

    @Inject
    public InformationDialog dialog;

    public AbstractDao<T> getDao() {
        return dao;
    }

    public void setDao(AbstractDao<T> dao) {
        this.dao = dao;
    }

    private Executor exec;

    public void updateItemsList() {

        itemsList.clear();
        Task<ObservableList<T>> task = new Task<ObservableList<T>>() {
            @Override
            protected ObservableList<T> call() throws Exception {
                ObservableList<T> list = FXCollections.observableArrayList(dao.findAll());
                return list;
            }
        };
        task.setOnFailed(e->{task.getException().printStackTrace();});
        task.setOnSucceeded(e -> {itemsList.setAll(task.getValue());});
        loadingInProgressProperty().bind(task.runningProperty());
        exec.execute(task);
    }

    public void initializeAbstract() {
        exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });
        updateItemsList();

        // create executor that uses daemon threads:


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
            updateItemsList();
        } catch (Exception ex) {
            dialog.showAlert(ex.getMessage());
        }
    }

    public void setOnSelect(Consumer<T> consumer) {
        onSelect = consumer;
    }

    public void add(T t) {
        try {
            itemWrapper.set(t);
            itemWrapper.reload();
            itemsList.add(itemWrapper.get());
        } catch (Exception ex) {
            ex.printStackTrace();
            dialog.showAlert(ex.getMessage());
        }
    }

    public void save() {
        itemWrapper.commit();
        dao.persist(itemWrapper.get());
        //updateItemsList();
    }

    public void delete() {
        if (dialog.showConfirmation("¿Realmente desea eliminar el item seleccionada?", false)) {
            dao.remove(itemWrapper.get());
            itemsList.remove(itemWrapper.get());
            //updateItemsList();
        }
    }

    public BooleanProperty loadingInProgressProperty() {
        return this.loadingInProgressProperty;
    }

    public void setLoadingInProgress(Boolean loadingInProgress) {
        this.loadingInProgressProperty.set(loadingInProgress);
    }

    public Boolean getLoadingInProgress() {
        return loadingInProgressProperty.get();
    }

    public String getContribuyenteRazonSocial(String ruc) {
        try {
            return tblContribuyentesDao.findByRuc(ruc).getRazonSocial();
        } catch (Exception ex) {
            return "";
        }
    }

}
