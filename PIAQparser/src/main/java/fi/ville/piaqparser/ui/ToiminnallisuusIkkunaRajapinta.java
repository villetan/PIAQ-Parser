/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.ui;

import java.util.Date;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

/**
 *
 * @author ville
 * 
 * Rajapinta, esittää vaatimukset ohjelman toiminnallisuusikkunalle.
 * 
 */
public interface ToiminnallisuusIkkunaRajapinta {
    
    public String getDateFrom();
    public void setDateFrom(String teksti);
    
    public String getDateTo();
    public void setDateTo(String teksti);
    
    public void addCheckBoxToValuesButtonGroup(JCheckBox box);
    public Date getUseDataFromFromDate();
    
    public Date getUseDataFromToDate();
    public List<JCheckBox> getValuesButtons();
    public List<String> getNotSelectedValuesButtons();
    
    public void setUseDataFromFromDate(Date date);
    public void setUseDataFromFromSeconds(int seconds);
    public void setUseDataFromToDate(Date date);
    public void setUseDateFromToSeconds(int seconds);
}