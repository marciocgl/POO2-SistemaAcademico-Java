package javafx.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.model.domain.Aluno;
import javafx.model.domain.Curso;
import javafx.model.domain.Nota;
import javafx.model.domain.Turma;

public class AlunoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /*
   public boolean inserirAlunoNota(Nota nota) {
        String sql = "INSERT INTO notas(idCurso, idTurma, idAlu, prova1, prova2, trabalho, media ) VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, nota.getCurso().getIdCurso());
            stmt.setInt(2, nota.getTurma().getIdTurma());
            stmt.setInt(3, nota.getAluno().getIdAlu());
            stmt.setFloat(4, 0);
            stmt.setFloat(5, 0);
            stmt.setFloat(6, 0);
            stmt.setFloat(7, (nota.getProva1()+nota.getProva2()+nota.getTrabalho()) / 3);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NotaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
   
    */
    public boolean inserir(Aluno aluno) {

        String sql = "INSERT INTO alunos(nomeAlu, matricula, dataNascimento, idCurso, idTurma ) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getNomeAlu());
            stmt.setString(2, aluno.getMatricula());
            stmt.setDate(3, Date.valueOf(aluno.getDataNascimento()));
            stmt.setInt(4, aluno.getCurso().getIdCurso());
            stmt.setInt(5, aluno.getTurma().getIdTurma());
            
           // inserirAlunoNota(nota);
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Aluno aluno) {
        String sql = "UPDATE alunos SET nomeAlu=?, matricula=?, dataNascimento=?, idCurso=?, idTurma=? WHERE idAlu=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getNomeAlu());
            stmt.setString(2, aluno.getMatricula());
            stmt.setDate(3, Date.valueOf(aluno.getDataNascimento()));
            stmt.setInt(4, aluno.getCurso().getIdCurso());
            stmt.setInt(5, aluno.getTurma().getIdTurma());
            stmt.setInt(6, aluno.getIdAlu());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Aluno aluno) {
        String sql = "DELETE FROM alunos WHERE idAlu=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aluno.getIdAlu());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Aluno> listar() {
        String sql = "SELECT * FROM alunos";
        List<Aluno> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {

                Aluno aluno = new Aluno();
                Turma turma = new Turma();
                Curso curso = new Curso();

                curso.setIdCurso(resultado.getInt("idCurso"));
                turma.setIdTurma(resultado.getInt("idTurma"));

                aluno.setIdAlu(resultado.getInt("idAlu"));
                aluno.setNomeAlu(resultado.getString("nomeAlu"));
                aluno.setMatricula(resultado.getString("matricula"));
                aluno.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate());

                CursoDAO cursoDAO = new CursoDAO();
                cursoDAO.setConnection(connection);
                curso = cursoDAO.buscar(curso);

                TurmaDAO turmaDAO = new TurmaDAO();
                turmaDAO.setConnection(connection);
                turma = turmaDAO.buscar(turma);

                aluno.setCurso(curso);
                aluno.setTurma(turma);

                retorno.add(aluno);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Aluno buscar(Aluno aluno) {
        String sql = "SELECT * FROM alunos WHERE idAlu=?";
        Aluno retorno = new Aluno();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aluno.getIdAlu());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {

                Curso curso = new Curso();
                Turma turma = new Turma();

                aluno.setIdAlu(resultado.getInt("idAlu"));
                aluno.setNomeAlu(resultado.getString("nomeAlu"));
                aluno.setMatricula(resultado.getString("matricula"));
                aluno.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate());
                curso.setIdCurso(resultado.getInt("idCurso"));
                turma.setIdTurma(resultado.getInt("idTurma"));

                aluno.setCurso(curso);
                aluno.setTurma(turma);

                retorno = aluno;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /*
    public List<Aluno> listarCursosETurmasPorAluno(Curso curso, Turma turma) {
        String sql = "SELECT * FROM alunos WHERE idAlu=?";
        List<Aluno> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, curso.getIdCurso());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Aluno aluno = new Aluno();

                curso.setIdCurso(resultado.getInt("idCurso"));
                turma.setIdTurma(resultado.getInt("idTurma"));

                aluno.setIdAlu(resultado.getInt("idAlu"));
                aluno.setNomeAlu(resultado.getString("nomeAlu"));
                aluno.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate());

                aluno.setTurma(turma);
                aluno.setCurso(curso);

                retorno.add(aluno);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;

    }
     */
}
