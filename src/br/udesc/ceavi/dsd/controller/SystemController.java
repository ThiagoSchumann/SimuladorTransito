package br.udesc.ceavi.dsd.controller;

import br.udesc.ceavi.dsd.util.LerArquivoMatrix;
import br.udesc.ceavi.dsd.abstractfactory.AbstractFactory;
import br.udesc.ceavi.dsd.abstractfactory.FactoryMonitor;
import br.udesc.ceavi.dsd.abstractfactory.FactorySemaphore;
import br.udesc.ceavi.dsd.command.Command;
import br.udesc.ceavi.dsd.command.CommandInvoker;
import br.udesc.ceavi.dsd.model.carro.Carro;
import br.udesc.ceavi.dsd.model.carro.ICarro;
import br.udesc.ceavi.dsd.view.FramePrincipalObserver;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
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

    private int numeroDeCarroNaSimulacao;
    private int numeroDeCarroObjetivo;

    private boolean simulationAtivo;

    private Random random = new Random();

    private Queue<ICarro> carrosEmEspera;
    private List<ICarro> carrosEmMalha;
    private CommandInvoker commandInvoker;
    private List<FramePrincipalObserver> observers;

    private SystemController() {
        this.commandInvoker = new CommandInvoker();
        this.carrosEmMalha = new ArrayList<>();
        this.carrosEmEspera = new ArrayDeque<>();
        this.simulationAtivo = false;
        this.numeroDeCarroNaSimulacao = 0;
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
        new Thread(() -> newCarroEmMalha()).start();
    }

    private void newCarroEmMalha() {
        while (true) {
            for (int i = carrosEmMalha.size(); i < numeroDeCarroObjetivo; i++) {
                Carro carro = new Carro();
                carro.enterSimulation(malhaController.getRespawnAleatorio());
                try {
                    System.out.println("Tamanho da Lista : " + carrosEmMalha.size());
                    System.out.println("I: " + i);
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SystemController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void notificarEntreiNaMalha(ICarro carro) {
        carrosEmMalha.add(carro);
        SwingUtilities.invokeLater(() -> observers.forEach((observer) -> observer.notificarNumeroDeCarro(carrosEmMalha.size())));
    }

    public Random getRandom() {
        return random;
    }

    public void execute(Command command) {
        commandInvoker.execute(command);
    }

    //Melhorar Logica
    public void notificarCarroMorto(ICarro carro) {
        carrosEmMalha.remove(carro);
        SwingUtilities.invokeLater(() -> observers.forEach((observer) -> observer.notificarNumeroDeCarro(carrosEmMalha.size())));
    }

    public void addObserver(FramePrincipalObserver aThis) {
        this.observers.add(aThis);
    }
}
