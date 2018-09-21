package com.chortitzer.cin.model.fps;

import java.util.ArrayList;
import java.util.List;

public class EnvasesModel {

    private String descripcion;
    private Double pesoNeto;
    private Double pesoTara;

    public static List<EnvasesModel> getList() {
        List<EnvasesModel> envasesList = new ArrayList<>();
        EnvasesModel envasesModel = new EnvasesModel();

        envasesModel.setDescripcion("Tambor Metalico - 190 Kg - 15.5Kg Tara");
        envasesModel.setPesoNeto(190.0);
        envasesModel.setPesoTara(15.5);
        envasesList.add(envasesModel);

        envasesModel = new EnvasesModel();
        envasesModel.setDescripcion("Tambor Metalico - 190 Kg - 18.6Kg Tara");
        envasesModel.setPesoNeto(190.0);
        envasesModel.setPesoTara(18.6);
        envasesList.add(envasesModel);

        envasesModel = new EnvasesModel();
        envasesModel.setDescripcion("Tambor Metalico - 50 Kg");
        envasesModel.setPesoNeto(50.0);
        envasesModel.setPesoTara(5.8);
        envasesList.add(envasesModel);

        envasesModel = new EnvasesModel();
        envasesModel.setDescripcion("Balde Metalico - 24 Kg");
        envasesModel.setPesoNeto(24.0);
        envasesModel.setPesoTara(2.3);
        envasesList.add(envasesModel);

        envasesModel = new EnvasesModel();
        envasesModel.setDescripcion("Bidon de Plastico - 25 Kg");
        envasesModel.setPesoNeto(25.0);
        envasesModel.setPesoTara(1.6);
        envasesList.add(envasesModel);

        return envasesList;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(Double pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public Double getPesoTara() {
        return pesoTara;
    }

    public void setPesoTara(Double pesoTara) {
        this.pesoTara = pesoTara;
    }

    public String toString() {
        return descripcion;
    }
}
