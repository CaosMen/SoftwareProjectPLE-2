package publicacao;

import projeto.*;
import usuario.colaborador.*;

import java.util.ArrayList;

public class Publicacao {
    private String titulo;
    private String nomeConferencia;
    private int anoPublicacao;
    private Projeto projeto;
    private ArrayList<Colaborador> autores;

    public Publicacao(String titulo, String nomeConferencia, int anoPublicacao, ArrayList<Colaborador> autores) {
        this.titulo = titulo;
        this.nomeConferencia = nomeConferencia;
        this.anoPublicacao = anoPublicacao;
        this.projeto = null;
        this.autores = autores;
    }

    public Publicacao(String titulo, String nomeConferencia, int anoPublicacao, ArrayList<Colaborador> autores, Projeto projeto) {
        this.titulo = titulo;
        this.nomeConferencia = nomeConferencia;
        this.anoPublicacao = anoPublicacao;
        this.projeto = projeto;
        this.autores = autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNomeConferencia() {
        return nomeConferencia;
    }

    public void setNomeConferencia(String nomeConferencia) {
        this.nomeConferencia = nomeConferencia;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public ArrayList<Colaborador> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<Colaborador> autores) {
        this.autores = autores;
    }

    public String stringAutores() {
        String autoresResult = "";

        for (int i = 0; i < this.autores.size(); i++) {
            if (i == (this.autores.size() - 1)) {
                autoresResult += this.autores.get(i).getNome() + ";";
            } else {
                autoresResult += this.autores.get(i).getNome() + ", ";
            }
        }

        return autoresResult;
    }

    public String toString(String position) {
        return "    [" + position + "] Título: " + this.titulo + "\n"
             + "    Nome da Conferência: " + this.nomeConferencia + "\n"
             + "    Ano da Publicação: " + this.anoPublicacao + "\n"
             + "    Autores: " + stringAutores() + "\n"
             + "    Projeto associado: " + ((this.projeto == null) ? ("Sem projeto associado!") : ("\n\n" + this.projeto.toString(position + ".1")));
    }
}
