package br.udesc.ceavi.dsd.controller;

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

    private List<TableObserver> observers;

    public MalhaController(int[][] matrix) {
        this.matrix = matrix;
        this.observers = new ArrayList<>();

        this.carro = new int[matrix.length][matrix[0].length];
        for (int linha = 0; linha < matrix[0].length; linha++) {
            for (int colunm = 0; colunm < matrix.length; colunm++) {
                double random = Math.random();
                if (matrix[colunm][linha] != 0 && random < 0.6) {
                    carro[colunm][linha] = 1;
                }
            }
        }
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
}
