/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Márcio Junior
 */
public class FXMLVBoxMainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Menu CadastrosAlunosCursosTurmas;
    
    @FXML
    private Menu MenuNotas;
    
    @FXML
    private Menu MenuGraficos;
    
    @FXML
    private Menu MenuRelatórios;
    
   @FXML
   private Menu MenuThreadseSockets ;
    
    @FXML
    private MenuItem menuItemCadastroCursos;

    @FXML
    private MenuItem menuItemCadastroTurmas;

    @FXML
    private MenuItem menuItemCadastroAlunos;

    @FXML
    private MenuItem menuItemNotas;

    @FXML
    private MenuItem menuItemGraficos;

    @FXML
    private MenuItem menuItemRelatorios;
    
    @FXML
    private MenuItem menuItemNoticias;

    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void showFXMLAnchorPaneCadastroCursos() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLCadastroCurso.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    public void showFXMLAnchorPaneCadastroTurmas() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLCadastroTurma.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    public void showFXMLAnchorPaneCadastroAlunos() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLCadastroAluno.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    public void showFXMLAnchorPaneFXMLNotas() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLNotas.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    public void showFXMLAnchorPaneFXMLGraficos() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLGraficosAlunos.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    public void showFXMLAnchorPaneFXMLRelatorios() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLRelatorioTurmasECursosCadastrados.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void showFXMLAnchorPaneFXMLNoticiasThreadsESockets() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLThreadsSockets.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
     @FXML
    public void showFXMLAnchorPaneFXMLWebServices() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLWebServices.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    
    
}
