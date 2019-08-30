package br.udesc.ceavi.dsd.command;

import br.udesc.ceavi.dsd.controller.SystemController;
import br.udesc.ceavi.dsd.model.carro.ICarro;
import br.udesc.ceavi.dsd.model.casa.ICasa;

/**
 *
 * @author Gustavo C. Santos
 * @since 29/08/2019
 */
public class MatarCarroCommand implements Command {

    private ICasa origem;

    public MatarCarroCommand(ICasa origem) {
        this.origem = origem;
    }

    @Override
    public void executar() {
        ICarro carro = origem.getCarro();
        origem.setCarro(null);
        origem.liberarRecurso();
        origem.repintar();

        carro.desativar();
        SystemController.getInstance().notificarCarroMorto(carro);
    }

}
