/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.services;

import fi.ville.piaqparser.domain.Mittaus;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ville
 */
public class TiedostonLukijaPalveluTest {

    private String tiedostoPolku;
    private TiedostonLukijaPalvelu tiedostonLukija;

    public TiedostonLukijaPalveluTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tiedostonLukija = new TiedostonLukijaPalvelu();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testHeaderCSV() {
        tiedostoPolku = "src/main/resources/CSVtesti.csv";
        List<String> otsikot = tiedostonLukija.lueOtsikonArvot(tiedostoPolku);
        assertEquals(true, otsikot.contains("co2"));
        assertEquals(true, otsikot.contains("no2"));
        assertEquals(true, otsikot.contains("sootA"));
        assertEquals(true, otsikot.contains("sootM"));
    }

    @Test
    public void testHeaderXML() {
        tiedostoPolku="src/main/resources/PIAQ3.xml";
        List<String> otsikot = tiedostonLukija.lueOtsikonArvot(tiedostoPolku);
        assertEquals(true, otsikot.contains("Co2"));
        assertEquals(true, otsikot.contains("SootA"));
        assertEquals(true, otsikot.contains("SootM"));
        assertEquals(true, otsikot.contains("RH%"));
        assertEquals(true, otsikot.contains("Temperature"));
        assertEquals(false, otsikot.contains("Skrikidii"));
    }
    
    //testaa vielä tiedostonlukijaPalvelun Mittauksetlistaksi metodit

}
