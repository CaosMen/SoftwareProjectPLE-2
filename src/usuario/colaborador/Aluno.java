package usuario.colaborador;

import publicacao.*;
import projeto.*;

import java.util.ArrayList;

public class Aluno extends Colaborador {
    private int tipo;
    String[] arrayTipo = {"Aluno de Graduação", "Aluno de Mestrado", "Aluno de Doutorado"};

    public Aluno(String nome, String email, int tipo) {
        this(nome, email, tipo, new ArrayList<Publicacao>(), new ArrayList<Projeto>());
    }

    public Aluno(String nome, String email, int tipo, ArrayList<Publicacao> publicacoes, ArrayList<Projeto> projetos) {
        super(nome, email, publicacoes, projetos);
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.arrayTipo[this.tipo];
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
