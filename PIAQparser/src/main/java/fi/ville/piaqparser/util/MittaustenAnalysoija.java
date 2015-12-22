/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.util;

import fi.ville.piaqparser.domain.Mittaus;
import java.util.ArrayList;

/**
 *
 * @author ville
 */
public class MittaustenAnalysoija {

    private ArrayList<Mittaus> mittaukset;

    public MittaustenAnalysoija(ArrayList<Mittaus> mittaukset) {
        this.mittaukset = mittaukset;
    }

    public ArrayList<Mittaus> ensimmainenJaViimeinenMittausListana() {
        if(mittaukset.size()==1){
            ArrayList<Mittaus> yksiMittaus=new ArrayList<>();
            yksiMittaus.add(mittaukset.get(0));
            return yksiMittaus;
        }
        ArrayList<Mittaus> palautettava = new ArrayList<>();
        palautettava.add(mittaukset.get(0));
        palautettava.add(mittaukset.get(mittaukset.size() - 1));
        return palautettava;
    }

    public boolean mittauksissaHyppyja() {
        
        for (int i=1;i<mittaukset.size()-2;i++) {
            long aikavali=mittaukset.get(i).getAikaleima().getTime()-mittaukset.get(i-1).getAikaleima().getTime();
            if(aikavali!=mittaukset.get(i+2).getAikaleima().getTime()-mittaukset.get(i+1).getAikaleima().getTime()){
                return true;
            }
            
        }
        return false;
    }

    public long mittaustenMittausvaliMS() {
        long mittausvaliMS = -50;
        if (!mittauksissaHyppyja()) {
            if(mittaukset.size()==1){
                return 0;
            }
            Mittaus mittaus1 = mittaukset.get(0);
            Mittaus mittaus2 = mittaukset.get(1);
            mittausvaliMS = mittaus2.getAikaleima().getTime() - mittaus1.getAikaleima().getTime();
        }
        return mittausvaliMS;
    }

}
