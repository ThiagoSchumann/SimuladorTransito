package br.udesc.ceavi.dsd.command;

import br.udesc.ceavi.dsd.model.carro.Carro;

/**
 *
 * @author Gustavo C. Santos
 * @since 27/08/2019
 */
public interface Command {

    public void execute();
    
    public void setCarro(Carro carro);
}
