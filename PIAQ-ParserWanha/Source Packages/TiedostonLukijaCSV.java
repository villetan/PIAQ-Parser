
import hiekkalaatikko.AikaKaantaja;
import hiekkalaatikko.Mittaus;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        for (String avain : indexesMap.keySet()) {
            System.out.println("avain " + avain);
        }
        int rivejaLuettu = 0;
        try {
            while ((line = br.readLine()) != null) {
                //selvitä mittausarvojen sarakkeet

                String[] rivi = line.split(splitBy);
                //DateFormat format = new SimpleDateFormat("DD/MM/YYYY, hh:mm:ss", Locale.ENGLISH);

                if (rivejaLuettu < 10000) {
                    // System.out.println("Rivejä luettu: " + rivejaLuettu++);
                }

                Mittaus mittaus = new Mittaus();
                Long aikaMittaus = Long.valueOf(rivi[0]).longValue();
                long kaannetty = aikaKaantaja.kaannaPegasorinAjaksi(aikaMittaus);
                mittaus.setAikaleima(new Date(kaannetty));

                for (String mittauksenAvain : indexesMap.keySet()) {
                    //TODO sarake tyhjä=> index out of bound, keksi siis tapa korvata tyhjät kentät 0:lla
                    mittaus.lisaaMittaus(mittauksenAvain, Double.parseDouble(rivi[indexesMap.get(mittauksenAvain)]));

                }
                mittausLista.add(mittaus);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("!!! FILE NOT FOUND EXCEPTION " + ex);
        } catch (IOException e) {
            System.out.println("!!! IOEXCEPTION " + e);
        }
        return mittausLista;
    }

    //toimii hyvin, palauttaa Mapin jossa avaimina on sarakkeiden nimet ja arvoina indeksit 0:sta alkaen
    public Map<String, Integer> selvitaSarakkeidenArvotJaIndeksit(String tiedostoPolku, BufferedReader br) {
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
