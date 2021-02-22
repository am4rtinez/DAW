/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculonumerico.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author amartinez
 */
public class CalculoNumerico extends javax.swing.JFrame {

    /**
     * Creates new form CalculoNumerico
     */
    public CalculoNumerico() {
        initComponents();   //Inicializa los componentes. Generado por el sistema.
        this.setTitle("Cálculo Numérico");      //Hacemos el set del titulo de la aplicación.
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/recursos/iconos/face-monkey.png")).getImage()); //Hacemos el set del icono.
        initApp(); //Lanzamos la inicialización de la app para ocultar lo que no debe de estar visible al arranque de la aplicación.
    }

    /**
     * Método que inicializa la app ocultando lo que no debe estar visible.
     */
    public void initApp() {
        resetButton.setVisible(false);
        helpButton.setVisible(false);
        calcButton.setVisible(false);
        minIntLabel.setVisible(false);
        minIntTF.setVisible(false);
        maxIntLabel.setVisible(false);
        maxIntTF.setVisible(false);
        showPanel2(false);
        showPanel3(false);
        resetMI.setVisible(false);
        calcMI.setVisible(false);
    }

    /**
     * Método que inicia la app.
     */
    public void iniciar() {
        showPanel3(true);
        SetVisibility(true);
//        minIntTF.setText("1");    //para testeo.
//        maxIntTF.setText("15");   //para testeo.
        statusLabel.setText("Última acción: Iniciar aplicación.");
    }

    /**
     * Método que restea la app.
     */
    public void reset() {
        initApp();
        minIntTF.setText("");
        maxIntTF.setText("");
        resultsTF.setText("");
    }
    
    /**
     * Método para mostrar u ocultar el jPanel2.
     * @param status 
     */
    public void showPanel2(Boolean status){
        panel2.setVisible(status);
    }
    
