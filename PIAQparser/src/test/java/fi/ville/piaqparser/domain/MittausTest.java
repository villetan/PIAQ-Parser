/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.domain;

import java.util.Date;
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
public class MittausTest {

    private Mittaus mittaus;

    public MittausTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mittaus = new Mittaus();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getMittaukset method, of class Mittaus.
     */
    @Test
    public void testTyhjaKonstruktori() {
        assertEquals(null, mittaus.getAikaleima());
    }

    @Test
    public void testLisaaMittaus() {
        mittaus.lisaaMittaus("co2", 50.43);
        assertEquals(1, mittaus.getMittaukset().size());
        assertEquals(50.43, mittaus.getMittauksenArvo("co2"), 0.0000000000001);
    }

    @Test
    public void testGetMittaus() {
        mittaus.lisaaMittaus("co2", 50.01);
        mittaus.lisaaMittaus("asd", 32.012345);
        mittaus.lisaaMittaus("Temperature", -20.0001);
        assertEquals(-20.0001, mittaus.getMittauksenArvo("Temperature"), 0.0000000000001);
        assertEquals(32.012345, mittaus.getMittauksenArvo("asd"), 0.0000000000001);
        assertEquals(50.01, mittaus.getMittauksenArvo("co2"), 0.0000000000001);
    }

    @Test
    public void testEquals() {
        mittaus.setAikaleima(new Date(10000000000l));
        mittaus.lisaaMittaus("co2", 50.01);
        mittaus.lisaaMittaus("asd", 32.012345);
        mittaus.lisaaMittaus("Temperature", -20.0001);
        Mittaus mittaus2 = new Mittaus();
        mittaus2.setAikaleima(new Date(10000000000l));
        mittaus2.lisaaMittaus("co2", 50.01);
        mittaus2.lisaaMittaus("asd", 32.012345);
        mittaus2.lisaaMittaus("Temperature", -20.0001);
        assertEquals(true, mittaus.equals(mittaus2));
        assertEquals(false, mittaus.equals(new Mittaus(new Date(10000000000l))));
    }

    @Test
    public void testPalautaAikaLeima() {
        mittaus.setAikaleima(new Date(2016 - 1900, 0, 1, 00, 0, 0));
        assertEquals("1/1/2016", mittaus.palautaAikaleimaPVM());
        assertEquals("00:00:00", mittaus.palautaAikaleimaKellonaika());
    }

    @Test
    public void testPalautaAikaLeima2() {
        mittaus.setAikaleima(new Date(2016 - 1900, 3, 23, 05, 00, 02));
        assertEquals("23/4/2016", mittaus.palautaAikaleimaPVM());
        assertEquals("05:00:02", mittaus.palautaAikaleimaKellonaika());
    }

    @Test
    public void testPalautaAikaLeima3() {
        mittaus.setAikaleima(new Date(2016 - 1900, 3, 23, 23, 23, 00));
        assertEquals("23:23:00", mittaus.palautaAikaleimaKellonaika());
    }

    @Test
    public void testPalautaAikaLeima4() {
        mittaus.setAikaleima(new Date(2016 - 1900, 3, 23, 23, 05, 02));
        assertEquals("23:05:02", mittaus.palautaAikaleimaKellonaika());
    }

    @Test
    public void testPalautaAikaLeima5() {
        mittaus.setAikaleima(new Date(2016 - 1900, 3, 23, 07, 00, 9));
        assertEquals("07:00:09", mittaus.palautaAikaleimaKellonaika());
    }
    
    @Test
    public void testPalautaAikaLeima6() {
        mittaus.setAikaleima(new Date(2016 - 1900, 3, 23, 07, 00, 22));
        assertEquals("07:00:22", mittaus.palautaAikaleimaKellonaika());
    }
    
    @Test
    public void testPalautaAikaLeima7() {
        mittaus.setAikaleima(new Date(2016 - 1900, 3, 23, 9, 22, 22));
        assertEquals("09:22:22", mittaus.palautaAikaleimaKellonaika());
    }
    
    @Test
    public void testPalautaAikaLeima8() {
        mittaus.setAikaleima(new Date(2016 - 1900, 3, 23, 17, 22, 22));
        assertEquals("17:22:22", mittaus.palautaAikaleimaKellonaika());
    }
}
