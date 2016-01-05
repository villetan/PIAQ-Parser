/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.util;

import fi.ville.piaqparser.domain.Mittaus;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ville Mittausten analysoimiseen luotu luokka.
 */
public class MittaustenAnalysoija {

    private ArrayList<Mittaus> mittaukset;

    public MittaustenAnalysoija(ArrayList<Mittaus> mittaukset) {
        this.mittaukset = mittaukset;
    }

    public ArrayList<Mittaus> ensimmainenJaViimeinenMittausListana() {
        if (mittaukset.size() == 1) {
            ArrayList<Mittaus> yksiMittaus = new ArrayList<>();
            yksiMittaus.add(mittaukset.get(0));
            return yksiMittaus;
        } else if (mittaukset.size() == 0) {
            return new ArrayList<>();
        }
        ArrayList<Mittaus> palautettava = new ArrayList<>();
        palautettava.add(mittaukset.get(0));
        palautettava.add(mittaukset.get(mittaukset.size() - 1));
        return palautettava;

    }

    public boolean mittauksissaHyppyja() {

        for (int i = 1; i < mittaukset.size() - 2; i++) {
            long aikavali = mittaukset.get(i).getAikaleima().getTime() - mittaukset.get(i - 1).getAikaleima().getTime();
            if (aikavali != mittaukset.get(i + 1).getAikaleima().getTime() - mittaukset.get(i).getAikaleima().getTime()) {
                System.out.println("HYPPY KOHDASSA: " + i);
                return true;
            }

        }
        return false;
    }

    public long mittaustenMittausvaliMS(ArrayList<Mittaus> listaJostaMittausvaliLasketaan) {
        long mittausvaliMS = -50;
        //if (!mittauksissaHyppyja()) {
        if (listaJostaMittausvaliLasketaan.size() == 1) {
            return 0;
        }
        if (listaJostaMittausvaliLasketaan.size() == 2) {
            Mittaus mittaus1 = listaJostaMittausvaliLasketaan.get(0);
            Mittaus mittaus2 = listaJostaMittausvaliLasketaan.get(1);
            mittausvaliMS = mittaus2.getAikaleima().getTime() - mittaus1.getAikaleima().getTime();
        }else{
           Mittaus mittaus1 = listaJostaMittausvaliLasketaan.get(1);
            Mittaus mittaus2 = listaJostaMittausvaliLasketaan.get(2);
            mittausvaliMS = mittaus2.getAikaleima().getTime() - mittaus1.getAikaleima().getTime(); 
            System.out.println("***!*!*!*!*!*!*!"+mittausvaliMS);
        }
        //}
        return mittausvaliMS;
    }

    public ArrayList<Mittaus> laskeMittaustenKeskiarvo(long haluttuMittausvaliMS, ArrayList<Mittaus> mittauksetLasketaanTasta) {
        ArrayList<Mittaus> keskiarvoLista = new ArrayList<>();
        long alkuperainenMittausvali = mittaustenMittausvaliMS(mittauksetLasketaanTasta);
        //TODO: jos rivejaMittaukseen ei ole int? esim 5/2?
        int rivejaMittaukseen = (int) haluttuMittausvaliMS / (int) alkuperainenMittausvali;
        if (haluttuMittausvaliMS >= alkuperainenMittausvali) {
            int mittauksiaLuettu = 0;
            HashMap<String, Double> mittaustenYhteenlasketut = new HashMap();
            Mittaus uusiMittausKeskiarvoListaan = new Mittaus();
            laskeMittausListanKeskiarvot(mittauksiaLuettu, uusiMittausKeskiarvoListaan, mittaustenYhteenlasketut, rivejaMittaukseen, keskiarvoLista, mittauksetLasketaanTasta);

            return keskiarvoLista;

        }

        return new ArrayList<>();
    }

