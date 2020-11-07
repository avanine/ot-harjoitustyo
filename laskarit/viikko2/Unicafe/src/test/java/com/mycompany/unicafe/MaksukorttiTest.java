package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertTrue(kortti.saldo() == 10);
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(15);
        assertTrue(kortti.saldo() == 25);
        kortti.lataaRahaa(50);
        assertTrue(kortti.saldo() == 75);
    }
    
    @Test
    public void rahanOttaminenToimii() {
        assertEquals(true, kortti.otaRahaa(8));
        assertTrue(kortti.saldo() == 2);
        assertEquals(false, kortti.otaRahaa(12));
        assertTrue(kortti.saldo() == 2);
    }
    
    @Test
    public void toStringToimii() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
