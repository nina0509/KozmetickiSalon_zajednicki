/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.transfer;

import static junit.framework.Assert.assertNotNull;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ninic
 */
public class OdgovorTest extends TestCase {
    
    Odgovor o;
    public OdgovorTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        o=new Odgovor();
    }
    
    @Override
    protected void tearDown() throws Exception {
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
