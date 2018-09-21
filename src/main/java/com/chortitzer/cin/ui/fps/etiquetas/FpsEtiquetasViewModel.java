package com.chortitzer.cin.ui.fps.etiquetas;

import com.chortitzer.cin.model.fps.EnvasesModel;
import com.chortitzer.cin.utils.Utils;
import com.chortitzer.cin.utils.ZplToPng;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.*;
import javafx.scene.image.Image;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FpsEtiquetasViewModel implements ViewModel {

    StringProperty loteProperty = new SimpleStringProperty();
    ObjectProperty<LocalDateTime> fechaElaboracion = new SimpleObjectProperty<>();

    StringProperty cantidadTamboresProperty = new SimpleStringProperty();
    StringProperty clienteProperty = new SimpleStringProperty();
    StringProperty codigoClienteProperty = new SimpleStringProperty();
    StringProperty ordenCompraProperty = new SimpleStringProperty();
    ObjectProperty<EnvasesModel> envaseProperty = new SimpleObjectProperty<>();
    DoubleProperty pesoNetoProperty = new SimpleDoubleProperty();
    DoubleProperty pesoTaraProperty = new SimpleDoubleProperty();

    ObjectProperty<Image> image = new SimpleObjectProperty<>();


    public void initialize() {
        envaseProperty.addListener((observable, oldValue, newValue) -> {
            pesoNetoProperty.set(newValue.getPesoNeto());
            pesoTaraProperty.set(newValue.getPesoTara());
        });
    }

    public void reset() {
        if (loteProperty.get() == null) {
            loteProperty.set("1");
        } else {
            loteProperty.set(((Integer) (Integer.parseInt(loteProperty.get()) + 1)).toString());
        }
        cantidadTamboresProperty.set("10");
        image.set(null);
    }

    private String getZplSmall() {
        return "^XA"
                + "^MTD"
                + "^PW799"
                + //"^FT64,256^XGE:CCLOGO20.GRF,1,1^FS" +
                //"^FO27,25^GB746,1100,1^FS" +
                "^FT590,60^A0N,30,31^FB220,1,0,L^FH\\^FDLote: " + String.format("%04d", Integer.valueOf(loteProperty.get())) + "^FS"
                + "^FT590,120^A0N,30,31^FB220,1,0,L^FH\\^FDTambor: " + "001^SFAAAAAAAAddd,3" + "^FS"
                + "^FT305,60^A0N,30,31^FB220,1,0,L^FH\\^FDLote: " + String.format("%04d", Integer.valueOf(loteProperty.get())) + "^FS"
                + "^FT305,120^A0N,30,31^FB220,1,0,L^FH\\^FDTambor: " + "002^SFAAAAAAAAddd,3" + "^FS"
                + "^FT15,60^A0N,30,31^FB220,1,0,L^FDLote: " + String.format("%04d", Integer.valueOf(loteProperty.get())) + "^FS"
                + "^FT15,120^A0N,30,31^FB220,1,0,L^FH\\^FDTambor: " + "003^SFAAAAAAAAddd,3" + "^FS"
                + "^PQ" + String.valueOf(Math.round(Integer.valueOf(cantidadTamboresProperty.get()) / 3)) + ",1,0,Y^XZ";
    }

    private String getZpl() {
        String result = "";

        String dFecha = fechaElaboracion.get().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String sCliente = clienteProperty.get().equals("") ? "" : "Costumer: " + clienteProperty.get();
        String sCodigoCliente = codigoClienteProperty.get().equals("") ? "" : "Costumer Code: " + codigoClienteProperty.get();
        String sOrdenCompra = ordenCompraProperty.get().equals("") ? "" : "Purchase Order Nr.: " + ordenCompraProperty.get();

        result += "^XA"
                + "^PW799"
                + "^FT40,180^XGE:CCLOGO20.GRF,1,1^FS"
                //+ "^FT40,180^GB100,100,10^FS"
                + "^FT250,80^A0N,45,44^FB679,1,0,L^FH\\^FDAceite Esencial de Palo Santo^FS"
                + "^FT250,170^A0N,81,80^FB679,1,0,L^FH\\^FDGuaiacwood Oil^FS"
                + "^FT40,200^A0N,41,40^FH\\^FD "
                + "^FT40,250^A0N,51,50^FH\\^FDLot Nr.:^FS"
                + "^FT380,250^A0N,51,50^FB320,1,0,R^FH\\^FD" + loteProperty.get() + "^FS"
                + "^FT40,310^A0N,51,50^FH\\^FDContainer Nr.:^FS"
                + "^FT380,310^A0N,51,50^FB320,1,0,R^FH\\^FD001^SFddd,1^FS"
                + "^FT40,370^A0N,51,50^FH\\^FDProduction Date:^FS"
                + "^FT380,370^A0N,51,50^FB320,1,0,R^FH\\^FD" + dFecha + "^FS"
                + "^FT40,430^A0N,51,50^FH\\^FDGross weight:^FS"
                + "^FT380,430^A0N,51,50^FB320,1,0,R^FH\\^FD" + getAlignedNumber(pesoNetoProperty.get() + pesoTaraProperty.get()) + " Kg^FS"
                + "^FT40,490^A0N,51,50^FH\\^FDTare weight:^FS"
                + "^FT380,490^A0N,51,50^FB320,1,0,R^FH\\^FD" + getAlignedNumber(pesoTaraProperty.get()) + " Kg^FS"
                + "^FT40,550^A0N,51,50^FH\\^FDNet weight:^FS"
                + "^FT380,550^A0N,51,50^FB320,1,0,R^FH\\^FD" + getAlignedNumber(pesoNetoProperty.get()) + " Kg^FS"
                //+ "^FT40,600^A0N,41,40^FH\\^FD "
                + "^FT40,610^A0N,41,40^FH\\^FD" + sCliente + "^FS"
                + "^FT40,660^A0N,41,40^FH\\^FD" + sCodigoCliente + "^FS"
                + "^FT40,710^A0N,41,40^FH\\^FD" + sOrdenCompra + "^FS"
                + "^FT40,760^A0N,28,28^FH\\^FDCAS: 8016-23-7 /\\ EINECS: 289-532-8 /\\ FEMA: 2534^FS"
                + "^FT40,790^A0N,28,28^FH\\^FDREACH: 01-2120138621-63-0014^FS"
                + "^FT590,830^A0N,41,40^FH\\^FDWarning^FS"
                + "^FT549,950^XGE:GH507.GRF,1,1^FS"
                + "^FT659,950^XGE:GH509.GRF,1,1^FS"
                + "^FT40,870^A0N,21,20^FB500,3,0,L^FH\\^FDCauses skin irritation. May cause an allergic skin reaction. Toxic to aquatic life with long lasting effects.^FS"
                + "^FT40,1000^A0N,21,20^FB500,7,0,L^FH\\^FDAvoid breathing dust/fume/gas/mist/vapours/spray. " +
                "Avoid release to the environment." +
                "Wear protective gloves, protective clothing, eye protection, face protection." +
                "If skin irritation or rash occurs: Get medical advice/attention." +
                "Take off contaminated clothing and wash it before reuse." +
                "Collect spillage. Dispose of contents/container to industrial combustion plant. ^FS"
                + "^FT40,1035^A0N,28,28^FH\\^FDManufactured by: COOPERATIVA CHORTITZER LTDA.^FS"
                + "^FT40,1065^A0N,28,28^FH\\^FDAvda. Artigas 2008, Asuncion 883, PARAGUAY^FS"
                + "^FT40,1095^A0N,28,28^FH\\^FDTel.: +595 21 236 2000 - eMail: export@chortitzer.com.py^FS"
                + "^PQ" + cantidadTamboresProperty.get() + ",1,0,Y^XZ";

        /*result += "^XA"
                + "^PW799"
                + "^FT300,200^XGE:CCLOGO20.GRF,1,1^FS"
                + "^FT60,260^A0N,51,50^FB679,1,0,C^FH\\^FDAceite Esencial de Palo Santo^FS"
                + "^FT60,355^A0N,81,80^FB679,1,0,C^FH\\^FDGuaiacwood Oil^FS"
                + "^FT60,400^A0N,41,40^FH\\^FD "
                + "^FT60,450^A0N,51,50^FH\\^FDLot Nr.:^FS"
                + "^FT400,450^A0N,51,50^FB320,1,0,R^FH\\^FD" + loteProperty.get() + "^FS"
                + "^FT60,510^A0N,51,50^FH\\^FDContainer Nr.:^FS"
                + "^FT400,510^A0N,51,50^FB320,1,0,R^FH\\^FD001^SFddd,1^FS"
                + "^FT60,570^A0N,51,50^FH\\^FDProduction Date:^FS"
                + "^FT400,570^A0N,51,50^FB320,1,0,R^FH\\^FD" + dFecha + "^FS"
                + "^FT60,630^A0N,51,50^FH\\^FDGross weight:^FS"
                + "^FT400,630^A0N,51,50^FB320,1,0,R^FH\\^FD" + getAlignedNumber(Double.parseDouble(pesoNetoProperty.get()) + Double.parseDouble(pesoTaraProperty.get())) + " Kg^FS"
                + "^FT60,690^A0N,51,50^FH\\^FDTare weight:^FS"
                + "^FT400,690^A0N,51,50^FB320,1,0,R^FH\\^FD" + getAlignedNumber(Double.parseDouble(pesoTaraProperty.get())) + " Kg^FS"
                + "^FT60,750^A0N,51,50^FH\\^FDNet weight:^FS"
                + "^FT400,750^A0N,51,50^FB320,1,0,R^FH\\^FD" + getAlignedNumber(Double.parseDouble(pesoNetoProperty.get())) + " Kg^FS"
                + "^FT60,800^A0N,41,40^FH\\^FD "
                + "^FT60,850^A0N,41,40^FH\\^FD" + sCliente + "^FS"
                + "^FT60,900^A0N,41,40^FH\\^FD" + sCodigoCliente + "^FS"
                + "^FT60,950^A0N,41,40^FH\\^FD" + sOrdenCompra + "^FS"
                + "^FT60,1005^A0N,28,28^FH\\^FDCAS: 8016-23-7 /\\ EINECS: 289-532-8 /\\ FEMA: 2534^FS"
                + "^FT60,1035^A0N,28,28^FH\\^FDHandling instructions: Dry Charge -/- Room Temperature^FS"
                + "^FT60,1065^A0N,28,28^FH\\^FDManufactured by: COOPERATIVA CHORTITZER LTDA.^FS"
                + "^FT60,1095^A0N,28,28^FH\\^FDCountry of origin: PARAGUAY^FS"
                + "^PQ" + cantidadTamboresProperty.get() + ",1,0,Y^XZ";
*/
        return result;
    }

    private String getAlignedNumber(double num) {
        DecimalFormat df = new DecimalFormat("##0.00");
        String res = df.format(num);
        return "                   ".substring(0, 6 - res.length()) + res;
    }

    public void preview() {
        image.set(ZplToPng.getPng(getZpl()));
    }

    public void imprimirGrande() {
        Utils.rawPrint(getZpl());
    }

    public void imprimirPequeno() {
        Utils.rawPrint(getZplSmall());
    }

}
