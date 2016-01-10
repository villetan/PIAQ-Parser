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
 * @author ville Luokka, joka tarjoaa toiminnallisuuden puuttuvan mittausdatan
 * täyttämiseen. Hienosti sanottuna, mittaus väli interpoloidaan lineaarisesti.
 * Rumasti sanottuna hyppäyksen alku- ja loppupisteen välille "piirretään"
 * suora, ja halutut mittaukset katsotaan suoran perusteella. Tämä tapa altistaa
 * mittausdatan virheille, mutta pienemmillä kuin 10s aikaväleillä tuskin
 * vaikuttaa.
 */
public class HypynTayttaja {

    /**
     * Metodi, joka täyttää mittauksessa olevan hypyn lineaarisilla arvioilla.
     * Esim. klo 13.15.22 on mittaus jonka jälkeen on kahdeksan sekunnin tauko,
     * jonka jälkeen mittaukset jatkuvat normaalisti 1s välein. Tällöin metodi
     * palautta 7 puuttuvaa mittausta tuolta aikaväliltä.
     *
     * @param hyppy Hyppy-olio, joka sisältää hypyn alku ja loppupisteen, sekä
     * välin pituuden.
     * @param mittausValiMS läpikäytävän listan mittausten "normaali" aikaväli
     * millisekunteina.
     * @return palauttaa listan mittauksista, jotka kuuluvat hypyn aikavälille.
     * Mittausten arvot ovat lineaarisesti arvioituja.
     */
    public ArrayList<Mittaus> taytaHyppy(Hyppy hyppy, long mittausValiMS) {
        ArrayList<Mittaus> palautettava = new ArrayList<>();
        Mittaus alkupiste = hyppy.getHyppyAlkoiMittauksesta();
        Mittaus loppupiste = hyppy.getHyppyPaattyiMittaukseen();
        double kkAlakerta = loppupiste.getAikaleima().getTime() - alkupiste.getAikaleima().getTime();
        for (long i = mittausValiMS; i < hyppy.hypynPituus(); i += mittausValiMS) {
            Mittaus lisattava = new Mittaus();
            lisattava.setAikaleima(new Date(alkupiste.getAikaleima().getTime() + i));
            palautettava.add(lisattava);
            arvioiSuoranAvulla(alkupiste, loppupiste, kkAlakerta, lisattava);
        }
        return palautettava;
    }

    private void arvioiSuoranAvulla(Mittaus alkupiste, Mittaus loppupiste, double kkAlakerta, Mittaus lisattava) {
        for (String alkupisteKey : alkupiste.getMittaukset().keySet()) {
            double kkYlakerta = loppupiste.getMittauksenArvo(alkupisteKey) - alkupiste.getMittauksenArvo(alkupisteKey);
            double kulmakerroin = kkYlakerta / kkAlakerta;
            double asetettavaArvo = kulmakerroin * (lisattava.getAikaleima().getTime() - alkupiste.getAikaleima().getTime()) + alkupiste.getMittauksenArvo(alkupisteKey);
            lisattava.lisaaMittaus(alkupisteKey, asetettavaArvo);
        }
    }

}
