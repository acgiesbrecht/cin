package com.chortitzer.cin.bas.precioscontratos.utils;

import javax.print.*;

public class Utils {

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

}
