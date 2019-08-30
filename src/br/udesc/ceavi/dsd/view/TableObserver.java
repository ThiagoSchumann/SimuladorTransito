package br.udesc.ceavi.dsd.view;

/**
 *
 * @author Gustavo C. Santos
 * 27/08/2019
 */
public interface TableObserver {

    public void printCarro(int cor, int colunm,int row);
    
    public void clearTableCell(int colunm,int row);

    public void repaint();

    public void drawRespawn(int colunm, int row);

    public void drawDeath(int colunm, int row);

}
