package br.udesc.ceavi.dsd.abstractfactory;

import br.udesc.ceavi.dsd.model.carro.Carro;
import br.udesc.ceavi.dsd.model.casa.Casa;

/**
 *
 * @author Gustavo C. Santos 28/08/2019
 */
public interface AbstractFactory {

    public Carro createCarro();

    public Casa createCasa(int valor, int colunm, int row);
}
