package com.chortitzer.cin.ui.fba.etiquetas;

import com.chortitzer.cin.model.dao.fba.FormulasDao;
import com.chortitzer.cin.model.dao.fba.TblEtiquetadoraContenidoDao;
import com.chortitzer.cin.model.dao.fba.TblProductoxConvertidoresDao;
import com.chortitzer.cin.model.fba.Formulas;
import com.chortitzer.cin.model.fba.TblEtiquetadoraContenido;
import com.chortitzer.cin.ui.AbstractViewModel;
import com.chortitzer.cin.utils.Utils;
import com.chortitzer.cin.utils.ZplToPng;
import com.google.common.base.Joiner;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import javax.inject.Inject;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FbaEtiquetasViewModel extends AbstractViewModel<Formulas> {

    String nombreAnimalDestino = "PARA ";
    String senacsaReg;
    String pesoNeto;
    String lote;
    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter dfs = DateTimeFormatter.ofPattern("yyMMdd");
    DecimalFormat myFormatter = new DecimalFormat("#.00");
    DecimalFormat myFormatterInt = new DecimalFormat("#");
    Formulas formulaActual;
    String indiciacionesDeUso = "INDICACIONES:\\&";
    String ingredientes = "INGREDIENTES:\\&";
    String nivelesDeGarantia = "NIVELES DE GARANTIA:\\&";
    String descripcion;
    String pb = "";
    String ed = "";
    String pbTC = "";
    String pbBS = "";
    String edTC = "";
    String edBS = "";
    String humedadTC = "";
    String grasaTC = "";
    String fibraTC = "";
    String cenizasTC = "";
    String calcio = "";
    String fosforo = "";
    String vitATC = "";
    String vitDTC = "";
    String vitETC = "";
    String NNP = "";
    String S = "";
    String Mg = "";
    String Na = "";
    String Co = "";
    String Cu = "";
    String Se = "";
    String Mn = "";
    String Zn = "";
    String Y = "";
    String Fe = "";
    String Cr = "";
    String secuencia = "";

    TblEtiquetadoraContenido contenido;

    StringProperty loteProperty = new SimpleStringProperty();
    StringProperty lineaProperty = new SimpleStringProperty();
    StringProperty turnoProperty = new SimpleStringProperty();
    StringProperty cantidadProperty = new SimpleStringProperty();
    StringProperty pesoProperty = new SimpleStringProperty();

    StringProperty indicacionesProperty = new SimpleStringProperty();
    StringProperty ingredientesProperty = new SimpleStringProperty();
    StringProperty nivelesProperty = new SimpleStringProperty();
    StringProperty senacsaRegProperty = new SimpleStringProperty();
    ObjectProperty<LocalDateTime> fechaElaboracion = new SimpleObjectProperty<>();
    ObjectProperty<LocalDateTime> fechaVencimiento = new SimpleObjectProperty<>();

    ObjectProperty<Image> image = new SimpleObjectProperty<>();

    @Inject
    FormulasDao formulasDao;
    private final ObservableList<Formulas> formulasList = FXCollections.observableArrayList();

    @Inject
    TblEtiquetadoraContenidoDao tblEtiquetadoraContenidoDao;

    @Inject
    TblProductoxConvertidoresDao tblProductoxConvertidoresDao;

    public void initialize() {
        setDao(formulasDao);
        initializeAbstract();
        selectedItem.addListener((observableValue, o, n) -> {
            contenido = tblEtiquetadoraContenidoDao.findByFormula(selectedItem.get().getNroID());

            reset();

            indicacionesProperty.set(contenido.getModoDeUso());
            ingredientesProperty.set(Joiner.on(", ").join(tblProductoxConvertidoresDao.findByFormula(selectedItem.get().getNroID()).toArray()));
            senacsaRegProperty.set(contenido.getRegistroMag());

            humedadTC = String.valueOf(1000 - contenido.getHumedad());
            pb = myFormatterInt.format(contenido.getProteina());
            pbTC = myFormatter.format(contenido.getProteina() / 10);
            pbBS = myFormatter.format(contenido.getProteina() * 10.0 / (100.0 - contenido.getHumedad() / 10.0));
            ed = myFormatterInt.format(contenido.getEnergiaDigestible());
            edTC = myFormatterInt.format(contenido.getEnergiaDigestible() * 100 * (4.4 / 100));
            edBS = myFormatterInt.format(contenido.getEnergiaDigestible() * 100 * (4.4 / 100) * 100 / (100 - contenido.getHumedad() / 10));

            fechaElaboracion.set(LocalDateTime.now());

            if (contenido.getCategoria().equals("B")) {
                nivelesProperty.set(getNivelesBalanceados());
                fechaVencimiento.set(fechaElaboracion.get().plusMonths(3));
            } else if (contenido.getCategoria().equals("S")) {
                nivelesProperty.set(getNivelesSales());
                fechaVencimiento.set(fechaElaboracion.get().plusMonths(6));
            }

        });
    }

    @Override
    public void reset() {
        lineaProperty.set("1");
        turnoProperty.set("1");
        pesoProperty.set("25");
        if (loteProperty.get() == null) {
            loteProperty.set("1");
        } else {
            loteProperty.set(((Integer) (Integer.parseInt(loteProperty.get()) + 1)).toString());
        }
        cantidadProperty.set("1");
        image.set(null);
    }

    private String getNivelesBalanceados() {
        grasaTC = myFormatterInt.format(contenido.getGrasa());
        fibraTC = myFormatterInt.format(contenido.getFda());
        cenizasTC = myFormatterInt.format(contenido.getMineralesTotales());
        calcio = myFormatterInt.format(contenido.getCalcio());
        fosforo = myFormatterInt.format(contenido.getFosforo());
        vitATC = myFormatterInt.format(contenido.getVitA());
        vitDTC = myFormatterInt.format(contenido.getVitD());
        vitETC = myFormatterInt.format(contenido.getVitE());

        return ("Materia Seca: " + humedadTC
                + " g/Kg, Nutrientes Digestibles Totales: " + ed
                + " g/Kg, Proteina: " + pb
                + " g/Kg, Grasas: " + grasaTC
                + " g/Kg, FDA: " + fibraTC
                + " g/Kg, Minerales totales: " + cenizasTC
                + " g/Kg, Calcio: " + calcio
                + " g/Kg, Fosforo: " + fosforo
                + " mg/Kg, Vitamina A: " + vitATC
                + " UI/Kg, Vitamina D: " + vitDTC
                + " UI/Kg, Vitamina E: " + vitETC + " UI/Kg.");
    }

    private String getNivelesSales() {
        grasaTC = myFormatterInt.format(contenido.getGrasa());
        fibraTC = myFormatterInt.format(contenido.getFda());
        cenizasTC = myFormatterInt.format(contenido.getMineralesTotales());
        calcio = myFormatterInt.format(contenido.getCalcio());
        fosforo = myFormatterInt.format(contenido.getFosforo());
        NNP = myFormatterInt.format(contenido.getNnp());
        S = myFormatterInt.format(contenido.getS());
        Mg = myFormatterInt.format(contenido.getMg());
        Na = myFormatterInt.format(contenido.getNa());
        Co = myFormatterInt.format(contenido.getCo());
        Cu = myFormatterInt.format(contenido.getCu());
        Se = myFormatterInt.format(contenido.getSe());
        Mn = myFormatterInt.format(contenido.getMn());
        Zn = myFormatterInt.format(contenido.getZn());
        Y = myFormatterInt.format(contenido.getY());
        Fe = myFormatterInt.format(contenido.getFe());
        Cr = myFormatterInt.format(contenido.getCr());

        return ("Materia Seca: " + humedadTC + " g/Kg"
                + "Nutrientes Digestibles Totales: " + ed + " g/Kg"
                + "Proteina: " + pb + " g/Kg"
                + "Grasas: " + grasaTC + " g/Kg"
                + "Calcio: " + calcio + " g/Kg"
                + "Fosforo: " + fosforo);
    }

    public void print() {
        if (contenido.getCategoria().equals("B")) {
            Utils.rawPrint(getZplBalanceados());
        } else if (contenido.getCategoria().equals("S")) {
            Utils.rawPrint(getZplSales());
        }
    }

    public String getZplBalanceados() {

        loadVariables();

        String base = "^XA"
                + "^MTD"
                + "^PW799"
                + "^CI27"
                + "^FO150,30^GB625,1275,2^FS" //cuadro principal
                + "^FO150,30^GB625,1100,2^FS" //cuadro principal int
                + "^FO455,30^GB0,1100,2^FS" // 1era horiz
                + "^FO610,30^GB0,1100,2^FS" // 2da horiz
                + "^FO610,393^GB165,0,2^FS"
                + "^FO610,767^GB165,0,2^FS"
                + "^FO25,1145^XGE:LC100.GRF,1,1^FS"
                + "^FO25,55^XGE:LB100.GRF,1,1^FS"
                + "^FO185,1150 ^BY2 ^BCN,100,Y,N,A ^FD" + secuencia + "0001^SFAAAAAAAAAAAAAAAdddd,1^FS"
                + "^FO470,50^XGE:" + contenido.getAnimalDestino().toUpperCase() + ".GRF,1,1^FS"
                + "^FT100,1315^A0B,71,70^FB1315,1,0,C^FH\\^FD" + selectedItem.get().getNombre() + "^FS"
                + "^FT140,1315^A0B,28,28^FB1315,1,0,C^FH\\^FD" + nombreAnimalDestino + senacsaReg + "^FS"
                + "^FT180,1115^A0B,20,19^FB1020,1,0,L^FH\\^FD" + "INDICACIONES:" + "^FS"
                + "^FT252,1115^A0B,20,19^FB1020,3,0,L^FH\\^FD" + indicacionesProperty.get() + "^FS"
                + "^FT282,1115^A0B,20,19^FB1020,1,0,L^FH\\^FD" + "COMPOSICION:" + "^FS"
                + "^FT352,1115^A0B,20,19^FB1020,3,0,L^FH\\^FD" + ingredientesProperty.get() + "^FS"
                + "^FT384,1115^A0B,20,19^FB1020,1,0,L^FH\\^FD" + "NIVELES DE GARANTIA:" + "^FS"
                + "^FT456,1115^A0B,20,19^FB1020,3,0,L^FH\\^FD" + nivelesProperty.get() + "^FS"
                + "^FT549,1115^A0B,39,38^FH\\^FDProteina Bruta (%):^FS"
                + "^FT597,1115^A0B,39,38^FH\\^FDEnergia Dig. (Kcal/Kg):^FS"
                + "^FT498,656^A0B,28,28^FB200,1,0,R^FH\\^FDBase natural^FS"
                + "^FT498,831^A0B,28,28^FB200,1,0,R^FH\\^FDBase seca^FS"
                + "^FT549,656^A0B,39,38^FB200,1,0,R^FH\\^FD" + pbTC + "^FS"
                + "^FT597,656^A0B,39,38^FB200,1,0,R^FH\\^FD" + edTC + "^FS"
                + "^FT549,831^A0B,39,38^FB200,1,0,R^FH\\^FD" + pbBS + "^FS"
                + "^FT597,831^A0B,39,38^FB200,1,0,R^FH\\^FD" + edBS + "^FS"
                + "^FT561,440^A0B,16,15^FH\\^FD*Calculo Estimativa ED:^FS"
                + "^FT579,440^A0B,16,15^FH\\^FDNDT=PB+(EE*2,25)+ENN^FS"
                + "^FT597,440^A0B,16,15^FH\\^FDED(Kcal/Kg)=NDT*4.409^FS"
                + "^FT658,1115^A0B,28,28^FH\\^FDFecha Elab.: " + fechaElaboracion.get().format(df) + "^FS"
                + "^FT692,1115^A0B,28,28^FH\\^FDFecha Venc.: " + fechaVencimiento.get().format(df) + "^FS"
                + "^FT726,1115^A0B,28,28^FH\\^FDLote: " + lote + "  Linea: " + lineaProperty.get() + "  Turno: " + turnoProperty.get() + "^FS"
                + "^FT760,1115^A0B,28,28^FH\\^FDSecuencia: 0001^SFAAAAAAAAAAAAAAAdddd,1^FS"
                + "^FT685,767^A0B,51,50^FB374,1,0,C^FH\\^FD" + pesoNeto + "^FS"
                //+ "^FT730,767^A0B,20,19^FB374,1,0,C^FH\\^FD" + senacsaReg + "^FS"
                + "^FT730,767^A0B,20,19^FB374,1,0,C^FH\\^FDDr. med. vet. Kornelius Kaethler^FS"
                + "^FT754,767^A0B,20,19^FB374,1,0,C^FH\\^FDRegente Tecnico MAG Nro.: 1979^FS"
                + "^FT643,370^A0B,24,23^FB320,1,0,R^FH\\^FDCooperativa Chortitzer Ltda.^FS"
                + "^FT671,370^A0B,24,23^FB320,1,0,R^FH\\^FDHabilitacion SENACSA Nro. 832^FS"
                + "^FT699,370^A0B,24,23^FB320,1,0,R^FH\\^FDTel.: (0492) 418 605^FS"
                + "^FT727,370^A0B,24,23^FB320,1,0,R^FH\\^FDAvda. Central 926 - Loma Plata^FS"
                + "^FT755,370^A0B,24,23^FB320,1,0,R^FH\\^FDINDUSTRIA PARAGUAYA^FS"
                + "^PQ" + cantidadProperty.get() + ",0,1,Y"
                + "^XZ";
        return base;

    }


    void loadVariables() {
        DecimalFormat myFormatterLote = new DecimalFormat("000");
        lote = myFormatterLote.format(Integer.parseInt(loteProperty.get()));

        DecimalFormat myFormatterCodigo = new DecimalFormat("000000000");
        String codigoFormula = myFormatterCodigo.format(Integer.parseInt(selectedItem.get().getCodigo().replace(".","")));

        pesoNeto = "Peso Neto: " + pesoProperty.get() + " Kg";
        if (pesoProperty.get().equals("Granel")) {
            pesoNeto = pesoProperty.get();
        }

        senacsaReg = " - Reg. SENACSA: " + senacsaRegProperty.get();
        if (senacsaRegProperty.get().equals("XXX")) {
            senacsaReg = "";
        }


        switch (contenido.getAnimalDestino().toUpperCase()) {
            case "BOVINO":
                nombreAnimalDestino += "BOVINOS";
                break;
            case "CABALLO":
                nombreAnimalDestino += "EQUINOS";
                break;
            case "AVE":
                nombreAnimalDestino += "AVES";
                break;
            case "OVINO":
                nombreAnimalDestino += "OVINOS";
                break;
            case "SUINO":
                nombreAnimalDestino += "SUINOS";
                break;
            default:
                nombreAnimalDestino = "";
                break;
        }

        secuencia = LocalDateTime.now().format(dfs) + codigoFormula + lote;

    }

    public String getZplSales() {

        loadVariables();

        String base = "^XA"
                + "^MTD"
                + "^PW799"
                + "^CI27"
                + "^FO15,180^GB775,920,2^FS" //cuadro principal
                + "^FO15,390^GB775,0,2^FS" // 1era horiz
                + "^FO15,940^GB775,0,2^FS" // 2da horiz
                + "^FO412,390^GB0,710,2^FS" // 1era vertical
                + "^FO25,30^XGE:LB100N.GRF,1,1^FS"
                + "^FT200,100^A0N,71,70^FB600,1,0,C^FD" + selectedItem.get().getNombre() + "^FS"
                + "^FT200,130^A0N,28,27^FB600,1,0,C^FH\\^FD" + contenido.getNombreCompleto() + "^FS"
                + "^FT200,165^A0N,25,24^FB600,1,0,C^FH\\^FD" + nombreAnimalDestino + senacsaReg + "^FS"

                + "^FT25,205^A0N,20,19^FB755,1,0,L^FH\\^FD" + "INDICACIONES:" + "^FS"
                + "^FT25,301^A0N,20,19^FB755,4,0,L^FH\\^FD" + indicacionesProperty.get() + "^FS"
                + "^FT25,331^A0N,20,19^FB755,1,0,L^FH\\^FD" + "COMPOSICION:" + "^FS"
                + "^FT25,379^A0N,20,19^FB755,2,0,L^FH\\^FD" + ingredientesProperty.get() + "^FS"

                + "^FT25,420^A0N,20,19^FB402,1,0,L^FH\\^FD" + "NIVELES DE GARANTIA:" + "^FS"
                + "^FT25,450^A0N,25,24^FH\\^FD" + "Materia Seca" + "^FS"
                + "^FT150,450^A0N,25,24^FB250,1,0,R^FH\\^FD" + humedadTC + " g/Kg" + "^FS"
                + "^FT25,479^A0N,25,24^FH\\^FD" + "N.D.T." + "^FS"
                + "^FT150,479^A0N,25,24^FB250,1,0,R^FH\\^FD" + ed + " g/Kg" + "^FS"
                + "^FT25,508^A0N,25,24^FH\\^FD" + "Proteina Cruda" + "^FS"
                + "^FT150,508^A0N,25,24^FB250,1,0,R^FH\\^FD" + pb + " g/Kg" + "^FS"
                + "^FT25,537^A0N,25,24^FH\\^FD" + "N.N.P. equiv. Prot." + "^FS"
                + "^FT150,537^A0N,25,24^FB250,1,0,R^FH\\^FD" + NNP + " g/Kg" + "^FS"
                + "^FT25,566^A0N,25,24^FH\\^FD" + "Ca " + "^FS"
                + "^FT150,566^A0N,25,24^FB250,1,0,R^FH\\^FD" + calcio + " g/Kg" + "^FS"
                + "^FT25,595^A0N,25,24^FH\\^FD" + "P" + "^FS"
                + "^FT150,595^A0N,25,24^FB250,1,0,R^FH\\^FD" + fosforo + " g/Kg" + "^FS"
                + "^FT25,624^A0N,25,24^FH\\^FD" + "S" + "^FS"
                + "^FT150,624^A0N,25,24^FB250,1,0,R^FH\\^FD" + S + " mg/Kg" + "^FS"
                + "^FT25,653^A0N,25,24^FH\\^FD" + "Mg" + "^FS"
                + "^FT150,653^A0N,25,24^FB250,1,0,R^FH\\^FD" + Mg + " mg/Kg" + "^FS"
                + "^FT25,682^A0N,25,24^FH\\^FD" + "Na" + "^FS"
                + "^FT150,682^A0N,25,24^FB250,1,0,R^FH\\^FD" + Na + " g/Kg" + "^FS"
                + "^FT25,711^A0N,25,24^FH\\^FD" + "Co" + "^FS"
                + "^FT150,711^A0N,25,24^FB250,1,0,R^FH\\^FD" + Co + " mg/Kg" + "^FS"
                + "^FT25,740^A0N,25,24^FH\\^FD" + "Cu" + "^FS"
                + "^FT150,740^A0N,25,24^FB250,1,0,R^FH\\^FD" + Cu + " mg/Kg" + "^FS"
                + "^FT25,769^A0N,25,24^FH\\^FD" + "Se" + "^FS"
                + "^FT150,769^A0N,25,24^FB250,1,0,R^FH\\^FD" + Se + " mg/Kg" + "^FS"
                + "^FT25,798^A0N,25,24^FH\\^FD" + "Mn" + "^FS"
                + "^FT150,798^A0N,25,24^FB250,1,0,R^FH\\^FD" + Mn + " mg/Kg" + "^FS"
                + "^FT25,827^A0N,25,24^FH\\^FD" + "Zn" + "^FS"
                + "^FT150,827^A0N,25,24^FB250,1,0,R^FH\\^FD" + Zn + " mg/Kg" + "^FS"
                + "^FT25,856^A0N,25,24^FH\\^FD" + "Y" + "^FS"
                + "^FT150,856^A0N,25,24^FB250,1,0,R^FH\\^FD" + Y + " mg/Kg" + "^FS"
                + "^FT25,885^A0N,25,24^FH\\^FD" + "Fe" + "^FS"
                + "^FT150,885^A0N,25,24^FB250,1,0,R^FH\\^FD" + Fe + " mg/Kg" + "^FS"
                + "^FT25,914^A0N,25,24^FH\\^FD" + "Cr" + "^FS"
                + "^FT150,914^A0N,25,24^FB250,1,0,R^FH\\^FD" + Cr + " mg/Kg" + "^FS"

                + "^FT422,460^A0N,29,28^FH\\^FDProteina Bruta (%):^FS"
                + "^FT422,500^A0N,29,28^FH\\^FDEnergia Dig. (Kcal/Kg):^FS"
                + "^FT572,420^A0N,20,19^FB200,1,0,R^FH\\^FDBase natural^FS"

                + "^FT572,460^A0N,29,28^FB200,1,0,R^FH\\^FD" + pbTC + "^FS"
                + "^FT572,500^A0N,29,28^FB200,1,0,R^FH\\^FD" + edTC + "^FS"
                + "^FO506,540^XGE:" + contenido.getAnimalDestino().toUpperCase() + "N.GRF,1,1^FS"

                + "^FT422,740^A0N,51,50^FB374,1,0,C^FH\\^FD" + pesoNeto + "^FS"
                + "^FT422,840^A0N,20,19^FB374,1,0,C^FH\\^FDRegente Tecnico MAG Nro.: 1979^FS"
                + "^FT422,864^A0N,20,19^FB374,1,0,C^FH\\^FDDr. med. vet. Kornelius Kaethler^FS"
                + "^FT25,980^A0N,28,28^FH\\^FDFecha Elab.: " + fechaElaboracion.get().format(df) + "^FS"
                + "^FT25,1014^A0N,28,28^FH\\^FDFecha Venc.: " + fechaVencimiento.get().format(df) + "^FS"
                + "^FT25,1048^A0N,28,28^FH\\^FDLote: " + lote + "  Linea: " + lineaProperty.get() + "  Turno: " + turnoProperty.get() + "^FS"
                + "^FT25,1082^A0N,28,28^FH\\^FDSecuencia:0001^SFAAAAAAAAAAAAAAAdddd,1^FS"
                + "^FT412,975^A0N,24,23^FB363,1,0,R^FH\\^FDCooperativa Chortitzer Ltda.^FS"
                + "^FT412,1003^A0N,24,23^FB363,1,0,R^FH\\^FDHabilitacion SENACSA Nro. 832^FS"
                + "^FT412,1031^A0N,24,23^FB363,1,0,R^FH\\^FDAvda. Central 926 - Loma Plata^FS"
                + "^FT412,1059^A0N,24,23^FB363,1,0,R^FH\\^FDTel.: (0492) 418 605^FS"
                + "^FT412,1087^A0N,24,23^FB363,1,0,R^FH\\^FDINDUSTRA PARAGUAYA^FS"
                + "^FO95,1117 ^BY2 ^BCN,100,Y,N,A ^FD" + secuencia + "0001^SFAAAAAAAAAAAAAAAdddd,1^FS"
                + "^PQ" + cantidadProperty.get() + ",0,1,Y"
                + "^XZ";
        return base;

    }

    public void preview() {
        if (contenido.getCategoria().equals("B")) {
            image.set(ZplToPng.getPng(getZplBalanceados()));
        } else if (contenido.getCategoria().equals("S")) {
            image.set(ZplToPng.getPng(getZplSales()));
        }
    }

}
