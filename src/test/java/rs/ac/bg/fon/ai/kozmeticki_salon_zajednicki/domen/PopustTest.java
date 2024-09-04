/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 *
 * @author Nikolina Baros
 */
public class PopustTest extends TestCase {
    
    public Popust p;
    public PopustTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
       p=new Popust();
    }
    
    @Override
    protected void tearDown() throws Exception {
        p=null;
    }

    @Test 
    public void testGetSetKlijent() {
        
        Klijent  k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
        p.setKlijent(k);
        assertEquals(k, p.getKlijent());
        
    }

   
   @Test
    public void testGetSetUsluga() {
        Usluga u=new Usluga(1, "klasican manikir", 120, 2000, new TipUsluge(1, "manikir"));
        p.setUsluga(u);
       assertEquals(u, p.getUsluga());
    }
    
     @Test
     public void testGetSetBrojRezIPopust() {
       p.setBrojRezUsluge(10);
       p.setPopust(5);
       assertEquals(10,p.getBrojRezUsluge());
         assertEquals(5, p.getPopust());
    }

     @Test
    public void testKonstruktorBezParametara() {
         
        
        assertNotNull(p);
    }
    
    
     @Test
    public void testKonstruktorSaParametrima() {
       Klijent  k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
       Usluga u=new Usluga(1, "klasican manikir", 120, 2000, new TipUsluge(1, "manikir"));
       p=new Popust(k, u, 10, 5);
        assertEquals(k, p.getKlijent());
        assertEquals(u, p.getUsluga());
        assertEquals(10, p.getBrojRezUsluge());
        assertEquals(5, p.getPopust());
      
       
    }
    
    
    @Test
    public void testToString() {
        Klijent  k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
       Usluga u=new Usluga(1, "klasican manikir", 120, 2000, new TipUsluge(1, "manikir"));
       p=new Popust(k, u, 10, 5);
       String expected= "Popust{" + "klijent=" + k + ", usluga=" + u + ", brojRezUsluge=10, popust=5}";
        assertEquals(expected, p.toString());
    }

    @Test
    public void testEquals() {
        
        Klijent k1 = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
        Klijent k2 = new Klijent(2, "Ana", "Markovic", "123456789", new Date());
        
         Usluga u1=new Usluga(1, "klasican manikir", 120, 2000, new TipUsluge(1, "manikir"));
         Usluga u2=new Usluga(2, "klasican pedikir", 120, 2000, new TipUsluge(2, "pedikir"));

        Popust p1=new Popust(k1, u1, 10 , 5);
        Popust p2=new Popust(k1, u1, 10 , 5);
        Popust p3=new Popust(k1, u2, 10 , 5);
        Popust p4=new Popust(k2, u1, 10 , 5);
        Popust p5=new Popust(k2, u2, 10 , 5);
        
        assertTrue(p1.equals(p2));
        assertFalse(p1.equals(p3));
        assertFalse(p1.equals(p4));
       assertFalse(p1.equals(p5));
       assertFalse(p1.equals(null));
       assertFalse(p1.equals(new Klijent()));
         
    }
    
    
     @Test
    public void testVratiNazivTabele() {
    
        assertEquals("popust", p.vratiNazivTabele());
    }
    
      @Test
    public void testVratiListu() throws SQLException, Exception {
        
         Date datum=new Date();
        java.sql.Date datumSQL=new java.sql.Date(datum.getTime());
        
       ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false); // Assume two rows
        when(rs.getInt("popust.popust")).thenReturn(10).thenReturn(5);
        when(rs.getInt("popust.brojRezUsluge")).thenReturn(15).thenReturn(6);

        // klijenti
         when(rs.getInt("klijent.klijentId")).thenReturn(1).thenReturn(2);
        when(rs.getString("klijent.ime")).thenReturn("Marko").thenReturn("Ana");
        when(rs.getString("klijent.prezime")).thenReturn("Markovic").thenReturn("Jovanovic");
        when(rs.getString("klijent.brTel")).thenReturn("123456789").thenReturn("987654321");
        when(rs.getDate("klijent.datRodj")).thenReturn(datumSQL);

        // usluge
        when(rs.getInt("usluga.uslugaId")).thenReturn(201).thenReturn(202);
        when(rs.getString("usluga.naziv")).thenReturn("Masaza").thenReturn("Frizura");
        when(rs.getInt("usluga.cena")).thenReturn(1500).thenReturn(2000);
         when(rs.getInt("usluga.trajanje")).thenReturn(150).thenReturn(120);
         
         // usluge
        when(rs.getInt("tipusluge.tipId")).thenReturn(1).thenReturn(2);
        when(rs.getString("tipusluge.naziv")).thenReturn("Masaza").thenReturn("Frizura");
       
         
        Klijent k1 = new Klijent(1, "Marko", "Markovic", "123456789", datum);
        Klijent k2 = new Klijent(2, "Ana", "Jovanovic", "987654321",datum);
        
        Usluga u1=new Usluga(201, "Masaza", 150, 1500, new TipUsluge(1, "Masaza"));
        Usluga u2=new Usluga(202, "Frizura", 120, 2000, new TipUsluge(2, "Frizura"));
        
        Popust p1=new Popust(k1, u1, 15, 10);
        Popust p2=new Popust(k2, u2, 6, 5);
        
        List<OpstiDomenskiObjekat> lista = p.vratiListu(rs);

        assertEquals(2, lista.size());
        assertTrue(lista.get(0) instanceof Popust);
        assertTrue(lista.get(1) instanceof Popust);
        
          assertEquals(lista.get(0), p1);
          assertEquals(lista.get(1), p2);
        assertThrows(java.lang.Exception.class,
				() -> p.vratiListu(null));
        
        
    }
    
    
    
    
    @Test
    public void testVratiKoloneZaInsert() {
        
        assertEquals("klijentId,uslugaId,brojRezUsluge,popust", p.vratiKoloneZaInsert());
    }
    
    
    
    @Test
    public void testVratiVrednostiZaUpdate() {
        
         Klijent  k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
       Usluga u=new Usluga(1, "klasican manikir", 120, 2000, new TipUsluge(1, "manikir"));
       p=new Popust(k, u, 10, 5);
       
        String expected = "klijentId=1, uslugaId=1, brojRezUsluge=10, popust=5";
        assertEquals(expected, p.vratiVrednostiZaUpdate());
    }

    
    @Test
    public void testVratiVrednostiZaInsert() {
        Klijent  k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
       Usluga u=new Usluga(1, "klasican manikir", 120, 2000, new TipUsluge(1, "manikir"));
       p=new Popust(k, u, 10, 5);
       
        String expected = "1,1,10,5";
        assertEquals(expected, p.vratiVrednostiZaInsert());
    }

    @Test
    public void testVratiPrimarniKljuc() {
      Klijent  k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
       Usluga u=new Usluga(1, "klasican manikir", 120, 2000, new TipUsluge(1, "manikir"));
       p=new Popust(k, u, 10, 5);
       
        assertEquals("popust.klijentId=1 AND popust.uslugaId=1", p.vratiPrimarniKljuc());
    }
}
