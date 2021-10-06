package javafx.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.model.dao.AlunoDAO;
import javafx.model.dao.CursoDAO;
import javafx.model.dao.NotaDAO;
import javafx.model.dao.TurmaDAO;
import javafx.model.database.Database;
import javafx.model.database.DatabaseFactory;
import javafx.model.domain.Aluno;
import javafx.model.domain.Curso;
import javafx.model.domain.Nota;
import javafx.model.domain.Turma;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Márcio Junior
 */
public class FXMLCadastroAlunoController implements Initializable {

    @FXML
    private TableView<Aluno> tableViewAlunos;
    @FXML
    private TableColumn<Aluno, String> tableColumnAlunoNome;
    @FXML
    private TableColumn<Aluno, String> tableColumnAlunoMatricula;
    @FXML
    private TableColumn<Aluno, Date> tableColumnAlunoDataNascimento;

    @FXML
    private TableColumn<Curso, String> tableColumnAlunoCurso;

    @FXML
    private TableColumn<Turma, String> tableColumnAlunoTurma;

    @FXML
    private TextField textFieldAlunoNome;
    @FXML
    private TextField textFieldAlunoMatricula;
    @FXML
    private DatePicker datePickerNascimento;

    @FXML
    private ComboBox<Curso> comboBoxAlunoNomeCurso;
    @FXML
    private ComboBox<Turma> comboBoxAlunoNomeTurma;

    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonLimpar;

    private ObservableList<Aluno> observableListAlunos;
    private List<Aluno> listAlunos;

    private ObservableList<Curso> observableListCursos;
    private List<Curso> listCursos;

    private ObservableList<Turma> observableListTurmas;
    private List<Turma> listTurmas;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    private final AlunoDAO alunoDAO = new AlunoDAO();

    private final TurmaDAO turmaDAO = new TurmaDAO();
    private final CursoDAO cursoDAO = new CursoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        alunoDAO.setConnection(connection);
        turmaDAO.setConnection(connection);
        cursoDAO.setConnection(connection);

        carregarTableViewAlunos();
        carregarComboboxCursos();
        carregarComboboxTurmas();

