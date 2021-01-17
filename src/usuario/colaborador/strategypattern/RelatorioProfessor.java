package usuario.colaborador.strategypattern;

import java.util.ArrayList;

import usuario.colaborador.Colaborador;
import usuario.colaborador.Professor;
import orientacao.Orientacao;

public class RelatorioProfessor implements IFRelatorio {
    public String printOrientacoes(Colaborador colaborador) {
        Professor professor = (Professor)colaborador;

        ArrayList<Orientacao> orientacoes = professor.getOrientacoes();

        StringBuilder bld = new StringBuilder();

        if (!orientacoes.isEmpty()) {
            bld.append("Orientações:\n");
            for (int i = 0; i < orientacoes.size(); i++) {
                Orientacao orientacao = orientacoes.get(i);
                bld.append("    [" + (i + 1) + "] Título: " + orientacao.getTitulo() + ", Ano de Início: " + orientacao.getAnoOrientacao() + ", Aluno: " + orientacao.getOrientando().getNome() + "\n");
            }
        } else {
            bld.append("Sem Orientações!\n");
        }

        return bld.toString();
    }

    public String gerar(Colaborador colaborador) {
        return "\nNome: " + colaborador.getNome() + "\n"
             + "E-mail: " + colaborador.getEmail() + "\n"
             + "Projetos: \n"
             + this.printProjetosByStatus(colaborador, "Em andamento")
             + this.printProjetosByStatus(colaborador, "Concluído")
             + "Produções Acadêmicas: \n"
             + this.printPublicacoes(colaborador)
             + this.printOrientacoes(colaborador);
    }
}
