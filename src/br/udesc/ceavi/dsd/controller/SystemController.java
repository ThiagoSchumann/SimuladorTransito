package br.udesc.ceavi.dsd.controller;

import br.udesc.ceavi.dsd.abstractfactory.AbstractFactory;
import br.udesc.ceavi.dsd.abstractfactory.FactoryMonitor;
import br.udesc.ceavi.dsd.abstractfactory.FactorySemaphore;
import br.udesc.ceavi.dsd.model.carro.Carro;
import br.udesc.ceavi.dsd.model.carro.ICarro;
import br.udesc.ceavi.dsd.model.casa.Casa;
import br.udesc.ceavi.dsd.model.casa.ICasa;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

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

    private int numeroDeCarroNaSimulacao;
    private int numeroDeCarroObjetivo;

    private boolean simulationAtivo;

    private Random random = new Random();

    private Queue<ICarro> carrosEmEspera = new ArrayDeque<>();
    private List<ICarro> carrosEmMalha = new ArrayList<>();

    private SystemController() {
        this.simulationAtivo = false;
        this.numeroDeCarroNaSimulacao = 0;
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

    public MalhaController getMalhaController() {
        return malhaController;
    }

    public AbstractFactory getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory.equals("Monitor") ? new FactoryMonitor() : new FactorySemaphore();
    }

    /**
     * Rebute Malha
     */
    public void rebutMalha() {
        this.malhaController.removeObservers();
    }

    public void startSimulation(int numeroCarro) {
        this.numeroDeCarroObjetivo = numeroCarro;
        while (simulationAtivo) {
            while ((numeroDeCarroNaSimulacao + carrosEmEspera.size()) < numeroCarro) {
                carrosEmEspera.add(factory.createCarro());
            }
            for (ICarro carro : carrosEmEspera) {
                ICasa casaAleatoria = malhaController.getCasaAleatoria();
                carro.enterSimulation(casaAleatoria);
            }
        }
    }

    public void notificarEntreiNaMalha(Carro carro) {
        carrosEmEspera.remove(carro);
        carrosEmMalha.add(carro);
    }

    public Random getRandom() {
        return random;
    }

}
