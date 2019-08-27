package br.udesc.ceavi.dsd.controller;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiag
 */
public class SystemController {

    private static SystemController instance;

    private MalhaController malhaController;

    public static synchronized SystemController getInstance() {
        if (instance == null) {
            instance = new SystemController();
        }
        return instance;
    }

    private SystemController() {
    }

    /**
     * Ler Arquivo onde contem a matriz
     *
     * @param text
     * @return true sucesso na leitura
     */
    public boolean readFile(String text) {
        try {
            LerArquivoMatrix ler = new LerArquivoMatrix(text);
            malhaController = new MalhaController(ler.getMatrix());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SystemController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SystemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
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

    public Object getMatrix(int col, int row) {
        return malhaController.getCasa(col, row);
    }

    public void startUsingSemaforo() {
    }

    public void startUsingMonitor() {
    }

    public MalhaController getMalhaController() {
        return malhaController;
    }

}
