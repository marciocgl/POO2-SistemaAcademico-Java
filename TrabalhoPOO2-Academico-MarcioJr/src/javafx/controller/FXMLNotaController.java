/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.controller;

import java.net.URL;
import java.sql.Connection;
import java.text.DecimalFormat;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Márcio Junior
 */
public class FXMLNotaController implements Initializable {

    @FXML
    private TableView<Nota> tableViewNotas;
    @FXML
    private TableColumn<Aluno, String> tableColumnNotasAlunoNome;
    @FXML
    private TableColumn<Curso, String> tableColumnNotasCursoNome;
    @FXML
    private TableColumn<Turma, String> tableColumnNotasTurmaNome;
    @FXML
    private TableColumn<Nota, Float> tableColumnNotasProva1;
    @FXML
    private TableColumn<Nota, Float> tableColumnNotasProva2;
    @FXML
    private TableColumn<Nota, Float> tableColumnNotasTrabalho;
    @FXML
    private TableColumn<Nota, Float> tableColumnNotasMedia;

    @FXML
    private ComboBox<Aluno> comboBoxAlunoNome;
    @FXML
    private ComboBox<Curso> comboBoxCursoNome;
    @FXML
    private ComboBox<Turma> comboBoxTurmaNome;

    /*
    @FXML
    private TextField textFieldNotasAlunoNome;
    @FXML
    private TextField textFieldNotasCursoNome;
    @FXML
    private TextField textFieldNotasTurmaNome;
     */
    @FXML
    private TextField textFieldNotasProva1;
    @FXML
    private TextField textFieldNotasProva2;
    @FXML
    private TextField textFieldNotasTrabalho;

    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonLimpar;

    private ObservableList<Nota> observableListNotas;
    private List<Nota> listNotas;

    private ObservableList<Aluno> observableListAlunos;
    private List<Aluno> listAlunos;

    private ObservableList<Curso> observableListCursos;
    private List<Curso> listCursos;

    private ObservableList<Turma> observableListTurmas;
    private List<Turma> listTurmas;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    private final NotaDAO notaDAO = new NotaDAO();
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final TurmaDAO turmaDAO = new TurmaDAO();
    private final CursoDAO cursoDAO = new CursoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        notaDAO.setConnection(connection);
        alunoDAO.setConnection(connection);
        turmaDAO.setConnection(connection);
        cursoDAO.setConnection(connection);

        carregarTableViewNotas();
        carregarComboboxAlunos();
        carregarComboboxCursos();
        carregarComboboxTurmas();

