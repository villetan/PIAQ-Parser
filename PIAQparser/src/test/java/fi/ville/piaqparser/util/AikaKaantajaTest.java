/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.util;

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
public class AikaKaantajaTest {

    private AikaKaantaja kaantaja;

    public AikaKaantajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kaantaja = new AikaKaantaja();

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of kaannaPegasorinAjastaJavaan method, of class AikaKaantaja.
     */
    //"käänteisoperaatiot"
    @Test
    public void testAjankaantaminen() {
        long mittaus = 494158779;
        long kaannettyJavaan = kaantaja.kaannaPegasorinAjastaJavaan(mittaus);
        assertEquals(mittaus, kaantaja.kaannaJavanAjastaPegasorin(kaannettyJavaan));
    }
    
    @Test
    public void testLueStringAjaksiMS(){
        String aika = "1 second";
        assertEquals(1000, kaantaja.kaannaStringAjaksiMS(aika),0.0000000001);
    }
    @Test
    public void testLueStringAjaksiMS2(){
        String aika = "35 seconds";
        assertEquals(35000, kaantaja.kaannaStringAjaksiMS(aika),0.0000000001);
    }
    
    @Test
    public void testLueStringAjaksiMS3(){
        String aika = "1 minute";
        assertEquals(60000, kaantaja.kaannaStringAjaksiMS(aika),0.0000000001);
    }
    @Test
    public void testLueStringAjaksiMS4(){
        String aika = "59 minutes";
        assertEquals(3540000, kaantaja.kaannaStringAjaksiMS(aika),0.0000000001);
    }
    
    @Test
    public void testLueStringAjaksiMS5(){
        String aika = "1 hour";
        assertEquals(3600000, kaantaja.kaannaStringAjaksiMS(aika),0.0000000001);
    }
    
    @Test
    public void testLueStringAjaksiMS6(){
        String aika = "24 hours";
        assertEquals(86400000, kaantaja.kaannaStringAjaksiMS(aika),0.0000000001);
    }
    
    @Test
    public void testLueStringAjaksiMSnull(){
        String aika = "11124 asdffwa";
        assertEquals(null, kaantaja.kaannaStringAjaksiMS(aika));
    }
    
    
}
