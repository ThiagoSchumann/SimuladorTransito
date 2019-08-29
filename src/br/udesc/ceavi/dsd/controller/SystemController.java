package br.udesc.ceavi.dsd.controller;

import br.udesc.ceavi.dsd.abstractfactory.AbstractFactory;
import br.udesc.ceavi.dsd.abstractfactory.FactoryMonitor;
import br.udesc.ceavi.dsd.abstractfactory.FactorySemaphore;
import java.io.FileNotFoundException;

/**
 *
 * @author thiag
 */
public class SystemController {

    private static SystemController instance;

    public static synchronized SystemController getInstance() {
        if (instance == null) {
            instance = new SystemController();
        }
        return instance;
    }

    private MalhaController malhaController;

    private AbstractFactory factory;

    private SystemController() {
    }

    /**
     * Ler Arquivo onde contem a matriz
     *
     * @param text
     */
    public void readFile(String text) throws FileNotFoundException, Exception {
        LerArquivoMatrix ler = new LerArquivoMatrix(text);
        malhaController = new MalhaController(ler.getMatrix());
    }

    /**
     * Informa se todos os dados para a simulacao foram informado de forma
     * coerente
     *
     * @return true sim
     */
    public boolean readySimulation() {
        return true;
    }

    public int getColumn() {
        return malhaController.getColumn();
    }

    public int getRow() {
        return malhaController.getRow();
    }

    public Object getCasa(int col, int row) {
        return malhaController.getCasaValue(col, row);
    }

    public void startUsingSemaforo() {
    }

    public void startUsingMonitor() {
    }

    public MalhaController getMalhaController() {
        return malhaController;
    }

    public AbstractFactory getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory.equals("Monitor") ? new FactoryMonitor() : new FactorySemaphore();
    }

}
