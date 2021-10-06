package javafx.model.domain;

import java.sql.Date;
import java.time.LocalDate;

public class Aluno {

    private int idAlu;
    private String nomeAlu;
    private String matricula;
    private LocalDate dataNascimento;
    private Curso curso;
    private Turma turma;

    public Aluno() {
    }

    public Aluno(String nomeAlu, String matricula, LocalDate dataNascimento, Curso curso, Turma turma, int idAlu) {

        this.idAlu = idAlu;
        this.nomeAlu = nomeAlu;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
        this.curso = curso;
        this.turma = turma;

    }

    public int getIdAlu() {
        return idAlu;
    }

    public void setIdAlu(int idAlu) {
        this.idAlu = idAlu;
    }

    public String getNomeAlu() {
        return nomeAlu;
    }

    public void setNomeAlu(String nomeAlu) {
        this.nomeAlu = nomeAlu;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        return this.nomeAlu;
    }

}
