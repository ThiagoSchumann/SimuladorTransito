package br.udesc.ceavi.dsd.view;

import br.udesc.ceavi.dsd.controller.MalhaController;
import br.udesc.ceavi.dsd.controller.SystemController;
import br.udesc.ceavi.dsd.util.Image;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Gustavo C. Santos
 * @since 26/08/2019
 */
public class MalhaTable extends JTable implements TableObserver {

    private SystemController controller;
    private JPanel parentPanel;
    private BufferedImage[][] orginalMalhaImages;
    private BufferedImage[][] canvas;

    public MalhaTable(JPanel parent) {
        this.controller = SystemController.getInstance();
        this.controller.getMalhaController().anexar(this);
        this.parentPanel = parent;
        this.orginalMalhaImages = new BufferedImage[controller.getColumn()][controller.getRow()];
        this.canvas = new BufferedImage[controller.getColumn()][controller.getRow()];
        this.initializeProperties();
        initImages();
    }

    /**
     * Inicializa as propriedades da tabela.
     */
    private void initializeProperties() {
        this.setModel(new ManhaTableModel());
        this.setDefaultRenderer(Object.class, new BoardTableRenderer());
        this.setBackground(new Color(0, 0, 0, 0));
        this.setRowSelectionAllowed(false);
        this.setCellSelectionEnabled(true);
        this.setDragEnabled(false);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setTableHeader(null);
        this.setFillsViewportHeight(true);
        this.setOpaque(false);
        this.setShowGrid(false);
        this.setEnabled(false);
    }

    private void initImages() {
        for (int colunm = 0; colunm < controller.getColumn(); colunm++) {
            for (int row = 0; row < controller.getRow(); row++) {
                orginalMalhaImages[colunm][row] = Image.getImagem((int) controller.getCasa(colunm, row));
                canvas[colunm][row] = Image.getImagem((int) controller.getCasa(colunm, row));
            }
        }
        this.repaint();
        this.revalidate();
    }

    private class ManhaTableModel extends AbstractTableModel {

        @Override
        public int getColumnCount() {
            return controller.getColumn();
        }

        @Override
        public int getRowCount() {
            return controller.getRow();
        }

        @Override
        public Object getValueAt(int row, int col) {
            return canvas[col][row];
        }
    }

    private class BoardTableRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value != null) {
                BufferedImage img = (BufferedImage) value;
                AffineTransform transform = AffineTransform.getScaleInstance((float) table.getColumnModel().getColumn(column).getWidth() / img.getWidth(),
                        (float) table.getRowHeight() / img.getHeight());
                AffineTransformOp operator = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
                this.setIcon(new ImageIcon(operator.filter(img, null)));
            }
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            return this;
        }
    }

    @Override
    /**
     * {@inheritdoc}
     */
    public Dimension getPreferredScrollableViewportSize() {
        Dimension size = parentPanel.getSize();
        if (size.getWidth() <= 0 || size.getHeight() <= 0) {
            return new Dimension(0, 0);
        }
        int inset = 15;
        size.height -= inset;
        size.width -= inset;
        float scaleX = (float) size.getWidth();
        float scaleY = (float) size.getHeight();
        if (scaleX > scaleY) {
            int width = (int) (scaleY / scaleX * size.getWidth());
            size = new Dimension(width, (int) size.getHeight());
        } else {
            int height = (int) (scaleX / scaleY * size.getHeight());
            size = new Dimension((int) size.getWidth(), height);
        }
        this.setRowHeight((int) size.getHeight() / this.getModel().getRowCount());
        return size;
    }

    @Override
    public void printCarro(Color cor, int colunm, int row) {
        BufferedImage origin = this.canvas[colunm][row];
        Graphics2D g = origin.createGraphics();
        g.drawImage(
                Image.replaceColor(
                        Image.getImagem(Image.CARRO),
                         cor.getRGB()),
                0, 0, null);
        g.dispose();

        parentPanel.repaint();
        parentPanel.revalidate();
        this.repaint();
        this.revalidate();
    }

    @Override
    public void clearTableCell(int colunm, int row) {
        BufferedImage novo = new BufferedImage(
                canvas[colunm][row].getWidth(),
                canvas[colunm][row].getHeight(),
                canvas[colunm][row].getType());

        Graphics2D g = novo.createGraphics();
        g.drawImage(orginalMalhaImages[colunm][row], 0, 0, null);
        this.canvas[colunm][row] = novo;
        g.dispose();

        parentPanel.repaint();
        parentPanel.revalidate();
        this.revalidate();
        this.repaint();
    }
}
