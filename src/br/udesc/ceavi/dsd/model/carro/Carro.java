package br.udesc.ceavi.dsd.model.carro;

import br.udesc.ceavi.dsd.command.Command;
import br.udesc.ceavi.dsd.model.casa.ICasa;
import br.udesc.ceavi.dsd.util.Image;
import java.util.Objects;

/**
 *
 * @author Gustavo C. Santos 27/08/2019
 */
public abstract class Carro extends Thread implements ICarro {

    protected boolean ativo;
    protected final int rgb;
    protected ICasa casa;
    protected Command rota;

    public Carro() {
        this.ativo = true;
        this.rgb = Image.gerarRGB();
    }

    @Override
    public void desativar() {
        this.ativo = false;
    }

    @Override
    public int getRBG() {
        return rgb;
    }

    @Override
    public void getRota() {
        rota = this.casa.getRota();
    }

    @Override
    public void setCasa(ICasa newCasa) {
        this.casa = newCasa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.ativo ? 1 : 0);
        hash = 59 * hash + this.rgb;
        hash = 59 * hash + Objects.hashCode(this.casa);
        hash = 59 * hash + Objects.hashCode(this.rota);
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
        final Carro other = (Carro) obj;
        return this.getId() == other.getId();
    }

    @Override
    public ICasa getCasa() {
        return casa;
    }

    @Override
    public void sleep(int tempo) throws InterruptedException{
        Thread.sleep(tempo);
    }
    
}
