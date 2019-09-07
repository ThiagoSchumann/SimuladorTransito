package br.udesc.ceavi.dsd.model.casa;

import br.udesc.ceavi.dsd.model.carro.ICarro;
import br.udesc.ceavi.dsd.strategy.Movimentacao;

/**
 *
 * @author Gustavo C. Santos 29/08/2019
 */
public interface ICasa {

    public void addRota(Movimentacao command);

    public int getColunm();

    public Movimentacao getRota();

    public int getRow();

    public int getValor();

    public void liberarRecurso();

    public void mover(ICarro carro);

    public void setCarro(ICarro carro);

    public boolean reservarCasa();

    public ICarro getCarro();

    public void repintar();

    public ICarro removerCarro();


}
