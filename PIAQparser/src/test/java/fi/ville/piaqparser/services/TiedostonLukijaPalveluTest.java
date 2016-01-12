/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.services;

import fi.ville.piaqparser.domain.Mittaus;
import fi.ville.piaqparser.util.AikaKaantaja;
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

//    @Test
//    public void testHeaderCSV() {
//        tiedostoPolku = "src/main/resources/CSVtesti.csv";
//        List<String> otsikot = tiedostonLukija.lueOtsikonArvot(tiedostoPolku);
//        assertEquals(true, otsikot.contains("co2"));
//        assertEquals(true, otsikot.contains("no2"));
//        assertEquals(true, otsikot.contains("sootA"));
//        assertEquals(true, otsikot.contains("sootM"));
//    }

    @Test
    public void testHeaderXML() {
        tiedostoPolku = "src/main/resources/PIAQ3.xml";
        List<String> otsikot = tiedostonLukija.lueOtsikonArvot(tiedostoPolku);
        assertEquals(true, otsikot.contains("Co2"));
        assertEquals(true, otsikot.contains("SootA"));
        assertEquals(true, otsikot.contains("SootM"));
        assertEquals(true, otsikot.contains("RH%"));
        assertEquals(true, otsikot.contains("Temperature"));
        assertEquals(false, otsikot.contains("Skrikidii"));
    }

    @Test
    public void testPariMittaustaXML() {
        tiedostoPolku = "src/main/resources/PIAQ3.xml";
        AikaKaantaja aikaKaantaja = new AikaKaantaja();
        ArrayList<Mittaus> mittaukset = tiedostonLukija.lueMittauksetListaksi(tiedostoPolku);

        assertEquals(999, mittaukset.size());

        Mittaus mittaus1 = mittaukset.get(0);
        assertEquals(27.745892, mittaus1.getMittauksenArvo("SootA"), 0.0000001);
        assertEquals(10.968647, mittaus1.getMittauksenArvo("SootM"), 0.0000001);
        assertEquals(0.40212, mittaus1.getMittauksenArvo("SootN"), 0.0000001);
        assertEquals(25.469999, mittaus1.getMittauksenArvo("Temperature"), 0.0000001);
        assertEquals(52.369999, mittaus1.getMittauksenArvo("RH%"), 0.0000001);
        assertEquals(1425.660034, mittaus1.getMittauksenArvo("Co2"), 0.0000001);
        assertEquals(aikaKaantaja.kaannaPegasorinAjastaJavaan(494158779), mittaus1.getAikaleima().getTime());

        Mittaus mittaus14 = mittaukset.get(12);
        assertEquals(27.253036, mittaus14.getMittauksenArvo("SootA"), 0.0000001);
        assertEquals(10.505719, mittaus14.getMittauksenArvo("SootM"), 0.0000001);
        assertEquals(0.399813, mittaus14.getMittauksenArvo("SootN"), 0.0000001);
        assertEquals(25.469999, mittaus14.getMittauksenArvo("Temperature"), 0.0000001);
        assertEquals(52.360001, mittaus14.getMittauksenArvo("RH%"), 0.0000001);
        assertEquals(1417.030029, mittaus14.getMittauksenArvo("Co2"), 0.0000001);
        assertEquals(494158791, aikaKaantaja.kaannaJavanAjastaPegasorin(mittaus14.getAikaleima().getTime()));

        Mittaus vikaMittaus = mittaukset.get(mittaukset.size() - 1);
        assertEquals(27.983816, vikaMittaus.getMittauksenArvo("SootA"), 0.0000001);
        assertEquals(9.548785, vikaMittaus.getMittauksenArvo("SootM"), 0.0000001);
        assertEquals(0.438192, vikaMittaus.getMittauksenArvo("SootN"), 0.0000001);
        assertEquals(25.469999, vikaMittaus.getMittauksenArvo("Temperature"), 0.0000001);
        assertEquals(52.240002, vikaMittaus.getMittauksenArvo("RH%"), 0.0000001);
        assertEquals(1388.589966, vikaMittaus.getMittauksenArvo("Co2"), 0.0000001);
        assertEquals(494159846, aikaKaantaja.kaannaJavanAjastaPegasorin(vikaMittaus.getAikaleima().getTime()));

    }

//    @Test
//    public void testPariMittaustaCSV() {
//        tiedostoPolku = "src/main/resources/CSVtesti.csv";
//        ArrayList<Mittaus> mittaukset = tiedostonLukija.lueMittauksetListaksi(tiedostoPolku);
//
//        assertEquals(15053, mittaukset.size());
//        Mittaus mittaus1=mittaukset.get(0);
//        assertEquals(310, mittaus1.getMittauksenArvo("co2"),0.0000001);
//        assertEquals(41, mittaus1.getMittauksenArvo("no2"),0.0000001);
//        assertEquals(13, mittaus1.getMittauksenArvo("sootA"),0.0000001);
//        assertEquals(205, mittaus1.getMittauksenArvo("sootM"),0.0000001);
//        
//        Mittaus mittaus20 = mittaukset.get(18);
//        assertEquals(24, mittaus20.getMittauksenArvo("co2"),0.0000001);
//        assertEquals(31, mittaus20.getMittauksenArvo("no2"),0.0000001);
//        assertEquals(17, mittaus20.getMittauksenArvo("sootA"),0.0000001);
//        assertEquals(225, mittaus20.getMittauksenArvo("sootM"),0.0000001);
//        
//        Mittaus vikaMittaus = mittaukset.get(mittaukset.size()-1);
//        assertEquals(248, vikaMittaus.getMittauksenArvo("co2"),0.0000001);
//        assertEquals(30, vikaMittaus.getMittauksenArvo("no2"),0.0000001);
//        assertEquals(13, vikaMittaus.getMittauksenArvo("sootA"),0.0000001);
//        assertEquals(183, vikaMittaus.getMittauksenArvo("sootM"),0.0000001);
//
//    }
}
