package orientacao;

import usuario.colaborador.*;

public class Orientacao {
    private String titulo;
    private int anoOrientacao;
    private Professor professor;
    private Aluno orientando;

    public Orientacao(String titulo, int anoOrientacao, Professor professor, Aluno orientando) {
        this.titulo = titulo;
        this.anoOrientacao = anoOrientacao;
        this.professor = professor;
        this.orientando = orientando;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoOrientacao() {
        return anoOrientacao;
    }

    public void setAnoOrientacao(int anoOrientacao) {
        this.anoOrientacao = anoOrientacao;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Aluno getOrientando() {
        return orientando;
    }

    public void setOrientando(Aluno orientando) {
        this.orientando = orientando;
    }
}
