package br.udesc.ceavi.dsd.controller;

import br.udesc.ceavi.dsd.model.casa.Casa;
import br.udesc.ceavi.dsd.model.casa.CasaSemaforo;
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

    private int[][] carro;

    private Casa[][] matrixCasa;

    private List<Casa> casasRespawn;

    private List<TableObserver> observers;

    public MalhaController(int[][] matrix) {
        this.matrix = matrix;
        this.observers = new ArrayList<>();
        this.matrixCasa = new Casa[matrix.length][matrix[0].length];

        initCasas();
        setRespawnCasa();
    }

    public void anexar(TableObserver observer) {
        this.observers.add(observer);
    }

    public int getColumn() {
        return matrix.length;
    }

    public int getRow() {
        return matrix[0].length;
    }

    public Object getCasa(int col, int row) {
        return matrix[col][row];
    }

    private void imprimirMatrizTest() {
        for (int row = 0; row < getRow(); row++) {
            for (int colunm = 0; colunm < getColumn(); colunm++) {
                System.out.print(carro[colunm][row] + " ");
            }
            System.out.println("");
        }
    }

    public void pintarCarro() {
        for (int row = 0; row < getRow(); row++) {
            for (int colunm = 0; colunm < getColumn(); colunm++) {
                for (TableObserver observer : observers) {
                    observer.clearTableCell(colunm, row);
                    if (carro[colunm][row] != 0) {
                        observer.printCarro(Image.gerarCorAleatoriamente(), colunm, row);
                    }
                }
            }
        }
    }

    private void initCasas() {
        for (int linha = 0; linha < matrix[0].length; linha++) {
            for (int coluna = 0; coluna < matrix.length; coluna++) {
                matrixCasa[coluna][linha] = new CasaSemaforo(matrix[coluna][linha],coluna,linha);
            }
        }
    }

    private void setRespawnCasa() {
        //condicao
        //chamar linha e add
    }
}
