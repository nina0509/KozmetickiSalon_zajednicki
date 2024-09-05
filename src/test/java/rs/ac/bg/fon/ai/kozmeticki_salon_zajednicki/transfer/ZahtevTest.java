/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.transfer;

import static junit.framework.Assert.assertNotNull;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.config.Operacija;
import rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen.Klijent;

/**
 *
 * @author ninic
 */
public class ZahtevTest extends TestCase {

    Zahtev z;

    public ZahtevTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        z = new Zahtev();
    }

    @Override
    protected void tearDown() throws Exception {
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
