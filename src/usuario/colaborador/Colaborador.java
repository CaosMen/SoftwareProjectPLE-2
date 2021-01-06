package usuario.colaborador;

import usuario.*;
import publicacao.*;
import projeto.*;

import java.util.ArrayList;

public class Colaborador extends Usuario {
    private ArrayList<Publicacao> publicacoes;
    private ArrayList<Projeto> projetos;

    public Colaborador(String nome, String email) {
        super(nome, email);
        this.publicacoes = new ArrayList<Publicacao>();
        this.projetos = new ArrayList<Projeto>();
    }

    public Colaborador(String nome, String email, ArrayList<Publicacao> publicacoes, ArrayList<Projeto> projetos) {
        super(nome, email);
        this.publicacoes = publicacoes;
        this.projetos = projetos;
    }

    public ArrayList<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(ArrayList<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

    public void addPublicacao(Publicacao publicacao) {
        if (!this.publicacoes.contains(publicacao)) {
            for (int i = 0; i < this.publicacoes.size(); i++) {
                if (this.publicacoes.get(i).getAnoPublicacao() < publicacao.getAnoPublicacao()) {
                    this.publicacoes.add(i, publicacao);
                    return;
                }
            }

            this.publicacoes.add(publicacao);
        }
    }

    public ArrayList<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(ArrayList<Projeto> projetos) {
        this.projetos = projetos;
    }

    public void addProjeto(Projeto projeto) {
        if (!this.projetos.contains(projeto)) {
            for (int i = 0; i < this.projetos.size(); i++) {
                if (this.projetos.get(i).getDataTermino().compareTo(projeto.getDataTermino()) < 0) {
                    this.projetos.add(i, projeto);
                    return;
                }
            }

            this.projetos.add(projeto);
        }
    }

    public void removeProjeto(Projeto projeto) {
        if (this.projetos.contains(projeto)) {
            this.projetos.remove(projeto);
        }
    }

    public ArrayList<Projeto> getProjetosByStatus(String status) {
        ArrayList<Projeto> projetosResult = new ArrayList<Projeto>();

        for (int i = 0; i < this.projetos.size(); i++) {
            if (this.projetos.get(i).getStatus().equals(status)) {
                projetosResult.add(this.projetos.get(i));
            }
        }

        return projetosResult;
    }
}
