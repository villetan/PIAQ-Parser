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

    public AikaKaantaja() {
        //tämä siksi ettei joka kierroksella tarvitse laskea
        Date dates1 = new Date(1970, 01, 01, 00, 00, 00);
        Date dates2 = new Date(2000, 01, 01, 00, 00, 00);
        dif = dates2.getTime() - dates1.getTime();

    }

    public long kaannaPegasorinAjastaJavaan(long timeInSecondsSince2000Jan1) {
        long timeInLong = dif + timeInSecondsSince2000Jan1 * 1000l;
        return timeInLong;
    }

    public long kaannaJavanAjastaPegasorin(long timeInMSSince1970Jan1) {
        long timeInSec = (timeInMSSince1970Jan1 - dif) / 1000;
        return timeInSec;
    }

    public Long kaannaStringAjaksiMS(String aika) {
        String[] splitattu = aika.split(" ");
        long palautettava = Long.valueOf(splitattu[0]).longValue();
        if (aika.contains("econd")) {
            return palautettava * 1000l;
        }
        if (aika.contains("inute")) {
            return palautettava * 60l * 1000l;
        }if(aika.contains("our")){
            return palautettava*60l*60l*1000l;
        }
        return null;
    }
}
