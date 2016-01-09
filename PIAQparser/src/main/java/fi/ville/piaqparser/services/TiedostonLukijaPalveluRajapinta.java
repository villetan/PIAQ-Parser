/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.services;

import fi.ville.piaqparser.domain.Mittaus;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ville
 * Rajapinta, joka määrittää Tiedoston lukijalle halutut metodit.
 */
public interface TiedostonLukijaPalveluRajapinta {
    /**
     * Lukee Mittaukset listaksi Mittausolioita. 
     * @param tiedostoPolku, tiedostopolku, josta .xml tai .csv tiedosto luetaan.
     * @return ArrayList mittausolioita.
     */
    public List<Mittaus> lueMittauksetListaksi(String tiedostoPolku);
    
    /**
     * Lukee Otsikkorivin arvot, lukuunottamatta Time saraketta, joka on oleellinen osa jokaista mittausta.
     * @param tiedostoPolku, tiedostopolku, josta .xml tai .csv tiedosto luetaan.
     * @return palauttaa Arraylist, jossa Stringeinä otsikkojen nimet. Näytetään UIssa.
     */
    public List<String> lueOtsikonArvot(String tiedostoPolku);
}
