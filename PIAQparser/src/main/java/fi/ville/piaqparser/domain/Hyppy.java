/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.domain;

import java.util.Date;

/**
 *
 * @author ville Luokka, joka kuvaa lyhyitä hyppyjä mittausdatassa.
 */
public class Hyppy implements Comparable<Hyppy> {

    private Mittaus hyppyAlkoiMittauksesta;
    private Mittaus hyppyPaattyiMittaukseen;

    public Hyppy(Mittaus hyppyAlkoiMittauksesta, Mittaus hyppyPaattyiMittaukseen) {
        this.hyppyAlkoiMittauksesta = hyppyAlkoiMittauksesta;
        this.hyppyPaattyiMittaukseen = hyppyPaattyiMittaukseen;
    }

    public Hyppy() {

    }

    /**
     * @return the hyppyAlkoiMittauksesta
     */
    public Mittaus getHyppyAlkoiMittauksesta() {
        return hyppyAlkoiMittauksesta;
    }

    public void setHyppyAlkoiMittauksesta(Mittaus hyppyAlkoiMittauksesta) {
        this.hyppyAlkoiMittauksesta = hyppyAlkoiMittauksesta;
    }

    public Mittaus getHyppyPaattyiMittaukseen() {
        return hyppyPaattyiMittaukseen;
    }

    public void setHyppyPaattyiMittaukseen(Mittaus hyppyPaattyiMittaukseen) {
        this.hyppyPaattyiMittaukseen = hyppyPaattyiMittaukseen;
    }

    /**
     * Palauttaa hypyn pituuden.
     *
     * @return hypynpituus millisekunteina.
     */
    public long hypynPituus() {
        return hyppyPaattyiMittaukseen.getAikaleima().getTime() - hyppyAlkoiMittauksesta.getAikaleima().getTime();
    }

    @Override
    public int compareTo(Hyppy h) {
        if (hypynPituus() == h.hypynPituus()) {
            return 0;
        }
        if (hypynPituus() > h.hypynPituus()) {
            return -1;
        } else {
            return 1;
        }
    }

}
