package br.udesc.ceavi.dsd.model.carro;

import br.udesc.ceavi.dsd.model.casa.ICasa;

/**
 *
 * @author Gustavo C. Santos 29/08/2019
 */
public interface ICarro {

    void desativar();

    void enterSimulation(ICasa casaAleatoria);

    @Override
    boolean equals(Object obj);

    int getRBG();

    void getRota();

    void mover();

    void setCasa(ICasa newCasa);

    public ICasa getCasa();

    public void sleep(int tempo) throws InterruptedException;

}
