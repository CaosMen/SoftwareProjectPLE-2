package usuario.colaborador;

import publicacao.*;
import projeto.*;
import orientacao.*;

import java.util.ArrayList;

public class Professor extends Colaborador {
    private ArrayList<Orientacao> orientacoes;

    public Professor(String nome, String email) {
        super(nome, email);
        this.orientacoes = new ArrayList<Orientacao>();
    }

    public Professor(String nome, String email, ArrayList<Publicacao> publicacoes, ArrayList<Projeto> projetos) {
        super(nome, email, publicacoes, projetos);
        this.orientacoes = new ArrayList<Orientacao>();
    }
    
    public ArrayList<Orientacao> getOrientacoes() {
        return orientacoes;
    }

    public void setOrientacoes(ArrayList<Orientacao> orientacoes) {
        this.orientacoes = orientacoes;
    }

    public void addOrientacao(Orientacao orientacao) {
        if (!this.orientacoes.contains(orientacao)) {
            for (int i = 0; i < this.orientacoes.size(); i++) {
                if (this.orientacoes.get(i).getAnoOrientacao() < orientacao.getAnoOrientacao()) {
                    this.orientacoes.add(i, orientacao);
                    return;
                }
            }

            this.orientacoes.add(orientacao);
        }
    }

    public void removeOrientacao(Orientacao orientacao) {
        if (this.orientacoes.contains(orientacao)) {
            this.orientacoes.remove(orientacao);
        }
    }
}
