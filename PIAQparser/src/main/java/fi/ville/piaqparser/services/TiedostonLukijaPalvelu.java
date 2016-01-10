/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.services;

import fi.ville.piaqparser.domain.Mittaus;
import fi.ville.piaqparser.util.TiedostonLukijaCSV;
import fi.ville.piaqparser.util.TiedostonLukijaXML;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ville Palvelu, joka tarjoaa palveluita tiedoston lukemiseen.
 */
public class TiedostonLukijaPalvelu implements TiedostonLukijaPalveluRajapinta {

    @Override
    public ArrayList<Mittaus> lueMittauksetListaksi(String tiedostoPolku) {
        if (tiedostoPolku.endsWith(".xml")) {
            return lueXmlListaksi(tiedostoPolku);
        } else if (tiedostoPolku.endsWith(".csv")) {
            try {
                return lueCsvListaksi(tiedostoPolku);
            } catch (Exception e) {
                System.out.println("File no found: " + e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<String> lueOtsikonArvot(String tiedostoPolku) {
        if (tiedostoPolku.endsWith(".xml")) {
            return lueXMLHeader(tiedostoPolku);

        } else if (tiedostoPolku.endsWith(".csv")) {
            try {
                return lueCSVHeader(tiedostoPolku);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TiedostonLukijaPalvelu.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return null;
    }

    private List<String> lueCSVHeader(String tiedostoPolku) throws FileNotFoundException {
        TiedostonLukijaCSV lukija = new TiedostonLukijaCSV();
        List<String> headers;

        headers = Arrays.asList(lukija.lueHeaderMapiksi(tiedostoPolku, new BufferedReader(new FileReader(new File(tiedostoPolku)))).keySet().toArray(new String[2]));
        return headers;
    }

    private List<String> lueXMLHeader(String tiedostoPolku) {
        TiedostonLukijaXML lukija = new TiedostonLukijaXML();
        List<String> headers = Arrays.asList(lukija.lueHeaderMapiksi(new File(tiedostoPolku), "Row").keySet().toArray(new String[2]));
        return headers;
    }

    private ArrayList<Mittaus> lueCsvListaksi(String tiedostoPolku) throws FileNotFoundException {
        TiedostonLukijaCSV lukija = new TiedostonLukijaCSV();

        ArrayList<Mittaus> csvMittaukset = lukija.lueMittauksetListaksi(tiedostoPolku, new BufferedReader(new FileReader(tiedostoPolku)));
        return csvMittaukset;

    }

    private ArrayList<Mittaus> lueXmlListaksi(String tiedostoPolku) {
        TiedostonLukijaXML lukija = new TiedostonLukijaXML();
        ArrayList<Mittaus> xmlMittaukset = lukija.lueMittauksetListaksi(new File(tiedostoPolku), "Row");
        return xmlMittaukset;
    }

}