        tableViewNotas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewNotas(newValue));

    }

    public void carregarTableViewNotas() {

        tableColumnNotasAlunoNome.setCellValueFactory(new PropertyValueFactory<>("aluno"));
        tableColumnNotasCursoNome.setCellValueFactory(new PropertyValueFactory<>("curso"));
        tableColumnNotasTurmaNome.setCellValueFactory(new PropertyValueFactory<>("turma"));
        tableColumnNotasProva1.setCellValueFactory(new PropertyValueFactory<>("prova1"));
        tableColumnNotasProva2.setCellValueFactory(new PropertyValueFactory<>("prova2"));
        tableColumnNotasTrabalho.setCellValueFactory(new PropertyValueFactory<>("trabalho"));
        tableColumnNotasMedia.setCellValueFactory(new PropertyValueFactory<>("media"));

        listNotas = notaDAO.listar();
        observableListNotas = FXCollections.observableArrayList(listNotas);
        tableViewNotas.setItems(observableListNotas);

    }

    public void carregarComboboxAlunos() {

        listAlunos = alunoDAO.listar();
        observableListAlunos = FXCollections.observableArrayList(listAlunos);
        comboBoxAlunoNome.setItems(observableListAlunos);

    }

    public void carregarComboboxCursos() {

        listCursos = cursoDAO.listar();
        observableListCursos = FXCollections.observableArrayList(listCursos);
        comboBoxCursoNome.setItems(observableListCursos);
    }

    public void carregarComboboxTurmas() {

        listTurmas = turmaDAO.listar();
        observableListTurmas = FXCollections.observableArrayList(listTurmas);
        comboBoxTurmaNome.setItems(observableListTurmas);
    }

    public void carregarNovaComboboxAlunos() {

        List<Aluno> listAlunos = new ArrayList<>();
        ObservableList<Aluno> observableNewListAlunos;

        listAlunos = alunoDAO.listar();
        observableNewListAlunos = FXCollections.observableArrayList(listAlunos);
        comboBoxAlunoNome.setItems(observableNewListAlunos);

    }

    public void carregarNovaComboboxCursos() {

        List<Curso> listCursos = new ArrayList<>();
        ObservableList<Curso> observableNewListCursos;

        listCursos = cursoDAO.listar();
        observableNewListCursos = FXCollections.observableArrayList(listCursos);
        comboBoxCursoNome.setItems(observableNewListCursos);

    }

    public void carregarNovaComboboxTurmas() {

        List<Turma> listTurmas = new ArrayList<>();
        ObservableList<Turma> observableNewListTurmas;

        listTurmas = turmaDAO.listar();
        observableNewListTurmas = FXCollections.observableArrayList(listTurmas);
        comboBoxTurmaNome.setItems(observableNewListTurmas);

    }

    public void selecionarItemTableViewNotas(Nota nota) {
        if (nota != null) {

            Curso curso = new Curso();
            Turma turma = new Turma();
            Aluno aluno = new Aluno();

            comboBoxAlunoNome.getSelectionModel().select(nota.getAluno());
            comboBoxCursoNome.getSelectionModel().select(nota.getCurso());
            comboBoxTurmaNome.getSelectionModel().select(nota.getTurma());
            textFieldNotasProva1.setText(String.valueOf(nota.getProva1()));
            textFieldNotasProva2.setText(String.valueOf(nota.getProva2()));
            textFieldNotasTrabalho.setText(String.valueOf(nota.getTrabalho()));

        } else {
            limparTextFields();
        }
    }

    public void limparTextFields() {

        comboBoxAlunoNome.getSelectionModel().clearAndSelect(0);
        comboBoxCursoNome.getSelectionModel().clearAndSelect(0);
        comboBoxTurmaNome.getSelectionModel().clearAndSelect(0);
        carregarNovaComboboxAlunos();
        carregarNovaComboboxCursos();
        carregarNovaComboboxTurmas();

        textFieldNotasProva1.setText("");
        textFieldNotasProva2.setText("");
        textFieldNotasTrabalho.setText("");

    }

    public boolean validouTextFields() {
        String errorMessage = "";

        if (comboBoxAlunoNome.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Nome do Aluno inválido!\n";
        }
        if (comboBoxCursoNome.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Curso inválida!\n";
        }
        if (comboBoxTurmaNome.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Turma inválida!\n";
        }
        if (textFieldNotasProva1.getText() == null || textFieldNotasProva1.getText().length() == 0) {
            errorMessage += "Nota prova 01 inválida!\n";
        }
        if (textFieldNotasProva2.getText() == null || textFieldNotasProva2.getText().length() == 0) {
            errorMessage += "Nota prova 02 inválida!\n";
        }
        if (textFieldNotasTrabalho.getText() == null || textFieldNotasTrabalho.getText().length() == 0) {
            errorMessage += "Nota do Trabalho inválida!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro de nota");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }

    public boolean limpouSelecaoTableViewNotas() {
        boolean limpou = tableViewNotas.getSelectionModel().getSelectedItem() == null;
        if (limpou) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Cadastro de Nota");
            alert.setTitle("Problemas na Inserção das notas");
            alert.setContentText("Antes de inserir você deve limpar a seleção da Tabela");
            alert.show();
            return false;
        }
    }

    @FXML
    public void handleButtonLimpar() {
        tableViewNotas.getSelectionModel().clearSelection();
        comboBoxAlunoNome.getSelectionModel().clearSelection();
        comboBoxCursoNome.getSelectionModel().clearSelection();
        comboBoxTurmaNome.getSelectionModel().clearSelection();
        textFieldNotasProva1.setText("");
        textFieldNotasProva2.setText("");
        textFieldNotasTrabalho.setText("");
    }

    @FXML
    public void handleButtonInserir() {

        if (limpouSelecaoTableViewNotas()) {
            if (validouTextFields()) {

                Nota nota = new Nota();

                nota.setProva1(Float.parseFloat(textFieldNotasProva1.getText()));
                nota.setProva2(Float.parseFloat(textFieldNotasProva2.getText()));
                nota.setTrabalho(Float.parseFloat(textFieldNotasTrabalho.getText()));

                nota.setCurso(this.comboBoxCursoNome.getSelectionModel().getSelectedItem());
                nota.setTurma(this.comboBoxTurmaNome.getSelectionModel().getSelectedItem());
                nota.setAluno(this.comboBoxAlunoNome.getSelectionModel().getSelectedItem());

                notaDAO.inserir(nota);
                carregarTableViewNotas();
                limparTextFields();

            }

        }

    }

    @FXML
    public void handleButtonAlterar() {

        if (validouTextFields()) {

            Nota nota = tableViewNotas.getSelectionModel().getSelectedItem();

            nota.setProva1(Float.parseFloat(textFieldNotasProva1.getText()));
            nota.setProva2(Float.parseFloat(textFieldNotasProva2.getText()));
            nota.setTrabalho(Float.parseFloat(textFieldNotasTrabalho.getText()));

            nota.setCurso(this.comboBoxCursoNome.getSelectionModel().getSelectedItem());
            nota.setTurma(this.comboBoxTurmaNome.getSelectionModel().getSelectedItem());
            nota.setAluno(this.comboBoxAlunoNome.getSelectionModel().getSelectedItem());

            notaDAO.alterar(nota);
            carregarTableViewNotas();

        }

    }

    @FXML
    public void handleButtonRemover() {

        if (validouTextFields()) {
            Nota nota = tableViewNotas.getSelectionModel().getSelectedItem();
            notaDAO.remover(nota);
            carregarTableViewNotas();
        }

    }

}
