package br.udesc.ceavi.dsd.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author gusta
 */
public class LerArquivoMatrix {

    private int[][] matrix;
    private String caminho;

    public LerArquivoMatrix(String caminho) throws FileNotFoundException {
        this.caminho = caminho;
        lerAquivo();
    }

    public int[][] getMatrix() throws Exception {
        if (matrix == null) {
            throw new Exception("Matrix não Intaciada");
        }
        return matrix;
    }

    private void lerAquivo() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(caminho));

        int linha = Integer.parseInt(scanner.nextLine());
        int coluna = Integer.parseInt(scanner.nextLine());
        matrix = new int[coluna][linha];

        int linhaIndex = 0;

        while (scanner.hasNext()) {
            String l = scanner.nextLine();
            String[] lv = l.split(" ");

            for (int i = 0; i < lv.length; i++) {
                matrix[i][linhaIndex] = Integer.parseInt(lv[i]);
            }
            linhaIndex++;
        }

        scanner.close();
    }

}
