package javafx.model.domain;

/**
 *
 * @author Márcio Junior
 */
public class Curso {

    private int idCurso;
    private String nomeCurso;
    private String siglaCurso;
    private String duracao;
    private String turno;

    public Curso() {
    }

    public Curso(String nomeCurso, String siglaCurso, String duracao, String turno, int idcurso) {
        this.idCurso = idcurso;
        this.nomeCurso = nomeCurso;
        this.siglaCurso = siglaCurso;
        this.duracao = duracao;
        this.turno = turno;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getSiglaCurso() {
        return siglaCurso;
    }

    public void setSiglaCurso(String siglaCurso) {
        this.siglaCurso = siglaCurso;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return this.nomeCurso;
    }

}
