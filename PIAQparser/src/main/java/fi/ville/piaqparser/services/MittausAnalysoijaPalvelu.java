/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.services;

import fi.ville.piaqparser.domain.Mittaus;
import fi.ville.piaqparser.util.MittaustenAnalysoija;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ville
 * Palvelu-luokka, joka tarjoaa palveluita luettujen mittausten
 * käsittelyyn.
 */
public class MittausAnalysoijaPalvelu {

    private MittaustenAnalysoija analysoija;
    private ArrayList<Mittaus> mittaukset;
    private TiedostonLukijaPalvelu lukija;
    private List<String> otsikkoRivi;

    public MittausAnalysoijaPalvelu(String filePath) {
        lukija = new TiedostonLukijaPalvelu();
        this.mittaukset = lukija.lueMittauksetListaksi(filePath);
        analysoija = new MittaustenAnalysoija(getMittaukset());
        otsikkoRivi = lukija.lueOtsikonArvot(filePath);
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
                if(!mittaus.getAikaleima().before(date1) && !mittaus.getAikaleima().after(date2)){
                    palautettava.add(mittaus);
                }
            }
            
            return palautettava;
        }
        if(date1.after(date2)){
            
            return null;
        }
        
        for(Mittaus m : getMittaukset()){
            if(m.getAikaleima().equals(date1) && m.getAikaleima().equals(date2)){
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

}
