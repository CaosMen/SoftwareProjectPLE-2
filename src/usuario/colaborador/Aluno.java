package usuario.colaborador;

import publicacao.*;
import projeto.*;

import java.util.ArrayList;

public class Aluno extends Colaborador {
    private int tipo;
    String[] tipos = {"Aluno de Graduação", "Aluno de Mestrado", "Aluno de Doutorado"};

    public Aluno(String nome, String email, int tipo) {
        super(nome, email);
        this.tipo = tipo;
    }

    public Aluno(String nome, String email, int tipo, ArrayList<Publicacao> publicacoes, ArrayList<Projeto> projetos) {
        super(nome, email, publicacoes, projetos);
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipos[this.tipo];
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
