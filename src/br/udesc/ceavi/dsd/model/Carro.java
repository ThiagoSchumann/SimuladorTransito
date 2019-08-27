package br.udesc.ceavi.dsd.model;

import br.udesc.ceavi.dsd.util.Image;

/**
 *
 * @author Gustavo C. Santos 27/08/2019
 */
public class Carro extends Thread {

    private boolean ativo;
    private final int rgb;

    public Carro() {
        this.ativo = true;
        this.rgb = Image.gerarRGB();
    }

    public void desativar() {
        this.ativo = false;
    }

    public int getRBG() {
        return rgb;
    }
}
