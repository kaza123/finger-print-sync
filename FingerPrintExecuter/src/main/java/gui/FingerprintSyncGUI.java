/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.logging.Level;
import sync_service.SyncService;

/**
 *
 * @author 'Kasun Chamara'
 */
public class FingerprintSyncGUI extends javax.swing.JFrame {

    public FingerprintSyncGUI() {
        initComponents();

        initOthers();
    }

    public void start() throws SQLException {
        SyncService.getInstance().start();
        btnStart.setEnabled(false);
        btnStop.setEnabled(true);
    }

    public void stop() throws SQLException {
        SyncService.getInstance().stop();
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    private void initOthers() {
        setTitle("Finger Print Synchronization");
        txtLog.setEditable(false);
        btnStart.setEnabled(false);
        
        TextAreaOutputStream textAreaOutputStream = new TextAreaOutputStream(txtLog);
        System.setOut(new PrintStream(textAreaOutputStream));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnStop.setText("Stop");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane2.setViewportView(txtLog);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStop)
                .addContainerGap(451, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(btnStop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        try {
            start();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(FingerprintSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        try {
            stop();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(FingerprintSyncGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnStopActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setDefaultCloseOperation(FingerprintSyncGUI.HIDE_ON_CLOSE);
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
//    static FingerprintSyncGUI fingerprintSyncGUI = new FingerprintSyncGUI();
//static final org.apache.log4j.Logger logger = LogManager.getLogger(FingerprintSyncGUI.class.getName());
    public static void main(String args[]) {
//   
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtLog;
    // End of variables declaration//GEN-END:variables
}
