package br.udesc.ceavi.dsd.model.carro;

import br.udesc.ceavi.dsd.model.casa.ICasa;

/**
 *
 * @author Gustavo C. Santos 29/08/2019
 */
public interface ICarro {

    public void desativar();

    public void enterSimulation(ICasa casaAleatoria);

    @Override
    public boolean equals(Object obj);

    public int getRBG();

    public void obterRota();

    public void mover();

    public void setCasa(ICasa newCasa);

    public ICasa getCasa();

    public void sleep(int tempo) throws InterruptedException;

    public long getId();

}
