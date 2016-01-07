/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.services;

import fi.ville.piaqparser.domain.Hyppy;
import fi.ville.piaqparser.domain.Mittaus;
import fi.ville.piaqparser.util.AikaKaantaja;
import fi.ville.piaqparser.util.HypynTayttaja;
import fi.ville.piaqparser.util.MittaustenAnalysoija;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ville Palvelu-luokka, joka tarjoaa palveluita luettujen mittausten
 * käsittelyyn.
 */
public class MittausAnalysoijaPalvelu {

    private MittaustenAnalysoija analysoija;
    private ArrayList<Mittaus> mittaukset;
    private TiedostonLukijaPalvelu lukija;
    private List<String> otsikkoRivi;
    private AikaKaantaja aikaKaantaja;
    private HypynTayttaja hypynTayttaja;

    public MittausAnalysoijaPalvelu(String filePath) {
        lukija = new TiedostonLukijaPalvelu();
        this.mittaukset = lukija.lueMittauksetListaksi(filePath);
        analysoija = new MittaustenAnalysoija(getMittaukset());
        otsikkoRivi = lukija.lueOtsikonArvot(filePath);
        aikaKaantaja = new AikaKaantaja();
        hypynTayttaja = new HypynTayttaja();
    }

    /**
     *
     * @return Palauttaa kontruktorissa määritellyn mittauslistan ensimmäisen
     * Mittaus -olion.
     */
    public Mittaus mittaustenEnsimmainen() {
        if (mittaukset.size() > 0) {
            return mittaukset.get(0);
        }
        return null;
    }

    /**
     *
     * @return konstruktorissa määritellyn mittauslistan viimeinen Mittaus
     * -olio.
     */
    public Mittaus mittaustenViimeinen() {
        if (mittaukset.size() > 0) {
            return mittaukset.get(mittaukset.size() - 1);
        }
        return null;
    }

    /**
     *
     * @return aikaleiman osuus dd/MM/YYYY, mittausten ensimmäiselle
     * mittaukselle
     */
    public String mittaustenEnsimmainenDateString() {
        return analysoija.ensimmainenJaViimeinenMittausListana().get(0).palautaAikaleimaPVM();
    }

    /**
     *
     * @return aikaleiman osuus hh:mm:ss ensimmäiselle mittaukselle
     */
    public String mittaustenEnsimmainenKelloString() {
        return analysoija.ensimmainenJaViimeinenMittausListana().get(0).palautaAikaleimaKellonaika();
    }

    /**
     *
     * @return aikaleiman osuus dd/MM/YYYY, mittausten viimeiselle mittaukselle
     */
    public String mittaustenViimeinenDateString() {
        if (analysoija.ensimmainenJaViimeinenMittausListana().size() > 1) {
            return analysoija.ensimmainenJaViimeinenMittausListana().get(1).palautaAikaleimaPVM();
        } else {
            return mittaustenEnsimmainenDateString();
        }
    }

    /**
     *
     * @return aikaleiman osuus hh:mm:ss viimeiselle mittaukselle
     */
    public String mittaustenViimeinenKelloString() {
        if (analysoija.ensimmainenJaViimeinenMittausListana().size() > 1) {
            return analysoija.ensimmainenJaViimeinenMittausListana().get(1).palautaAikaleimaKellonaika();
        } else {
            return mittaustenEnsimmainenKelloString();
        }
    }

    /**
     *
     * @return Mittausten otsikkorivi. Mitattujen arvojen otsikot.
     */
    public List<String> MittaustenOtsikkoRivi() {
        return otsikkoRivi;
    }

    /**
     * Valitsee mittaukset konstruktorissa määritellystä listasta halutulta
     * aikaväliltä date1-date2
     *
     * @param date1 aloitushetki
     * @param date2 lopetushetki
     * @return Lista Mittaus-olioita, jotka on valittu halutulta aikaväliltä.
     */
    public ArrayList<Mittaus> valitseMittauksetAikavalilta(Date date1, Date date2) {

        ArrayList<Mittaus> palautettava = new ArrayList<>();
        if (date1.before(date2)) {

            for (Mittaus mittaus : getMittaukset()) {
                if (!mittaus.getAikaleima().before(date1) && !mittaus.getAikaleima().after(date2)) {
                    palautettava.add(mittaus);
                }
            }

            return palautettava;
        }
        if (date1.after(date2)) {

            return null;
        }

        for (Mittaus m : getMittaukset()) {
            if (m.getAikaleima().equals(date1) && m.getAikaleima().equals(date2)) {
                palautettava.add(m);
            }
        }

        return palautettava;
    }

    /**
     * @return konstruktorissa määritellyt mittaukset.
     */
    public ArrayList<Mittaus> getMittaukset() {
        return mittaukset;
    }

    /**
     * Poistaa mittauslistasta ne toteutuneet mittaukset jotka määritellään
     * alla.
     *
     * @param poistettavatSarakkeidenNimet ,sarakkeiden nimet jotka karsitaan.
     * @param listaJostaPoistetaan lista josta poisto-operaatio halutaan tehdä
     * @return lista Mittaus-olioita, joista on poistettu halutut mittaukset
     */
    public ArrayList<Mittaus> poistaMittauksistaSarakkeita(List<String> poistettavatSarakkeidenNimet, ArrayList<Mittaus> listaJostaPoistetaan) {
        ArrayList<Mittaus> palautettava = listaJostaPoistetaan;
        for (String sarakkeenOtsikko : poistettavatSarakkeidenNimet) {
            for (Mittaus mittaus : listaJostaPoistetaan) {
                mittaus.karsiMittaus(sarakkeenOtsikko);
            }
        }
        return palautettava;
    }

