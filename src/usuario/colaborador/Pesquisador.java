package usuario.colaborador;

import publicacao.*;
import projeto.*;

import java.util.ArrayList;

public class Pesquisador extends Colaborador {
    public Pesquisador(String nome, String email) {
        super(nome, email);
    }

    public Pesquisador(String nome, String email, ArrayList<Publicacao> publicacoes, ArrayList<Projeto> projetos) {
        super(nome, email, publicacoes, projetos);
    }
}
