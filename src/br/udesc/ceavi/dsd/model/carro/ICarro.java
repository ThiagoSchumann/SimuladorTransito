package br.udesc.ceavi.dsd.model.carro;

import br.udesc.ceavi.dsd.model.casa.Casa;

/**
 *
 * @author Gustavo C. Santos 27/08/2019
 */
public interface ICarro {

    public void desativar();

    public int getRBG();
    
    public void getRota();
    
    public void validar();
    
    public void mover();
    
    public void setCasa(Casa newCasa);
}
