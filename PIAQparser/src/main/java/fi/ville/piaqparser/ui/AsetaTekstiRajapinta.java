/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.ui;

import javax.swing.JRadioButton;

/**
 *
 * @author ville
 */
public interface AsetaTekstiRajapinta {
    
    public String getDateFrom();
    public void setDateFrom(String teksti);
    
    public String getDateTo();
    public void setDateTo(String teksti);
    
    public void addButtonToValuesButtonGroup(JRadioButton button);
    
    
}
