package br.udesc.ceavi.dsd.model.carro;

import br.udesc.ceavi.dsd.model.casa.Casa;
import br.udesc.ceavi.dsd.command.Command;
import br.udesc.ceavi.dsd.util.Image;

/**
 *
 * @author Gustavo C. Santos 27/08/2019
 */
public abstract class Carro extends Thread implements ICarro {

    protected boolean ativo;
    protected final int rgb;
    protected Casa casa;
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
        rota.setCarro(this);
    }

    @Override
    public abstract void validar();

    @Override
    public abstract void mover();

    @Override
    public abstract void setCasa(Casa newCasa);
}
