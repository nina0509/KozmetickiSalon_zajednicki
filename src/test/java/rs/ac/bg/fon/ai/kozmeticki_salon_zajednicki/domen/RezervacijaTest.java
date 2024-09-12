/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author ninic
 */
public class RezervacijaTest{

    public Rezervacija r;

    @BeforeEach
    public void setUp() throws Exception {
        r = new Rezervacija();
    }

    @AfterEach
    public void tearDown() throws Exception {
        r = null;

    }

    @Test
    public void testKonstruktorBezParametara() {

        assertNotNull(r);
    }

    @Test
    public void testKonstruktorSaParametrima() {

        Klijent k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
        Date datum = new Date();
        r = new Rezervacija(1, datum, 1200, true, k);

        assertEquals(datum, r.getDatum());
        assertEquals(1, r.getRezervacijaId());
        assertEquals(1200, r.getUkupnaCena());
        assertEquals(true, r.isPojavljivanje());
        assertEquals(k, r.getKlijent());

    }

    @Test
    public void testGetSetSveIspravno() {

        Klijent k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
        Date datum = new Date();
        List<StavkaRezervacije> stavke = new ArrayList<>();
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
    public void testSetIdNegativan() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> r.setRezervacijaId(-1));
    }

    @Test
    public void testSetDatNull() {
        assertThrows(java.lang.NullPointerException.class,
                () -> r.setDatum(null));
    }


    @Test
    public void testUkupnaCenaNegativna() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> r.setUkupnaCena(-1));
    }

    @Test
    public void testKlijentNULL() {
        assertThrows(java.lang.NullPointerException.class,
                () -> r.setKlijent(null));
    }

    @Test
    public void testStavkeNULL() {
        assertThrows(java.lang.NullPointerException.class,
                () -> r.setStavke(null));
    }

    @Test
    public void testToString() {

        Calendar myCalendar = new GregorianCalendar(2025, 2, 11);
        Date datum = myCalendar.getTime();

        Calendar myCalendar1 = new GregorianCalendar(2021, 2, 11);
        Date datum1 = myCalendar1.getTime();

        Klijent k = new Klijent(1, "Marko", "Markovic", "123456789", datum1);
        List<StavkaRezervacije> stavke = new ArrayList<>();
        r = new Rezervacija(1, datum, 1200, true, k);
        stavke.add(new StavkaRezervacije(1, r, LocalTime.now(), LocalTime.now(), 1200, new Usluga()));
        r.setStavke(stavke);

        String expected = "Rezervacija{" + "rezervacijaId=1, datum=" + datum + ", ukupnaCena=1200, pojavljivanje=true, klijent=" + k + ", stavke=" + stavke + '}';
        assertEquals(expected, r.toString());

    }

    @Test
    public void testVratiNazivTabele() {

        assertEquals("rezervacija", r.vratiNazivTabele());
    }

    @Test
    public void testEquals() {
        Calendar myCalendar = new GregorianCalendar(2025, 2, 11);
        Date datum = myCalendar.getTime();

        r = new Rezervacija(1, datum, 1200, true, new Klijent());

        Rezervacija r2 = new Rezervacija(1, datum, 1200, true, new Klijent());

        assertTrue(r.equals(r));
        assertTrue(r.equals(r2));

        r2.setRezervacijaId(2);
        assertFalse(r.equals(r2));

        Calendar myCalendar1 = new GregorianCalendar(2026, 2, 11);
        Date datum1 = myCalendar1.getTime();
        r2.setDatum(datum1);
        assertFalse(r.equals(r2));

        r2.setRezervacijaId(1);
        assertFalse(r.equals(r2));

        assertFalse(r.equals(null));
        assertFalse(r.equals(new Klijent()));

    }

    @Test
    public void testVratiListu() throws SQLException, Exception {

        Calendar myCalendar = new GregorianCalendar(2025, 2, 11);
        Date datum = myCalendar.getTime();
        java.sql.Date datumSQL = new java.sql.Date(datum.getTime());

        Date datRodj = new Date();
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
        when(rs.getDate("klijent.datRodj")).thenReturn(new java.sql.Date(datRodj.getTime()));

        Klijent k1 = new Klijent(1, "Marko", "Markovic", "123456789", datRodj);
        Klijent k2 = new Klijent(2, "Ana", "Jovanovic", "987654321", datRodj);

        Rezervacija r1 = new Rezervacija(1, datum, 1000, true, k1);
        Rezervacija r2 = new Rezervacija(2, datum, 1500, false, k2);

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
        Calendar myCalendar = new GregorianCalendar(2025, 2, 11);
        Date datum = myCalendar.getTime();

        Calendar myCalendar1 = new GregorianCalendar(2021, 2, 11);
        Date datum1 = myCalendar1.getTime();

        Klijent k = new Klijent(1, "Marko", "Markovic", "123456789", datum1);

        r = new Rezervacija(1, datum, 1200, true, k);

        java.sql.Date datumSql = new java.sql.Date(datum.getTime());
        String expected = "datum='" + datumSql + "', ukupnaCena=1200, pojavljivanje=true, klijentId=1";
        assertEquals(expected, r.vratiVrednostiZaUpdate());
    }

    @Test
    public void testVratiVrednostiZaInsert() {
        Klijent k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
        Date datum = new Date();
        java.sql.Date datum1 = new java.sql.Date(datum.getTime());
        r = new Rezervacija(1, datum, 1200, true, k);

        String expected = "'" + datum1 + "',1200,true,1";
        assertEquals(expected, r.vratiVrednostiZaInsert());
    }

    @Test
    public void testVratiPrimarniKljuc() {

        Calendar myCalendar = new GregorianCalendar(2025, 2, 11);
        Date datum = myCalendar.getTime();
        r = new Rezervacija(1, datum, 1200, true, new Klijent());
        assertEquals("rezervacija.rezervacijaId=1", r.vratiPrimarniKljuc());
    }

}