    /**
     * Método para mostrar u ocultar el jPanel3.
     * @param status 
     */
    public void showPanel3(Boolean status){
        panel3.setVisible(status);
    }
    public void ayuda() {
        statusLabel.setText("Última acción: Visualizar ayuda.");
        String message = "Dados un número entero mínimo y otro máximo, mayor que el mínimno, muestra por pantalla todos los enteros X tales que su cuadrado (x*x) se encuentra entre este mínimo y este máximo.";
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean checkInteger(String valor) {
        try {
            Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void calcular() {
        if (minIntTF.getText().trim().isEmpty() || !checkInteger(minIntTF.getText().trim())) {
            JOptionPane.showMessageDialog(new JFrame(), "El valor mínimo introducido no es válido. Modifique los datos.", "Dialog", JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Última acción: Realizar calculos error.");
        } else if (maxIntTF.getText().trim().isEmpty() || !checkInteger(maxIntTF.getText().trim())) {
            JOptionPane.showMessageDialog(new JFrame(), "El valor máximo introducido no es válido. Modifique los datos.", "Dialog", JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Última acción: Realizar calculos error.");
        } else {
            int min = Integer.valueOf(minIntTF.getText());
            int max = Integer.valueOf(maxIntTF.getText());
            if (max <= min) {
                JOptionPane.showMessageDialog(new JFrame(), "El valor máximo debe ser superior al valor mínimo. Modifique los datos.", "Dialog", JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("Última acción: Realizar calculos error.");
            } else {
                showPanel2(true);
                String resultados = "";
                for (int i = min; i <= max && (i * i) <= max; i++) {
                    resultados = resultados + i + " ";
                }
                resultsTF.setText(resultados);
                statusLabel.setText("Última acción: Realizar calculos.");
            }
        }
    }

    /**
     * Método para mostrar u ocultar los distintos componentes que no deben aparecer visibles en un principio.
     * @param status 
     */
    public void SetVisibility(Boolean status) {
        resetButton.setVisible(status);
        helpButton.setVisible(status);
        calcButton.setVisible(status);
        minIntLabel.setVisible(status);
        minIntTF.setVisible(status);
        maxIntLabel.setVisible(status);
        maxIntTF.setVisible(status);
        resetMI.setVisible(status);
        calcMI.setVisible(status);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new javax.swing.JPanel();
        initButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        calcButton = new javax.swing.JButton();
        minIntLabel = new javax.swing.JLabel();
        minIntTF = new javax.swing.JTextField();
        maxIntTF = new javax.swing.JTextField();
        maxIntLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        panel2Label = new javax.swing.JLabel();
        resultsTF = new javax.swing.JTextField();
        panel3 = new javax.swing.JPanel();
        panel3Label = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        initMI = new javax.swing.JMenuItem();
        resetMI = new javax.swing.JMenuItem();
        calcMI = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));

        panel1.setBackground(new java.awt.Color(204, 204, 204));

        initButton.setText("INICIAR");
        initButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initButtonActionPerformed(evt);
            }
        });

        resetButton.setText("RESET");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        helpButton.setText("AYUDA");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        calcButton.setText("CALCULAR");
        calcButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcButtonActionPerformed(evt);
            }
        });

        minIntLabel.setText("Introduce el entero mínimo:");

        maxIntLabel.setText("Introduce el entero máximo:");

        jLabel1.setText("JPanel1 - Entrada de datos");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(maxIntLabel)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(initButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(minIntLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(calcButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(minIntTF, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maxIntTF, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(17, 17, 17)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minIntLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minIntTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxIntLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxIntTF, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(initButton)
                    .addComponent(resetButton)
                    .addComponent(helpButton)
                    .addComponent(calcButton))
                .addContainerGap())
        );

        panel2.setBackground(new java.awt.Color(204, 204, 204));

        panel2Label.setText("JPanel2 - Salida de cálculos");

        resultsTF.setEditable(false);
        resultsTF.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resultsTF)
                    .addComponent(panel2Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addComponent(panel2Label)
                .addGap(17, 17, 17)
                .addComponent(resultsTF, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel3.setBackground(new java.awt.Color(153, 153, 153));
        panel3.setPreferredSize(new java.awt.Dimension(380, 100));

        panel3Label.setText("JPanel3 - Barra de estado");

        statusLabel.setBackground(new java.awt.Color(255, 255, 255));
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel.setOpaque(true);

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel3Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                .addComponent(panel3Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setText("Archivo");

        initMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        initMI.setText("Iniciar");
        initMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initMIActionPerformed(evt);
            }
        });
        jMenu1.add(initMI);

        resetMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        resetMI.setText("Reset");
        resetMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetMIActionPerformed(evt);
            }
        });
        jMenu1.add(resetMI);

        calcMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        calcMI.setText("Calcular");
        calcMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcMIActionPerformed(evt);
            }
        });
        jMenu1.add(calcMI);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        jMenuItem4.setText("Acerca de..");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        ayuda();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void initMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initMIActionPerformed
        iniciar();
    }//GEN-LAST:event_initMIActionPerformed

    private void resetMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetMIActionPerformed
        reset();
    }//GEN-LAST:event_resetMIActionPerformed

    private void calcMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcMIActionPerformed
        calcular();
    }//GEN-LAST:event_calcMIActionPerformed

    private void calcButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcButtonActionPerformed
        calcular();
    }//GEN-LAST:event_calcButtonActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        ayuda();
    }//GEN-LAST:event_helpButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        reset();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void initButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initButtonActionPerformed
        iniciar();
    }//GEN-LAST:event_initButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CalculoNumerico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalculoNumerico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calcButton;
    private javax.swing.JMenuItem calcMI;
    private javax.swing.JButton helpButton;
    private javax.swing.JButton initButton;
    private javax.swing.JMenuItem initMI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JLabel maxIntLabel;
    private javax.swing.JTextField maxIntTF;
    private javax.swing.JLabel minIntLabel;
    private javax.swing.JTextField minIntTF;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JLabel panel2Label;
    private javax.swing.JPanel panel3;
    private javax.swing.JLabel panel3Label;
    private javax.swing.JButton resetButton;
    private javax.swing.JMenuItem resetMI;
    private javax.swing.JTextField resultsTF;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
}
