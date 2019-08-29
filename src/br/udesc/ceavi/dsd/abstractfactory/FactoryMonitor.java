package br.udesc.ceavi.dsd.abstractfactory;

import br.udesc.ceavi.dsd.model.casa.CasaMonitor;
import br.udesc.ceavi.dsd.model.carro.CarroMonitor;
import br.udesc.ceavi.dsd.model.carro.Carro;
import br.udesc.ceavi.dsd.model.casa.Casa;

/**
 *
 * @author Gustavo C. Santos
 * @since 28/08/2019
 */
public class FactoryMonitor implements AbstractFactory{

    @Override
    public Carro createCarro() {
        return new CarroMonitor();
    }

    @Override
    public Casa createCasa(int valor, int colunm, int row) {
        return new CasaMonitor(valor,colunm,row);
    }

}
