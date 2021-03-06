package com.chortitzer.cin.ui.bascula.pesadas;

import com.chortitzer.cin.model.bascula.Tblempresa;
import com.chortitzer.cin.model.bascula.Tblpesadas;
import com.chortitzer.cin.model.bascula.Tblproductos;
import com.chortitzer.cin.ui.AbstractView;
import com.chortitzer.cin.ui.fieldextensions.TableColumnBase;
import com.chortitzer.cin.ui.fieldextensions.TableColumnInteger;
import com.chortitzer.cin.ui.fieldextensions.TableColumnLocalDateTime;
import com.chortitzer.cin.ui.fieldextensions.TextFieldInteger;
import com.chortitzer.cin.utils.tiwulfx.TypeAheadField;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import tornadofx.control.DateTimePicker;

import java.time.LocalDateTime;

public class TblpesadasView extends AbstractView<Tblpesadas> implements FxmlView<TblpesadasViewModel> {

    private DateTimePicker dtpFecha = new DateTimePicker();
    private TextField txtIdRemision = new TextField();
    private TextField txtChapa = new TextField();
    private TypeAheadField<Tblempresa> thfEmpresa = new TypeAheadField<>();
    private TypeAheadField<Tblproductos> thfProducto = new TypeAheadField<>();
    private TextFieldInteger txtBruto = new TextFieldInteger();
    private TextFieldInteger txtTara = new TextFieldInteger();
    private TextFieldInteger txtPrecioPorKg = new TextFieldInteger();
    private Button btnRemision = new Button("Nota de Remision");

    @InjectViewModel
    private TblpesadasViewModel viewModel;

    public void initialize() {

        setViewModel(viewModel);
        initializeAbstract();

        TableColumnInteger<Tblpesadas> col1 = new TableColumnInteger<>("Nº", "id", 60.0);
        TableColumnLocalDateTime<Tblpesadas> col2 = new TableColumnLocalDateTime<>("Fecha/Hora", "fechahora", 160.0);
        TableColumnBase<Tblpesadas, String> col3 = new TableColumnBase<>("Nº Remision", "idRemision", 140.0);
        TableColumnBase<Tblpesadas, String> col4 = new TableColumnBase<>("Chapa", "chapa", 70.0);
        TableColumnBase<Tblpesadas, Tblempresa> col5 = new TableColumnBase<>("Empresa", "empresaid", 350.0);
        TableColumnBase<Tblpesadas, Tblproductos> col6 = new TableColumnBase<>("Producto", "productoid", 200.0);
        TableColumnInteger<Tblpesadas> col7 = new TableColumnInteger<>("Bruto (Kg)", "bruto", 70.0);
        TableColumnInteger<Tblpesadas> col8 = new TableColumnInteger<>("Tara (Kg)", "tara", 70.0);
        TableColumnInteger<Tblpesadas> col9 = new TableColumnInteger<>("Neto (Kg)", "neto", 70.0);
        TableColumnInteger<Tblpesadas> col10 = new TableColumnInteger<>("Precio (PYG/Kg)", "precioGsPorKg", 70.0);

        itemsTable.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10);
        itemsTable.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                viewModel.showTblBasNotasDeRemisionView();
                itemsTable.refresh();
            }
        });

        gridPane.add(new Label("Fecha"), 1, 1);
        gridPane.add(new Label("Remsion"), 1, 2);
        gridPane.add(new Label("Chapa"), 1, 3);
        gridPane.add(new Label("Empresa"), 1, 4);
        gridPane.add(new Label("Producto"), 3, 1);
        gridPane.add(new Label("Bruto (Kg)"), 3, 2);
        gridPane.add(new Label("Tara (Kg)"), 3, 3);
        gridPane.add(new Label("Precio (PYG/Kg)"), 3, 4);

        gridPane.add(dtpFecha, 2, 1);
        gridPane.add(txtIdRemision, 2, 2);
        gridPane.add(txtChapa, 2, 3);
        gridPane.add(thfEmpresa, 2, 4);
        gridPane.add(thfProducto, 4, 1);
        gridPane.add(txtBruto, 4, 2);
        gridPane.add(txtTara, 4, 3);
        gridPane.add(txtPrecioPorKg, 4, 4);
        gridPane.add(btnRemision, 4, 5);

        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(pesada -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (pesada.getId().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (pesada.getChapa().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (pesada.getIdRemision().toString().replace("-", "").toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (pesada.getEmpresaid().getNombre().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else
                    return pesada.getProductoid().getDescripcion().toString().toLowerCase().contains(lowerCaseFilter);
            });
        });

        itemsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, o, n) -> {
            dtpFecha.dateTimeValueProperty().bindBidirectional(viewModel.fechaProperty());
            txtChapa.textProperty().bindBidirectional(viewModel.chapaProperty());
            txtIdRemision.textProperty().bindBidirectional(viewModel.idRemisionProperty());
            thfEmpresa.valueProperty().bindBidirectional(viewModel.empresaProperty());
            thfProducto.valueProperty().bindBidirectional(viewModel.productoProperty());
            txtBruto.textProperty().bindBidirectional(viewModel.brutoProperty(), new NumberStringConverter());
            txtTara.textProperty().bindBidirectional(viewModel.taraProperty(), new NumberStringConverter());
            txtPrecioPorKg.textProperty().bindBidirectional(viewModel.precioProperty(), new NumberStringConverter());
        });

        itemsTable.setRowFactory(tv -> new TableRow<Tblpesadas>() {
            @Override
            public void updateItem(Tblpesadas item, boolean empty) {
                super.updateItem(item, empty);
                getStyleClass().removeAll("pesadas", "pesadas-ndr", "pesadas-ndr-facm", "pesadas-ndr-facf", "pesadas-ndr-facm-facf");
                if (item == null) {
                    setStyle("");
                } else if (item.getIdNotaDeRemision() == null) {
                    getStyleClass().addAll("pesadas");
                } else {
                    if (item.getIdNotaDeRemision().getIdFacturaFlete() != null && item.getIdNotaDeRemision().getIdFacturaMercaderia() != null) {
                        getStyleClass().addAll("pesadas-ndr-facm-facf");
                    } else if (item.getIdNotaDeRemision().getIdFacturaFlete() != null) {
                        getStyleClass().addAll("pesadas-ndr-facf");
                    } else if (item.getIdNotaDeRemision().getIdFacturaMercaderia() != null) {
                        getStyleClass().addAll("pesadas-ndr-facm");
                    } else {
                        getStyleClass().addAll("pesadas-ndr");
                    }
                }
                setVisible(false);
                setVisible(true);
            }
        });

        thfEmpresa.setItems(viewModel.getEmpresas());
        thfProducto.setItems(viewModel.getProductos());

        btnAdd.setOnAction((event) -> {
            viewModel.add(new Tblpesadas());
            dtpFecha.setDateTimeValue(LocalDateTime.now());
            thfEmpresa.requestFocus();
            addAbstract();
        });

        btnRemision.setOnAction((event) -> {
            viewModel.showTblBasNotasDeRemisionView();
            itemsTable.refresh();
        });

        buttonBar.setDisable(true);
    }
}
