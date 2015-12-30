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
 */
public class MittausAnalysoijaPalvelu {

    private MittaustenAnalysoija analysoija;
    private ArrayList<Mittaus> mittaukset;
    private TiedostonLukijaPalvelu lukija;
    private List<String> otsikkoRivi;

    public MittausAnalysoijaPalvelu(String filePath) {
        lukija= new TiedostonLukijaPalvelu();
        this.mittaukset= lukija.lueMittauksetListaksi(filePath);
        analysoija = new MittaustenAnalysoija(mittaukset);
        otsikkoRivi= lukija.lueOtsikonArvot(filePath);
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
        }else{
            return mittaustenEnsimmainenDateString();
        }
    }
    public String mittaustenViimeinenKelloString(){
        if (analysoija.ensimmainenJaViimeinenMittausListana().size() > 1) {
            return analysoija.ensimmainenJaViimeinenMittausListana().get(1).palautaAikaleimaKellonaika();
        }else{
            return mittaustenEnsimmainenKelloString();
        }
    }
    
    public List<String> MittaustenOtsikkoRivi(){
        return otsikkoRivi;
    }
    
    
    
    

}
