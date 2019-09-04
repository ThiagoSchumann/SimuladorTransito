package br.udesc.ceavi.dsd.controller;

import br.udesc.ceavi.dsd.abstractfactory.AbstractFactory;
import br.udesc.ceavi.dsd.command.Command;
import br.udesc.ceavi.dsd.command.MatarCarroCommand;
import br.udesc.ceavi.dsd.command.MoverUmaCasaCommand;
import br.udesc.ceavi.dsd.model.casa.Casa;
import br.udesc.ceavi.dsd.model.casa.ICasa;
import br.udesc.ceavi.dsd.view.TableObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Gustavo C. Santos
 * @since 27/08/2019
 */
public class MalhaController {

    private int[][] matrix;
    private int numCasasValida;
    private ICasa[][] matrixCasa;

    private List<ICasa> casasRespawn;
    private List<ICasa> casasDeath;

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
        setCommands();
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
                int valor = matrixCasa[coluna][row].getValor();
                if (valor == 1 || valor == 2 || valor == 3 || valor == 4) {
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

    public void setCommands() {
        //Adicionar os commands dentro da casa
        //Add Pontos de Morte
        for (ICasa iCasa : casasDeath) {
            iCasa.addRota(new MatarCarroCommand(iCasa));
        }
        //Agora percorre a estrutura para encontrar as possíveis rotas de cada casa
        for (int linha = 0; linha < getRow(); linha++) {
            for (int coluna = 0; coluna < getColumn(); coluna++) {
                //Se houver valor na casa significa que a mesma faz parte da malha
                if (matrixCasa[coluna][linha].getValor() != 0) {
                    ICasa destino;
                    ICasa origem = matrixCasa[coluna][linha];
                    if (!casasDeath.contains(origem)) {
                        switch (matrixCasa[coluna][linha].getValor()) {
                            case 1:
                                destino = matrixCasa[coluna][linha - 1];
                                if (isCruzamento(destino)) {
                                    construirCaminhoCruzamenteo(origem);
                                } else {
                                    origem.addRota(new MoverUmaCasaCommand(origem, destino));
                                }
                                break;
                            case 2:
                                destino = matrixCasa[coluna + 1][linha];
                                if (isCruzamento(destino)) {
                                    construirCaminhoCruzamenteo(origem);
                                } else {
                                    origem.addRota(new MoverUmaCasaCommand(origem, destino));
                                }
                                break;
                            case 3:
                                destino = matrixCasa[coluna][linha + 1];
                                if (isCruzamento(destino)) {
                                    construirCaminhoCruzamenteo(origem);
                                } else {
                                    origem.addRota(new MoverUmaCasaCommand(origem, destino));
                                }
                                break;
                            case 4:
                                destino = matrixCasa[coluna - 1][linha];
                                if (isCruzamento(destino)) {
                                    construirCaminhoCruzamenteo(origem);
                                } else {
                                    origem.addRota(new MoverUmaCasaCommand(origem, destino));
                                }
                                break;
                            default:
                                origem.addRota(new MatarCarroCommand(origem));
                                break;
                        }
                    }
                }
            }
        }
    }

    public boolean isCruzamento(ICasa casa) {
        int valor = casa.getValor();
        return !(valor == 1 || valor == 2 || valor == 3 || valor == 4);
    }

    public void drawExpecialCasa() {
        for (TableObserver observer : observers) {
            for (ICasa casa : casasRespawn) {
                observer.drawRespawn(casa.getColunm(), casa.getRow());
            }
            for (ICasa casa : casasDeath) {
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

    public ICasa getRespawnAleatorio() {
        Collections.shuffle(casasRespawn);
        return casasRespawn.get(SystemController.getInstance().getRandom().nextInt(casasRespawn.size() - 1));
    }

    public void clearCasa(int colunm, int row) {
        for (TableObserver observer : observers) {
            observer.clearTableCell(colunm, row);
        }
    }

    public void printCasaCarro(int color, int colunm, int row) {
        for (TableObserver observer : observers) {
            observer.printCarro(color, colunm, row);
        }
    }

    private void construirCaminhoCruzamenteo(ICasa origem) {
        ICasa saida1;
        ICasa saida2;
        ICasa saida3;
        switch (origem.getValor()) {
            case 1:
                //Entrada 1
                saida1 = matrixCasa[origem.getColunm() + 1][origem.getRow() - 1];
                if (isValidHouse(saida1)) {
                    origem.addRota(new MoverUmaCasaCommand(origem, saida1));
                }
                //Entrada 2
                saida2 = matrixCasa[origem.getColunm()][origem.getRow() - 3];
                if (isValidHouse(saida2)) {
                    origem.addRota(new MoverUmaCasaCommand(origem, saida2));
                }
                //Entrada 3
                saida3 = matrixCasa[origem.getColunm() - 2][origem.getRow() - 2];
                if (isValidHouse(saida3)) {
                    origem.addRota(new MoverUmaCasaCommand(origem, saida3));
                }
                break;
            case 2:
                //Entrada 1
                saida1 = matrixCasa[origem.getColunm() + 1][origem.getRow() + 1];
                if (isValidHouse(saida1)) {
                    origem.addRota(new MoverUmaCasaCommand(origem, saida1));
                }
                //Entrada 2
                saida2 = matrixCasa[origem.getColunm() + 3][origem.getRow()];
                if (isValidHouse(saida2)) {
                    origem.addRota(new MoverUmaCasaCommand(origem, saida2));
                }
                //Entrada 3
                saida3 = matrixCasa[origem.getColunm() + 2][origem.getRow() - 2];
                if (isValidHouse(saida3)) {
                      origem.addRota(new MoverUmaCasaCommand(origem, saida3));
                }
                break;
            case 3:
                //Entrada 1
                saida1 = matrixCasa[origem.getColunm() - 1][origem.getRow() + 1];
                if (isValidHouse(saida1)) {
                    origem.addRota(new MoverUmaCasaCommand(origem, saida1));
                }
                //Entrada 2
                saida2 = matrixCasa[origem.getColunm()][origem.getRow() + 3];
                if (isValidHouse(saida2)) {
                    origem.addRota(new MoverUmaCasaCommand(origem, saida2));
                }
                //Entrada 3
                saida3 = matrixCasa[origem.getColunm() + 2][origem.getRow() + 2];
                if (isValidHouse(saida3)) {
                      origem.addRota(new MoverUmaCasaCommand(origem, saida3));
                }
                break;
            case 4:
                //Entrada 1
                saida1 = matrixCasa[origem.getColunm() - 1][origem.getRow() - 1];
                if (isValidHouse(saida1)) {
                    origem.addRota(new MoverUmaCasaCommand(origem, saida1));
                }
                //Entrada 2
                saida2 = matrixCasa[origem.getColunm() - 3][origem.getRow()];
                if (isValidHouse(saida2)) {
                    origem.addRota(new MoverUmaCasaCommand(origem, saida2));
                }
                //Entrada 3
                saida3 = matrixCasa[origem.getColunm() - 2][origem.getRow() + 2];
                if (isValidHouse(saida3)) {
                      origem.addRota(new MoverUmaCasaCommand(origem, saida3));
                }
                break;
        }
    }

    public boolean isValidHouse(ICasa casa) {
        return casa.getValor() != 0;
    }
}
