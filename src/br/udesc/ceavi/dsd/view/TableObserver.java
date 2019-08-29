package br.udesc.ceavi.dsd.view;

import java.awt.Color;

/**
 *
 * @author Gustavo C. Santos
 * 27/08/2019
 */
public interface TableObserver {

    public void printCarro(Color cor, int colunm,int row);
    
    public void clearTableCell(int colunm,int row);

    public void repaint();

}
