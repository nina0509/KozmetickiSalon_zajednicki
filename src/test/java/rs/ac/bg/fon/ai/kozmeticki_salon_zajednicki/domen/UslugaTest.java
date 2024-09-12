/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author ninic
 */
public class UslugaTest {

    Usluga u;


    @BeforeEach
    public void setUp() throws Exception {
        u = new Usluga();
    }

    @AfterEach
    public void tearDown() throws Exception {
        u = null;
    }

    @Test
    public void testKonstruktorBezParametara() {

        assertNotNull(u);
    }

    @Test
    public void testKonstruktorSaParametrima() {

        TipUsluge tu = new TipUsluge(1, "manikir");
        u = new Usluga(1, "klasican manikir", 120, 1200, tu);

        assertEquals(1, u.getUslugaId());
        assertEquals("klasican manikir", u.getNaziv());
        assertEquals(120, u.getTrajanje());
        assertEquals(1200, u.getCena());
        assertEquals(tu, u.getTip());

    }

    @Test
    public void testGetSetSve() {

        TipUsluge tu = new TipUsluge(1, "manikir");

        u.setCena(1200);
        u.setNaziv("klasican manikir");
        u.setTrajanje(120);
        u.setUslugaId(1);
        u.setTip(tu);

        assertEquals(1, u.getUslugaId());
        assertEquals("klasican manikir", u.getNaziv());
        assertEquals(120, u.getTrajanje());
        assertEquals(1200, u.getCena());
        assertEquals(tu, u.getTip());

    }

    @Test
    public void testSetIdNegativan() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> u.setUslugaId(-1));
    }

    @Test
    public void testSetNazivKraciOd2() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> u.setNaziv("A"));
    }

    @Test
    public void testSetNazivNull() {
        assertThrows(java.lang.NullPointerException.class,
                () -> u.setNaziv(null));
    }

    @Test
    public void testTrajanjeNegativanBr() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> u.setTrajanje(-1));
    }

    @Test
    public void testCenaNegativanBr() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> u.setCena(-1));
    }

    @Test
    public void testSetTipUslugeNull() {
        assertThrows(java.lang.NullPointerException.class,
                () -> u.setTip(null));
    }

    @ParameterizedTest
    @CsvSource({
        "1, manikir, 1, manikir,true",
        "1, manikir, 2, manikir,false",
        "1, manikir, 1, pedikir,false",
        "1, manikir, 2, pedikir,false",})
    void testEquals(int id1, String n1,
            int id2, String n2, boolean exp) {

        u.setUslugaId(id1);
        u.setNaziv(n1);

        Usluga u1 = new Usluga();
        u1.setUslugaId(id2);
        u1.setNaziv(n2);

        assertEquals(exp, u1.equals(u));

    }

    @Test
    public void testEqualsNull() {

        assertFalse(u.equals(null));

    }

    @Test
    public void testEqualsDrugaKlasa() {

        assertFalse(u.equals(new Klijent()));

    }

    @Test
    public void testIstaAdresa() {

        assertTrue(u.equals(u));

    }

    @Test
    public void testToString() {

        TipUsluge tu = new TipUsluge(1, "manikir");
        u = new Usluga(1, "klasican manikir", 120, 1200, tu);

        String expected = "klasican manikir";

        assertEquals(expected, u.toString());

    }

    @Test
    public void testVratiNazivTabele() {

        assertEquals("usluga", u.vratiNazivTabele());
    }

    @Test
    public void testVratiListu() throws SQLException, Exception {

        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(rs.getInt("tipusluge.tipId")).thenReturn(1).thenReturn(2);
        when(rs.getString("tipusluge.naziv")).thenReturn("manikir").thenReturn("pedikir");

        when(rs.getInt("usluga.uslugaId")).thenReturn(1).thenReturn(2);
        when(rs.getString("usluga.naziv")).thenReturn("klasican manikir").thenReturn("klasican pedikir");
        when(rs.getInt("usluga.trajanje")).thenReturn(120).thenReturn(100);
        when(rs.getInt("usluga.cena")).thenReturn(1200).thenReturn(1000);

        TipUsluge tu1 = new TipUsluge(1, "manikir");
        TipUsluge tu2 = new TipUsluge(2, "pedikir");

        Usluga u1 = new Usluga(1, "klasican manikir", 120, 1200, tu1);
        Usluga u2 = new Usluga(2, "klasican pedikir", 100, 1000, tu2);

        List<OpstiDomenskiObjekat> lista = u.vratiListu(rs);

        assertEquals(2, lista.size());
        assertTrue(lista.get(0) instanceof Usluga);
        assertTrue(lista.get(1) instanceof Usluga);

        assertEquals(lista.get(0), u1);
        assertEquals(lista.get(1), u2);
        assertThrows(java.lang.Exception.class,
                () -> u.vratiListu(null));

    }

    @Test
    public void testVratiKoloneZaInsert() {

        assertEquals("naziv,trajanje,cena,tipId", u.vratiKoloneZaInsert());
    }

    @Test
    public void testVratiVrednostiZaUpdate() {

        TipUsluge tu = new TipUsluge(1, "manikir");
        u = new Usluga(1, "klasican manikir", 120, 1200, tu);

        String expected = "naziv='klasican manikir', trajanje=120, cena=1200, tipId=1";
        assertEquals(expected, u.vratiVrednostiZaUpdate());
    }

    @Test
    public void testVratiVrednostiZaInsert() {
        TipUsluge tu = new TipUsluge(1, "manikir");
        u = new Usluga(1, "klasican manikir", 120, 1200, tu);

        String expected = "'klasican manikir',120,1200,1";
        assertEquals(expected, u.vratiVrednostiZaInsert());
    }

    @Test
    public void testVratiPrimarniKljuc() {
        TipUsluge tu = new TipUsluge(1, "manikir");
        u = new Usluga(1, "klasican manikir", 120, 1200, tu);

        assertEquals("usluga.uslugaId=1", u.vratiPrimarniKljuc());
    }

}
