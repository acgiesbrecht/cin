package com.chortitzer.cin.ui.fba.etiquetas;

import javafx.scene.image.Image;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;

public class ZplToPng {

    public static Image getPng(String zpl) {
        Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
        // adjust print density (8dpmm), label width (4 inches), label height (6 inches), and label index (0) as necessary
        WebTarget target = client.target("http://api.labelary.com/v1/printers/8dpmm/labels/4x7/0/");
        Invocation.Builder request = target.request();
        //request.accept("application/pdf"); // omit this line to get PNG images back
        Response response = request.post(Entity.entity(zpl, MediaType.APPLICATION_FORM_URLENCODED));

        if (response.getStatus() == 200) {
            byte[] body = response.readEntity(byte[].class);
            Image img = new Image(new ByteArrayInputStream(body));
            return img;
            //File file = new File("label.png"); // change file name for PNG images
            //Files.write(file.toPath(), body);
        } else {
            String body = response.readEntity(String.class);
            System.out.println("Error: " + body);
            return null;
        }

    }
}