    private void laskeMittausListanKeskiarvot(int mittauksiaLuettu, Mittaus uusiMittausKeskiarvoListaan, HashMap<String, Double> mittaustenYhteenlasketut, int rivejaMittaukseen, ArrayList<Mittaus> keskiarvoLista, ArrayList<Mittaus> mittauksetLasketaanTasta) {
        for (int i = 0; i < mittauksetLasketaanTasta.size(); i++) {

            if (mittauksiaLuettu == 0) {
                uusiMittausKeskiarvoListaan.setAikaleima(mittauksetLasketaanTasta.get(i).getAikaleima());
            }
            Mittaus mittaus = mittauksetLasketaanTasta.get(i);

            laskeMittaustenArvotYhteen(mittaus, mittauksiaLuettu, mittaustenYhteenlasketut);

            mittauksiaLuettu++;
            if (mittauksiaLuettu == rivejaMittaukseen) {

                asetaMittaukselleKeskiarvo(mittaus, mittaustenYhteenlasketut, mittauksiaLuettu, uusiMittausKeskiarvoListaan);

                keskiarvoLista.add(uusiMittausKeskiarvoListaan);
                mittauksiaLuettu = 0;
                uusiMittausKeskiarvoListaan = new Mittaus();
                mittaustenYhteenlasketut = new HashMap();
                uusiMittausKeskiarvoListaan = new Mittaus();

            }

        }

        laskeViimeinenMittausKeskiarvoListaan(rivejaMittaukseen, keskiarvoLista, mittauksetLasketaanTasta);

    }

    private void laskeViimeinenMittausKeskiarvoListaan(int rivejaMittaukseen, ArrayList<Mittaus> keskiarvoLista, ArrayList<Mittaus> mittauksetLasketaanTasta) {
        if (mittauksetLasketaanTasta.size() % rivejaMittaukseen != 0) {
            //viimeinen keskiarvo!
            Mittaus viimeinenMittaus = new Mittaus();
            HashMap<String, Double> yhteenlasketutArvot = new HashMap<>();
            int rivejaViimeiseenMittaukseen = mittauksetLasketaanTasta.size() % rivejaMittaukseen;
            for (int j = mittauksetLasketaanTasta.size() - rivejaViimeiseenMittaukseen; j < mittauksetLasketaanTasta.size(); j++) {
                if (j == mittauksetLasketaanTasta.size() - rivejaViimeiseenMittaukseen) {
                    viimeinenMittaus.setAikaleima(mittauksetLasketaanTasta.get(j).getAikaleima());
                    for (String avain : mittauksetLasketaanTasta.get(j).getMittaukset().keySet()) {
                        yhteenlasketutArvot.put(avain, mittauksetLasketaanTasta.get(j).getMittauksenArvo(avain));
                    }
                    continue;
                }
                for (String avain : mittauksetLasketaanTasta.get(j).getMittaukset().keySet()) {
                    yhteenlasketutArvot.put(avain, yhteenlasketutArvot.get(avain) + mittauksetLasketaanTasta.get(j).getMittauksenArvo(avain));
                }
            }

            for (String key : yhteenlasketutArvot.keySet()) {
                viimeinenMittaus.lisaaMittaus(key, yhteenlasketutArvot.get(key) / rivejaViimeiseenMittaukseen);
            }
            keskiarvoLista.add(viimeinenMittaus);
        }
    }

    private void asetaMittaukselleKeskiarvo(Mittaus mittaus, HashMap<String, Double> mittaustenYhteenlasketut, int mittauksiaLuettu, Mittaus uusiMittausKeskiarvoListaan) {
        for (String mittauksenAvain : mittaus.getMittaukset().keySet()) {
            double keskiarvoMittaus = mittaustenYhteenlasketut.get(mittauksenAvain) / mittauksiaLuettu;
            uusiMittausKeskiarvoListaan.lisaaMittaus(mittauksenAvain, keskiarvoMittaus);

        }
    }

    private void laskeMittaustenArvotYhteen(Mittaus mittaus, int mittauksiaLuettu, HashMap<String, Double> mittaustenYhteenlasketut) {
        for (String mittauksenAvain : mittaus.getMittaukset().keySet()) {
            if (mittauksiaLuettu == 0) {
                mittaustenYhteenlasketut.put(mittauksenAvain, mittaus.getMittauksenArvo(mittauksenAvain));
            } else {
                double aikaisempiArvo = mittaustenYhteenlasketut.get(mittauksenAvain);
                mittaustenYhteenlasketut.put(mittauksenAvain, aikaisempiArvo + mittaus.getMittauksenArvo(mittauksenAvain));

            }

        }
    }

}
