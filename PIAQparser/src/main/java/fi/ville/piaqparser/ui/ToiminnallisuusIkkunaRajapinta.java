/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.ui;

import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

/**
 *
 * @author ville
 *
 * Rajapinta, esittää vaatimukset ohjelman toiminnallisuusikkunalle. Suurimmaksi
 * osaksi erilaiset komponenttien gettereitä ja settereitä.
 */
public interface ToiminnallisuusIkkunaRajapinta {

    public String getDateFrom();

    public void setDateFrom(String teksti);

    public String getDateTo();

    public void setDateTo(String teksti);

    /**
     * Lisää ChecckBoxin ButtonGroupiin.
     *
     * @param box, JCheckBox, joka lisätään ButtonGroupiin.
     */
    public void addCheckBoxToValuesButtonGroup(JCheckBox box);

    public Date getUseDataFromFromDate();

    public Date getUseDataFromToDate();

    public List<JCheckBox> getValuesButtons();

    public List<String> getNotSelectedValuesButtons();

    public String getSelectedRadioButton();

    public void setUseDataFromFromDate(Date date);

    public void setUseDataFromFromSeconds(int seconds);

    public void setUseDataFromToDate(Date date);

    public void setUseDateFromToSeconds(int seconds);

    public void setRadioButtonVisible(JRadioButton button);

    public void setRadioButtonNotVisible(JRadioButton button);

    public ButtonGroup getdataForEveryButtonGroup();
}
