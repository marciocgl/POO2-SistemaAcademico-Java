package javafx.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.model.domain.Aluno;
import javafx.model.domain.Curso;
import javafx.model.domain.Nota;
import javafx.model.domain.Turma;

/**
 *
 * @author Márcio Junior
 */
public class NotaDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Nota nota) {
        String sql = "INSERT INTO notas(idCurso, idTurma, idAlu, prova1, prova2, trabalho, media ) VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, nota.getCurso().getIdCurso());
            stmt.setInt(2, nota.getTurma().getIdTurma());
            stmt.setInt(3, nota.getAluno().getIdAlu());
            stmt.setFloat(4, nota.getProva1());
            stmt.setFloat(5, nota.getProva2());
            stmt.setFloat(6, nota.getTrabalho());
            stmt.setFloat(7, calcularMedia(nota));
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NotaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Nota nota) {

        String sql = "UPDATE notas SET idCurso=?, idTurma=?, idAlu=?, prova1=?, prova2=?, trabalho=?, media=? WHERE idNotas=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, nota.getCurso().getIdCurso());
            stmt.setInt(2, nota.getTurma().getIdTurma());
            stmt.setInt(3, nota.getAluno().getIdAlu());
            stmt.setFloat(4, nota.getProva1());
            stmt.setFloat(5, nota.getProva2());
            stmt.setFloat(6, nota.getTrabalho());
            stmt.setFloat(7, (calcularMedia(nota)));
            stmt.setFloat(8, nota.getIdNotas());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NotaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Nota nota) {
        // String sql = "DELETE FROM notas WHERE idNotas=?";
        String sql = "UPDATE notas SET idCurso=?, idTurma=?, idAlu=?, prova1=?, prova2=?, trabalho=?, media=? WHERE idNotas=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            // stmt.setInt(1, nota.getIdNotas());
            stmt.setInt(1, nota.getCurso().getIdCurso());
            stmt.setInt(2, nota.getTurma().getIdTurma());
            stmt.setInt(3, nota.getAluno().getIdAlu());
            stmt.setFloat(4, 0);
            stmt.setFloat(5, 0);
            stmt.setFloat(6, 0);
            stmt.setFloat(7, 0);
            stmt.setFloat(8, nota.getIdNotas());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NotaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Nota> listar() {
        String sql = "SELECT * FROM notas";
        List<Nota> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {

                Nota nota = new Nota();
                Turma turma = new Turma();
                Curso curso = new Curso();
                Aluno aluno = new Aluno();

                curso.setIdCurso(resultado.getInt("idCurso"));
                turma.setIdTurma(resultado.getInt("idTurma"));
                aluno.setIdAlu(resultado.getInt("idAlu"));

                nota.setIdNotas(resultado.getInt("idNotas"));
                nota.setProva1(resultado.getFloat("prova1"));
                nota.setProva2(resultado.getFloat("prova2"));
                nota.setTrabalho(resultado.getFloat("trabalho"));
                nota.setMedia(resultado.getFloat("media"));

                CursoDAO cursoDAO = new CursoDAO();
                cursoDAO.setConnection(connection);
                curso = cursoDAO.buscar(curso);

                TurmaDAO turmaDAO = new TurmaDAO();
                turmaDAO.setConnection(connection);
                turma = turmaDAO.buscar(turma);

                AlunoDAO alunoDAO = new AlunoDAO();
                alunoDAO.setConnection(connection);
                aluno = alunoDAO.buscar(aluno);

                nota.setCurso(curso);
                nota.setTurma(turma);
                nota.setAluno(aluno);

                retorno.add(nota);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Nota buscar(Nota nota) {
        String sql = "SELECT * FROM notas WHERE idNotas=?";
        Nota retorno = new Nota();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, nota.getIdNotas());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {

                Curso curso = new Curso();
                Turma turma = new Turma();
                Aluno aluno = new Aluno();

                nota.setIdNotas(resultado.getInt("idNotas"));
                nota.setProva1(resultado.getFloat("prova1"));
                nota.setProva2(resultado.getFloat("prova2"));
                nota.setTrabalho(resultado.getFloat("trabalho"));
                nota.setMedia(resultado.getFloat("media"));

                curso.setIdCurso(resultado.getInt("idCurso"));
                turma.setIdTurma(resultado.getInt("idTurma"));
                aluno.setIdAlu(resultado.getInt("idAlu"));

                nota.setCurso(curso);
                nota.setTurma(turma);
                nota.setAluno(aluno);

                retorno = nota;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<Nota> listarAlunosECursosETurmasPorNota(Curso curso, Turma turma, Aluno aluno) {
        String sql = "SELECT * FROM notas WHERE idNotas=?";
        List<Nota> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, curso.getIdCurso());
            stmt.setInt(2, turma.getIdTurma());
            stmt.setInt(3, aluno.getIdAlu());

            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {

                Nota nota = new Nota();

                curso.setIdCurso(resultado.getInt("idCurso"));
                turma.setIdTurma(resultado.getInt("idTurma"));
                aluno.setIdAlu(resultado.getInt("idAlu"));

                nota.setIdNotas(resultado.getInt("idNotas"));
                nota.setProva1(resultado.getFloat("prova1"));
                nota.setProva2(resultado.getFloat("prova2"));
                nota.setTrabalho(resultado.getFloat("trabalho"));
                nota.setMedia(resultado.getFloat("media"));

                nota.setTurma(turma);
                nota.setCurso(curso);
                nota.setAluno(aluno);

                retorno.add(nota);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public float calcularMedia(Nota nota) {

        Locale.setDefault(new Locale("pt", "BR"));  // mudança global

        DecimalFormat df = new DecimalFormat();
        df.applyPattern("#,##0.00");

        nota.setMedia((nota.getProva1() + nota.getProva2() + nota.getTrabalho()) / 3);

        df.format(nota.getMedia());

        // System.out.println(df.format(nota.getMedia()));
        return nota.getMedia();
    }

}
