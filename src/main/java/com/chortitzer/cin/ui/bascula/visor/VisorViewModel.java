package com.chortitzer.cin.ui.bascula.visor;

import com.chortitzer.cin.model.bascula.TblBasPesadas;
import com.chortitzer.cin.model.dao.bascula.TblBasPesadasDao;
import com.chortitzer.cin.model.dao.bascula.TblContribuyentesDao;
import com.chortitzer.cin.ui.AbstractViewModel;
import com.chortitzer.cin.utils.Utils;
import com.fazecast.jSerialComm.SerialPort;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import javax.inject.Inject;
import java.io.InputStream;

public class VisorViewModel extends AbstractViewModel<TblBasPesadas> {

    public BooleanProperty automaticProperty = new SimpleBooleanProperty();
    public StringProperty visorProperty = new SimpleStringProperty();
    @Inject
    TblBasPesadasDao tblBasPesadasDao;
    @Inject
    TblContribuyentesDao tblContribuyentesDao;
    @Inject
    Utils UTILS;
    SerialPort comPort;
    private Timeline lecturaSerial = new Timeline(
            new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    try {
                        InputStream in = comPort.getInputStream();
                        visorProperty.setValue(String.valueOf((char) in.read()));
                        in.close();

                    } catch (Exception e) {
                        dialog.showAlert(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }));

    public void initialize() {
        setDao(tblBasPesadasDao);
        comPort = SerialPort.getCommPorts()[1];
        comPort.setBaudRate(9600);
        comPort.setParity(0);
        comPort.setNumStopBits(1);
        comPort.setNumDataBits(8);
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 400, 0);

        lecturaSerial.setCycleCount(Timeline.INDEFINITE);
        automaticProperty.addListener((ChangeListener) (o, oldVal, newVal) -> {
            try {
                if ((Boolean) newVal) {
                    openPort();
                    lecturaSerial.play();
                } else {
                    lecturaSerial.stop();
                    comPort.closePort();
                }
            } catch (Exception ex) {
                dialog.showAlert(ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

    private void openPort() {
        try {
            comPort.openPort();
            /*comPort.addDataListener(new SerialPortDataListener() {
                @Override
                public int getListeningEvents() {
                    return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                }

                @Override
                public void serialEvent(SerialPortEvent event) {
                    if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                        return;
                    byte[] newData = new byte[comPort.bytesAvailable()];
                    int numRead = comPort.readBytes(newData, newData.length);
                    visorProperty.setValue(String.valueOf(numRead));
                    System.out.println("Read " + numRead + " bytes.");
                }
            });*/
        } catch (Exception ex) {
            ex.printStackTrace();
            dialog.showAlert(ex.getMessage());
            comPort.closePort();
        }
    }

    public StringProperty chapaProperty() {
        return itemWrapper.field("chapa", TblBasPesadas::getChapa, TblBasPesadas::setChapa);
    }


}
