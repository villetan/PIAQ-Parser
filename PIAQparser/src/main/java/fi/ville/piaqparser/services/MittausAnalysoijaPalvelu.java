/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.services;

import fi.ville.piaqparser.domain.Mittaus;
import fi.ville.piaqparser.util.AikaKaantaja;
import fi.ville.piaqparser.util.MittaustenAnalysoija;
import java.util.ArrayList;
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

    public MittausAnalysoijaPalvelu(String filePath) {
        lukija = new TiedostonLukijaPalvelu();
        this.mittaukset = lukija.lueMittauksetListaksi(filePath);
        analysoija = new MittaustenAnalysoija(getMittaukset());
        otsikkoRivi = lukija.lueOtsikonArvot(filePath);
        aikaKaantaja=new AikaKaantaja();
    }

    public Mittaus mittaustenEnsimmainen() {
        if (mittaukset.size() > 0) {
            return mittaukset.get(0);
        }
        return null;
    }
    
    public Mittaus mittaustenViimeinen(){
        if(mittaukset.size()>0){
            return mittaukset.get(mittaukset.size()-1);
        }
        return null;
    }

    public String mittaustenEnsimmainenDateString() {
        return analysoija.ensimmainenJaViimeinenMittausListana().get(0).palautaAikaleimaPVM();
    }

    public String mittaustenEnsimmainenKelloString() {
        return analysoija.ensimmainenJaViimeinenMittausListana().get(0).palautaAikaleimaKellonaika();
    }

    public String mittaustenViimeinenDateString() {
        if (analysoija.ensimmainenJaViimeinenMittausListana().size() > 1) {
            return analysoija.ensimmainenJaViimeinenMittausListana().get(1).palautaAikaleimaPVM();
        } else {
            return mittaustenEnsimmainenDateString();
        }
    }

    public String mittaustenViimeinenKelloString() {
        if (analysoija.ensimmainenJaViimeinenMittausListana().size() > 1) {
            return analysoija.ensimmainenJaViimeinenMittausListana().get(1).palautaAikaleimaKellonaika();
        } else {
            return mittaustenEnsimmainenKelloString();
        }
    }

    public List<String> MittaustenOtsikkoRivi() {
        return otsikkoRivi;
    }

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
     * @return the mittaukset
     */
    public ArrayList<Mittaus> getMittaukset() {
        return mittaukset;
    }

    public ArrayList<Mittaus> poistaMittauksistaSarakkeita(List<String> poistettavatSarakkeidenNimet, ArrayList<Mittaus> listaJostaPoistetaan) {
        ArrayList<Mittaus> palautettava = listaJostaPoistetaan;
        for (String sarakkeenOtsikko : poistettavatSarakkeidenNimet) {
            for (Mittaus mittaus : listaJostaPoistetaan) {
                mittaus.karsiMittaus(sarakkeenOtsikko);
            }
        }
        return palautettava;
    }
    
    public ArrayList<Mittaus> laskeKeskiarvoLista(String radioButtonCheckedName,ArrayList<Mittaus> mittauksetJoistaKeskiarvoLasketaan){
        long aikaMS = aikaKaantaja.kaannaStringAjaksiMS(radioButtonCheckedName);
        
        return analysoija.laskeMittaustenKeskiarvo(aikaMS, mittauksetJoistaKeskiarvoLasketaan);
    }
    
    public long mittaustenMittausvali(ArrayList<Mittaus> mittausJostaMVlasketaan){
        return analysoija.mittaustenMittausvaliMS(mittausJostaMVlasketaan);
    }
    
    public boolean naytaAikaValiNappi(ArrayList<Mittaus> mittauksetJoitaTarkastellaan, String nappi){
        long mittausvali = mittaustenMittausvali(mittauksetJoitaTarkastellaan);
        long napinAika=aikaKaantaja.kaannaStringAjaksiMS(nappi);
        if(mittausvali<=napinAika && napinAika<=mittaustenAikavalinPituus(mittauksetJoitaTarkastellaan)){
            return true;
        }
        return false;
    }
    
    public long mittaustenAikavalinPituus(ArrayList<Mittaus> mittaukset){
        if(mittaukset.size()==0){
            return 0;
        }if( mittaukset.size()==1){
            return 1;
        }else{
            return mittaukset.get(mittaukset.size()-1).getAikaleima().getTime()-mittaukset.get(0).getAikaleima().getTime();
        }
    }

}
