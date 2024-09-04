/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import junit.framework.TestCase;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 *
 * @author ninic
 */
public class StavkaRezervacijeTest extends TestCase {

    StavkaRezervacije sr;

    public StavkaRezervacijeTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        sr = new StavkaRezervacije();
    }

    @Override
    protected void tearDown() throws Exception {
        sr = null;
    }

    @Test
    public void testKonstruktorBezParametara() {

        assertNotNull(sr);
    }

    @Test
    public void testKonstruktorSaParametrima() {

        Usluga u = new Usluga(1, "klasican manikir", 120, 1200, new TipUsluge(1, "manikir"));
        Rezervacija r = new Rezervacija(1, new Date(), 1200, true, new Klijent());

        sr = new StavkaRezervacije(1, r, LocalTime.MIN, LocalTime.MAX, 1200, u);

        assertEquals(1, sr.getRBStavke());
        assertEquals(r, sr.getRezervacija());
        assertEquals(LocalTime.MIN, sr.getVremePocetka());
        assertEquals(LocalTime.MAX, sr.getVremeZavrsetka());
        assertEquals(1200, sr.getCena());
        assertEquals(u, sr.getUsluga());

    }

    @Test
    public void testGetSetSve() {

        Usluga u = new Usluga(1, "klasican manikir", 120, 1200, new TipUsluge(1, "manikir"));
        Rezervacija r = new Rezervacija(1, new Date(), 1200, true, new Klijent());

        sr.setRBStavke(1);
        sr.setCena(1200);
        sr.setRezervacija(r);
        sr.setVremePocetka(LocalTime.MIN);
        sr.setVremeZavrsetka(LocalTime.MAX);
        sr.setUsluga(u);

        assertEquals(1, sr.getRBStavke());
        assertEquals(r, sr.getRezervacija());
        assertEquals(LocalTime.MIN, sr.getVremePocetka());
        assertEquals(LocalTime.MAX, sr.getVremeZavrsetka());
        assertEquals(1200, sr.getCena());
        assertEquals(u, sr.getUsluga());

    }

    @Test
    public void testToString() {

        Usluga u = new Usluga(1, "klasican manikir", 120, 1200, new TipUsluge(1, "manikir"));
        Rezervacija r = new Rezervacija(1, new Date(), 1200, true, new Klijent());

        sr = new StavkaRezervacije(1, r, LocalTime.MIN, LocalTime.MAX, 1200, u);

        String expected = "StavkaRezervacije{RBStavke=1, vremePocetka=" + LocalTime.MIN + ", vremeZavrsetka=" + LocalTime.MAX + ", cena=1200, usluga=" + u.toString() + '}';

        assertEquals(expected, sr.toString());

    }

    @Test
    public void testVratiNazivTabele() {

        assertEquals("stavkarezervacije", sr.vratiNazivTabele());
    }

    @Test
    public void testEquals() {

        Usluga u1 = new Usluga(1, "klasican manikir", 120, 1200, new TipUsluge(1, "manikir"));
        Usluga u2 = new Usluga(2, "klasican pedikir", 120, 1200, new TipUsluge(2, "pedikir"));

        Rezervacija r = new Rezervacija(1, new Date(), 1200, true, new Klijent());

        sr = new StavkaRezervacije(1, r, LocalTime.MIN, LocalTime.MAX, 1200, u1);

        StavkaRezervacije s2 = new StavkaRezervacije(1, r, LocalTime.MIN, LocalTime.MAX, 1200, u1);
        StavkaRezervacije s3 = new StavkaRezervacije(1, r, LocalTime.MIN, LocalTime.of(10, 0), 1200, u1);
        StavkaRezervacije s4 = new StavkaRezervacije(1, r, LocalTime.of(10, 0), LocalTime.MAX, 1200, u1);
        StavkaRezervacije s5 = new StavkaRezervacije(1, r, LocalTime.MIN, LocalTime.MAX, 1200, u2);
        StavkaRezervacije s6 = new StavkaRezervacije(2, r, LocalTime.MIN, LocalTime.MAX, 1200, u2);

        assertTrue(sr.equals(sr));
        assertTrue(sr.equals(s2));

        assertFalse(sr.equals(s3));
        assertFalse(sr.equals(s4));
        assertFalse(sr.equals(s5));
        assertFalse(sr.equals(s6));

        assertFalse(sr.equals(null));
        assertFalse(sr.equals(new Klijent()));

    }

    @Test
    public void testVratiListu() throws SQLException, Exception {

        ResultSet rs = Mockito.mock(ResultSet.class);

        Date datum = new Date();
        java.sql.Date datumSql = new java.sql.Date(datum.getTime());

        LocalTime pocetak = LocalTime.MIN;
        java.sql.Time pocetakSQL = Time.valueOf(pocetak);

        LocalTime kraj = LocalTime.of(23, 59, 59);

        java.sql.Time krajSQL = Time.valueOf(kraj);

        when(rs.next()).thenReturn(true).thenReturn(false); // Prvi put vraća true, drugi put false

        // stavke rezervacije
        when(rs.getInt("stavkarezervacije.RBstavke")).thenReturn(1);
        when(rs.getInt("stavkarezervacije.cena")).thenReturn(1200);
        when(rs.getTime("stavkarezervacije.vremePocetka")).thenReturn(pocetakSQL);
        when(rs.getTime("stavkarezervacije.vremeZavrsetka")).thenReturn(krajSQL);

        //  klijent
        when(rs.getInt("klijent.klijentId")).thenReturn(1);
        when(rs.getString("klijent.ime")).thenReturn("Marko");
        when(rs.getString("klijent.prezime")).thenReturn("Markovic");
        when(rs.getString("klijent.brTel")).thenReturn("0612345678");
        when(rs.getDate("klijent.datRodj")).thenReturn(datumSql);

        //  rezervacija
        when(rs.getInt("rezervacija.rezervacijaId")).thenReturn(1);
        when(rs.getInt("rezervacija.ukupnaCena")).thenReturn(1200);
        when(rs.getBoolean("rezervacija.pojavljivanje")).thenReturn(true);
        when(rs.getDate("rezervacija.datum")).thenReturn(datumSql);

        // usluga
        when(rs.getInt("usluga.uslugaId")).thenReturn(1);
        when(rs.getString("usluga.naziv")).thenReturn("Frizura");
        when(rs.getInt("usluga.trajanje")).thenReturn(120);
        when(rs.getInt("usluga.cena")).thenReturn(1200);

        //  tip usluge
        when(rs.getInt("tipusluge.tipId")).thenReturn(1);
        when(rs.getString("tipusluge.naziv")).thenReturn("Kosa");

        Klijent k = new Klijent(1, "Marko", "Markovic", "0612345678", datum);
        Rezervacija r = new Rezervacija(1, datum, 1200, true, k);
        Usluga u = new Usluga(1, "Frizura", 120, 1200, new TipUsluge(1, "Kosa"));
        StavkaRezervacije stavkaRezervacije = new StavkaRezervacije(1, r, pocetak, kraj, 1200, u);

        // Pozivanje metode vratiListu
        List<OpstiDomenskiObjekat> lista = sr.vratiListu(rs);

        assertEquals(1, lista.size());
        assertTrue(lista.get(0) instanceof StavkaRezervacije);
        System.out.println(lista.get(0));
        System.out.println(stavkaRezervacije);
        assertTrue(lista.get(0).equals(stavkaRezervacije));

        assertThrows(java.lang.Exception.class,
                () -> sr.vratiListu(null));

    }

    @Test
    public void testVratiKoloneZaInsert() {

        assertEquals("RBstavke,rezervacijaId,vremePocetka,vremeZavrsetka,cena,uslugaId", sr.vratiKoloneZaInsert());

    }

    @Test
    public void testVratiVrednostiZaUpdate() {

        Usluga u = new Usluga(1, "klasican manikir", 120, 1200, new TipUsluge(1, "manikir"));
        Rezervacija r = new Rezervacija(1, new Date(), 1200, true, new Klijent());

        sr = new StavkaRezervacije(1, r, LocalTime.MIN, LocalTime.MAX, 1200, u);

        String expected = "vremePocetka='" + LocalTime.MIN + "', vremeZavrsetka='" + LocalTime.MAX + "', cena=1200, uslugaId=1";

        assertEquals(expected, sr.vratiVrednostiZaUpdate());

    }

    @Test
    public void testVratiVrednostiZaInsert() {
        Usluga u = new Usluga(1, "klasican manikir", 120, 1200, new TipUsluge(1, "manikir"));
        Rezervacija r = new Rezervacija(1, new Date(), 1200, true, new Klijent());

        sr = new StavkaRezervacije(1, r, LocalTime.MIN, LocalTime.MAX, 1200, u);
        java.sql.Time vreme1 = java.sql.Time.valueOf(LocalTime.MIN);
        java.sql.Time vreme2 = java.sql.Time.valueOf(LocalTime.MAX);

        String expected = "1,1,'" + vreme1 + "','" + vreme2 + "',1200,1";

        assertEquals(expected, sr.vratiVrednostiZaInsert());
    }

    @Test
    public void testVratiPrimarniKljuc() {
        Rezervacija r = new Rezervacija(1, new Date(), 1200, true, new Klijent());
        Usluga u = new Usluga(1, "klasican manikir", 120, 1200, new TipUsluge(1, "manikir"));

        sr = new StavkaRezervacije(1, r, LocalTime.MIN, LocalTime.MAX, 1200, u);
        assertEquals("stavkarezervacije.RBstavke=1 AND stavkarezervacije.rezervacijaId=1", sr.vratiPrimarniKljuc());
    }

}
