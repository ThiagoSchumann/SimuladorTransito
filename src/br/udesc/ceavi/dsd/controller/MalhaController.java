package br.udesc.ceavi.dsd.controller;

import br.udesc.ceavi.dsd.abstractfactory.AbstractFactory;
import br.udesc.ceavi.dsd.model.casa.Casa;
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
    private int numCasasValida;
    private Casa[][] matrixCasa;

    private List<Casa> casasRespawn;
    private List<Casa> casasDeath;

    private List<TableObserver> observers;

    public MalhaController(int[][] matrix) {
        this.matrix = matrix;
        this.observers = new ArrayList<>();
        this.matrixCasa = new Casa[matrix.length][matrix[0].length];
        this.casasDeath = new ArrayList<>();
        this.casasRespawn = new ArrayList<>();
        this.numCasasValida = 0;
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
                if (matrixCasa[coluna][row].getValor() != 0) {
                    numCasasValida++;
                }
            }
        }
    }

    private void setExtremidadeCasa() {
        for (int linha = 0; linha < getRow(); linha++) {
            for (int coluna = 0; coluna < getColumn(); coluna++) {
                //Se houver valor na casa significa que a mesma faz parte da malha
                if (matrixCasa[coluna][linha].getValor() != 0) {
                    int lado = 0;
                    int valorCasa = matrixCasa[coluna][linha].getValor();

                    if (coluna == 0) {
                        lado = 1;
                    } else if (linha == 0) {
                        lado = 2;
                    } else if (coluna == getColumn() - 1) {
                        lado = 3;
                    } else if (linha == getRow() - 1) {
                        lado = 4;
                    }
                    //lado == 1 - Primeira Coluna
                    switch (lado) {
                        case 1:
                            switch (valorCasa) {
                                case 2:
                                    this.casasRespawn.add(matrixCasa[coluna][linha]);
                                    //add casasRespawn
                                    break;
                                case 4:
                                    this.casasDeath.add(matrixCasa[coluna][linha]);
                                    //add casasDeath
                                    break;
                            }
                            break;
                        case 2:
                            switch (valorCasa) {
                                case 1:
                                    this.casasDeath.add(matrixCasa[coluna][linha]);
                                    //add casasDeath
                                    break;
                                case 3:
                                    this.casasRespawn.add(matrixCasa[coluna][linha]);
                                    //add casasRespawn
                                    break;
                            }
                            break;
                        case 3:
                            switch (valorCasa) {
                                case 2:
                                    this.casasDeath.add(matrixCasa[coluna][linha]);
                                    //add casasDeath
                                    break;
                                case 4:
                                    this.casasRespawn.add(matrixCasa[coluna][linha]);
                                    //add casasRespawn
                                    break;
                            }
                            break;
                        case 4:
                            switch (valorCasa) {
                                case 1:
                                    this.casasRespawn.add(matrixCasa[coluna][linha]);
                                    //add casasRespawn
                                    break;
                                case 3:
                                    this.casasDeath.add(matrixCasa[coluna][linha]);
                                    //add casasDeath
                                    break;
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public void drawExpecialCasa() {
        for (TableObserver observer : observers) {
            for (Casa casa : casasRespawn) {
                observer.drawRespawn(casa.getColunm(), casa.getRow());
            }
            for (Casa casa : casasDeath) {
                observer.drawDeath(casa.getColunm(), casa.getRow());
            }
        }
    }

    public int getNumCasasValida() {
        return numCasasValida;
    }

    public void removeObservers() {
        observers.clear();
    }
    
}
