package br.udesc.ceavi.dsd.view;

import br.udesc.ceavi.dsd.controller.SystemController;
import javax.swing.JFileChooser;

/**
 *
 * @author thiag
 */
public class Principal extends javax.swing.JFrame {

    private SystemController controller;

    public Principal() {
        this.controller = SystemController.getInstance();
        initComponents();
        btnStart.setEnabled(false);
        btnStop.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        tfArquivo = new javax.swing.JTextField();
        btnAbrirMalha = new javax.swing.JButton();
        jpConfig = new javax.swing.JPanel();
        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        jpTabela = new javax.swing.JPanel();
        lbLimiteCarro = new javax.swing.JLabel();
        jsNumCarro = new javax.swing.JSpinner();
        rbSemaforo = new javax.swing.JRadioButton();
        rbMonitor = new javax.swing.JRadioButton();
        lbNumCarroAtual = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Simulação");
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Arquivo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        tfArquivo.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 440;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 0, 0);
        getContentPane().add(tfArquivo, gridBagConstraints);

        btnAbrirMalha.setText("Abrir Malha");
        btnAbrirMalha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirMalhaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 0, -15);
        getContentPane().add(btnAbrirMalha, gridBagConstraints);

        jpConfig.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jpConfigLayout = new javax.swing.GroupLayout(jpConfig);
        jpConfig.setLayout(jpConfigLayout);
        jpConfigLayout.setHorizontalGroup(
            jpConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jpConfigLayout.setVerticalGroup(
            jpConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 500;
        gridBagConstraints.ipady = 500;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 15, 0);
        getContentPane().add(jpConfig, gridBagConstraints);

        btnStart.setText("Iniciar");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(btnStart, gridBagConstraints);

        btnStop.setText("Parar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 100, 0, 0);
        getContentPane().add(btnStop, gridBagConstraints);

        lbLimiteCarro.setText("Quantos Carros Na Simulação");
        jpTabela.add(lbLimiteCarro);

        jsNumCarro.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jsNumCarro.setPreferredSize(new java.awt.Dimension(100, 22));
        jpTabela.add(jsNumCarro);

        buttonGroup1.add(rbSemaforo);
        rbSemaforo.setSelected(true);
        rbSemaforo.setText("Semaforo");
        jpTabela.add(rbSemaforo);

        buttonGroup1.add(rbMonitor);
        rbMonitor.setText("Monitor");
        jpTabela.add(rbMonitor);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jpTabela, gridBagConstraints);

        lbNumCarroAtual.setText("Numero de Carros Adicionados");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(lbNumCarroAtual, gridBagConstraints);

        setSize(new java.awt.Dimension(741, 744));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirMalhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirMalhaActionPerformed
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
        int retorno = fileChooser.showOpenDialog(this);

        if (retorno == JFileChooser.APPROVE_OPTION) {
            String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (controller.readFile(absolutePath)) {
                tfArquivo.setText(absolutePath);
                btnStart.setEnabled(true);
            } else {

            }
        }
    }//GEN-LAST:event_btnAbrirMalhaActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        if (controller.readySimulation()) {
            btnAbrirMalha.setEnabled(false);
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
        }
    }//GEN-LAST:event_btnStartActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirMalha;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jpConfig;
    private javax.swing.JPanel jpTabela;
    private javax.swing.JSpinner jsNumCarro;
    private javax.swing.JLabel lbLimiteCarro;
    private javax.swing.JLabel lbNumCarroAtual;
    private javax.swing.JRadioButton rbMonitor;
    private javax.swing.JRadioButton rbSemaforo;
    private javax.swing.JTextField tfArquivo;
    // End of variables declaration//GEN-END:variables
}
