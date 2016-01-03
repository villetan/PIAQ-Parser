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
public class Mittaus {

    private Date aikaleima;

    private HashMap<String, Double> mittaukset;

    public Mittaus(Date aikaleima) {
        this.aikaleima = aikaleima;
        this.mittaukset = new HashMap<>();
    }

    public Mittaus() {
        this.mittaukset = new HashMap<>();

    }

    public HashMap<String, Double> getMittaukset() {
        return mittaukset;
    }

    public void lisaaMittaus(String nimi, double arvo) {
        if (mittaukset != null) {
            mittaukset.put(nimi, arvo);
        }
    }

    public void karsiMittaus(String nimi) {
        if (mittaukset != null) {
            mittaukset.remove(nimi);
        }
    }

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

    public String palautaAikaleimaPVM() {
        String pvm = aikaleima.getDate() + "/" + (aikaleima.getMonth() + 1) + "/" + (aikaleima.getYear() + 1900);
        return pvm;
    }

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

}
