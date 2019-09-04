package br.udesc.ceavi.dsd.model.carro;

import br.udesc.ceavi.dsd.command.Command;
import br.udesc.ceavi.dsd.command.EntraNaMalhaCommand;
import br.udesc.ceavi.dsd.controller.SystemController;
import br.udesc.ceavi.dsd.model.casa.ICasa;
import br.udesc.ceavi.dsd.util.Image;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo C. Santos 27/08/2019
 */
public class Carro extends Thread implements ICarro {

    private boolean ativo;
    private final int rgb;
    private ICasa casa;
    private Command rota;
    private SystemController systemController;
    private Random random;

    public Carro() {
        this.random = new Random();
        this.systemController = SystemController.getInstance();
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
    public void obterRota() {
        rota = this.casa.getRota();
    }

    @Override
    public void setCasa(ICasa newCasa) {
        this.casa = newCasa;
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
    public void sleep(int tempo) {
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException ex) {
            Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void enterSimulation(ICasa casaAleatoria) {
        rota = new EntraNaMalhaCommand(this, casaAleatoria);
        start();
    }

    @Override
    public void mover() {
        rota.executar();
        rota = null;
    }

    @Override
    public Random getRandom() {
        return random;
    }

    @Override
    public void run() {
        mover();

        while (ativo) {
            obterRota();
            if (rota == null) {
                break;
            }
            mover();
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
