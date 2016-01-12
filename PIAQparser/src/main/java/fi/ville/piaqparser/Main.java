/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser;

import fi.ville.piaqparser.domain.Mittaus;
import fi.ville.piaqparser.services.MittausAnalysoijaPalvelu;
import fi.ville.piaqparser.services.TiedostonLukijaPalvelu;
import fi.ville.piaqparser.ui.TiedostonValitsijaFrame;
import fi.ville.piaqparser.util.AikaKaantaja;
import fi.ville.piaqparser.util.MittaustenAnalysoija;
import fi.ville.piaqparser.util.TiedostonKirjoittajaCSV;
import fi.ville.piaqparser.util.TiedostonKirjoittajaXML;
import fi.ville.piaqparser.util.TiedostonLukijaCSV;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ville
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        TiedostonLukijaPalvelu lukija = new TiedostonLukijaPalvelu();
        ArrayList<Mittaus> mittaukset = lukija.lueMittauksetListaksi("/home/ville/Asiakirjat/PegasorHarjoitus/data 4.1.2016/Piaq_export0.csv");
        System.out.println("aaa "+mittaukset.size());
        
        
        TiedostonValitsijaFrame ui = new TiedostonValitsijaFrame();
    }
}
