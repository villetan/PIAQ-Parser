package fi.ville.piaqparser.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Date;

/**
 *
 * @author ville Huolehtii eri ajanmittausten kääntämisestiä toiseen muotoon.
 *
 */
public class AikaKaantaja {

    private long dif;

    /**
     * Laskee valmiiksi vakiona olevan aikavälin kahden eri ajanmittauksen
     * standardin välillä.
     */
    public AikaKaantaja() {
        //tämä siksi ettei joka kierroksella tarvitse laskea
        Date dates1 = new Date(1970, 01, 01, 00, 00, 00);
        Date dates2 = new Date(2000, 01, 01, 00, 00, 00);
        dif = dates2.getTime() - dates1.getTime();

    }

    /**
     * Kaantaa ajan Pegasorin ajasta (sekunteja ajanhetkestä 1.1.2000 00:00:00)
     * Javan aikaan(millisekunteja ajanhetkestä 1.1.1970 00:00:00.000)
     *
     * @param timeInSecondsSince2000Jan1, aika sekunteina ajanhetkestä 1.1.2000
     * 00:00:00.
     * @return palauttaa käänntetyn ajan.
     */
    public long kaannaPegasorinAjastaJavaan(long timeInSecondsSince2000Jan1) {
        long timeInLong = dif + timeInSecondsSince2000Jan1 * 1000l;
        return timeInLong;
    }

    /**
     * Kaantaa ajan Javan ajasta Pegasorin aikaan.
     *
     * @param timeInMSSince1970Jan1, aika millisekunteina ajanhetekstä 1.1.1970
     * 00:00:00.000.
     * @return palauttaa käännetyn ajan.
     */
    public long kaannaJavanAjastaPegasorin(long timeInMSSince1970Jan1) {
        long timeInSec = (timeInMSSince1970Jan1 - dif) / 1000;
        return timeInSec;
    }

    /**
     * Kaantaa muotoa "1 second", "3 minutes", "4 hours" jne. muotoa olevat
     * string-muotoiset ajanmääreet millisekunneiksi.
     *
     * @param aika, String -muotoinen aika, joka käännetään.
     * @return palauttaa parametri aika:n käännettynä millisekunneiksi.
     */
    public Long kaannaStringAjaksiMS(String aika) {
        String[] splitattu = aika.split(" ");
        long palautettava = Long.valueOf(splitattu[0]).longValue();
        if (aika.contains("econd")) {
            return palautettava * 1000l;
        }
        if (aika.contains("inute")) {
            return palautettava * 60l * 1000l;
        }
        if (aika.contains("our")) {
            return palautettava * 60l * 60l * 1000l;
        }
        return null;
    }
}
