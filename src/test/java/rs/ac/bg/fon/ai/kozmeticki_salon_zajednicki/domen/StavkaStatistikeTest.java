/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StavkaStatistikeTest extends TestCase {

    StavkaStatistike ss;

    public StavkaStatistikeTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        ss = new StavkaStatistike();
    }

    @Override
    protected void tearDown() throws Exception {
        ss = null;
    }

    @Test
    public void testKonstruktorBezParametara() {

        assertNotNull(ss);
    }

    @Test
    public void testKonstruktorSaParametrima() {

        Statistika s = new Statistika(2001, 2);
        Usluga u = new Usluga(1, "klasican manikir", 120, 1200, new TipUsluge(1, "manikir"));
        ss = new StavkaStatistike(s, u, 2);

        assertEquals(s, ss.getStatistika());
        assertEquals(u, ss.getUsluga());
        assertEquals(2, ss.getBrojRezUsluge());

    }

    @Test
    public void testGetSetSve() {
        Statistika s = new Statistika(2001, 2);
        Usluga u = new Usluga(1, "klasican manikir", 120, 1200, new TipUsluge(1, "manikir"));

        ss.setBrojRezUsluge(2);
        ss.setStatistika(s);
        ss.setUsluga(u);

        assertEquals(s, ss.getStatistika());
        assertEquals(u, ss.getUsluga());
        assertEquals(2, ss.getBrojRezUsluge());

    }
    
    @Test
    public void testStatistikaNULL() {
        assertThrows(java.lang.NullPointerException.class,
                () -> ss.setStatistika(null)); 
    }
    
    @Test
    public void testUslugaNULL() {
        assertThrows(java.lang.NullPointerException.class,
                () -> ss.setUsluga(null)); 
    }
    
    @Test
    public void testSetBrojRezUslugeNegativan() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> ss.setBrojRezUsluge(-1)); 
    }

    @Test
    public void testToString() {
        Statistika s = new Statistika(2001, 2);
        Usluga u = new Usluga(1, "klasican manikir", 120, 1200, new TipUsluge(1, "manikir"));
        ss = new StavkaStatistike(s, u, 2);

        String expected = "StavkaStatistike{godina=2001, usluga=" + u.toString() + ", brojRezUsluge=2}";

        assertEquals(expected, ss.toString());

    }

    @Test
    public void testVratiNazivTabele() {

        assertEquals("stavkastatistike", ss.vratiNazivTabele());
    }

    @Test
    public void testEquals() {

        Statistika s1 = new Statistika(2001, 1);
        Statistika s2 = new Statistika(2002, 2);
        Usluga u1 = new Usluga(1, "klasican manikir", 120, 1200, new TipUsluge(1, "manikir"));
        Usluga u2 = new Usluga(2, "klasican pedikir", 120, 1200, new TipUsluge(2, "pedikir"));

        ss = new StavkaStatistike(s1, u1, 2);
        StavkaStatistike ss1 = new StavkaStatistike(s1, u1, 2);
        StavkaStatistike ss2 = new StavkaStatistike(s1, u2, 2);
        StavkaStatistike ss3 = new StavkaStatistike(s2, u1, 2);
        StavkaStatistike ss4 = new StavkaStatistike(s2, u2, 2);
        StavkaStatistike ss5 = new StavkaStatistike(s1, u1, 3);

        assertTrue(ss.equals(ss));
        assertTrue(ss.equals(ss1));

        assertFalse(ss.equals(ss2));
        assertFalse(ss.equals(ss3));
        assertFalse(ss.equals(ss4));

        assertTrue(ss.equals(ss5));

        assertFalse(ss.equals(null));
        assertFalse(ss.equals(new Klijent()));

    }

    @Test
    public void testVratiListu() throws SQLException, Exception {

        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(false);

        when(rs.getInt("stavkastatistike.brojRezUsluge")).thenReturn(2);

        // usluga
        when(rs.getInt("usluga.uslugaId")).thenReturn(1);
        when(rs.getString("usluga.naziv")).thenReturn("Frizura");
        when(rs.getInt("usluga.trajanje")).thenReturn(120);
        when(rs.getInt("usluga.cena")).thenReturn(1200);

        //  tip usluge
        when(rs.getInt("tipusluge.tipId")).thenReturn(1);
        when(rs.getString("tipusluge.naziv")).thenReturn("Kosa");

        when(rs.getInt("statistika.godina")).thenReturn(2021);
        when(rs.getInt("statistika.ukupnoRez")).thenReturn(2);

        Usluga u = new Usluga(1, "Frizura", 120, 1200, new TipUsluge(1, "Kosa"));
        Statistika s = new Statistika(2021, 2);
        ss = new StavkaStatistike(s, u, 2);

        List<OpstiDomenskiObjekat> lista = ss.vratiListu(rs);

        assertEquals(1, lista.size());
        assertTrue(lista.get(0) instanceof StavkaStatistike);
        assertTrue(lista.get(0).equals(ss));

        assertThrows(java.lang.Exception.class,
                () -> ss.vratiListu(null));

    }

    @Test
    public void testVratiKoloneZaInsert() {

        assertEquals("godina,uslugaId,brojRezUsluge", ss.vratiKoloneZaInsert());
    }

    @Test
    public void testVratiVrednostiZaUpdate() {

        Statistika s = new Statistika(2001, 2);
        Usluga u = new Usluga(1, "klasican manikir", 120, 1200, new TipUsluge(1, "manikir"));
        ss = new StavkaStatistike(s, u, 2);

        String expected = "godina=2001, uslugaId=1, brojRezUsluge=2";

        assertEquals(expected, ss.vratiVrednostiZaUpdate());

    }

    @Test
    public void testVratiVrednostiZaInsert() {
        Statistika s = new Statistika(2001, 2);
        Usluga u = new Usluga(1, "klasican manikir", 120, 1200, new TipUsluge(1, "manikir"));
        ss = new StavkaStatistike(s, u, 2);

        String expected = "2001,1,2";
        assertEquals(expected, ss.vratiVrednostiZaInsert());
    }

    @Test
    public void testVratiPrimarniKljuc() {
        Statistika s = new Statistika(2001, 2);
        Usluga u = new Usluga(1, "klasican manikir", 120, 1200, new TipUsluge(1, "manikir"));
        ss = new StavkaStatistike(s, u, 2);

        assertEquals("stavkastatistike.godina=2001 AND stavkastatistike.uslugaId=1", ss.vratiPrimarniKljuc());
    }

}
