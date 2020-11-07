package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    public KassapaateTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(400);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void rahamaaraOikeinAlussa() {
        assertTrue(kassa.kassassaRahaa() == 100000);
    }

    @Test
    public void myytyjenLounaidenMaaraOikeinAlussa() {
        assertTrue(kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty() == 0);
    }

    @Test
    public void kateisostoToimiiEdullisissa() {
        assertTrue(kassa.syoEdullisesti(500) == 260);
        assertTrue(kassa.kassassaRahaa() == 100240);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);

        // raha ei riitä, joten tarkistetaan, että saadaan kaikki takaisin
        assertTrue(kassa.syoEdullisesti(200) == 200);
        // kassan rahamäärän tulisi pysyä samana
        assertTrue(kassa.kassassaRahaa() == 100240);
        //myytyjen lounaiden määrä edelleen 1
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);
    }

    @Test
    public void kateisostoToimiiMaukkaissa() {
        assertTrue(kassa.syoMaukkaasti(500) == 100);
        assertTrue(kassa.kassassaRahaa() == 100400);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);

        // raha ei riitä, joten tarkistetaan, että saadaan kaikki takaisin
        assertTrue(kassa.syoMaukkaasti(200) == 200);
        // kassan rahamäärän tulisi pysyä samana
        assertTrue(kassa.kassassaRahaa() == 100400);
        //myytyjen lounaiden määrä edelleen 1
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }

    @Test
    public void korttiostoToimiiEdullisissa() {
        assertEquals(true, kassa.syoEdullisesti(kortti));
        assertTrue(kortti.saldo() == 160);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);

        assertEquals(false, kassa.syoEdullisesti(kortti));
        assertTrue(kortti.saldo() == 160);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);

        kortti.lataaRahaa(80);
        assertEquals(true, kassa.syoEdullisesti(kortti));
        assertTrue(kortti.saldo() == 0);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 2);
    }

    @Test
    public void korttiostoToimiiMaukkaissa() {
        assertEquals(true, kassa.syoMaukkaasti(kortti));
        assertTrue(kortti.saldo() == 0);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);

        assertEquals(false, kassa.syoMaukkaasti(kortti));
        assertTrue(kortti.saldo() == 0);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);

        kortti.lataaRahaa(400);
        assertEquals(true, kassa.syoMaukkaasti(kortti));
        assertTrue(kortti.saldo() == 0);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 2);
    }
    
    @Test
    public void kortilleLataaminenToimii() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertTrue(kortti.saldo() == 900);
        assertTrue(kassa.kassassaRahaa() == 100500);
        
        kassa.lataaRahaaKortille(kortti, -1);
        assertTrue(kortti.saldo() == 900);
        assertTrue(kassa.kassassaRahaa() == 100500);
    }
}
