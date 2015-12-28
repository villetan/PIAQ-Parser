/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.services;

import fi.ville.piaqparser.domain.Mittaus;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ville
 */
public interface TiedostonLukijaPalveluRajapinta {
    public List<Mittaus> lueMittauksetListaksi(String tiedostoPolku);
    public List<String> lueOtsikonArvot(String tiedostoPolku);
}
