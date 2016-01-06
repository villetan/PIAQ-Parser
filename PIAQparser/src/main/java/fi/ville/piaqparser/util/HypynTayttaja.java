/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.util;

import fi.ville.piaqparser.domain.Hyppy;
import fi.ville.piaqparser.domain.Mittaus;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ville
 */
public class HypynTayttaja {

    public ArrayList<Mittaus> taytaHyppy(Hyppy hyppy, long mittausValiMS) {
        ArrayList<Mittaus> palautettava = new ArrayList<>();
        Mittaus alkupiste = hyppy.getHyppyAlkoiMittauksesta();
        Mittaus loppupiste = hyppy.getHyppyPaattyiMittaukseen();
        double kkAlakerta = loppupiste.getAikaleima().getTime() - alkupiste.getAikaleima().getTime();
        for (long i = mittausValiMS; i < hyppy.hypynPituus(); i += mittausValiMS) {
            Mittaus lisattava = new Mittaus();
            lisattava.setAikaleima(new Date(alkupiste.getAikaleima().getTime() + i));
            palautettava.add(lisattava);
            for (String alkupisteKey : alkupiste.getMittaukset().keySet()) {
                double kkYlakerta = loppupiste.getMittauksenArvo(alkupisteKey) - alkupiste.getMittauksenArvo(alkupisteKey);
                double kulmakerroin = kkYlakerta / kkAlakerta;
                double asetettavaArvo = kulmakerroin * (lisattava.getAikaleima().getTime() - alkupiste.getAikaleima().getTime()) + alkupiste.getMittauksenArvo(alkupisteKey);
                lisattava.lisaaMittaus(alkupisteKey, asetettavaArvo);
            }
        }
        return palautettava;
    }

}
