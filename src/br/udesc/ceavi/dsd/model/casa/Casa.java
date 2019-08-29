package br.udesc.ceavi.dsd.model.casa;

import br.udesc.ceavi.dsd.command.Command;
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

    public Casa(int valor,int colunm,int row) {
        this.movimentacoes = new ArrayList<>();
        this.valor = valor;
        this.colunm = colunm;
        this.row = row;
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

    public int getValor() {
        return valor;
    }

}
