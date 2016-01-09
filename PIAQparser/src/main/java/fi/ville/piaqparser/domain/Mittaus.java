package fi.ville.piaqparser.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author ville
 *
 * Luokka pitää huolta datasta tulevan "rivin" eli yhden mittauksen
 * tallentamisesta olio-muotoon.
 */
public class Mittaus implements Comparable<Mittaus>{

    private Date aikaleima;

    private HashMap<String, Double> mittaukset;

    public Mittaus(Date aikaleima) {
        this.aikaleima = aikaleima;
        this.mittaukset = new HashMap<>();
    }

    public Mittaus() {
        this.mittaukset = new HashMap<>();

    }

    /**
     * 
     * @return palauttaa Mittaus olion kaikki eri mittaukset, lukuunottamatta aikaleimaa.
     */
    public HashMap<String, Double> getMittaukset() {
        return mittaukset;
    }

    /**
     * Lisaa Mittausoliolle mittauksen.
     * @param nimi mittauksen nimi
     * @param arvo mittauksen arvo
     */
    public void lisaaMittaus(String nimi, double arvo) {
        if (mittaukset != null) {
            mittaukset.put(nimi, arvo);
        }
    }
/**
 * Poistaa Mittausoliolta mittauksen
 * @param nimi mittaus joka poistetaan
 */
    public void karsiMittaus(String nimi) {
        if (mittaukset != null) {
            mittaukset.remove(nimi);
        }
    }
/**
 * 
 * @param avain mittauksen nimi
 * @return palauttaa avainta vastaavan arvon.
 */
    public Double getMittauksenArvo(String avain) {
        if (mittaukset.keySet().contains(avain)) {
            return mittaukset.get(avain);
        }
        return null;
    }

    public String toString() {
        String palautettava = "Mittaus: " + aikaleima + "\n";
        for (String key : mittaukset.keySet()) {
            palautettava += key + " " + mittaukset.get(key) + "\n";

        }
        palautettava += "\n";
        return palautettava;
    }
/**
 * Muotoilee päivämäärän sopivaan muotoon.
 * @return 
 */
    public String palautaAikaleimaPVM() {
        String pvm = aikaleima.getDate() + "/" + (aikaleima.getMonth() + 1) + "/" + (aikaleima.getYear() + 1900);
        return pvm;
    }

    /**
     * Muotoilee kellonajan sopivaan muotoon.
     * @return 
     */
        public String palautaAikaleimaKellonaika() {
        String kellonaika = aikaleima.getHours() + ":" + aikaleima.getMinutes() + ":" + aikaleima.getSeconds();
        if (aikaleima.getSeconds() < 10) {
            kellonaika = aikaleima.getHours() + ":" + aikaleima.getMinutes() + ":" + "0" + aikaleima.getSeconds();
            if (aikaleima.getMinutes() < 10) {
                kellonaika = aikaleima.getHours() + ":" + "0" + aikaleima.getMinutes() + ":" + "0" + aikaleima.getSeconds();
                if (aikaleima.getHours() < 10) {
                    kellonaika = "0" + aikaleima.getHours() + ":" + "0" + aikaleima.getMinutes() + ":" + "0" + aikaleima.getSeconds();
                    return kellonaika;
                }
                return kellonaika;
            }
            return kellonaika;
        }
        if (aikaleima.getMinutes() < 10) {
            kellonaika = aikaleima.getHours() + ":" + "0" + aikaleima.getMinutes() + ":" + aikaleima.getSeconds();
            if (aikaleima.getHours() < 10) {
                kellonaika = "0" + aikaleima.getHours() + ":" + "0" + aikaleima.getMinutes() + ":" + aikaleima.getSeconds();
                return kellonaika;
            }
            return kellonaika;
        }
        if (aikaleima.getHours() < 10) {
            kellonaika = "0" + aikaleima.getHours() + ":" + aikaleima.getMinutes() + ":" + aikaleima.getSeconds();
            return kellonaika;
        }

        return kellonaika;
    }

    /**
     * @return the aikaleima
     */
    public Date getAikaleima() {
        return aikaleima;
    }

    /**
     * @param aikaleima the aikaleima to set
     */
    public void setAikaleima(Date aikaleima) {
        this.aikaleima = aikaleima;
    }

    /**
     * 
     * @param verrattava
     * @return Mittaukset katsotaan samoiksi jos niillä on sama aikaleima.
     */
    public boolean equals(Mittaus verrattava) {
        if (this.aikaleima != null && verrattava.getAikaleima() != null) {
            if (!aikaleima.equals(verrattava.getAikaleima())) {

                return false;
            }
            if (!this.mittaukset.equals(verrattava.getMittaukset())) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Mittaus m) {
        if(m.getAikaleima().equals(this.aikaleima)){
            return 0;
        }
        if(m.getAikaleima().before(aikaleima)){
            return 1;
        }else{
            return -1;
        }
    }
    
    

}
