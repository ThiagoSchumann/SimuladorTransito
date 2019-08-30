package br.udesc.ceavi.dsd.model.casa;

import br.udesc.ceavi.dsd.command.Command;
import br.udesc.ceavi.dsd.model.carro.ICarro;
import java.util.List;

/**
 *
 * @author Gustavo C. Santos
 * 29/08/2019
 */
public interface ICasa {

    void addRota(Command command);

    int getColunm();

    Command getRota();

    int getRow();

    int getValor();

    void liberarRecurso();

    void mover(ICarro carro);

    void setCarro(ICarro carro);

    boolean reservarCasa();

    public ICarro getCarro();

    public void repintar();

    public ICarro removerCarro();

}
