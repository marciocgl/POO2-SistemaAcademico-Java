/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author Márcio Junior
 */
public class FXMLWebServicesController implements Initializable {

    @FXML
    private Label labelWebServicesNoticias;

    public List<String> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        try {
            Client client = (Client) ClientBuilder.newClient();
            WebTarget target = client.target("https://ifes-poo2-trabalho5.herokuapp.com/noticias");

            String arrayNoticias = target.request().get(String.class);

            ObjectMapper mapper = new ObjectMapper();
            list = mapper.readValue(arrayNoticias, new TypeReference<List<String>>() {
            });

            list.forEach((frase) -> labelWebServicesNoticias.setText(labelWebServicesNoticias.getText()
                    + frase + "\n"));

        } catch (IOException ex) {
            Logger.getLogger(FXMLWebServicesController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
}
 
    

