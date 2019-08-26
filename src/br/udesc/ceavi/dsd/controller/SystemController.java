/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     * @param text
     * @return true sucesso na leitura
     */
    public boolean readFile(String text) {
        try {
            LerArquivoMatrix ler = new LerArquivoMatrix(text);
            int[][] matrix = ler.getMatrix();
            for (int linha = 0; linha < matrix[0].length; linha++) {
                for (int coluna = 0; coluna < matrix.length; coluna++) {
                    System.out.print(matrix[coluna][linha] + " ");
                }
                System.out.println("");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SystemController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SystemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
     * Informa se todos os dados para a simulacao foram informado de forma coerente
     * @return true sim
     */
    public boolean readySimulation() {
        return false;
    }

}
