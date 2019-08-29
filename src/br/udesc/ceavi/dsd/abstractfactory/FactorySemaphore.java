package br.udesc.ceavi.dsd.abstractfactory;

import br.udesc.ceavi.dsd.model.carro.Carro;
import br.udesc.ceavi.dsd.model.carro.CarroSemaforo;
import br.udesc.ceavi.dsd.model.casa.Casa;
import br.udesc.ceavi.dsd.model.casa.CasaSemaforo;

/**
 *
 * @author Gustavo C. Santos
 * @since 28/08/2019
 */
public class FactorySemaphore implements AbstractFactory{

    @Override
    public Carro createCarro() {
        return new CarroSemaforo();
    }

    @Override
    public Casa createCasa(int valor, int colunm, int row) {
        return new CasaSemaforo(valor, colunm, row);
    }

}
