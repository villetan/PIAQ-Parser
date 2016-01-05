/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.util;

import fi.ville.piaqparser.domain.Mittaus;
import fi.ville.piaqparser.services.TiedostonLukijaPalvelu;
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
public class MittaustenAnalysoijaTest {

    public MittaustenAnalysoija analysoija;
    ArrayList<Mittaus> mittaukset;

    public MittaustenAnalysoijaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        mittaukset = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Mittaus mittaus = new Mittaus();
            mittaus.setAikaleima(new Date(2015, 12, 12, 20, 20, i));
            mittaus.lisaaMittaus("co2", i);
            mittaus.lisaaMittaus("no2", 10 * i);
            mittaus.lisaaMittaus("temperature", 100 * i);
            mittaukset.add(mittaus);
        }
        analysoija = new MittaustenAnalysoija(mittaukset);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testEkaJaVikaMittausOikein() {
        ArrayList<Mittaus> ekaJaVika = analysoija.ensimmainenJaViimeinenMittausListana();
        assertEquals(2, ekaJaVika.size());
        assertEquals(mittaukset.get(0), ekaJaVika.get(0));
        assertEquals(mittaukset.get(mittaukset.size() - 1), ekaJaVika.get(1));
    }

    @Test
    public void testEkaJaVikaMittausSamat() {
        for (int i = 0; i < 9; i++) {
            mittaukset.remove(0);
        }
        assertEquals(1, analysoija.ensimmainenJaViimeinenMittausListana().size());
        assertEquals(mittaukset.get(mittaukset.size() - 1), analysoija.ensimmainenJaViimeinenMittausListana().get(0));
    }

    @Test
    public void testMittauksissaHyppyjaTrue() {
        mittaukset.remove(5);
        assertEquals(true, analysoija.mittauksissaHyppyja());
    }

    @Test
    public void testMittauksissaHyppyjaFalse() {
        assertEquals(false, analysoija.mittauksissaHyppyja());
    }

    @Test
    public void testMittausValiOikein() {
        assertEquals(true, analysoija.mittaustenMittausvaliMS(mittaukset) >= 0);
        assertEquals(1000, analysoija.mittaustenMittausvaliMS(mittaukset));
    }

    @Test
    public void testMittausvaliOikeinYhdellaMittauksella() {
        for (int i = 0; i < 9; i++) {
            mittaukset.remove(0);
        }
        assertEquals(0, analysoija.mittaustenMittausvaliMS(mittaukset));
    }

    @Test
    public void testMittausvaliOikeinJokaToinenSekunti() {
        ArrayList mittauksetSpecial = new ArrayList<Mittaus>();
        for (int i = 0; i < 10; i = i + 2) {
            Mittaus mittaus = new Mittaus();
            mittaus.setAikaleima(new Date(2015, 12, 12, 20, 20, i));
            mittaus.lisaaMittaus("co2", i);
            mittaus.lisaaMittaus("no2", 10 * i);
            mittaus.lisaaMittaus("temperature", 100 * i);
            mittauksetSpecial.add(mittaus);
        }
        MittaustenAnalysoija analysoija2 = new MittaustenAnalysoija(mittauksetSpecial);
        assertEquals(true, analysoija2.mittaustenMittausvaliMS(mittauksetSpecial) >= 0);
        assertEquals(2000, analysoija2.mittaustenMittausvaliMS(mittauksetSpecial));
    }

    @Test
    public void testKeskiArvonLaskuriSizeOikein() {
        ArrayList<Mittaus> keskiarvoLista = analysoija.laskeMittaustenKeskiarvo(5000l, mittaukset);

        assertEquals(2, keskiarvoLista.size());
    }

    @Test
    public void testKeskiArvoLaskuriMittauksetOikein() {
        ArrayList<Mittaus> keskiarvoLista = analysoija.laskeMittaustenKeskiarvo(5000l,mittaukset);
        Mittaus mittaus1 = keskiarvoLista.get(0);
        assertEquals(2.0, mittaus1.getMittauksenArvo("co2"), 0.00000000001);
        assertEquals(20.0, mittaus1.getMittauksenArvo("no2"), 0.00000000001);
        assertEquals(200.0, mittaus1.getMittauksenArvo("temperature"), 0.00000000001);
        Mittaus mittaus2 = keskiarvoLista.get(1);
        assertEquals(7.0, mittaus2.getMittauksenArvo("co2"), 0.00000000001);
        assertEquals(70.0, mittaus2.getMittauksenArvo("no2"), 0.00000000001);
        assertEquals(700.0, mittaus2.getMittauksenArvo("temperature"), 0.00000000001);
    }

    @Test
    public void testKeskiArvoLaskuriSizeOikeinPart2() {
        ArrayList<Mittaus> keskiarvoLista = analysoija.laskeMittaustenKeskiarvo(2000l,mittaukset);
        assertEquals(5, keskiarvoLista.size());
    }

    @Test
    public void testKeskiArvoLaskuriMittauksetOikeinPart2() {
        ArrayList<Mittaus> keskiarvoLista = analysoija.laskeMittaustenKeskiarvo(2000l,mittaukset);
        Mittaus mittaus1 = keskiarvoLista.get(0);
        assertEquals(0.5, mittaus1.getMittauksenArvo("co2"), 0.00000000001);
        assertEquals(5.0, mittaus1.getMittauksenArvo("no2"), 0.00000000001);
        assertEquals(50.0, mittaus1.getMittauksenArvo("temperature"), 0.00000000001);
        Mittaus mittaus2 = keskiarvoLista.get(1);
        assertEquals(2.5, mittaus2.getMittauksenArvo("co2"), 0.00000000001);
        assertEquals(25.0, mittaus2.getMittauksenArvo("no2"), 0.00000000001);
        assertEquals(250.0, mittaus2.getMittauksenArvo("temperature"), 0.00000000001);
        Mittaus mittaus3 = keskiarvoLista.get(2);
        assertEquals(4.5, mittaus3.getMittauksenArvo("co2"), 0.00000000001);
        assertEquals(45.0, mittaus3.getMittauksenArvo("no2"), 0.00000000001);
        assertEquals(450.0, mittaus3.getMittauksenArvo("temperature"), 0.00000000001);
        Mittaus mittaus4 = keskiarvoLista.get(3);
        assertEquals(6.5, mittaus4.getMittauksenArvo("co2"), 0.00000000001);
        assertEquals(65.0, mittaus4.getMittauksenArvo("no2"), 0.00000000001);
        assertEquals(650.0, mittaus4.getMittauksenArvo("temperature"), 0.00000000001);
        Mittaus mittaus5 = keskiarvoLista.get(4);
        assertEquals(8.5, mittaus5.getMittauksenArvo("co2"), 0.00000000001);
        assertEquals(85.0, mittaus5.getMittauksenArvo("no2"), 0.00000000001);
        assertEquals(850.0, mittaus5.getMittauksenArvo("temperature"), 0.00000000001);
    }

    @Test
    public void testMittauksissaHyppyja() {
        ArrayList<Mittaus> mittauksia = new ArrayList<>();
        mittauksia.add(new Mittaus(new Date(1)));
        mittauksia.add(new Mittaus(new Date(2)));
        mittauksia.add(new Mittaus(new Date(9)));
        mittauksia.add(new Mittaus(new Date(10)));
        MittaustenAnalysoija ma = new MittaustenAnalysoija(mittauksia);

        assertEquals(true, ma.mittauksissaHyppyja());
    }

    @Test
    public void testLaskeKeskiarvoKunMittaustenMaaraHuono() {
        for (int i = 10; i < 13; i++) {
            Mittaus mittaus = new Mittaus();
            mittaus.setAikaleima(new Date(2015, 12, 12, 20, 20, i));
            mittaus.lisaaMittaus("co2", i);
            mittaus.lisaaMittaus("no2", 10 * i);
            mittaus.lisaaMittaus("temperature", 100 * i);
            mittaukset.add(mittaus);
        }
        ArrayList<Mittaus> keskiarvotLaskettu = analysoija.laskeMittaustenKeskiarvo(2000l,mittaukset);
        assertEquals(13, mittaukset.size());
        assertEquals(7, keskiarvotLaskettu.size());

        assertEquals(12, keskiarvotLaskettu.get(6).getMittauksenArvo("co2"), 0.0000000001);
        assertEquals(120, keskiarvotLaskettu.get(6).getMittauksenArvo("no2"), 0.0000000001);
        assertEquals(1200, keskiarvotLaskettu.get(6).getMittauksenArvo("temperature"), 0.0000000001);
        assertEquals(mittaukset.get(12).getAikaleima(), keskiarvotLaskettu.get(6).getAikaleima());

    }

    @Test
    public void testLaskeKeskiarvoKunMittaustenMaaraHuono2() {
        for (int i = 10; i < 15; i++) {
            Mittaus mittaus = new Mittaus();
            mittaus.setAikaleima(new Date(2015, 12, 12, 20, 20, i));
            mittaus.lisaaMittaus("co2", i);
            mittaus.lisaaMittaus("no2", 10 * i);
            mittaus.lisaaMittaus("temperature", 100 * i);
            mittaukset.add(mittaus);
        }
        ArrayList<Mittaus> keskiarvotLaskettu = analysoija.laskeMittaustenKeskiarvo(4000l,mittaukset);
        assertEquals(4, keskiarvotLaskettu.size());
        assertEquals(1.5, keskiarvotLaskettu.get(0).getMittauksenArvo("co2"),0.0000000001);
        assertEquals(13, keskiarvotLaskettu.get(keskiarvotLaskettu.size()-1).getMittauksenArvo("co2"),0.0000000001);
    }
    
    @Test
    public void testLaskeKeskiarvoXML(){
        ArrayList<Mittaus> mittauksetXML=new TiedostonLukijaPalvelu().lueMittauksetListaksi("src/main/resources/PIAQ3.xml");
        MittaustenAnalysoija a = new MittaustenAnalysoija(mittauksetXML);
        
        ArrayList<Mittaus> keskiarvot=a.laskeMittaustenKeskiarvo(30000, mittauksetXML);
        assertEquals(34, keskiarvot.size());
    }

}
