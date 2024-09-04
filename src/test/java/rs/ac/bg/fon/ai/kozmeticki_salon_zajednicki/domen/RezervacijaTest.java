/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
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
 * @author ninic
 */
public class RezervacijaTest extends TestCase {

    public Rezervacija r;

    public RezervacijaTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        r = new Rezervacija();
    }

    @Override
    protected void tearDown() throws Exception {
        r = null;

    }

    @Test
    public void testKonstruktorBezParametara() {

        assertNotNull(r);
    }
    
    
    @Test
    public void testKonstruktorSaParametrima() {

    Klijent  k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
    Date datum=new Date();
    r=new Rezervacija(1, datum, 1200, true, k);
  
    assertEquals(datum, r.getDatum());
    assertEquals(1, r.getRezervacijaId());
    assertEquals(1200, r.getUkupnaCena());
    assertEquals(true, r.isPojavljivanje());
    assertEquals(k, r.getKlijent());
    
    
    }
    
     @Test
    public void testGetSetSve() {

    Klijent  k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
    Date datum=new Date();
    List<StavkaRezervacije> stavke=new ArrayList<>();
    r.setRezervacijaId(1);
    r.setDatum(datum);
    r.setUkupnaCena(1200);
    r.setPojavljivanje(true);
    r.setKlijent(k);
    r.setStavke(stavke);
   
    assertEquals(datum, r.getDatum());
    assertEquals(1, r.getRezervacijaId());
    assertEquals(1200, r.getUkupnaCena());
    assertEquals(true, r.isPojavljivanje());
    assertEquals(k, r.getKlijent());
    assertEquals(stavke, r.getStavke());
    
    
    }
    
    
    
    @Test
    public void testToString() {
    Klijent  k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
    Date datum=new Date();
    List<StavkaRezervacije> stavke=new ArrayList<>();
    r=new Rezervacija(1, datum, 1200, true, k);
    stavke.add(new StavkaRezervacije(1,r,LocalTime.now(),LocalTime.now(),1200,null));
    r.setStavke(stavke);
    
    String expected="Rezervacija{" + "rezervacijaId=1, datum=" + datum + ", ukupnaCena=1200, pojavljivanje=true, klijent=" + k + ", stavke=" + stavke + '}';
    assertEquals(expected, r.toString());
    
    
    }
    
    
    @Test
    public void testVratiNazivTabele() {
    
        assertEquals("rezervacija", r.vratiNazivTabele());
    }
    
    @Test
    public void testEquals() {
       Rezervacija r1=new Rezervacija();
       r1.setRezervacijaId(1);
       Date datum=new Date();
       r1.setDatum(datum);
       
       Rezervacija r2=new Rezervacija();
       r2.setRezervacijaId(1);
       r2.setDatum(datum);
        
        assertTrue(r1.equals(r));
        assertTrue(r1.equals(r2));
        
        r2.setRezervacijaId(2);
        assertFalse(r1.equals(r2));
        
        r2.setDatum(null);
        assertFalse(r1.equals(r2));
        
        r2.setRezervacijaId(1);
        assertFalse(r1.equals(r2));
       
        
     
       assertFalse(r1.equals(null));
       assertFalse(r1.equals(new Klijent()));
         
    }
    
    
    @Test
    public void testVratiListu() throws SQLException, Exception {
        
        Date datum=new Date();
        java.sql.Date datumSQL=new java.sql.Date(datum.getTime());
        
       ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false); // Assume two rows
        when(rs.getInt("rezervacija.rezervacijaId")).thenReturn(1).thenReturn(2);
        when(rs.getInt("rezervacija.ukupnaCena")).thenReturn(1000).thenReturn(1500);
        when(rs.getBoolean("rezervacija.pojavljivanje")).thenReturn(true).thenReturn(false);
        when(rs.getDate("rezervacija.datum")).thenReturn(datumSQL).thenReturn(datumSQL);
        // klijent
         when(rs.getInt("klijent.klijentId")).thenReturn(1).thenReturn(2);
        when(rs.getString("klijent.ime")).thenReturn("Marko").thenReturn("Ana");
        when(rs.getString("klijent.prezime")).thenReturn("Markovic").thenReturn("Jovanovic");
        when(rs.getString("klijent.brTel")).thenReturn("123456789").thenReturn("987654321");
        when(rs.getDate("klijent.datRodj")).thenReturn(new java.sql.Date(new Date().getTime()));

       Klijent  k1 = new Klijent(1, "Marko", "Markovic", "123456789", datum);
       Klijent  k2 = new Klijent(2, "Ana", "Jovanovic", "987654321", datum);

       Rezervacija r1=new Rezervacija(1, datum, 1000, true, k1);
      Rezervacija r2=new Rezervacija(2, datum, 1500, false, k2);
       
        List<OpstiDomenskiObjekat> lista = r.vratiListu(rs);

        assertEquals(2, lista.size());
        assertTrue(lista.get(0) instanceof Rezervacija);
        assertTrue(lista.get(1) instanceof Rezervacija);
        
        assertEquals(lista.get(0), r1);
        assertEquals(lista.get(1), r2);
        assertThrows(java.lang.Exception.class,
				() -> r.vratiListu(null));
        
        
    }
    
    
    
     @Test
    public void testVratiKoloneZaInsert() {
        
        assertEquals("datum,ukupnaCena,pojavljivanje,klijentId", r.vratiKoloneZaInsert());
    }
    
    
    @Test
    public void testVratiVrednostiZaUpdate() {
        
    Klijent  k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
    Date datum=new Date();
    r=new Rezervacija(1, datum, 1200, true, k);
  
     java.sql.Date datum1 = new java.sql.Date(datum.getTime());
    String expected = "datum='" + datum1 + "', ukupnaCena=1200, pojavljivanje=true, klijentId=1";
    assertEquals(expected, r.vratiVrednostiZaUpdate());
    }
    
    
    @Test
    public void testVratiVrednostiZaInsert() {
    Klijent  k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
    Date datum=new Date();
    java.sql.Date datum1 = new java.sql.Date(datum.getTime());
    r=new Rezervacija(1, datum, 1200, true, k);
  
        String expected ="'" + datum1 + "',1200,true,1";
        assertEquals(expected, r.vratiVrednostiZaInsert());
    }
    
     @Test
    public void testVratiPrimarniKljuc() {
    
      r=new Rezervacija(1, null, 1200, true, null);
      assertEquals("rezervacija.rezervacijaId=1", r.vratiPrimarniKljuc());
    }
    
    

}
