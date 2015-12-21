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
        mittaus= new Mittaus();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMittaukset method, of class Mittaus.
     */
    @Test
    public void testTyhjaKonstruktori(){
        assertEquals(null, mittaus.getAikaleima());
    }
    
    @Test
    public void testLisaaMittaus(){
        mittaus.lisaaMittaus("co2", 50.43);
        assertEquals(1, mittaus.getMittaukset().size());
        assertEquals(50.43, mittaus.getMittauksenArvo("co2"), 0.0000000000001);
    }
    
    @Test
    public void testGetMittaus(){
        mittaus.lisaaMittaus("co2", 50.01);
        mittaus.lisaaMittaus("asd", 32.012345);
        mittaus.lisaaMittaus("Temperature", -20.0001);
        assertEquals(-20.0001, mittaus.getMittauksenArvo("Temperature"),0.0000000000001);
        assertEquals(32.012345, mittaus.getMittauksenArvo("asd"),0.0000000000001);
        assertEquals(50.01, mittaus.getMittauksenArvo("co2"),0.0000000000001);        
    }
    
}
