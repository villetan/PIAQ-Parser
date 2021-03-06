package fi.ville.piaqparser.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import fi.ville.piaqparser.domain.Mittaus;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ville Luokka, joka sisältää CSV-tiedoston lukemiseen vaadittavan
 * logiikan.
 */
public class TiedostonLukijaCSV {

    String line = "";
    String splitBy = "\t";

    /**
     * Lukee Mittauksen listaksi, ei ota huomioon ensimmäistä saraketta
     * (oletettavasti aika)
     *
     *
     * @param tiedostoPolku luettavan tiedoston polku
     * @param br Buffered reader, lukee tiedoston polusta.
     * @return ArrayList Mittauksista.
     */
    public ArrayList<Mittaus> lueMittauksetListaksi(String tiedostoPolku, BufferedReader br) {
        AikaKaantaja aikaKaantaja = new AikaKaantaja();
        ArrayList<Mittaus> mittausLista = new ArrayList<>();
        Map<String, Integer> indexesMap = lueHeaderMapiksi(tiedostoPolku, br);

        int rivejaLuettu = 0;
        try {
            while ((line = br.readLine()) != null) {
                if (line.contains("#") || line.contains("ime")) {
                    continue;
                }
                String[] rivi = line.split(splitBy);
                //DateFormat format = new SimpleDateFormat("DD/MM/YYYY, hh:mm:ss", Locale.ENGLISH);

                Mittaus mittaus = new Mittaus();
                if (!rivi[0].contains(",")) {
                    String luettuAika = rivi[0];
                    luettuAika=luettuAika.replace(":", "-");
                    
                    String[] splitattu = luettuAika.split("T");
                    String dateOsio = splitattu[0];
                    String timeOsio = splitattu[1];
                    String[] dateSplitattu = dateOsio.split("-");
                    int vuosi = Integer.parseInt(dateSplitattu[0]);
                    int kk = Integer.parseInt(dateSplitattu[1]);
                    int paiva = Integer.parseInt(dateSplitattu[2]);
                    String[] timeSplitattu = timeOsio.split("-");
                    int tunti = Integer.parseInt(timeSplitattu[0]);
                    int minuutti = Integer.parseInt(timeSplitattu[1]);
                    String sek = timeSplitattu[2].replace(".000", "");
                    int sekunti = Integer.parseInt(sek);
                    Date date = new Date(vuosi - 1900, kk - 1, paiva, tunti, minuutti, sekunti);

                    mittaus.setAikaleima(date);
                } else {
                    rivi = line.split(",");
                    String luettuAika = rivi[0];
                    luettuAika=luettuAika.replace(":", "-");
                    String[] splitattu = luettuAika.split("T");
                    String dateOsio = splitattu[0];
                    String timeOsio = splitattu[1];
                    String[] dateSplitattu = dateOsio.split("-");
                    int vuosi = Integer.parseInt(dateSplitattu[2]);
                    int kk = Integer.parseInt(dateSplitattu[1]);
                    int paiva = Integer.parseInt(dateSplitattu[0]);
                    String[] timeSplitattu = timeOsio.split("-");
                    int tunti = Integer.parseInt(timeSplitattu[0]);
                    int minuutti = Integer.parseInt(timeSplitattu[1]);
                    String sek = timeSplitattu[2].replace(".000", "");
                    int sekunti = Integer.parseInt(sek);
                    Date date = new Date(vuosi - 1900, kk - 1, paiva, tunti, minuutti, sekunti);
                    mittaus.setAikaleima(date);
                }
                for (String mittauksenAvain : indexesMap.keySet()) {
                    //TODO sarake tyhjä=> index out of bound, keksi siis tapa korvata tyhjät kentät 0:lla
                    mittaus.lisaaMittaus(mittauksenAvain, Double.parseDouble(rivi[indexesMap.get(mittauksenAvain)]));

                }
                if (mittaus.getAikaleima() != null) {
                    mittausLista.add(mittaus);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("!!! FILE NOT FOUND EXCEPTION " + ex);
        } catch (IOException e) {
            System.out.println("!!! IOEXCEPTION " + e);
        }
        return mittausLista;
    }

    /**
     * Lukee tiedoston otsikkorivin. Oletetaan että kaikkia otsikkorivin arvoja
     * vastaa joku arvo.
     *
     * @param tiedostoPolku - luettavan tiedoston tiedostopolku;
     * @param br lukija joka lukee tiedostopolun tiedoston
     * @return HashMap, avaimena mittauksen sarakkeen nimi ja arvona kyseisen
     * mittauksen indeksi
     */
    public HashMap<String, Integer> lueHeaderMapiksi(String tiedostoPolku, BufferedReader br) {
        HashMap<String, Integer> mittaustenIndeksit = new HashMap<>();
        try {
            String riwi = "";

            while ((riwi = br.readLine()) != null) {
                if(riwi.contains("#")){
                    continue;
                }
                if (riwi.contains("\t")) {
                    String[] mittaus = riwi.split(splitBy);
                    for (int i = 1; i < mittaus.length; i++) {
                        mittaustenIndeksit.put(mittaus[i], i);

                    }
                    return mittaustenIndeksit;
                }
                if (riwi.contains(",")) {
                    String[] mittaus = riwi.split(",");
                    for (int i = 1; i < mittaus.length; i++) {
                        mittaustenIndeksit.put(mittaus[i], i);

                    }
                    return mittaustenIndeksit;
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("!!! FILE NOT FOUND EXCEPTION " + ex);
        } catch (IOException e) {
            System.out.println("!!! IOEXCEPTION " + e);
        }

        return mittaustenIndeksit;
    }

}
