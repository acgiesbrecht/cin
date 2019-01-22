package com.chortitzer.cin.ui.bascula.visor;

import com.chortitzer.cin.model.bascula.*;
import com.chortitzer.cin.model.dao.bascula.TblBasPersonasDao;
import com.chortitzer.cin.model.dao.bascula.TblBasPesadasDao;
import com.chortitzer.cin.model.dao.bascula.TblContribuyentesDao;
import com.chortitzer.cin.ui.AbstractViewModel;
import com.chortitzer.cin.utils.SerialPacketListener;
import com.chortitzer.cin.utils.Utils;
import com.fazecast.jSerialComm.SerialPort;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Inject;

public class VisorViewModel extends AbstractViewModel<TblContribuyentes> {

    public BooleanProperty automaticProperty = new SimpleBooleanProperty();
    public StringProperty visorProperty = new SimpleStringProperty();
    @Inject
    TblBasPersonasDao tblBasPersonasDao;
    @Inject
    Utils UTILS;
    private final ObservableList<TblBasPersonas> personasList = FXCollections.observableArrayList();

    private SerialPort comPort;
    private SerialPacketListener listener = new SerialPacketListener();

    public void initialize() {
        //setDao(tblContribuyentesDao);
        personasList.addAll(tblBasPersonasDao.findAll());
        comPort = SerialPort.getCommPorts()[3];
        comPort.setBaudRate(9600);
        comPort.setParity(0);
        comPort.setNumStopBits(1);
        comPort.setNumDataBits(8);
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 400, 0);
    }

    public void closePort() {
        comPort.closePort();
        visorProperty.unbind();
    }

    public void openPort() {
        try {
            comPort.openPort();
            comPort.addDataListener(listener);
            visorProperty.bind(listener.valueProperty);
        } catch (Exception ex) {
            ex.printStackTrace();
            dialog.showAlert(ex.getMessage());
            comPort.closePort();
        }
    }

    /*public StringProperty chapaProperty() {
        return itemWrapper.field("chapa", TblBasPesadas::getChapa, TblBasPesadas::setChapa);
    }*/

    public ObservableList<TblBasPersonas> getPersonasList() {
        return personasList;
    }


}
