/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ville.piaqparser.domain;

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
public class HyppyTest {
    private Hyppy hyppy;
    
    public HyppyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        hyppy=new Hyppy();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testHyppyTyhja(){
        assertEquals(null, hyppy.getHyppyAlkoiMittauksesta());
        assertEquals(null, hyppy.getHyppyPaattyiMittaukseen());
    }
    
    @Test
    public void testHypynPituus(){
        Mittaus mittaus1=new Mittaus();
        mittaus1.setAikaleima(new Date(123456000));
        Mittaus mittaus2 = new Mittaus();
        mittaus2.setAikaleima(new Date(123458000));
        hyppy.setHyppyAlkoiMittauksesta(mittaus1);
        hyppy.setHyppyPaattyiMittaukseen(mittaus2);
        assertEquals(2000, hyppy.hypynPituus());
                
    }
    
    @Test
    public void testHyppyKonstruktori(){
        Mittaus mittaus1=new Mittaus();
        mittaus1.setAikaleima(new Date(123456000));
        Mittaus mittaus2 = new Mittaus();
        mittaus2.setAikaleima(new Date(123458000));
        Hyppy h = new Hyppy(mittaus1, mittaus2);
        assertEquals(mittaus1, h.getHyppyAlkoiMittauksesta());
        assertEquals(mittaus2, h.getHyppyPaattyiMittaukseen());
        
    }
    
}
