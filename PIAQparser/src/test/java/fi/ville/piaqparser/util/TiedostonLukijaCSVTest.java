/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.util;

import fi.ville.piaqparser.domain.Mittaus;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
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
public class TiedostonLukijaCSVTest {
    
    private String tiedostopolku;
    private TiedostonLukijaCSV tiedostonLukija;
    private BufferedReader br;
    
    public TiedostonLukijaCSVTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException {
        tiedostopolku = "src/main/resources/CSVtesti.csv";
        br = new BufferedReader(new FileReader(tiedostopolku));
        tiedostonLukija = new TiedostonLukijaCSV();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of lueMittausListaksi method, of class TiedostonLukijaCSV.
     */
    //Ei lue ensimmäistä saraketta!
    @Test
    public void testOtsikkoSizeOikein() {
        HashMap<String, Integer> map = tiedostonLukija.selvitaSarakkeidenArvotJaIndeksit(tiedostopolku, br);
        assertEquals(4, map.size());
    }

    //indeksöinti 1 eteenpäin:
    @Test
    public void testOtsikonIndeksitOikein() {
        HashMap<String, Integer> map = tiedostonLukija.selvitaSarakkeidenArvotJaIndeksit(tiedostopolku, br);
        assertEquals(1, map.get("co2"), 0.00001);
        assertEquals(2, map.get("no2"), 0.00001);
        assertEquals(4, map.get("sootM"), 0.00001);
    }

    @Test
    public void testMittauksetSizeOikein() {
        ArrayList<Mittaus> mittaukset = tiedostonLukija.lueMittausListaksi(tiedostopolku, br);
        assertEquals(15053, mittaukset.size());
    }

    @Test
    public void testVikanCo2() {
        ArrayList<Mittaus> mittaukset = tiedostonLukija.lueMittausListaksi(tiedostopolku, br);
        assertEquals(248, mittaukset.get(mittaukset.size() - 1).getMittauksenArvo("co2"), 0.00000000001);
    }

    @Test
    public void testEkanSootA() {
        ArrayList<Mittaus> mittaukset = tiedostonLukija.lueMittausListaksi(tiedostopolku, br);
        assertEquals(13, mittaukset.get(0).getMittauksenArvo("sootA"), 0.000000000001);
    }

    @Test
    public void test10sSootM() {
        ArrayList<Mittaus> mittaukset = tiedostonLukija.lueMittausListaksi(tiedostopolku, br);
        assertEquals(288, mittaukset.get(9).getMittauksenArvo("sootM"),0.00000000000001);  
    }
    
}