        tableViewAlunos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewAlunos(newValue));

    }

    public void carregarTableViewAlunos() {

        tableColumnAlunoNome.setCellValueFactory(new PropertyValueFactory<>("nomeAlu"));
        tableColumnAlunoMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tableColumnAlunoDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        tableColumnAlunoCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        tableColumnAlunoTurma.setCellValueFactory(new PropertyValueFactory<>("turma"));

        listAlunos = alunoDAO.listar();
        observableListAlunos = FXCollections.observableArrayList(listAlunos);
        tableViewAlunos.setItems(observableListAlunos);

    }

    public void carregarComboboxCursos() {

        listCursos = cursoDAO.listar();
        observableListCursos = FXCollections.observableArrayList(listCursos);
        comboBoxAlunoNomeCurso.setItems(observableListCursos);
    }

    public void carregarComboboxTurmas() {

        listTurmas = turmaDAO.listar();
        observableListTurmas = FXCollections.observableArrayList(listTurmas);
        comboBoxAlunoNomeTurma.setItems(observableListTurmas);
    }

    public void selecionarItemTableViewAlunos(Aluno aluno) {
        if (aluno != null) {
            Curso curso = new Curso();
            Turma turma = new Turma();

            textFieldAlunoNome.setText(aluno.getNomeAlu());
            textFieldAlunoMatricula.setText(aluno.getMatricula());
            datePickerNascimento.setValue(aluno.getDataNascimento());
            comboBoxAlunoNomeCurso.getSelectionModel().select(aluno.getCurso());
            comboBoxAlunoNomeTurma.getSelectionModel().select(aluno.getTurma());

        } else {
            limparTextFieldseCombobox();
        }
    }

    public void carregarNovaComboboxCursos() {

        List<Curso> listCursos = new ArrayList<>();
        ObservableList<Curso> observableNewListCursos;

        listCursos = cursoDAO.listar();
        observableNewListCursos = FXCollections.observableArrayList(listCursos);
        comboBoxAlunoNomeCurso.setItems(observableNewListCursos);

    }

    public void carregarNovaComboboxTurmas() {

        List<Turma> listTurmas = new ArrayList<>();
        ObservableList<Turma> observableNewListTurmas;

        listTurmas = turmaDAO.listar();
        observableNewListTurmas = FXCollections.observableArrayList(listTurmas);
        comboBoxAlunoNomeTurma.setItems(observableNewListTurmas);

    }

    public void limparTextFieldseCombobox() {
        textFieldAlunoNome.setText("");
        textFieldAlunoMatricula.setText("");
        datePickerNascimento.setValue(null);
        comboBoxAlunoNomeCurso.getSelectionModel().clearAndSelect(0);
        comboBoxAlunoNomeTurma.getSelectionModel().clearAndSelect(0);
        carregarNovaComboboxCursos();
        carregarNovaComboboxTurmas();

    }

    public boolean validouTextFields() {
        String errorMessage = "";

        if (textFieldAlunoNome.getText() == null || textFieldAlunoNome.getText().length() == 0) {
            errorMessage += "Nome do Aluno inválido!\n";
        }
        if (textFieldAlunoMatricula.getText() == null || textFieldAlunoMatricula.getText().length() == 0) {
            errorMessage += "Matrícula do Aluno inválida!\n";
        }
        if (datePickerNascimento.getValue() == null) {
            errorMessage += "Data inválida!\n";
        }
        if (comboBoxAlunoNomeCurso.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Curso do aluno inválido!\n";
        }

        if (comboBoxAlunoNomeTurma.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Turma do aluno inválido!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro de alunos");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }

    public boolean limpouSelecaoTableViewAlunos() {
        boolean limpou = tableViewAlunos.getSelectionModel().getSelectedItem() == null;
        if (limpou) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Cadastro de Aluno");
            alert.setTitle("Problemas na Inserção do Aluno");
            alert.setContentText("Antes de inserir você deve limpar a seleção da Tabela");
            alert.show();
            return false;
        }
    }

    @FXML
    public void handleButtonLimpar() {
        tableViewAlunos.getSelectionModel().clearSelection();
        textFieldAlunoNome.setText("");
        textFieldAlunoMatricula.setText("");
        datePickerNascimento.setValue(null);
        comboBoxAlunoNomeCurso.getSelectionModel().clearSelection();
        comboBoxAlunoNomeTurma.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleButtonInserir() {

        if (limpouSelecaoTableViewAlunos()) {
            if (validouTextFields()) {
                Aluno aluno = new Aluno();
                Curso curso = new Curso();
                Turma turma = new Turma();
                Nota nota = new Nota();

                aluno.setNomeAlu(textFieldAlunoNome.getText());
                aluno.setMatricula(textFieldAlunoMatricula.getText());
                aluno.setDataNascimento(datePickerNascimento.getValue());
                aluno.setCurso((Curso) comboBoxAlunoNomeCurso.getSelectionModel().getSelectedItem());
                aluno.setTurma((Turma) comboBoxAlunoNomeTurma.getSelectionModel().getSelectedItem());

                limparTextFieldseCombobox();

                alunoDAO.inserir(aluno);
                carregarTableViewAlunos();
                limparTextFieldseCombobox();

            }

        }

    }

    @FXML
    public void handleButtonAlterar() {

        if (validouTextFields()) {

            Aluno aluno = tableViewAlunos.getSelectionModel().getSelectedItem();

            aluno.setNomeAlu(textFieldAlunoNome.getText());
            aluno.setMatricula(textFieldAlunoMatricula.getText());
            aluno.setDataNascimento(datePickerNascimento.getValue());
            aluno.setCurso((Curso) comboBoxAlunoNomeCurso.getSelectionModel().getSelectedItem());
            aluno.setTurma((Turma) comboBoxAlunoNomeTurma.getSelectionModel().getSelectedItem());

            alunoDAO.alterar(aluno);
            carregarTableViewAlunos();

        }

    }

    @FXML
    public void handleButtonRemover() {

        if (validouTextFields()) {
            Aluno aluno = tableViewAlunos.getSelectionModel().getSelectedItem();
            alunoDAO.remover(aluno);
            carregarTableViewAlunos();
        }

    }

}
