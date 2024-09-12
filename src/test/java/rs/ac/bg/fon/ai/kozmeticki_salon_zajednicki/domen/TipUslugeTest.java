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
public class TipUslugeTest {

    TipUsluge tu;

   
    @BeforeEach
    public void setUp() throws Exception {
        tu = new TipUsluge();
    }

    @AfterEach
    public void tearDown() throws Exception {
        tu = null;
    }

    @Test
    public void testKonstruktorBezParametara() {

        assertNotNull(tu);
    }

    @Test
    public void testKonstruktorSaParametrima() {

        tu = new TipUsluge(1, "manikir");

        assertEquals(1, tu.getTipId());
        assertEquals("manikir", tu.getNaziv());

    }

    @Test
    public void testGetSetSve() {

        tu.setNaziv("manikir");
        tu.setTipId(1);

        assertEquals(1, tu.getTipId());
        assertEquals("manikir", tu.getNaziv());

    }

    @Test
    public void testSetIdNegativan() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> tu.setTipId(-1));
    }

    @Test
    public void testSetNazivKraciOd2() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> tu.setNaziv("A"));
    }

    @Test
    public void testSetNazivNull() {
        assertThrows(java.lang.NullPointerException.class,
                () -> tu.setNaziv(null));
    }

    @ParameterizedTest
    @CsvSource({
        "1, manikir, 1, manikir,true",
        "1, manikir, 2, manikir,true",
        "1, manikir, 1, pedikir,false",
        "1, manikir, 2, pedikir,false",})
    void testEquals(int id1, String n1,
            int id2, String n2, boolean exp) {

        tu = new TipUsluge(id1, n1);
        TipUsluge t1 = new TipUsluge(id2, n2);
        assertEquals(exp, t1.equals(tu));

    }

    @Test
    public void testEqualsNull() {

        assertFalse(tu.equals(null));

    }

    @Test
    public void testEqualsDrugaKlasa() {

        assertFalse(tu.equals(new Klijent()));

    }

    @Test
    public void testIstaAdresa() {

        assertTrue(tu.equals(tu));

    }

    @Test
    public void testToString() {
        tu = new TipUsluge(1, "manikir");

        String expected = "manikir";

        assertEquals(expected, tu.toString());

    }

    @Test
    public void testVratiNazivTabele() {

        assertEquals("tipusluge", tu.vratiNazivTabele());
    }

    @Test
    public void testVratiListu() throws SQLException, Exception {

        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false); // Assume two rows
        when(rs.getInt("tipusluge.tipId")).thenReturn(1).thenReturn(2);
        when(rs.getString("tipusluge.naziv")).thenReturn("manikir").thenReturn("pedikir");

        tu = new TipUsluge(1, "manikir");

        TipUsluge tu1 = new TipUsluge(2, "pedikir");

        List<OpstiDomenskiObjekat> lista = tu.vratiListu(rs);

        assertEquals(2, lista.size());
        assertTrue(lista.get(0) instanceof TipUsluge);
        assertTrue(lista.get(1) instanceof TipUsluge);

        assertEquals(lista.get(0), tu);
        assertEquals(lista.get(1), tu1);
        assertThrows(java.lang.Exception.class,
                () -> tu.vratiListu(null));

    }

    @Test
    public void testVratiKoloneZaInsert() {

        assertEquals("naziv", tu.vratiKoloneZaInsert());
    }

    @Test
    public void testVratiVrednostiZaUpdate() {

        tu = new TipUsluge(1, "manikir");

        String expected = "naziv='manikir'";
        assertEquals(expected, tu.vratiVrednostiZaUpdate());
    }

    @Test
    public void testVratiVrednostiZaInsert() {
        tu = new TipUsluge(1, "manikir");

        String expected = "manikir";
        assertEquals(expected, tu.vratiVrednostiZaInsert());
    }

    @Test
    public void testVratiPrimarniKljuc() {
        tu = new TipUsluge(1, "manikir");
        assertEquals("tipusluge.tipId=1", tu.vratiPrimarniKljuc());
    }

}
