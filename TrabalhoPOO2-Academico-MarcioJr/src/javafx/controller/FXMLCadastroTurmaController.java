package javafx.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.model.dao.CursoDAO;
import javafx.model.dao.TurmaDAO;
import javafx.model.database.Database;
import javafx.model.database.DatabaseFactory;
import javafx.model.domain.Curso;
import javafx.model.domain.Turma;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Márcio Junior
 */
public class FXMLCadastroTurmaController implements Initializable {

    @FXML
    private TableView<Turma> tableViewTurma;
    @FXML
    private TableColumn<Turma, String> tableColumnTurmaNome;
    @FXML
    private TableColumn<Turma, String> tableColumnTurmaOrientador;
    @FXML
    private TableColumn<Curso, String> tableColumnCursoNome;

    @FXML
    private TextField textFieldTurmaNome;
    @FXML
    private TextField textFieldTurmaOrientador;
    @FXML
    private ComboBox<Curso> comboBoxTurmaNomeCurso;

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

    private ObservableList<Turma> observableListTurmas;
    private List<Turma> listTurmas;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    private final TurmaDAO turmaDAO = new TurmaDAO();
    private final CursoDAO cursoDAO = new CursoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        turmaDAO.setConnection(connection);
        cursoDAO.setConnection(connection);

        carregarTableViewTurmas();
        carregarComboboxCursos();

        tableViewTurma.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewTurmas(newValue));

    }

    public void carregarTableViewTurmas() {

        tableColumnTurmaNome.setCellValueFactory(new PropertyValueFactory<>("nomeTurma"));
        tableColumnTurmaOrientador.setCellValueFactory(new PropertyValueFactory<>("nomeOrientador"));
        tableColumnCursoNome.setCellValueFactory(new PropertyValueFactory<>("curso"));

        listTurmas = turmaDAO.listar();
        observableListTurmas = FXCollections.observableArrayList(listTurmas);
        tableViewTurma.setItems(observableListTurmas);

    }

    public void carregarComboboxCursos() {

        listCursos = cursoDAO.listar();
        observableListCursos = FXCollections.observableArrayList(listCursos);
        comboBoxTurmaNomeCurso.setItems(observableListCursos);
    }

    public void selecionarItemTableViewTurmas(Turma turma) {
        if (turma != null) {
            Curso curso = new Curso();
            textFieldTurmaNome.setText(turma.getNomeTurma());
            textFieldTurmaOrientador.setText(turma.getNomeOrientador());
            comboBoxTurmaNomeCurso.getSelectionModel().select(turma.getCurso());

        } else {
            limparTextFieldsEComboBox();
        }
    }

    public void carregarNovaComboboxCursos() {

        List<Curso> listCursos = new ArrayList<>();
        ObservableList<Curso> observableNewListCursos;

        listCursos = cursoDAO.listar();
        observableNewListCursos = FXCollections.observableArrayList(listCursos);
        comboBoxTurmaNomeCurso.setItems(observableNewListCursos);

    }

    public void limparTextFieldsEComboBox() {

        textFieldTurmaNome.setText("");
        textFieldTurmaOrientador.setText("");
        comboBoxTurmaNomeCurso.getSelectionModel().clearAndSelect(0);
        carregarNovaComboboxCursos();

    }

    public boolean validouTextFields() {
        String errorMessage = "";

        if (textFieldTurmaNome.getText() == null || textFieldTurmaNome.getText().length() == 0) {
            errorMessage += "Nome da Turma inválido!\n";
        }
        if (textFieldTurmaOrientador.getText() == null || textFieldTurmaOrientador.getText().length() == 0) {
            errorMessage += "Nome do Orientador inválido!\n";
        }
        if (comboBoxTurmaNomeCurso.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Curso inválido!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro de Turmas");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }

    public boolean limpouSelecaoTableViewTurmas() {
        boolean limpou = tableViewTurma.getSelectionModel().getSelectedItem() == null;
        if (limpou) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Cadastro de Turmas");
            alert.setTitle("Problemas na Inserção da Turma");
            alert.setContentText("Antes de inserir você deve limpar a seleção da Tabela");
            alert.show();
            return false;
        }
    }

    @FXML
    public void handleButtonLimpar() {
        tableViewTurma.getSelectionModel().clearSelection();
        textFieldTurmaNome.setText("");
        textFieldTurmaOrientador.setText("");
        comboBoxTurmaNomeCurso.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleButtonInserir() {

        if (limpouSelecaoTableViewTurmas()) {
            if (validouTextFields()) {
                Turma turma = new Turma();
                turma.setNomeTurma(textFieldTurmaNome.getText());
                turma.setNomeOrientador(textFieldTurmaOrientador.getText());
                turma.setCurso((Curso) comboBoxTurmaNomeCurso.getSelectionModel().getSelectedItem());
                turmaDAO.inserir(turma);
                carregarTableViewTurmas();
                limparTextFieldsEComboBox();
            }

        }

    }

    @FXML
    public void handleButtonAlterar() {

        if (validouTextFields()) {

            Turma turma = tableViewTurma.getSelectionModel().getSelectedItem();

            turma.setNomeTurma(textFieldTurmaNome.getText());
            turma.setNomeOrientador(textFieldTurmaOrientador.getText());
            turma.setCurso((Curso) comboBoxTurmaNomeCurso.getSelectionModel().getSelectedItem());

            turmaDAO.alterar(turma);
            carregarTableViewTurmas();

        }

    }

    @FXML
    public void handleButtonRemover() {

        if (validouTextFields()) {
            Turma turma = tableViewTurma.getSelectionModel().getSelectedItem();
            turmaDAO.remover(turma);
            carregarTableViewTurmas();
        }

    }

}
