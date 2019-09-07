package br.udesc.ceavi.dsd.model.casa;

import br.udesc.ceavi.dsd.model.carro.ICarro;
import br.udesc.ceavi.dsd.strategy.Movimentacao;

/**
 *
 * @author Gustavo C. Santos 29/08/2019
 */
public interface ICasa {

    public void addRota(Movimentacao command);

    public Movimentacao getRota();

    public int getColunm();

    public int getRow();

    public int getValor();

    public void liberarRecurso();

    public void mover(ICarro carro);

    public boolean reservarCasa();

    public void setCarro(ICarro carro);

    public ICarro getCarro();

    public ICarro removerCarro();

    public void repintar();

}
