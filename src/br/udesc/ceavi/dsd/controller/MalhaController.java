package br.udesc.ceavi.dsd.controller;

import br.udesc.ceavi.dsd.abstractfactory.AbstractFactory;
import br.udesc.ceavi.dsd.model.casa.Casa;
import br.udesc.ceavi.dsd.util.Image;
import br.udesc.ceavi.dsd.view.TableObserver;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gustavo C. Santos
 * @since 27/08/2019
 */
public class MalhaController {

    private int[][] matrix;

    private Casa[][] matrixCasa;

    private List<Casa> casasRespawn;
    private List<Casa> casasDeath;

    private List<TableObserver> observers;

    public MalhaController(int[][] matrix) {
        this.matrix = matrix;
        this.observers = new ArrayList<>();
        this.matrixCasa = new Casa[matrix.length][matrix[0].length];
    }

    public void initMalha() {
        initCasas();
        setExtremidadeCasa();
    }

    public void anexar(TableObserver observer) {
        this.observers.add(observer);
    }

    public int getColumn() {
        return matrixCasa.length;
    }

    public int getRow() {
        return matrixCasa[0].length;
    }

    public Object getCasaValue(int col, int row) {
        return matrixCasa[col][row].getValor();
    }


    private void initCasas() {
        AbstractFactory factory = SystemController.getInstance().getFactory();
        for (int row = 0; row < matrix[0].length; row++) {
            for (int coluna = 0; coluna < matrix.length; coluna++) {
                matrixCasa[coluna][row] = factory.createCasa(matrix[coluna][row], coluna, row);
            }
        }
    }

    private void setExtremidadeCasa() {
        for (int linha = 0; linha < matrixCasa[0].length; linha++) {
            for (int coluna = 0; coluna < matrixCasa.length; coluna++) {
                //Se houver valor na casa significa que a mesma faz parte da malha
                if (matrixCasa[coluna][linha].getValor() != 0) {
                    int lado = 0;
                    int valorCasa = matrixCasa[coluna][linha].getValor();
                    if (coluna == 0) lado = 1;
                    else if (linha == 0) lado = 2;
                    else if (coluna == matrixCasa.length - 1) lado = 3;
                    else if (linha == matrixCasa.length - 1) lado = 4;
                    //lado == 1 - Primeira Coluna
                    if (lado == 1){
                        switch (valorCasa){
                            case 2:
                                //add casasRespawn
                                break;
                            case 4:
                                //add casasDeath
                                break;                            
                        }
                    }
                    //lado == 2 - Primeira Linha
                    else if (lado == 2){
                       switch (valorCasa){
                            case 1:
                                //add casasDeath
                                break;                            
                            case 3:
                                //add casasRespawn
                                break;
                        }   
                    }
                    //lado == 3 - Ultima Coluna
                    else if (lado == 3){
                        switch (valorCasa){
                            case 2:
                                //add casasDeath 
                                break;
                            case 4:
                                //add casasRespawn
                                break;                            
                        } 
                    }
                    //lado == 4 - Ultima Linha
                    else if (lado == 4){
                       switch (valorCasa){
                            case 1:
                                //add casasRespawn 
                                break;                            
                            case 3:
                                //add casasDeath
                                break;                       
                        }  
                    }
                }
            }
        }
    }

    
}
