package javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Márcio Junior
 */

//### ANOTAÇÕES DE LEMBRETE####
//Adicionar CSS
//Tratar Notas Acima de 10
//Colocar Formato na média Final

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLVBoxMain.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        Image image = new Image("/javafx/resources/icon.png");
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.setTitle("Sistema Acadêmico");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
