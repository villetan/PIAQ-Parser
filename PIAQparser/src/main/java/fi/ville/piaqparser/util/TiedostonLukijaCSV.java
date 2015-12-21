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
 * @author ville
 */
public class TiedostonLukijaCSV {

    String line = "";
    String splitBy = ",";

    public ArrayList<Mittaus> lueMittausListaksi(String tiedostoPolku, BufferedReader br) {
        AikaKaantaja aikaKaantaja = new AikaKaantaja();
        ArrayList<Mittaus> mittausLista = new ArrayList<>();
        Map<String, Integer> indexesMap = selvitaSarakkeidenArvotJaIndeksit(tiedostoPolku, br);

        int rivejaLuettu = 0;
        try {
            while ((line = br.readLine()) != null) {
                //selvitä mittausarvojen sarakkeet

                String[] rivi = line.split(splitBy);
                //DateFormat format = new SimpleDateFormat("DD/MM/YYYY, hh:mm:ss", Locale.ENGLISH);

                Mittaus mittaus = new Mittaus();
                Long aikaMittaus = Long.valueOf(rivi[0]).longValue();
                long kaannetty = aikaKaantaja.kaannaPegasorinAjastaJavaan(aikaMittaus);
                mittaus.setAikaleima(new Date(kaannetty));

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

    //toimii hyvin, palauttaa Mapin jossa avaimina on sarakkeiden nimet ja arvoina indeksit 0:sta alkaen
    public HashMap<String, Integer> selvitaSarakkeidenArvotJaIndeksit(String tiedostoPolku, BufferedReader br) {
        HashMap<String, Integer> mittaustenIndeksit = new HashMap<>();
        try {

            String[] mittaus = br.readLine().split(splitBy);
            for (int i = 1; i < mittaus.length; i++) {
                mittaustenIndeksit.put(mittaus[i], i);

            }
        } catch (FileNotFoundException ex) {
            System.out.println("!!! FILE NOT FOUND EXCEPTION " + ex);
        } catch (IOException e) {
            System.out.println("!!! IOEXCEPTION " + e);
        }
        return mittaustenIndeksit;
    }

}
