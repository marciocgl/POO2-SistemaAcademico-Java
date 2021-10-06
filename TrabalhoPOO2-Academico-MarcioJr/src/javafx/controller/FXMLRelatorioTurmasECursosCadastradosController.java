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
import javafx.model.dao.TurmaDAO;
import javafx.model.database.Database;
import javafx.model.database.DatabaseFactory;
import javafx.model.domain.Curso;
import javafx.model.domain.Turma;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Márcio Junior
 */
public class FXMLRelatorioTurmasECursosCadastradosController implements Initializable {

    @FXML
    private TableView<Turma> tableViewRelatorio;
    @FXML
    private TableColumn<Turma, String> tableColumnTurma;
    @FXML
    private TableColumn<Curso, String> tableColumnCurso;

    @FXML
    private Button buttonImprimirRelatório;

    private ObservableList<Turma> observableListTurmas;
    private List<Turma> listTurmas;

    private ObservableList<Curso> observableListCursos;
    private List<Curso> listCursos;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    private final TurmaDAO turmaDAO = new TurmaDAO();
    private final CursoDAO cursoDAO = new CursoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cursoDAO.setConnection(connection);
        turmaDAO.setConnection(connection);

    }

    public void handleButtonImprimirCursosETurma() {
        tableColumnTurma.setCellValueFactory(new PropertyValueFactory<>("nomeTurma"));
        tableColumnCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        listTurmas = turmaDAO.listar();
        observableListTurmas = FXCollections.observableArrayList(listTurmas);
        tableViewRelatorio.setItems(observableListTurmas);
        listCursos = cursoDAO.listar();
        observableListCursos = FXCollections.observableArrayList(listCursos);
    }

}

/*
       public void handleImprimir() throws JRException {
        URL url = getClass().getResource("/javafx/relatorios/RelatorioCursos.jasper");
        JasperReport report = (JasperReport) JRLoader.loadObject(url);
        JasperPrint print = JasperFillManager.fillReport(report, null, connection);
        JasperViewer viewer = new JasperViewer(print, false);
        viewer.setVisible(true);
    }
 */
