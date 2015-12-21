
import java.util.Date;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ville
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

    public void lisaaMittaus(String nimi, double arvo) {
        if (mittaukset != null) {
            mittaukset.put(nimi, arvo);
        }
    }

    public double haeMittauksenArvo(String mittauksenNimi) {
        return mittaukset.get(mittauksenNimi);
    }

    public Date getAikaleima() {
        return aikaleima;
    }
    public void setAikaleima(Date aikaleima) {
        this.aikaleima = aikaleima;
    }

}
