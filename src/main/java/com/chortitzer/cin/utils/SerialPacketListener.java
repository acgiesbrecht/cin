package com.chortitzer.cin.utils;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import org.apache.commons.lang.math.NumberUtils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SerialPacketListener implements SerialPortPacketListener {

    public StringProperty valueProperty = new SimpleStringProperty();
    private Executor exec;
    private Task<String> task;

    public SerialPacketListener() {
        exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public int getPacketSize() {
        return 6;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        try {
            task = new Task<String>() {
                @Override
                protected String call() {
                    try {
                        byte[] newData = event.getReceivedData();
                        String data = (new String(newData)).trim();
                        if (NumberUtils.isDigits(data)) {
                            valueProperty.setValue(data);
                        } else {
                            valueProperty.setValue("error");
                        }
                        return "";
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return "error";
                    }
                }
            };
            exec.execute(task);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
