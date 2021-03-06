/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.ui;

import fi.ville.piaqparser.domain.Hyppy;
import fi.ville.piaqparser.domain.Mittaus;
import fi.ville.piaqparser.services.MittausAnalysoijaPalvelu;
import fi.ville.piaqparser.services.TiedostonLukijaPalvelu;
import fi.ville.piaqparser.util.MittaustenAnalysoija;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author ville Pääikkuna, joka avautuu ensimmäisenä, kun ohjelma
 * käynnistetään. Täällä valitaan käsiteltävä tiedosto, ja validoidaan se .xml
 * tai .csv tiedostoksi.
 */
public class TiedostonValitsijaFrame extends javax.swing.JFrame {

    /**
     * Creates new form UserInterface
     */
    public TiedostonValitsijaFrame() {

        initComponents();
        this.setVisible(true);
        this.jSplitPane1.getBottomComponent().setVisible(false);
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
        jSplitPane1 = new javax.swing.JSplitPane();
        timeAndOther = new javax.swing.JPanel();
        piaqFileParser = new javax.swing.JPanel();
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

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        timeAndOther.setMaximumSize(new java.awt.Dimension(1, 1));
        timeAndOther.setMinimumSize(new java.awt.Dimension(1, 1));
        timeAndOther.setPreferredSize(new java.awt.Dimension(1, 1));

        javax.swing.GroupLayout timeAndOtherLayout = new javax.swing.GroupLayout(timeAndOther);
        timeAndOther.setLayout(timeAndOtherLayout);
        timeAndOtherLayout.setHorizontalGroup(
            timeAndOtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        timeAndOtherLayout.setVerticalGroup(
            timeAndOtherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 458, Short.MAX_VALUE)
        );

        jSplitPane1.setBottomComponent(timeAndOther);

        Header.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        Header.setText("PIAQ File parser");

        FilePathTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilePathTextFieldActionPerformed(evt);
            }
        });

        ChooseAFHeader.setText("Choose a file for parsing");

        BrowseButton.setText("Browse");
        BrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowseButtonActionPerformed(evt);
            }
        });

        ErrorTextField.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout piaqFileParserLayout = new javax.swing.GroupLayout(piaqFileParser);
        piaqFileParser.setLayout(piaqFileParserLayout);
        piaqFileParserLayout.setHorizontalGroup(
            piaqFileParserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(piaqFileParserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ErrorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(481, Short.MAX_VALUE))
            .addGroup(piaqFileParserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(piaqFileParserLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(piaqFileParserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, piaqFileParserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(piaqFileParserLayout.createSequentialGroup()
                                .addComponent(FilePathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BrowseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(ChooseAFHeader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        piaqFileParserLayout.setVerticalGroup(
            piaqFileParserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(piaqFileParserLayout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(ErrorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(piaqFileParserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(piaqFileParserLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(31, 31, 31)
                    .addComponent(ChooseAFHeader)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(piaqFileParserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FilePathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BrowseButton))
                    .addContainerGap(61, Short.MAX_VALUE)))
        );

        jSplitPane1.setLeftComponent(piaqFileParser);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private MittausAnalysoijaPalvelu mittausAnalysoijaPalvelu;
    private void BrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowseButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(TiedostonValitsijaFrame.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            if (fc.getSelectedFile().getPath().contains(".xml") || fc.getSelectedFile().getPath().contains(".csv")) {
                setSize(this.getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
                ErrorTextField.setText("");
                FilePathTextField.setText(fc.getSelectedFile().getPath());

                this.file = fc.getSelectedFile();
                ToiminnallisuusPanel uusiUI = new ToiminnallisuusPanel();

                this.jSplitPane1.setBottomComponent(uusiUI);
                this.jSplitPane1.getBottomComponent().setVisible(true);
                mittausAnalysoijaPalvelu = new MittausAnalysoijaPalvelu(file.getPath());
                setToiminnallisuusIkkunanArvot(uusiUI);

            } else {
                ErrorTextField.setText("Not a csv or xml file!");
            }
        } else {
            ErrorTextField.setText("Canceled");
        }
    }//GEN-LAST:event_BrowseButtonActionPerformed

    private void setToiminnallisuusIkkunanArvot(ToiminnallisuusPanel uusiUI) {
        asetaAikaIkkunoidenArvot(uusiUI);
        luoOtsikkoCheckBoxit(uusiUI);
        uusiUI.setMittausAnalysoijaPalvelu(mittausAnalysoijaPalvelu);
        uusiUI.setFile(file);
        naytaSopivatDataForEveryNapit(uusiUI);
        naytaVaroitusIkkuna();
    }

    private void naytaVaroitusIkkuna() {
        ArrayList<Hyppy> mittauksessaOlevatHypyt = mittausAnalysoijaPalvelu.mittauksissaOlevatHypyt(mittausAnalysoijaPalvelu.getMittaukset());
        if (mittauksessaOlevatHypyt.size() > 0) {
            if (mittauksessaOlevatHypyt.get(0) != null && mittauksessaOlevatHypyt.get(0).hypynPituus() > 60000 || (mittauksessaOlevatHypyt.get(0).hypynPituus() > 2*mittausAnalysoijaPalvelu.mittaustenMittausvali(mittausAnalysoijaPalvelu.getMittaukset()) && mittauksessaOlevatHypyt.get(0).hypynPituus() > 60000)) {
                TiedostonSuurinHyppyIkkuna caution = new TiedostonSuurinHyppyIkkuna();
                caution.setHypyt(mittauksessaOlevatHypyt);
                caution.setBiggestGapEnds();
                caution.setBiggestGapLenght();
                caution.setBiggestGapStarts();
                caution.setVisible(true);
            }
        }
    }

    private void naytaSopivatDataForEveryNapit(ToiminnallisuusPanel uusiUI) {
        ButtonGroup dataForEveryButtonGroup = uusiUI.getdataForEveryButtonGroup();
        for (AbstractButton button : Collections.list(dataForEveryButtonGroup.getElements())) {
            if (mittausAnalysoijaPalvelu.naytaAikaValiNappi(mittausAnalysoijaPalvelu.getMittaukset(), button.getText())) {
                uusiUI.setRadioButtonVisible((JRadioButton) button);
            } else {
                uusiUI.setRadioButtonNotVisible((JRadioButton) button);
            }
        }
    }

    private void asetaAikaIkkunoidenArvot(ToiminnallisuusPanel uusiUI) {
        uusiUI.setDateFrom(mittausAnalysoijaPalvelu.mittaustenEnsimmainenDateString().replace("-", "/") + " " + mittausAnalysoijaPalvelu.mittaustenEnsimmainenKelloString().replace("-", ":"));
        uusiUI.setDateTo(mittausAnalysoijaPalvelu.mittaustenViimeinenDateString().replace("-", "/") + " " + mittausAnalysoijaPalvelu.mittaustenViimeinenKelloString().replace("-", ":"));

        uusiUI.setUseDataFromFromDate(mittausAnalysoijaPalvelu.mittaustenEnsimmainen().getAikaleima());
        uusiUI.setUseDataFromFromSeconds(mittausAnalysoijaPalvelu.mittaustenEnsimmainen().getAikaleima().getSeconds());
        uusiUI.setUseDataFromToDate(mittausAnalysoijaPalvelu.mittaustenViimeinen().getAikaleima());
        uusiUI.setUseDateFromToSeconds(mittausAnalysoijaPalvelu.mittaustenViimeinen().getAikaleima().getSeconds());
    }

    private void luoOtsikkoCheckBoxit(ToiminnallisuusPanel uusiUI) {
        if (mittausAnalysoijaPalvelu.mittaustenOtsikkoRivi().size() > 10) {
            ErrorTextField.setText("Too many rows in the data");
        } else {
            for (String sarakkeenOtsikko : mittausAnalysoijaPalvelu.mittaustenOtsikkoRivi()) {
                JCheckBox button = new JCheckBox(sarakkeenOtsikko);
                button.setName(sarakkeenOtsikko);
                uusiUI.addCheckBoxToValuesButtonGroup(button);
            }
        }
    }

    private void FilePathTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilePathTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FilePathTextFieldActionPerformed

    private File file;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BrowseButton;
    private javax.swing.JLabel ChooseAFHeader;
    private javax.swing.JLabel ErrorTextField;
    private javax.swing.JTextField FilePathTextField;
    private javax.swing.JLabel Header;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel piaqFileParser;
    private javax.swing.JPanel timeAndOther;
    // End of variables declaration//GEN-END:variables
}