    /**
     * check MittaustenAnalysoija Javadoc
     *
     * @param radioButtonCheckedName UI:n napin nimi, joka on nimetty esim. "5
     * seconds"
     * @param mittauksetJoistaKeskiarvoLasketaan , lista josta keskiarvot
     * halutaan laskettavan
     * @return palauttaa toivotunlaisen listan, jossa keskiarvot laskettu. Ja
     * mittausväli on sama kuin mikä on UI:ssa valittu
     */
    public ArrayList<Mittaus> laskeKeskiarvoLista(String radioButtonCheckedName, ArrayList<Mittaus> mittauksetJoistaKeskiarvoLasketaan) {
        long aikaMS = aikaKaantaja.kaannaStringAjaksiMS(radioButtonCheckedName);

        return analysoija.laskeMittaustenKeskiarvo(aikaMS, mittauksetJoistaKeskiarvoLasketaan);
    }

    /**
     * Check MittaustenAnalysoija Javadoc
     *
     * @param mittausJostaMVlasketaan lista mittauksia, joista mittausväli
     * halutaan laskea.
     * @return palauttaa mittausvälin pituuden millisekunteina.
     */
    public long mittaustenMittausvali(ArrayList<Mittaus> mittausJostaMVlasketaan) {
        return analysoija.mittaustenMittausvaliMS(mittausJostaMVlasketaan);
    }

    /**
     * Laskee aikavälin siten, että nappeja, jotka ei ole mittausten puitteissa
     * mahdollista valita, ei näytetä.
     *
     * @param mittauksetJoitaTarkastellaan
     * @param nappi napin nimi muotoa "1 second", "24 hours" jne.
     * @return palauttaa totuusarvon, joka kertoo siitä pitäisikö napi näyttää
     * vai ei.
     */
    public boolean naytaAikaValiNappi(ArrayList<Mittaus> mittauksetJoitaTarkastellaan, String nappi) {
        long mittausvali = mittaustenMittausvali(mittauksetJoitaTarkastellaan);
        long napinAika = aikaKaantaja.kaannaStringAjaksiMS(nappi);
        if (mittausvali <= napinAika && napinAika <= mittaustenAikavalinPituus(mittauksetJoitaTarkastellaan)) {
            return true;
        }
        return false;
    }

    /**
     * Laskee aikavälin, jolta mittaukset ovat. Eli ensimmäisen ja viimeisen
     * mittauksen aikaleiman välisen ajan pituuden.
     *
     * @param mittaukset, lista josta aikaväli lasketaan.
     * @return palauttaa mittauslistan pituuden millisekunteina.
     */
    public long mittaustenAikavalinPituus(ArrayList<Mittaus> mittaukset) {
        if (mittaukset.size() == 0) {
            return 0;
        }
        if (mittaukset.size() == 1) {
            return 1;
        } else {
            return mittaukset.get(mittaukset.size() - 1).getAikaleima().getTime() - mittaukset.get(0).getAikaleima().getTime();
        }
    }

    /**
     * Etsii parametrina olevasta listasta hypyt ja palauttaa ne Hyppyolioina.
     *
     * @param hypyllinenListalista josta ollaan kiinnostuneita
     * @return järjestetty lista hypyistä joissa suurin hyppy on ensin toiseki
     * suurin toisena jne.
     */
    public ArrayList<Hyppy> mittauksissaOlevatHypyt(ArrayList<Mittaus> hypyllinenLista) {
        ArrayList<Hyppy> palautettava = analysoija.etsiListastaHypyt(hypyllinenLista, mittaustenMittausvali(hypyllinenLista));
        Collections.sort(palautettava);
        return palautettava;
    }

    private boolean taytetaankoHyppy(Hyppy hyppy) {
        if (hyppy.hypynPituus() < 10000) {
            return true;
        }
        return false;
    }

    /**
     * Metodi, joka automaattisesti täyttää alle 10s hypyt listasta jota
     * tarkastellaan. e.m 10s hypyt väliä voi muuttaa tämän luokan metodissa
     * taytetaankoHyppy.
     *
     * @param hypyllinenLista, lista jota tarkastellaan, ja josta hypyt halutaan
     * "täyttää"
     * @return palauttaa listan jossa hypyt on korjattu.
     */
    public ArrayList<Mittaus> taytaHypytListasta(ArrayList<Mittaus> hypyllinenLista) {
        ArrayList<Mittaus> hypytonLista = hypyllinenLista;
        ArrayList<Hyppy> hypyt = mittauksissaOlevatHypyt(hypyllinenLista);
        for (Hyppy hyppy : hypyt) {
            if (taytetaankoHyppy(hyppy)) {
                ArrayList<Mittaus> arvioidutMittauksetHypysta = hypynTayttaja.taytaHyppy(hyppy, mittaustenMittausvali(hypyllinenLista));
                hypytonLista.addAll(arvioidutMittauksetHypysta);
            }
        }
        Collections.sort(hypytonLista);
        return hypytonLista;
    }

}
