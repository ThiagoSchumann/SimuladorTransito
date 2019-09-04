package br.udesc.ceavi.dsd.command;

import br.udesc.ceavi.dsd.controller.SystemController;
import br.udesc.ceavi.dsd.model.carro.Carro;
import br.udesc.ceavi.dsd.model.carro.ICarro;
import br.udesc.ceavi.dsd.model.casa.ICasa;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo C. Santos
 * @since 30/08/2019
 */
public class EntraNaMalhaCommand implements Command {

    private ICasa casa;
    private ICarro carro;

    public EntraNaMalhaCommand(Carro carro, ICasa casa) {
        this.casa = casa;
        this.carro = carro;
    }

    @Override
    public void executar() {
        SystemController system = SystemController.getInstance();
        casa.mover(carro);
        casa.repintar();
        system.notificarEntreiNaMalha(carro);
    }

}
