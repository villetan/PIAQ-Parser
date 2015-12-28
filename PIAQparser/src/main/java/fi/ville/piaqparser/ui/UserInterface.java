/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ville
 */
public class UserInterface extends javax.swing.JFrame {

    /**
     * Creates new form UserInterface
     */
    public UserInterface() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Header = new javax.swing.JLabel();
        FilePathTextField = new javax.swing.JTextField();
        ChooseAFHeader = new javax.swing.JLabel();
        BrowseButton = new javax.swing.JButton();
        ErrorTextField = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Header.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        Header.setText("PIAQ File parser");

        ChooseAFHeader.setText("Choose a file for parsing");

        BrowseButton.setText("Browse");
        BrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(340, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ErrorTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ChooseAFHeader)
                            .addComponent(FilePathTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BrowseButton)))
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(ChooseAFHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FilePathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BrowseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ErrorTextField)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowseButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(UserInterface.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            if (fc.getSelectedFile().getPath().contains(".xml") || fc.getSelectedFile().getPath().contains(".csv")) {
                add(timeWindow());
                add(parametersWindow());
                add(commandsWindow());
                ErrorTextField.setText("");
                FilePathTextField.setText(fc.getSelectedFile().getPath());
                this.file = fc.getSelectedFile();
            } else {
                ErrorTextField.setText("Not a csv or xml file!");
            }
        } else {
            ErrorTextField.setText("Canceled");
        }
    }//GEN-LAST:event_BrowseButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().setVisible(true);
            }
        });
    }

    private File file;

    private JPanel timeWindow() {
        //Hmm. Kantsii tehdä napit ja muu mukava esim. JPaneliin ja tökätä JPanel JFrameen kun ehto täyttyy
        int ekaIkkunaLoppu = rootPane.getHeight();
        setSize(rootPane.getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, WIDTH));
        

        jPanel.setBackground(Color.red);
        jPanel.setBounds(rootPane.getX(), ekaIkkunaLoppu, rootPane.getWidth() / 2, 2 * (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 5);
        JLabel timeOtsikkoJLabel = new JLabel("Time", JLabel.CENTER);
        timeOtsikkoJLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        timeOtsikkoJLabel.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        timeOtsikkoJLabel.setFont(new Font("Ubuntu", 0, 24));
        jPanel.add(timeOtsikkoJLabel);
        
        for(Component component : timeWindowComponents()){
            jPanel.add(component);
        }
        return jPanel;

    }
    private JLabel hasDataFrom;
    private ArrayList<Component> timeWindowComponents(){
        ArrayList<Component> komponentit = new ArrayList<>();
        hasDataFrom= new JLabel("This sheet has data from", JLabel.CENTER);
        hasDataFrom.setLocation(300, 300);
        komponentit.add(hasDataFrom);
        
        return komponentit;
    }
    
    

    private JPanel parametersWindow() {
        Rectangle bounds = timeWindow().getBounds();
        int siirtyma = timeWindow().getWidth();
        bounds.setLocation(siirtyma, (int) bounds.getY());
        JPanel jPanel = new JPanel();

        jPanel.setLayout(new BoxLayout(jPanel, WIDTH));
        jPanel.setBackground(Color.CYAN);
        jPanel.setBounds(bounds);
        JLabel paramOtsikkoJLabel = new JLabel("Parameters", JLabel.CENTER);
        paramOtsikkoJLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        paramOtsikkoJLabel.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        paramOtsikkoJLabel.setFont(new Font("Ubuntu", 0, 24));
        jPanel.add(paramOtsikkoJLabel);
        return jPanel;
    }

    private JPanel commandsWindow() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, WIDTH));
        jPanel.setBackground(Color.YELLOW);
        jPanel.setBounds(rootPane.getX(), timeWindow().getY() + timeWindow().getHeight(), rootPane.getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-(timeWindow().getY()+timeWindow().getHeight()));

        JLabel commandsOtsikkoJLabel = new JLabel("Commands", JLabel.CENTER);
        commandsOtsikkoJLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        commandsOtsikkoJLabel.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        commandsOtsikkoJLabel.setFont(new Font("Ubuntu", 0, 24));
        jPanel.add(commandsOtsikkoJLabel);

        return jPanel;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BrowseButton;
    private javax.swing.JLabel ChooseAFHeader;
    private javax.swing.JLabel ErrorTextField;
    private javax.swing.JTextField FilePathTextField;
    private javax.swing.JLabel Header;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
