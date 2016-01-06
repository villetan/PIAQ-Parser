/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.util;

import fi.ville.piaqparser.domain.Hyppy;
import fi.ville.piaqparser.domain.Mittaus;
import java.util.ArrayList;
import java.util.Date;
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
public class HypynTayttajaTest {

    HypynTayttaja hypynTayttaja;
    Hyppy hyppy;

    public HypynTayttajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        hypynTayttaja = new HypynTayttaja();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testHypynTayttajaAika() {
        Mittaus mittaus1 = new Mittaus();
        mittaus1.setAikaleima(new Date(111111111000l));
        Mittaus mittaus2 = new Mittaus();
        mittaus2.setAikaleima(new Date(111111118000l));
        hyppy = new Hyppy(mittaus1, mittaus2);
        ArrayList<Mittaus> arvioidut = hypynTayttaja.taytaHyppy(hyppy, 1000l);
        assertEquals(6, arvioidut.size());
        assertEquals(111111112000l, arvioidut.get(0).getAikaleima().getTime());
        assertEquals(111111113000l, arvioidut.get(1).getAikaleima().getTime());
        assertEquals(111111114000l, arvioidut.get(2).getAikaleima().getTime());
        assertEquals(111111115000l, arvioidut.get(3).getAikaleima().getTime());
        assertEquals(111111116000l, arvioidut.get(4).getAikaleima().getTime());
        assertEquals(111111117000l, arvioidut.get(5).getAikaleima().getTime());
    }

    @Test
    public void testHypynTayttajaAika2() {
        Mittaus mittaus1 = new Mittaus();
        mittaus1.setAikaleima(new Date(111111111000l));
        Mittaus mittaus2 = new Mittaus();
        mittaus2.setAikaleima(new Date(111111113000l));
        hyppy = new Hyppy(mittaus1, mittaus2);
        ArrayList<Mittaus> arvioidut = hypynTayttaja.taytaHyppy(hyppy, 1000l);
        assertEquals(1, arvioidut.size());
        assertEquals(111111112000l, arvioidut.get(0).getAikaleima().getTime());

    }

    @Test
    public void testMittauksetOikein() {

        Mittaus mittaus1 = new Mittaus();
        mittaus1.setAikaleima(new Date(111111111000l));
        mittaus1.lisaaMittaus("suora", 0);
        Mittaus mittaus2 = new Mittaus();
        mittaus2.setAikaleima(new Date(111111113000l));
        mittaus2.lisaaMittaus("suora", 2);
        hyppy = new Hyppy(mittaus1, mittaus2);
        ArrayList<Mittaus> arvioidut = hypynTayttaja.taytaHyppy(hyppy, 1000l);
        assertEquals(1, arvioidut.get(0).getMittauksenArvo("suora"), 0.0000000001);

    }

    @Test
    public void testMittauksetOikein2() {

        Mittaus mittaus1 = new Mittaus();
        mittaus1.setAikaleima(new Date(111111111000l));
        mittaus1.lisaaMittaus("suora", 1);
        Mittaus mittaus2 = new Mittaus();
        mittaus2.setAikaleima(new Date(111111119000l));
        mittaus2.lisaaMittaus("suora", 2);
        hyppy = new Hyppy(mittaus1, mittaus2);
        ArrayList<Mittaus> arvioidut = hypynTayttaja.taytaHyppy(hyppy, 1000l);
        assertEquals(1.125, arvioidut.get(0).getMittauksenArvo("suora"), 0.0000000001);
        assertEquals(1.25, arvioidut.get(1).getMittauksenArvo("suora"), 0.0000000001);
        assertEquals(1.375, arvioidut.get(2).getMittauksenArvo("suora"), 0.0000000001);
        assertEquals(1.5, arvioidut.get(3).getMittauksenArvo("suora"), 0.0000000001);
        assertEquals(1.625, arvioidut.get(4).getMittauksenArvo("suora"), 0.0000000001);
        assertEquals(1.75, arvioidut.get(5).getMittauksenArvo("suora"), 0.0000000001);
        assertEquals(1.875, arvioidut.get(6).getMittauksenArvo("suora"), 0.0000000001);
    }

    @Test
    public void testMittauksetOikeinPariSuoraa() {
        Mittaus mittaus1 = new Mittaus();
        mittaus1.setAikaleima(new Date(111111119000l));
        mittaus1.lisaaMittaus("suora", 0);
        mittaus1.lisaaMittaus("suora1", 1);
        mittaus1.lisaaMittaus("suora2", 38);
        Mittaus mittaus2 = new Mittaus();
        mittaus2.setAikaleima(new Date(111111121000l));
        mittaus2.lisaaMittaus("suora", 0);
        mittaus2.lisaaMittaus("suora1", 2);
        mittaus2.lisaaMittaus("suora2", 40);

        hyppy = new Hyppy(mittaus1, mittaus2);
        ArrayList<Mittaus> arvioidut = hypynTayttaja.taytaHyppy(hyppy, 1000l);
        assertEquals(0, arvioidut.get(0).getMittauksenArvo("suora"), 0.0000000001);
        assertEquals(1.5, arvioidut.get(0).getMittauksenArvo("suora1"),0.0000000001);
        assertEquals(39, arvioidut.get(0).getMittauksenArvo("suora2"),0.0000000001);
    }
    
    @Test
    public void testMittauksetOikeinjokaToinenSekunti(){
        Mittaus mittaus1 = new Mittaus();
        mittaus1.lisaaMittaus("testi", 350);
        mittaus1.setAikaleima(new Date(111111111000l));
        Mittaus mittaus2 = new Mittaus();
        mittaus2.setAikaleima(new Date(111111115000l));
        mittaus2.lisaaMittaus("testi", 400);
        hyppy = new Hyppy(mittaus1, mittaus2);
        ArrayList<Mittaus> arvioidut = hypynTayttaja.taytaHyppy(hyppy, 2000l);
        assertEquals(111111113000l, arvioidut.get(0).getAikaleima().getTime());
        assertEquals(375, arvioidut.get(0).getMittauksenArvo("testi"),0.0000000001);
    }

}
