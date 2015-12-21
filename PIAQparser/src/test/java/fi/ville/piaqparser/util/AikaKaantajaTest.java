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

}
