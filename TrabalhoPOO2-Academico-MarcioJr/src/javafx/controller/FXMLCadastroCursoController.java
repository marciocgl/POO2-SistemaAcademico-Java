package javafx.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.model.dao.CursoDAO;
import javafx.model.database.Database;
import javafx.model.database.DatabaseFactory;
import javafx.model.domain.Curso;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Márcio Junior
 */
public class FXMLCadastroCursoController implements Initializable {

    @FXML
    private TableView<Curso> tableViewCursos;
    @FXML
    private TableColumn<Curso, String> tableColumnCursoNome;
    @FXML
    private TableColumn<Curso, String> tableColumnSiglaCurso;
    @FXML
    private TableColumn<Curso, String> tableColumnCursoDuracao;
    @FXML
    private TableColumn<Curso, String> tableColumnCursoTurno;

    @FXML
    private TextField textFieldCursoNome;
    @FXML
    private TextField textFieldCursoSigla;
    @FXML
    private TextField textFieldCursoDuracao;
    @FXML
    private TextField textFieldCursoTurno;

    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonLimpar;

    private ObservableList<Curso> observableListCursos;
    private List<Curso> listCursos;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final CursoDAO cursoDAO = new CursoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cursoDAO.setConnection(connection);

        carregarTableViewCursos();

        tableViewCursos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewCursos(newValue));
    }

    public void carregarTableViewCursos() {
        tableColumnCursoNome.setCellValueFactory(new PropertyValueFactory<>("nomeCurso"));
        tableColumnSiglaCurso.setCellValueFactory(new PropertyValueFactory<>("siglaCurso"));
        tableColumnCursoDuracao.setCellValueFactory(new PropertyValueFactory<>("duracao"));
        tableColumnCursoTurno.setCellValueFactory(new PropertyValueFactory<>("turno"));

        listCursos = cursoDAO.listar();
        observableListCursos = FXCollections.observableArrayList(listCursos);
        tableViewCursos.setItems(observableListCursos);
    }

    public void selecionarItemTableViewCursos(Curso curso) {
        if (curso != null) {
            textFieldCursoNome.setText(curso.getNomeCurso());
            textFieldCursoSigla.setText(curso.getSiglaCurso());
            textFieldCursoDuracao.setText(curso.getDuracao());
            textFieldCursoTurno.setText(curso.getTurno());

        } else {
            limparTextFields();
        }
    }

    public void limparTextFields() {
        textFieldCursoNome.setText("");
        textFieldCursoSigla.setText("");
        textFieldCursoDuracao.setText("");
        textFieldCursoTurno.setText("");
    }

    public boolean validouTextFields() {
        String errorMessage = "";

        if (textFieldCursoNome.getText() == null || textFieldCursoNome.getText().length() == 0) {
            errorMessage += "Nome do Curso inválido!\n";
        }
        if (textFieldCursoSigla.getText() == null || textFieldCursoSigla.getText().length() == 0) {
            errorMessage += "Sigla do Curso inválida!\n";
        }
        if (textFieldCursoDuracao.getText() == null || textFieldCursoDuracao.getText().length() == 0) {
            errorMessage += "Duracao do Curso inválida!\n";
        }
        if (textFieldCursoTurno.getText() == null || textFieldCursoTurno.getText().length() == 0) {
            errorMessage += "Turno do Curso inválido!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro de Cursos");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }

    public boolean limpouSelecaoTableViewCursos() {
        boolean limpou = tableViewCursos.getSelectionModel().getSelectedItem() == null;
        if (limpou) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Cadastro de Cursos");
            alert.setTitle("Problemas na Inserção do Curso");
            alert.setContentText("Antes de inserir você deve limpar a seleção da Tabela");
            alert.show();
            return false;
        }
    }

    @FXML
    public void handleButtonLimpar() {
        tableViewCursos.getSelectionModel().clearSelection();
        textFieldCursoNome.setText("");
        textFieldCursoSigla.setText("");
        textFieldCursoDuracao.setText("");
        textFieldCursoTurno.setText("");
    }

    @FXML
    public void handleButtonInserir() {

        if (limpouSelecaoTableViewCursos()) {
            if (validouTextFields()) {
                Curso curso = new Curso();
                curso.setNomeCurso(textFieldCursoNome.getText());
                curso.setSiglaCurso(textFieldCursoSigla.getText());
                curso.setDuracao(textFieldCursoDuracao.getText());
                curso.setTurno(textFieldCursoTurno.getText());

                cursoDAO.inserir(curso);
                carregarTableViewCursos();
                limparTextFields();
            }

        }

    }

    @FXML
    public void handleButtonAlterar() {

        if (validouTextFields()) {
            Curso curso = tableViewCursos.getSelectionModel().getSelectedItem();
            curso.setNomeCurso(textFieldCursoNome.getText());
            curso.setSiglaCurso(textFieldCursoSigla.getText());
            curso.setDuracao(textFieldCursoDuracao.getText());
            curso.setTurno(textFieldCursoTurno.getText());

            cursoDAO.alterar(curso);
            carregarTableViewCursos();
        }

    }

    @FXML
    public void handleButtonRemover() {

        if (validouTextFields()) {
            Curso curso = tableViewCursos.getSelectionModel().getSelectedItem();
            cursoDAO.remover(curso);
            carregarTableViewCursos();
        }

    }

}
