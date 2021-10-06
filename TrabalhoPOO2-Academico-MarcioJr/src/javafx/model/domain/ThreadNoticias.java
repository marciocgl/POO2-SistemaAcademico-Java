/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.model.domain;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author Márcio Junior
 */
// 35.199.121.110 / 12345.
public class ThreadNoticias implements Runnable {

    private Label label;
    private List<String> list;

    public ThreadNoticias(Label label, List<String> list) {
        this.label = label;
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {

            for (String string : list) {
                Platform.runLater(() -> label.setText(string));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadNoticias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
