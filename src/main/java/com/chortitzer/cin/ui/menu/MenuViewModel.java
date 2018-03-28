package com.chortitzer.cin.ui.menu;

import com.chortitzer.cin.model.admin.TblUsuariosCin;
import com.chortitzer.cin.model.dao.admin.TblUsuariosCinDao;
import com.chortitzer.cin.utils.EnumRoles;
import de.saxsys.mvvmfx.ViewModel;

import javax.inject.Inject;
import java.util.List;

public class MenuViewModel implements ViewModel {

    @Inject
    TblUsuariosCinDao tblUsuariosCinDao;

    TblUsuariosCin currentUsuario;

    public void initialize() {

        List<TblUsuariosCin> tblUsuariosCinList = tblUsuariosCinDao.findAll();
        tblUsuariosCinList.stream().forEach(m -> {
            try {
                if (m.getNombreUsuario().matches(System.getProperty("user.name"))) {
                    currentUsuario = m;
                }
            } catch (Exception ex) {
                currentUsuario = null;
            }
        });
    }


    public Boolean hasRole(EnumRoles rol) {
        if (currentUsuario != null) {
            return currentUsuario.getRoles().toUpperCase().contains(rol.toString());
        } else {
            return false;
        }

    }

}
