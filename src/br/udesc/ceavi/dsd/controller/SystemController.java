package br.udesc.ceavi.dsd.controller;

import br.udesc.ceavi.dsd.util.LerArquivoMatrix;
import br.udesc.ceavi.dsd.abstractfactory.AbstractFactory;
import br.udesc.ceavi.dsd.abstractfactory.FactoryMonitor;
import br.udesc.ceavi.dsd.abstractfactory.FactorySemaphore;
import br.udesc.ceavi.dsd.model.carro.Carro;
import br.udesc.ceavi.dsd.model.carro.ICarro;
import br.udesc.ceavi.dsd.view.FramePrincipalObserver;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

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

    private int numeroDeCarroObjetivo;

    private boolean simulationAtivo;

    private Random random;

    private Map<Long, ICarro> carrosEmEspera;
    private Map<Long, ICarro> carrosEmMalha;
    private List<FramePrincipalObserver> observers;

    private SystemController() {
        this.random = new Random();
        this.carrosEmMalha = new HashMap<>();
        this.carrosEmEspera = new HashMap<>();
        this.simulationAtivo = false;
        this.observers = new ArrayList<>();
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
        this.simulationAtivo = true;

        for (int i = 0; i < numeroDeCarroObjetivo; i++) {
            newCarroEmMalha();
        }

        Thread respawn = new Thread(() -> addAutomatico());
        respawn.start();
    }

    private void newCarroEmMalha() {
        Carro carro = new Carro();
        carrosEmEspera.put(carro.getId(), carro);
        carro.enterSimulation(malhaController.getRespawnAleatorio());
    }

    public void notificarEntreiNaMalha(ICarro carro) {
        carrosEmEspera.remove(carro.getId());
        carrosEmMalha.put(carro.getId(), carro);
        SwingUtilities.invokeLater(() -> observers.forEach((observer) -> observer.notificarNumeroDeCarro(carrosEmMalha.size())));
    }

    public void notificarCarroMorto(ICarro carro) {
        carrosEmMalha.remove(carro.getId());
        SwingUtilities.invokeLater(() -> observers.forEach((observer) -> observer.notificarNumeroDeCarro(carrosEmMalha.size())));
    }

    public void addObserver(FramePrincipalObserver aThis) {
        this.observers.add(aThis);
    }

    //Runneable
    private void addAutomatico() {
        while (simulationAtivo) {
            for (int i = (carrosEmEspera.size() + carrosEmMalha.size()); i < numeroDeCarroObjetivo; i++) {
                newCarroEmMalha();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SystemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        List<ICarro> arrayList = new ArrayList<>();
        arrayList.addAll(carrosEmMalha.values());
        arrayList.addAll(carrosEmEspera.values());

        arrayList.forEach((value) -> {
            try {
                value.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(SystemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        observers.forEach(obs -> obs.notificarSimulacaoFinalizada());
    }

    public void pararRepawn() {
        simulationAtivo = false;
    }
}
