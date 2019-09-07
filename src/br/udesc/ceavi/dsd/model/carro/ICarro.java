package br.udesc.ceavi.dsd.model.carro;

import br.udesc.ceavi.dsd.model.casa.ICasa;
import java.util.Random;

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

    public long getId();

    public void join() throws InterruptedException;

    public Random getRandom();

    public void sleep(int nextInt);

    public int getVelocidade();

}
