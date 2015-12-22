/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.util;

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
        
        mittaukset=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
          Mittaus mittaus = new Mittaus();
          mittaus.setAikaleima(new Date(2015, 12, 12, 20, 20, i));
          mittaus.lisaaMittaus("co2", i);
          mittaus.lisaaMittaus("no2", 10*i);
          mittaus.lisaaMittaus("temperature", 100*i);
            mittaukset.add(mittaus);
        }
        analysoija = new MittaustenAnalysoija(mittaukset);
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void testEkaJaVikaMittausOikein(){
        ArrayList<Mittaus> ekaJaVika=analysoija.ensimmainenJaViimeinenMittausListana();
        assertEquals(2, ekaJaVika.size());
        assertEquals(mittaukset.get(0), ekaJaVika.get(0));
        assertEquals(mittaukset.get(mittaukset.size()-1), ekaJaVika.get(1));
    }
    
    @Test
    public void testEkaJaVikaMittausSamat(){
        for(int i =0;i<9;i++){
            mittaukset.remove(0);
        }
        assertEquals(1, analysoija.ensimmainenJaViimeinenMittausListana().size());
        assertEquals(mittaukset.get(mittaukset.size()-1), analysoija.ensimmainenJaViimeinenMittausListana().get(0));
    }
    
    @Test
    public void testMittauksissaHyppyjaTrue(){
        mittaukset.remove(5);
        assertEquals(true, analysoija.mittauksissaHyppyja());
    }
    
    @Test
    public void testMittauksissaHyppyjaFalse(){
        assertEquals(false, analysoija.mittauksissaHyppyja());
    }
    
    @Test
    public void testMittausValiOikein(){
        assertEquals(true, analysoija.mittaustenMittausvaliMS()>=0);
        assertEquals(1000, analysoija.mittaustenMittausvaliMS());
    }
    
    @Test
    public void testMittausvaliOikeinYhdellaMittauksella(){
        for(int i =0;i<9;i++){
            mittaukset.remove(0);
        }
        assertEquals(0, analysoija.mittaustenMittausvaliMS());
    }
    
    @Test
    public void testMittausvaliOikeinJokaToinenSekunti(){
        ArrayList mittauksetSpecial=new ArrayList<Mittaus>();
        for (int i = 0; i < 10; i=i+2) {
          Mittaus mittaus = new Mittaus();
          mittaus.setAikaleima(new Date(2015, 12, 12, 20, 20, i));
          mittaus.lisaaMittaus("co2", i);
          mittaus.lisaaMittaus("no2", 10*i);
          mittaus.lisaaMittaus("temperature", 100*i);
            mittauksetSpecial.add(mittaus);
        }
        MittaustenAnalysoija analysoija2 = new MittaustenAnalysoija(mittauksetSpecial);
        assertEquals(true, analysoija2.mittaustenMittausvaliMS()>=0);
        assertEquals(2000, analysoija2.mittaustenMittausvaliMS());
    }
    
    

    
}
