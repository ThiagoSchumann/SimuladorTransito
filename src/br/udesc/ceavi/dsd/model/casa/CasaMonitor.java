package br.udesc.ceavi.dsd.model.casa;

import br.udesc.ceavi.dsd.model.carro.ICarro;


/**
 *
 * @author Gustavo C. Santos
 * @since 28/08/2019
 */
public class CasaMonitor extends Casa {

    public CasaMonitor(int valor, int colunm, int row) {
        super(valor, colunm, row);
    }

    @Override
    public void mover(ICarro carro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void liberarRecurso() {
        this.notify();
    }

    
    @Override
    public boolean reservarCasa()  {
           return true;
    }


}
