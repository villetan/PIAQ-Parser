/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.domain;

import java.util.Date;

/**
 *
 * @author ville
 */
public class Hyppy {
    
    private Mittaus hyppyAlkoiMittauksesta;
    private Mittaus hyppyPaattyiMittaukseen;

    public Hyppy(Mittaus hyppyAlkoiMittauksesta, Mittaus hyppyPaattyiMittaukseen) {
        this.hyppyAlkoiMittauksesta = hyppyAlkoiMittauksesta;
        this.hyppyPaattyiMittaukseen = hyppyPaattyiMittaukseen;
    }
    public Hyppy(){
        
    }

    /**
     * @return the hyppyAlkoiMittauksesta
     */
    
    
    public Mittaus getHyppyAlkoiMittauksesta() {
        return hyppyAlkoiMittauksesta;
    }

    /**
     * @param hyppyAlkoiMittauksesta the hyppyAlkoiMittauksesta to set
     */
    public void setHyppyAlkoiMittauksesta(Mittaus hyppyAlkoiMittauksesta) {
        this.hyppyAlkoiMittauksesta = hyppyAlkoiMittauksesta;
    }

    /**
     * @return the hyppyPaattyiMittaukseen
     */
    public Mittaus getHyppyPaattyiMittaukseen() {
        return hyppyPaattyiMittaukseen;
    }

    /**
     * @param hyppyPaattyiMittaukseen the hyppyPaattyiMittaukseen to set
     */
    public void setHyppyPaattyiMittaukseen(Mittaus hyppyPaattyiMittaukseen) {
        this.hyppyPaattyiMittaukseen = hyppyPaattyiMittaukseen;
    }
    
    public long hypynPituus(){
        return hyppyPaattyiMittaukseen.getAikaleima().getTime()-hyppyAlkoiMittauksesta.getAikaleima().getTime();
    }
    
}
