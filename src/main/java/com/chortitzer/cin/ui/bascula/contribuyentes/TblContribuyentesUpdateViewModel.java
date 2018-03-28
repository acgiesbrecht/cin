package com.chortitzer.cin.ui.bascula.contribuyentes;

import com.chortitzer.cin.model.dao.bascula.TblContribuyentesDao;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.*;
import javafx.concurrent.Task;
import org.apache.commons.lang.StringEscapeUtils;

import javax.inject.Inject;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.zip.ZipInputStream;

public class TblContribuyentesUpdateViewModel implements ViewModel {

    @Inject
    TblContribuyentesDao tblContribuyentesDao;


    private Executor exec;
    public DoubleProperty progressProperty = new SimpleDoubleProperty();
    public BooleanProperty runningProperty = new SimpleBooleanProperty();
    public StringProperty messageProperty = new SimpleStringProperty();

    public void update() {
        exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });

        Task<Integer> task = new Task<Integer>() {
            @Override
            protected Integer call() {
                try {
                    Connection conn = null;
                    Statement stmt = null;
                    Class.forName("org.postgresql.Driver");
                    //conn = DriverManager.getConnection("jdbc:postgresql://192.168.1.26:5432/industria_bas", "postgres", "123456789");
                    conn = DriverManager.getConnection("jdbc:postgresql://192.168.3.122:5432/industria", "postgres", "123456");
                    stmt = conn.createStatement();
                    stmt.executeUpdate("TRUNCATE TABLE tbl_contribuyentes");

                    String temp = "";
                    Integer count = 0;

                    for (Integer i = 0; i <= 9; i++) {

                        updateMessage("Descargando listado de RUC con terminacion " + String.valueOf(i));
                        URL url = new URL("http://www.set.gov.py/rest/contents/download/collaboration/sites/PARAGUAY-SET/documents/informes-periodicos/ruc/ruc" + String.valueOf(i) + ".zip");
                        ZipInputStream zipStream = new ZipInputStream(url.openStream(), StandardCharsets.UTF_8);
                        zipStream.getNextEntry();

                        Scanner sc = new Scanner(zipStream, "UTF-8");

                        PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO tbl_contribuyentes (razon_social, ruc_sin_dv, dv) VALUES (?, ?, ?)");
                        //StringJoiner joiner = new StringJoiner("");
                        int a = 0;
                        while (sc.hasNextLine() && a <= 50000) {
                            String[] ruc = sc.nextLine().split("\\|");
                            temp = ruc[0] + " - " + ruc[1] + " - " + ruc[2];
                            if (ruc[0].length() > 0 && ruc[1].length() > 0 && ruc[2].length() == 1) {
                                prepStmt.setString(1, StringEscapeUtils.escapeSql(ruc[1]));
                                prepStmt.setString(2, ruc[0]);
                                prepStmt.setString(3, ruc[2]);
                                prepStmt.addBatch();
                                a++;
                                //joiner.add("INSERT INTO MG.TBL_CONTRIBUYENTES VALUES ('" + ruc[0] + "','" + ruc[2] + "', '" + StringEscapeUtils.escapeSql(ruc[1]) + "');");
                                try {
                                    if (a == 50000) {
                                        int[] numUpdates = prepStmt.executeBatch();
                                        a = 0;
                                        updateMessage("Guardando en base");
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                //joiner.add("INSERT INTO tbl_contribuyentes VALUES ('" + StringEscapeUtils.escapeSql(ruc[1]) + "','" + ruc[0] + "','" + ruc[2] + "');");
                                //}
                                updateProgress(count, 1000000);
                                updateMessage("Cantidad de contribuyentes procesada: " + String.format("%,d", count) + " de aprox.." + String.valueOf(1000000));
                                count++;
                            } else {
                                updateMessage(temp);
                            }
                        }

                        updateMessage("Guardando en base...");
                        try {
                            int[] numUpdates = prepStmt.executeBatch();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        //stmt.executeUpdate(joiner.toString());
                        //tblContribuyentesDao.persist(listNew);
                    }
                    updateMessage("Actualizacion existosa");
                    updateProgress(10, 10);
                    if (stmt != null) conn.close();
                    return 1;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
            }
        };
        progressProperty.bind(task.progressProperty());
        runningProperty.bind(task.runningProperty());
        messageProperty.bind(task.messageProperty());
        task.setOnFailed(e -> {
            task.getException().printStackTrace();
        });
        task.setOnSucceeded(e -> {
            progressProperty.unbind();
            progressProperty.setValue(1);
        });
        exec.execute(task);
    }

}
