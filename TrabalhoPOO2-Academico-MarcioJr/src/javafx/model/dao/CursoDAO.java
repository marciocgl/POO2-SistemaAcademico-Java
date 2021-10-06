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

/**
 *
 * @author Márcio Junior
 */
public class CursoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Curso curso) {
        String sql = "INSERT INTO cursos(nomeCurso, siglaCurso, duracao, turno) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, curso.getNomeCurso());
            stmt.setString(2, curso.getSiglaCurso());
            stmt.setString(3, curso.getDuracao());
            stmt.setString(4, curso.getTurno());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Curso curso) {
        String sql = "UPDATE cursos SET nomeCurso=?, siglaCurso=?, duracao=?, turno=? WHERE idCurso=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, curso.getNomeCurso());
            stmt.setString(2, curso.getSiglaCurso());
            stmt.setString(3, curso.getDuracao());
            stmt.setString(4, curso.getTurno());
            stmt.setInt(5, curso.getIdCurso());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Curso curso) {
        String sql = "DELETE FROM cursos WHERE idCurso=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, curso.getIdCurso());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Curso> listar() {
        String sql = "SELECT * FROM cursos";
        List<Curso> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(resultado.getInt("idCurso"));
                curso.setNomeCurso(resultado.getString("nomeCurso"));
                curso.setSiglaCurso(resultado.getString("siglaCurso"));
                curso.setDuracao(resultado.getString("duracao"));
                curso.setTurno(resultado.getString("turno"));
                retorno.add(curso);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Curso buscar(Curso curso) {
        String sql = "SELECT * FROM cursos WHERE idCurso=?";
        Curso retorno = new Curso();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, curso.getIdCurso());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                curso.setIdCurso(resultado.getInt("idCurso"));
                curso.setNomeCurso(resultado.getString("nomeCurso"));
                curso.setSiglaCurso(resultado.getString("siglaCurso"));
                curso.setDuracao(resultado.getString("duracao"));
                curso.setTurno(resultado.getString("turno"));
                retorno = curso;

            }
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
