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

/**
 *
 * @author ninic
 */
public class OdgovorTest {
    
    Odgovor o;
 
    @BeforeEach
    public void setUp() throws Exception {
        o=new Odgovor();
    }
    
    @AfterEach
    public void tearDown() throws Exception {
        o=null;
    }

     @Test
    public void testKonstruktorBezParametara() {

        assertNotNull(o);
    }

   
    public void testGetSet() {
        
        o.setOdgovor("odgovor");
        assertEquals("odgovor", o.getOdgovor());
        
    }

  
    
}
