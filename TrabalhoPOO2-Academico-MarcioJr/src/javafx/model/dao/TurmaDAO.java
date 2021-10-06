package javafx.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.model.domain.Curso;
import javafx.model.domain.Turma;

/**
 *
 * @author Márcio Junior
 */
public class TurmaDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Turma turma) {
        String sql = "INSERT INTO turmas(nomeTurma, nomeOrientador, idCurso) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, turma.getNomeTurma());
            stmt.setString(2, turma.getNomeOrientador());
            stmt.setInt(3, turma.getCurso().getIdCurso());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Turma turma) {
        String sql = "UPDATE turmas SET nomeTurma=?, nomeOrientador=?, idCurso=? WHERE idTurma=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, turma.getNomeTurma());
            stmt.setString(2, turma.getNomeOrientador());
            stmt.setInt(3, turma.getCurso().getIdCurso());
            stmt.setInt(4, turma.getIdTurma());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Turma turma) {
        String sql = "DELETE FROM turmas WHERE idTurma=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, turma.getIdTurma());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Turma> listar() {
        String sql = "SELECT * FROM turmas";
        List<Turma> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Turma turma = new Turma();
                Curso curso = new Curso();

                curso.setIdCurso(resultado.getInt("idCurso"));
                turma.setIdTurma(resultado.getInt("idTurma"));
                turma.setNomeTurma(resultado.getString("nomeTurma"));
                turma.setNomeOrientador(resultado.getString("nomeOrientador"));

                CursoDAO cursoDAO = new CursoDAO();
                cursoDAO.setConnection(connection);
                curso = cursoDAO.buscar(curso);

                turma.setCurso(curso);

                retorno.add(turma);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Turma buscar(Turma turma) {
        String sql = "SELECT * FROM turmas WHERE idTurma=?";
        Turma retorno = new Turma();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, turma.getIdTurma());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {

                Curso curso = new Curso();
                turma.setIdTurma(resultado.getInt("idTurma"));
                turma.setNomeTurma(resultado.getString("nomeTurma"));
                turma.setNomeOrientador(resultado.getString("nomeOrientador"));
                curso.setIdCurso(resultado.getInt("idCurso"));

                turma.setCurso(curso);
                retorno = turma;

            }
        } catch (SQLException ex) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<Turma> listarCursoPorTurma(Curso curso) {
        String sql = "SELECT * FROM turmas WHERE idCurso=?";
        List<Turma> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, curso.getIdCurso());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Turma turma = new Turma();
                curso.setIdCurso(resultado.getInt("idCurso"));
                turma.setIdTurma(resultado.getInt("idTurma"));
                turma.setNomeTurma(resultado.getString("nomeTurma"));
                turma.setNomeOrientador(resultado.getString("nomeOrientador"));
                turma.setCurso(curso);

                retorno.add(turma);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
