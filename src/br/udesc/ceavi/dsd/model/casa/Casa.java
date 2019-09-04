package br.udesc.ceavi.dsd.model.casa;

import br.udesc.ceavi.dsd.command.Command;
import br.udesc.ceavi.dsd.controller.SystemController;
import br.udesc.ceavi.dsd.model.carro.ICarro;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 *
 * @author Gustavo C. Santos
 * @since 27/08/2019
 */
public abstract class Casa implements ICasa {

    protected ICarro carro;
    private int colunm, row;
    private int valor;

    protected List<Command> movimentacoes;

    public Casa(int valor, int colunm, int row) {
        this.movimentacoes = new ArrayList<>();
        this.valor = valor;
        this.colunm = colunm;
        this.row = row;
    }

    @Override
    public void addRota(Command command) {
        movimentacoes.add(command);
    }

    @Override
    public Command getRota() {
        if (movimentacoes.size() != 1) {
            Collections.shuffle(movimentacoes);
        }
        return movimentacoes.get(0);
    }

    @Override
    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Casa{" + "colunm=" + colunm + ", row=" + row + ", valor=" + valor + '}';
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColunm() {
        return colunm;
    }

    @Override
    public ICarro getCarro() {
        return carro;
    }

    @Override
    public void setCarro(ICarro carro) {
        this.carro = carro;
    }

    @Override
    public void repintar() {
        SystemController.getInstance().getMalhaController().clearCasa(this.colunm, this.row);
        if (carro != null) {
            SystemController.getInstance().getMalhaController().printCasaCarro(carro.getRBG(), this.colunm, this.row);
        }
    }

    @Override
    public ICarro removerCarro() {
        ICarro p = this.carro;
        this.carro = null;
        return p;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.colunm;
        hash = 59 * hash + this.row;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Casa other = (Casa) obj;
        if (this.colunm != other.colunm) {
            return false;
        }
        if (this.row != other.row) {
            return false;
        }
        return true;
    }
    

}
