package com.chortitzer.cin.utils;

import com.chortitzer.cin.model.dao.bascula.TblBasTimbradosRucDao;
import com.chortitzer.cin.model.dao.bascula.TblContribuyentesDao;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.print.*;
import java.util.function.Predicate;

@Singleton
public class Utils {

    @Inject
    TblContribuyentesDao tblContribuyentesDao;
    @Inject
    TblBasTimbradosRucDao tblBasTimbradosRucDao;

    public static void rawPrint(String zplCommand) {
        try {
            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
            PrintService printService = null;

            for (int i = 0; i < services.length; i++) {
                if (services[i].getName().equals("ZDesigner ZM400 200 dpi (ZPL)")) {
                    printService = services[i];
                }
            }
            if (printService != null) {

// convertimos el comando a bytes
                byte[] by = zplCommand.getBytes();
                DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
                Doc doc = new SimpleDoc(by, flavor, null);

// creamos el printjob
                DocPrintJob job = printService.createPrintJob();

// imprimimos
                job.print(doc, null);

            } else {
                System.out.println("Printer not found!");
            }

        } catch (PrintException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Predicate<String> rucPredicate = new Predicate<String>() {
        @Override
        public boolean test(String ruc) {
            try {
                String base = ruc.substring(0, ruc.length() - 2);
                String dv = ruc.substring(ruc.length() - 1, ruc.length());
                int dv_calc = Pa_Calcular_Dv_11_A(base, 11);
                int dv_prov = Integer.parseInt(dv);
                return (dv_calc == dv_prov);
            } catch (Exception ex) {
                return false;
            }
        }
    };

    public String getContribuyenteRazonSocial(String ruc) {
        if (rucPredicate.test(ruc)) {
            try {
                return tblContribuyentesDao.findByRuc(ruc).getRazonSocial();
            } catch (Exception ex) {
                return "";
            }
        }
        return "";
    }

    private static Integer Pa_Calcular_Dv_11_A(String p_numero, Integer p_basemax) {
        Integer v_total, v_resto, k, v_numero_aux, v_digit;
        String v_numero_al = "";

        for (Integer i = 0; i < p_numero.length(); i++) {
            char c = p_numero.charAt(i);
            if (Character.isDigit(c)) {
                v_numero_al += c;
            } else {
                v_numero_al += (int) c;
            }
        }

        k = 2;
        v_total = 0;

        for (Integer i = v_numero_al.length() - 1; i >= 0; i--) {
            k = k > p_basemax ? 2 : k;
            v_numero_aux = v_numero_al.charAt(i) - 48;
            v_total += v_numero_aux * k++;
        }

        v_resto = v_total % 11;
        v_digit = v_resto > 1 ? 11 - v_resto : 0;

        return v_digit;
    }

    public String getRucFromTimbrado(Integer timbrado) {
        if (timbrado > 10000000) {
            try {
                return tblBasTimbradosRucDao.findRucByTimbrado(timbrado);
            } catch (Exception ex) {
                return "";
            }
        }
        return "";
    }

}
