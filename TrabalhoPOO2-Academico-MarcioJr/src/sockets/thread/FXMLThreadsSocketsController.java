/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets.thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.model.domain.ThreadNoticias;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Márcio Junior
 */
public class FXMLThreadsSocketsController implements Initializable {
    
    public Label labelThreadseSocketsNoticias;
    public List<String> list;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            Socket socket = new Socket("35.199.121.110",12345);
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            
            list = (List<String>) entrada.readObject();
            
            
            ThreadNoticias threadNoticias = new ThreadNoticias(labelThreadseSocketsNoticias,list);
            Thread thread = new Thread(threadNoticias);
            thread.setDaemon(true);
            thread.start();
            
            
        }catch(IOException | ClassNotFoundException ex){
            Logger.getLogger(ThreadNoticias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
