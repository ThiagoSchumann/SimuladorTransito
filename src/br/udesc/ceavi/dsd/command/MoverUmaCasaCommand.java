package br.udesc.ceavi.dsd.command;

import br.udesc.ceavi.dsd.model.casa.ICasa;

/**
 *
 * @author Gustavo C. Santos
 * @since 29/08/2019
 */
public class MoverUmaCasaCommand implements Command {

    private ICasa origem;
    private ICasa destino;

    @Override
    public void executar() {
        destino.mover(destino.getCarro());
        origem.setCarro(null);
        origem.liberarRecurso();
        origem.repintar();
        destino.repintar();
    }

}
