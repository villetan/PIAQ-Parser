/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.util;

import fi.ville.piaqparser.domain.Mittaus;
import java.io.File;
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
public class TiedostonLukijaXMLTest {
    
    private File file;
    private TiedostonLukijaXML tiedostonLukija;
    String nodeName;
    
    public TiedostonLukijaXMLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        file = new File("src/main/resources/PIAQ3.xml");
        nodeName = "Row";
        tiedostonLukija = new TiedostonLukijaXML();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of lueXMLtiedosto method, of class TiedostonLukijaXML.
     */
     //ei ota huomioon date mittausta.
    @Test
    public void testOtsikonSize(){
                HashMap<String, Integer> xmlOtsikkoIndeksit = tiedostonLukija.lueaHeaderMapiksi(file, nodeName);
        assertEquals(6, xmlOtsikkoIndeksit.size());
    }

    @Test
    public void testOtsikonIndeksit() {
        HashMap<String, Integer> xmlOtsikkoIndeksit = tiedostonLukija.lueaHeaderMapiksi(file, nodeName);
        assertEquals(1, xmlOtsikkoIndeksit.get("SootA"), 0.00001);
        assertEquals(3, xmlOtsikkoIndeksit.get("SootN"), 0.00001);
        assertEquals(6, xmlOtsikkoIndeksit.get("Co2"), 0.00001);
    }

    @Test
    public void testMittauksetSize() {
        ArrayList<Mittaus> mittaukset = tiedostonLukija.lueXMLtiedosto(file, nodeName);
        assertEquals(999, mittaukset.size());
    }

    @Test
    public void testJoitakinMittauksia() {
        ArrayList<Mittaus> mittaukset = tiedostonLukija.lueXMLtiedosto(file, nodeName);
        //Expected is the actual value
        assertEquals(494158779, (mittaukset.get(0).getAikaleima().getTime() - 946684800000l) / 1000);
        assertEquals(27.745892000000001, mittaukset.get(0).getMittauksenArvo("SootA"), 0.0000000000000001);
        assertEquals(52.369999, mittaukset.get(5).getMittauksenArvo("RH%"), 0.0000000000000000001);
        assertEquals(1388.589966, mittaukset.get(mittaukset.size()-1).getMittauksenArvo("Co2"),0.0000000000000000001);
    }
    
}
