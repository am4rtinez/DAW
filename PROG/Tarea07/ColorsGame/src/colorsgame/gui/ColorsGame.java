package colorsgame.gui;

import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author amartinez
 */
public class ColorsGame extends javax.swing.JFrame {

    /**
     * Creates new form ColorsGameUI
     */
    public ColorsGame() {
        initComponents();
        //Añadimos los radio buttons al grupo para que solo aparezca uno marcado.
        colorsButtonGroup.add(redRB);
        colorsButtonGroup.add(yellowRB);
        colorsButtonGroup.add(greenRB);
        colorsButtonGroup.add(userRB);
        this.setTitle("Colors Game");   //Pone título a la aplicación.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        colorsButtonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        labelInfo = new javax.swing.JLabel();
        redRB = new javax.swing.JRadioButton();
        yellowRB = new javax.swing.JRadioButton();
        greenRB = new javax.swing.JRadioButton();
        colorPane = new javax.swing.JPanel();
        userRB = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelInfo.setBackground(new java.awt.Color(255, 255, 255));
        labelInfo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelInfo.setText("Selecciona un color..");

        redRB.setText("Rojo");
        redRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redRBActionPerformed(evt);
            }
        });

        yellowRB.setText("Amarillo");
        yellowRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yellowRBActionPerformed(evt);
            }
        });

        greenRB.setText("Verde");
        greenRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                greenRBActionPerformed(evt);
            }
        });

        colorPane.setBackground(new java.awt.Color(255, 255, 255));
        colorPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout colorPaneLayout = new javax.swing.GroupLayout(colorPane);
        colorPane.setLayout(colorPaneLayout);
        colorPaneLayout.setHorizontalGroup(
            colorPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );
        colorPaneLayout.setVerticalGroup(
            colorPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        userRB.setText("Usuario");
        userRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userRBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(redRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(greenRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(yellowRB, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(userRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(colorPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(redRB, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(yellowRB, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(greenRB, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userRB, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(colorPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void redRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redRBActionPerformed
        // TODO add your handling code here:
        modificaObjetos(Color.RED);
        
    }//GEN-LAST:event_redRBActionPerformed

    private void yellowRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yellowRBActionPerformed
        // TODO add your handling code here:
        modificaObjetos(Color.YELLOW);
    }//GEN-LAST:event_yellowRBActionPerformed

    private void greenRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_greenRBActionPerformed
        // TODO add your handling code here:
        modificaObjetos(Color.GREEN);
    }//GEN-LAST:event_greenRBActionPerformed

    private void userRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userRBActionPerformed
        labelInfo.setOpaque(false);
        labelInfo.setForeground(Color.BLACK);
        labelInfo.setText("Ángel Martínez");
        colorPane.setVisible(false);
    }//GEN-LAST:event_userRBActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Windows Classic look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ColorsGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ColorsGame().setVisible(true);
            }
        });
    }
    
    /**
     * Método que se encarga de modificar los objetos.
     * @param color 
     */
    public void modificaObjetos(Color color){
        colorPane.setVisible(true);
        colorPane.setBackground(color);
        labelInfo.setOpaque(true);
        labelInfo.setBackground(Color.BLACK);
        labelInfo.setForeground(color);
        labelInfo.setText("Hola Mundo!");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel colorPane;
    private javax.swing.ButtonGroup colorsButtonGroup;
    private javax.swing.JRadioButton greenRB;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelInfo;
    private javax.swing.JRadioButton redRB;
    private javax.swing.JRadioButton userRB;
    private javax.swing.JRadioButton yellowRB;
    // End of variables declaration//GEN-END:variables
}
