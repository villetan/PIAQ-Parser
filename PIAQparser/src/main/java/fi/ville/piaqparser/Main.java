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
//        String tiedostoPolkuCSV = "/home/ville/CSVtesti.csv";
//        String tiedostoPolkuXML = "/home/ville/Asiakirjat/PegasorHarjoitus/PIAQ3.xml";
//        BufferedReader br = new BufferedReader(new FileReader(tiedostoPolkuCSV));
//        TiedostonLukijaCSV tiedostonLukija = new TiedostonLukijaCSV();
//
//        ArrayList<Mittaus> mittaukset = tiedostonLukija.lueMittauksetListaksi(tiedostoPolkuCSV, br);
//
//        System.out.println("size" + mittaukset.size());
////        for (int i = 0; i < 12000; i++) {
////            mittaukset.remove(mittaukset.size() - 1);
////        }
////        TiedostonKirjoittajaXML kirjoittaja = new TiedostonKirjoittajaXML();
////        kirjoittaja.kirjoitaTiedosto(mittaukset);
//        System.out.println("Get tokan sootA " + mittaukset.get(1).getMittauksenArvo("sootA"));
        
      //  ArrayList<Mittaus> mittauksetXML=new TiedostonLukijaPalvelu().lueMittauksetListaksi("src/main/resources/PIAQ3.xml");
        ArrayList<Mittaus> mittauksetXML=new TiedostonLukijaPalvelu().lueMittauksetListaksi("src/main/resources/Piaq_export0.xml");
        MittaustenAnalysoija a = new MittaustenAnalysoija(mittauksetXML);
        a.mittaustenMittausvaliMS(mittauksetXML);
        ArrayList<Mittaus> keskiarvot=a.laskeMittaustenKeskiarvo(30000, mittauksetXML);
        

        TiedostonValitsijaFrame ui = new TiedostonValitsijaFrame();
        

    }
}
