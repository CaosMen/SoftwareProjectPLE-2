package usuario.colaborador.strategypattern;

import java.util.ArrayList;

import usuario.colaborador.Colaborador;
import projeto.Projeto;
import publicacao.Publicacao;

public interface IFRelatorio {
    public String gerar(Colaborador colaborador);

    default String printProjetosByStatus(Colaborador colaborador, String status) {
        ArrayList<Projeto> projetos = colaborador.getProjetos();

        StringBuilder bld = new StringBuilder();

        int j = 1;

        for (int i = 0; i < projetos.size(); i++) {
            Projeto projetoAtual = projetos.get(i);

            if (projetoAtual.getStatus().equals(status)) {
                bld.append("\n    [" + j + "] " + projetoAtual.toString().replace("\n", "\n    ") + "\n");

                j++;
            }
        }

        if (j > 1) {
            bld.insert(0, status + ":\n");
            bld.append("\n");
        } else {
            bld.append("Sem projetos com o status " + status + "!\n");
        }

        return bld.toString();
    }

    default String printPublicacoes(Colaborador colaborador) {
        ArrayList<Publicacao> publicacoes = colaborador.getPublicacoes();

        StringBuilder bld = new StringBuilder();

        if (!publicacoes.isEmpty()) {
            bld.append("Publicações:\n");

            for (int i = 0; i < publicacoes.size(); i++) {
                bld.append("\n    [" + (i + 1) + "] " + publicacoes.get(i).toStringProjeto().replace("\n", "\n    ") + "\n");
            }

            bld.append("\n");
        } else {
            bld.append("Sem Publicações!\n");
        }

        return bld.toString();
    }
}
