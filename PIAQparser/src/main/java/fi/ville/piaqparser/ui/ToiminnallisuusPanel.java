/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.ui;

import fi.ville.piaqparser.domain.Mittaus;
import fi.ville.piaqparser.services.MittausAnalysoijaPalvelu;
import fi.ville.piaqparser.util.AikaKaantaja;
import fi.ville.piaqparser.util.TiedostonKirjoittajaXML;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author ville
 * Panel, jossa sijaitsee toiminnallisuuden napit, kentät ja muut
 * valitsimet. Valitsimien luominen on osittain myös TiedostonvalitsijaFramen
 * vastuulla.
 */
public class ToiminnallisuusPanel extends javax.swing.JPanel implements ToiminnallisuusIkkunaRajapinta {

    /**
     * Creates new form UusiUI
     */
    public ToiminnallisuusPanel() {
        initComponents();
        setVisible(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dataForEveryButtonGroup = new javax.swing.ButtonGroup();
        CommandsWindow = new javax.swing.JPanel();
        Commands = new javax.swing.JLabel();
        makeNewXML = new javax.swing.JButton();
        TimeWindow = new javax.swing.JPanel();
        TimeLabel = new javax.swing.JLabel();
        thisSheetHasDataFrom = new javax.swing.JLabel();
        DataFrom = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        DataTo = new javax.swing.JLabel();
        UseDataFrom = new javax.swing.JLabel();
        useDataFromFromDate = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        useDataFromToDate = new javax.swing.JSpinner();
        useDataFromFromSeconds = new javax.swing.JSpinner();
        useDataFromToSeconds = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ErrorField = new javax.swing.JLabel();
        ParametersWindow = new javax.swing.JPanel();
        Parameters = new javax.swing.JLabel();
        values = new javax.swing.JLabel();
        dataForEvery = new javax.swing.JLabel();
        radioButton1second = new javax.swing.JRadioButton();
        radioButton15seconds = new javax.swing.JRadioButton();
        radioButton30seconds = new javax.swing.JRadioButton();
        radioButton1minute = new javax.swing.JRadioButton();
        radioButton5minutes = new javax.swing.JRadioButton();
        radioButton15minutes = new javax.swing.JRadioButton();
        radioButton1hour = new javax.swing.JRadioButton();
        valuesButtonsPanel = new javax.swing.JPanel();

        CommandsWindow.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Commands.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        Commands.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Commands.setText("Commands");

        makeNewXML.setText("Make a new Excel(XML)");
        makeNewXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeNewXMLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CommandsWindowLayout = new javax.swing.GroupLayout(CommandsWindow);
        CommandsWindow.setLayout(CommandsWindowLayout);
        CommandsWindowLayout.setHorizontalGroup(
            CommandsWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CommandsWindowLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(makeNewXML)
                .addContainerGap(411, Short.MAX_VALUE))
            .addComponent(Commands, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CommandsWindowLayout.setVerticalGroup(
            CommandsWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CommandsWindowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Commands, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(makeNewXML)
                .addGap(23, 23, 23))
        );

        TimeWindow.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TimeLabel.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        TimeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TimeLabel.setText("Time");

        thisSheetHasDataFrom.setText("This sheet has data:");

        DataFrom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DataFrom.setText("jLabel1");

        jLabel2.setText("To");

        DataTo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DataTo.setText("jLabel1");

        UseDataFrom.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        UseDataFrom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        UseDataFrom.setText("Use data from");

        useDataFromFromDate.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1451890639274L), null, null, java.util.Calendar.SECOND));
        useDataFromFromDate.setName("useDataFromSpinner"); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("To");

        useDataFromToDate.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MINUTE));
        useDataFromToDate.setName("useDataFromToSpinner"); // NOI18N

        useDataFromFromSeconds.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        useDataFromFromSeconds.setName("useDataFromFromSeconds"); // NOI18N

        useDataFromToSeconds.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        useDataFromToSeconds.setName("useDataFromToSeconds"); // NOI18N

        jLabel3.setText("seconds");

        jLabel4.setText("seconds");

        jLabel5.setText("From");

        ErrorField.setForeground(java.awt.Color.red);
        ErrorField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout TimeWindowLayout = new javax.swing.GroupLayout(TimeWindow);
        TimeWindow.setLayout(TimeWindowLayout);
        TimeWindowLayout.setHorizontalGroup(
            TimeWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TimeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(UseDataFrom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(TimeWindowLayout.createSequentialGroup()
                .addGroup(TimeWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TimeWindowLayout.createSequentialGroup()
                        .addGroup(TimeWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TimeWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(TimeWindowLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(useDataFromFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(useDataFromToDate, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(TimeWindowLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(useDataFromFromSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(useDataFromToSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(TimeWindowLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(TimeWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(TimeWindowLayout.createSequentialGroup()
                                        .addGroup(TimeWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(TimeWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(DataTo, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                            .addComponent(DataFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(thisSheetHasDataFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TimeWindowLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ErrorField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        TimeWindowLayout.setVerticalGroup(
            TimeWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimeWindowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ErrorField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thisSheetHasDataFrom)
                .addGap(18, 18, 18)
                .addGroup(TimeWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DataFrom)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(TimeWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DataTo)
                    .addComponent(jLabel2))
                .addGap(44, 44, 44)
                .addComponent(UseDataFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(TimeWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(useDataFromFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(useDataFromToDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(TimeWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(useDataFromFromSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(useDataFromToSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        useDataFromFromDate.getAccessibleContext().setAccessibleName("");

        ParametersWindow.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Parameters.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        Parameters.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Parameters.setText("Parameters");

        values.setText("Values");

        dataForEvery.setText("Data for every");

        dataForEveryButtonGroup.add(radioButton1second);
        radioButton1second.setText("1 second");

        dataForEveryButtonGroup.add(radioButton15seconds);
        radioButton15seconds.setText("15 seconds");

        dataForEveryButtonGroup.add(radioButton30seconds);
        radioButton30seconds.setText("30 seconds");

        dataForEveryButtonGroup.add(radioButton1minute);
        radioButton1minute.setText("1 minute");

        dataForEveryButtonGroup.add(radioButton5minutes);
        radioButton5minutes.setText("5 minutes");

        dataForEveryButtonGroup.add(radioButton15minutes);
        radioButton15minutes.setText("15 minutes");

        dataForEveryButtonGroup.add(radioButton1hour);
        radioButton1hour.setText("1 hour");

        javax.swing.GroupLayout valuesButtonsPanelLayout = new javax.swing.GroupLayout(valuesButtonsPanel);
        valuesButtonsPanel.setLayout(valuesButtonsPanelLayout);
        valuesButtonsPanelLayout.setHorizontalGroup(
            valuesButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        valuesButtonsPanelLayout.setVerticalGroup(
            valuesButtonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ParametersWindowLayout = new javax.swing.GroupLayout(ParametersWindow);
        ParametersWindow.setLayout(ParametersWindowLayout);
        ParametersWindowLayout.setHorizontalGroup(
            ParametersWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Parameters, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ParametersWindowLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ParametersWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(values, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valuesButtonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ParametersWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dataForEvery, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(radioButton1hour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(radioButton15minutes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(radioButton5minutes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(radioButton1minute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(radioButton30seconds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(radioButton15seconds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(radioButton1second, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        ParametersWindowLayout.setVerticalGroup(
            ParametersWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ParametersWindowLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(Parameters, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ParametersWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataForEvery)
                    .addComponent(values))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ParametersWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ParametersWindowLayout.createSequentialGroup()
                        .addComponent(radioButton1second)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButton15seconds)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButton30seconds)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButton1minute)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButton5minutes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButton15minutes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioButton1hour))
                    .addComponent(valuesButtonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CommandsWindow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TimeWindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ParametersWindow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TimeWindow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ParametersWindow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CommandsWindow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private MittausAnalysoijaPalvelu mittausAnalysoijaPalvelu;
    private ArrayList<Mittaus> luetutMittaukset;
    private File file;
    private void makeNewXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeNewXMLActionPerformed
        TiedostonKirjoittajaXML kirjoittaja = kirjoitaTiedosto();
        if (kirjoittaja != null) {
            UusiXmlLuotu xmlCreatedWindow = new UusiXmlLuotu();
            xmlCreatedWindow.setFilePathName(kirjoittaja.getSaveToFilePath());
            xmlCreatedWindow.setVisible(true);
        }
    }//GEN-LAST:event_makeNewXMLActionPerformed

    private TiedostonKirjoittajaXML kirjoitaTiedosto() {
        TiedostonKirjoittajaXML kirjoittaja = new TiedostonKirjoittajaXML();
        MittausAnalysoijaPalvelu mittausAnalysoijaPalvelu=new MittausAnalysoijaPalvelu(file.getPath());
        ArrayList<Mittaus> valitutMittaukset = mittausAnalysoijaPalvelu.valitseMittauksetAikavalilta(getUseDataFromFromDate(), getUseDataFromToDate());
        if (!getUseDataFromFromDate().before(mittausAnalysoijaPalvelu.mittaustenEnsimmainen().getAikaleima())
                && !getUseDataFromToDate().after(mittausAnalysoijaPalvelu.mittaustenViimeinen().getAikaleima())
                && !getUseDataFromToDate().before(getUseDataFromFromDate())) {
            ArrayList<Mittaus> hypytTaytetty = mittausAnalysoijaPalvelu.taytaHypytListasta(valitutMittaukset);
            hypytTaytetty = mittausAnalysoijaPalvelu.poistaMittauksistaSarakkeita(getNotSelectedValuesButtons(), hypytTaytetty);
            String valittu = getSelectedRadioButton();
            ArrayList<Mittaus> lopulliset = mittausAnalysoijaPalvelu.laskeKeskiarvoLista(valittu, hypytTaytetty);
            String tiedostonNimi = (valitutMittaukset.get(0).palautaAikaleimaPVM() + "_" + valitutMittaukset.get(0).palautaAikaleimaKellonaika() + "-" + valitutMittaukset.get(valitutMittaukset.size() - 1).palautaAikaleimaPVM() + "_" + valitutMittaukset.get(valitutMittaukset.size() - 1).palautaAikaleimaKellonaika() + ".xml").replace("/", ",");
            String saveToFilePath = "src/main/resources/" + tiedostonNimi;
            kirjoittaja.setSaveToFilePath(saveToFilePath);
            kirjoittaja.kirjoitaTiedosto(lopulliset);
            ErrorField.setText("");
            return kirjoittaja;
        } else {
            ErrorField.setText("Choose dates correctly!");
            return null;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Commands;
    private javax.swing.JPanel CommandsWindow;
    private javax.swing.JLabel DataFrom;
    private javax.swing.JLabel DataTo;
    private javax.swing.JLabel ErrorField;
    private javax.swing.JLabel Parameters;
    private javax.swing.JPanel ParametersWindow;
    private javax.swing.JLabel TimeLabel;
    private javax.swing.JPanel TimeWindow;
    private javax.swing.JLabel UseDataFrom;
    private javax.swing.JLabel dataForEvery;
    private javax.swing.ButtonGroup dataForEveryButtonGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton makeNewXML;
    private javax.swing.JRadioButton radioButton15minutes;
    private javax.swing.JRadioButton radioButton15seconds;
    private javax.swing.JRadioButton radioButton1hour;
    private javax.swing.JRadioButton radioButton1minute;
    private javax.swing.JRadioButton radioButton1second;
    private javax.swing.JRadioButton radioButton30seconds;
    private javax.swing.JRadioButton radioButton5minutes;
    private javax.swing.JLabel thisSheetHasDataFrom;
    private javax.swing.JSpinner useDataFromFromDate;
    private javax.swing.JSpinner useDataFromFromSeconds;
    private javax.swing.JSpinner useDataFromToDate;
    private javax.swing.JSpinner useDataFromToSeconds;
    private javax.swing.JLabel values;
    private javax.swing.JPanel valuesButtonsPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void addCheckBoxToValuesButtonGroup(JCheckBox box) {
        valuesButtonsPanel.setLayout(new BoxLayout(valuesButtonsPanel, BoxLayout.Y_AXIS));
        valuesButtonsPanel.add(box);
    }

    @Override
    public void setUseDataFromFromDate(Date date) {
        useDataFromFromDate.setModel(new SpinnerDateModel(date, null, null, java.util.Calendar.MINUTE));
    }

    @Override
    public void setUseDataFromFromSeconds(int seconds) {

        useDataFromFromSeconds.setValue(seconds);
    }

    @Override
    public void setUseDataFromToDate(Date date) {
        useDataFromToDate.setModel(new SpinnerDateModel(date, null, null, java.util.Calendar.MINUTE));
    }

    @Override
    public void setUseDateFromToSeconds(int seconds) {
        useDataFromToSeconds.setValue(seconds);
    }

    @Override
    public List<JCheckBox> getValuesButtons() {
        List<JCheckBox> checkboxes = new ArrayList<>();
        Component[] components = valuesButtonsPanel.getComponents();
        for (Component component : components) {
            checkboxes.add((JCheckBox) component);
        }
        return checkboxes;
    }

    @Override
    public List<String> getNotSelectedValuesButtons() {
        List<String> palautettava = new ArrayList<>();
        for (JCheckBox checkBox : getValuesButtons()) {
            if (!checkBox.isSelected()) {
                palautettava.add(checkBox.getText());
            }
        }
        return palautettava;
    }

    @Override
    public String getDateFrom() {
        return DataFrom.getText();
    }

    @Override
    public void setDateFrom(String teksti) {
        DataFrom.setText(teksti);
    }

    @Override
    public String getDateTo() {
        return DataTo.getText();
    }

    @Override
    public void setDateTo(String teksti) {
        DataTo.setText(teksti);
    }

    @Override
    public Date getUseDataFromFromDate() {
        Date useDataFrom = (Date) useDataFromFromDate.getValue();
        useDataFrom.setSeconds((int) useDataFromFromSeconds.getValue());
        return useDataFrom;
    }

    @Override
    public Date getUseDataFromToDate() {
        Date useDataTo = (Date) useDataFromToDate.getValue();
        useDataTo.setSeconds((int) useDataFromToSeconds.getValue());
        return useDataTo;
    }

    /**
     * @param mittausAnalysoijaPalvelu the mittausAnalysoijaPalvelu to set
     */
    public void setMittausAnalysoijaPalvelu(MittausAnalysoijaPalvelu mittausAnalysoijaPalvelu) {
        this.mittausAnalysoijaPalvelu = mittausAnalysoijaPalvelu;
    }

    /**
     * @param filePath the filePath to set
     */
    /**
     * @param lueatutMittaukset the lueatutMittaukset to set
     */
    public void setLuetutMittaukset(ArrayList<Mittaus> lueatutMittaukset) {
        this.luetutMittaukset = lueatutMittaukset;
    }

    @Override
    public String getSelectedRadioButton() {
        String valittu = "";
        for (AbstractButton button : Collections.list(dataForEveryButtonGroup.getElements())) {
            if (button.isSelected()) {
                valittu = button.getText();
                return valittu;
            }
        }
        long mittausvali = mittausAnalysoijaPalvelu.mittaustenMittausvali(mittausAnalysoijaPalvelu.getMittaukset());
        if (mittausvali < 60000) {
            return mittausvali / 1000 + " seconds";
        } else if (mittausvali >= 60000 && mittausvali < 3600000) {
            return (mittausvali / 1000) / 60 + " minutes";
        } else {
            return ((mittausvali / 1000) / 60) / 24 + " hours";
        }
    }

    @Override
    public void setRadioButtonVisible(JRadioButton button) {
        button.setEnabled(true);
    }

    @Override
    public ButtonGroup getdataForEveryButtonGroup() {
        return dataForEveryButtonGroup;
    }

    @Override
    public void setRadioButtonNotVisible(JRadioButton button) {
        button.setEnabled(false);
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

}
