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
        this(titulo, nomeConferencia, anoPublicacao, autores, null);
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
        StringBuilder bld = new StringBuilder();

        for (int i = 0; i < this.autores.size(); i++) {
            bld.append(this.autores.get(i).getNome());

            if (i == (this.autores.size() - 1)) {
                bld.append(";");
            } else {
                bld.append(", ");
            }
        }

        return bld.toString();
    }

    @Override
    public String toString() {
        return "Título: " + this.titulo + "\n"
             + "Nome da Conferência: " + this.nomeConferencia + "\n"
             + "Ano da Publicação: " + this.anoPublicacao + "\n"
             + "Autores: " + stringAutores();
    }

    public String toStringProjeto() {
        return this.toString() + "\nProjeto associado: " + ((this.projeto == null) ? ("Sem projeto associado!") : ("\n\n" + this.projeto.toString())); 
    }
}
