package usuario.colaborador.strategypattern;

import usuario.colaborador.Colaborador;

public class RelatorioColaborador implements IFRelatorio {
    public String gerar(Colaborador colaborador) {
        return "\nNome: " + colaborador.getNome() + "\n"
             + "E-mail: " + colaborador.getEmail() + "\n"
             + "Projetos: \n"
             + this.printProjetosByStatus(colaborador, "Em andamento")
             + this.printProjetosByStatus(colaborador, "Concluído")
             + "Produções Acadêmicas: \n"
             + this.printPublicacoes(colaborador);
    }
}
