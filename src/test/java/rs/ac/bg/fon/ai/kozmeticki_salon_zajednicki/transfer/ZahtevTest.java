/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.transfer;


import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.config.Operacija;
import rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen.Klijent;

/**
 *
 * @author ninic
 */
public class ZahtevTest {

    Zahtev z;

    @BeforeEach
    public void setUp() throws Exception {
        z = new Zahtev();
    }

    @AfterEach
    public void tearDown() throws Exception {
        z = null;
    }

    @Test
    public void testKonstruktorBezParametara() {

        assertNotNull(z);
    }

    @Test
    public void testKonstruktorSaParametrima() {

        z = new Zahtev(Operacija.LOGIN, "objekat");

        assertEquals(Operacija.LOGIN, z.getOperacija());
        assertEquals("objekat", z.getParametar());

    }
    
    @Test
    public void testGetSetSve() {
        
        z.setOperacija(Operacija.LOGIN);
        z.setParametar("objekat");
        
       assertEquals(Operacija.LOGIN, z.getOperacija());
       assertEquals("objekat", z.getParametar());


    }
    
    

}
