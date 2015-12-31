/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.services;

import fi.ville.piaqparser.domain.Mittaus;
import fi.ville.piaqparser.util.AikaKaantaja;
import java.util.ArrayList;
import java.util.Date;
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
public class MittausAnalysoijaPalveluTest {
    public MittausAnalysoijaPalvelu mittausAnalysoijaPalvelu;
    
    public MittausAnalysoijaPalveluTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mittausAnalysoijaPalvelu=new MittausAnalysoijaPalvelu("src/main/resources/PIAQ3.xml");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testMittauksetAikavaliltaKokoXML(){
        AikaKaantaja aikaKaantaja=new AikaKaantaja();
        Date date1 = new Date(aikaKaantaja.kaannaPegasorinAjastaJavaan(494158779));
        Date date2 = new Date(aikaKaantaja.kaannaPegasorinAjastaJavaan(494159846));
        ArrayList<Mittaus> mittaukset = mittausAnalysoijaPalvelu.valitseMittauksetAikavalilta(date1, date2);
        assertEquals(999, mittaukset.size());
        assertEquals(10.968647, mittaukset.get(0).getMittauksenArvo("SootM"),0.0000000001);
        assertEquals(25.469999, mittaukset.get(1).getMittauksenArvo("Temperature"),0.0000000001);
        assertEquals(494158781, aikaKaantaja.kaannaJavanAjastaPegasorin(mittaukset.get(2).getAikaleima().getTime()));
    }
    
    @Test 
    public void testMittauksetAikaValiltaPt1(){
        AikaKaantaja aikaKaantaja=new AikaKaantaja();
        Date date1 = new Date(aikaKaantaja.kaannaPegasorinAjastaJavaan(494158779));
        Date date2 = new Date(aikaKaantaja.kaannaPegasorinAjastaJavaan(494158788));
        ArrayList<Mittaus> mittaukset = mittausAnalysoijaPalvelu.valitseMittauksetAikavalilta(date1, date2);
        assertEquals(10, mittaukset.size());
        Mittaus mittaus1 = mittaukset.get(0);
        assertEquals(aikaKaantaja.kaannaPegasorinAjastaJavaan(494158779), mittaus1.getAikaleima().getTime());
        assertEquals(27.745892, mittaus1.getMittauksenArvo("SootA"),0.0000000001);
        assertEquals(10.968647, mittaus1.getMittauksenArvo("SootM"),0.0000000001);
        assertEquals(0.40212, mittaus1.getMittauksenArvo("SootN"),0.0000000001);
        assertEquals(25.469999, mittaus1.getMittauksenArvo("Temperature"),0.0000000001);
        assertEquals(52.369999, mittaus1.getMittauksenArvo("RH%"),0.0000000001);
        assertEquals(1425.660034, mittaus1.getMittauksenArvo("Co2"),0.0000000001);
        
        Mittaus mittausKeskelta = mittaukset.get(5);
        assertEquals(494158784, aikaKaantaja.kaannaJavanAjastaPegasorin(mittausKeskelta.getAikaleima().getTime()));
        assertEquals(27.623056, mittausKeskelta.getMittauksenArvo("SootA"),0.0000000001);
        assertEquals(10.607784, mittausKeskelta.getMittauksenArvo("SootM"),0.0000000001);
        assertEquals(0.406469, mittausKeskelta.getMittauksenArvo("SootN"),0.0000000001);
        assertEquals(25.469999, mittausKeskelta.getMittauksenArvo("Temperature"),0.0000000001);
        assertEquals(52.369999, mittausKeskelta.getMittauksenArvo("RH%"),0.0000000001);
        assertEquals(1421.939941, mittausKeskelta.getMittauksenArvo("Co2"),0.0000000001);
        
        Mittaus vikaMittaus=mittaukset.get(mittaukset.size()-1);
        assertEquals(494158788, aikaKaantaja.kaannaJavanAjastaPegasorin(vikaMittaus.getAikaleima().getTime()));
        assertEquals(27.396786, vikaMittaus.getMittauksenArvo("SootA"),0.0000000001);
        assertEquals(10.507862, vikaMittaus.getMittauksenArvo("SootM"),0.0000000001);
        assertEquals(0.403143, vikaMittaus.getMittauksenArvo("SootN"),0.0000000001);
        assertEquals(25.469999, vikaMittaus.getMittauksenArvo("Temperature"),0.0000000001);
        assertEquals(52.369999, vikaMittaus.getMittauksenArvo("RH%"),0.0000000001);
        assertEquals(1420.810059, vikaMittaus.getMittauksenArvo("Co2"),0.0000000001);
        
    }
    
    @Test 
    public void testMittauksetValilta1mittaus(){
        AikaKaantaja aikaKaantaja=new AikaKaantaja();
        Date date1 = new Date(aikaKaantaja.kaannaPegasorinAjastaJavaan(494158779));
        Date date2 = new Date(aikaKaantaja.kaannaPegasorinAjastaJavaan(494158779));
        ArrayList<Mittaus> mittaukset = mittausAnalysoijaPalvelu.valitseMittauksetAikavalilta(date1, date2);
        assertEquals(1, mittaukset.size());
        Mittaus mittaus1 = mittaukset.get(0);
        assertEquals(aikaKaantaja.kaannaPegasorinAjastaJavaan(494158779), mittaus1.getAikaleima().getTime());
        assertEquals(27.745892, mittaus1.getMittauksenArvo("SootA"),0.0000000001);
        assertEquals(10.968647, mittaus1.getMittauksenArvo("SootM"),0.0000000001);
        assertEquals(0.40212, mittaus1.getMittauksenArvo("SootN"),0.0000000001);
        assertEquals(25.469999, mittaus1.getMittauksenArvo("Temperature"),0.0000000001);
        assertEquals(52.369999, mittaus1.getMittauksenArvo("RH%"),0.0000000001);
        assertEquals(1425.660034, mittaus1.getMittauksenArvo("Co2"),0.0000000001);
    }
    
    @Test
    public void testMittauksetAikaValiltaKeskelta(){
        AikaKaantaja aikaKaantaja=new AikaKaantaja();
        Date date1 = new Date(aikaKaantaja.kaannaPegasorinAjastaJavaan(494158782));
        Date date2 = new Date(aikaKaantaja.kaannaPegasorinAjastaJavaan(494158783));
        ArrayList<Mittaus> mittaukset = mittausAnalysoijaPalvelu.valitseMittauksetAikavalilta(date1, date2);
        assertEquals(2, mittaukset.size());
        Mittaus mittaus1 = mittaukset.get(0);
        assertEquals(1424.959961, mittaus1.getMittauksenArvo("Co2"),0.0000000001);
        Mittaus mittaus2 = mittaukset.get(1);
        assertEquals(27.730257, mittaus2.getMittauksenArvo("SootA"),0.0000000001);

    }
    
    @Test
    public void testMittauksetHuonoillaDate(){
        AikaKaantaja aikaKaantaja=new AikaKaantaja();
        Date date1 = new Date(aikaKaantaja.kaannaPegasorinAjastaJavaan(494158782));
        Date date2 = new Date(aikaKaantaja.kaannaPegasorinAjastaJavaan(23));
        ArrayList<Mittaus> mittaukset = mittausAnalysoijaPalvelu.valitseMittauksetAikavalilta(date1, date2);
        assertEquals(null, mittaukset);
    }

    
    
}
