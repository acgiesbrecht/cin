package com.chortitzer.cin.ui.menu;

import com.chortitzer.cin.model.admin.TblMaquinasCin;
import com.chortitzer.cin.model.dao.admin.TblMaquinasCinDao;
import com.chortitzer.cin.utils.EnumRoles;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;

import javax.inject.Inject;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MenuViewModel implements ViewModel {

    @Inject
    TblMaquinasCinDao tblMaquinasCinDao;

    TblMaquinasCin currentMaquina;

    public void initialize() {

        List<TblMaquinasCin> tblMaquinasCinList = tblMaquinasCinDao.findAll();
        tblMaquinasCinList.stream().forEach(m -> {
            try {
                if (m.getNombreEquipo().toUpperCase().matches(InetAddress.getLocalHost().getHostName().toUpperCase())) {
                    currentMaquina = m;
                }
            } catch (Exception ex) {
                currentMaquina = null;
            }
        });
    }

    List<String> getCurrentIp() {
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            List<String> addresses = new ArrayList<>();
            while (e.hasMoreElements()) {
                NetworkInterface n = (NetworkInterface) e.nextElement();
                Enumeration ee = n.getInetAddresses();
                while (ee.hasMoreElements()) {
                    InetAddress i = (InetAddress) ee.nextElement();
                    addresses.add(i.getHostAddress());
                }
            }
            return addresses;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Boolean hasRole(EnumRoles rol) {
        if (currentMaquina != null) {
            return currentMaquina.getRoles().toUpperCase().contains(rol.toString());
        } else {
            return false;
        }

    }

}
