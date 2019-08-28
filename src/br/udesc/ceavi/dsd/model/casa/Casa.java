package br.udesc.ceavi.dsd.model.casa;

import br.udesc.ceavi.dsd.Command.Command;
import br.udesc.ceavi.dsd.model.carro.Carro;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Gustavo C. Santos
 * @since 27/08/2019
 */
public abstract class Casa implements ICasa {

    protected Carro carro;
    private int colunm, row;
    private int valor;
    
    protected List<Command> movimentacoes;

    public Casa() {
        this.movimentacoes = new ArrayList<>();
    }

    public void addRota(Command command) {
        movimentacoes.add(command);
    }

    public Command getRota() {
        if (movimentacoes.size() != 1) {
            Collections.shuffle(movimentacoes);
        }
        return movimentacoes.get(0);
    }

}
