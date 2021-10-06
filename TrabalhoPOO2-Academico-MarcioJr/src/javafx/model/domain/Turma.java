package javafx.model.domain;

/**
 *
 * @author Márcio Junior
 */
public class Turma {

    private int idTurma;
    private String nomeTurma;
    private String nomeOrientador;
    private Curso curso;

    public Turma(String nomeTurma, String nomeOrientador, Curso curso, int idTurma) {

        this.idTurma = idTurma;
        this.nomeTurma = nomeTurma;
        this.nomeOrientador = nomeOrientador;
        this.curso = curso;
    }

    public Turma() {

    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public String getNomeOrientador() {
        return nomeOrientador;
    }

    public void setNomeOrientador(String nomeOrientador) {
        this.nomeOrientador = nomeOrientador;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return this.nomeTurma;
    }
}
