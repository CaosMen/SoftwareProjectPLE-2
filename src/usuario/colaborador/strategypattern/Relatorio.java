package usuario.colaborador.strategypattern;

import usuario.colaborador.Colaborador;

public class Relatorio {
    private IFRelatorio ifRelatorio;

    public Relatorio(IFRelatorio ifRelatorio) {
        this.ifRelatorio = ifRelatorio;
    }

    public String gerarRelatorio(Colaborador colaborador) {
        return ifRelatorio.gerar(colaborador);
    }
}
