/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.model.domain;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 *
 * @author Márcio Junior
 */
public class Nota {

    private int idNotas;
    private Curso curso;
    private Turma turma;
    private Aluno aluno;
    private float prova1;
    private float prova2;
    private float trabalho;
    private float media;

    public Nota() {
    }

    public Nota(Curso curso, Turma turma, Aluno aluno, float prova1, float prova2, float trabalho, float media, int idNotas) {

        this.idNotas = idNotas;
        this.curso = curso;
        this.turma = turma;
        this.aluno = aluno;
        this.prova1 = prova1;
        this.prova2 = prova2;
        this.trabalho = trabalho;
        this.media = media;
    }

    public int getIdNotas() {
        return idNotas;
    }

    public void setIdNotas(int idNotas) {
        this.idNotas = idNotas;
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

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public float getProva1() {
        return prova1;
    }

    public void setProva1(float prova1) {
        this.prova1 = prova1;
    }

    public float getProva2() {
        return prova2;
    }

    public void setProva2(float prova2) {
        this.prova2 = prova2;
    }

    public float getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(float trabalho) {
        this.trabalho = trabalho;
    }

    public float getMedia() {

        return media;
    }

    public void setMedia(float media) {

        this.media = media;
    }

}
