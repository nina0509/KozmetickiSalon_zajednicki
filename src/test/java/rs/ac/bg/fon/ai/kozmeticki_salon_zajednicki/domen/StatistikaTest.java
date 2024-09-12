/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author ninic
 */
public class StatistikaTest extends TestCase {

    Statistika s;

    public StatistikaTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        s = new Statistika();
    }

    @Override
    protected void tearDown() throws Exception {
        s = null;
    }

    @Test
    public void testKonstruktorBezParametara() {

        assertNotNull(s);
    }

    @Test
    public void testKonstruktorSaParametrima() {

        s = new Statistika(2001, 2);

        assertEquals(2001, s.getGodina());
        assertEquals(2, s.getUkupnoRezervacija());

    }

    @Test
    public void testGetSetSve() {

        Date datum = new Date();
        List<StavkaStatistike> stavke = new ArrayList<>();
        s.setGodina(2001);
        s.setUkupnoRezervacija(2);
        s.setStavke(stavke);

        assertEquals(2001, s.getGodina());
        assertEquals(stavke, s.getStavke());
        assertEquals(2, s.getUkupnoRezervacija());

    }

    @Test
    public void testGodinaNegativanBr() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> s.setGodina(-1));
    }

    @Test
    public void testGodinaPosleTreutne() {
        int tren = LocalDate.now().getYear() + 1;
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> s.setGodina(tren));
    }

    @Test
    public void testUkupnoRezervacijaNegativanBr() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> s.setUkupnoRezervacija(-1));
    }

    @Test
    public void testStavkeNull() {
        assertThrows(java.lang.NullPointerException.class,
                () -> s.setStavke(null));
    }

    @ParameterizedTest
    @CsvSource({
        "2001, 2, 2001, 3,true",
        "2001, 2, 2001, 2,true",
        "2001, 2, 2002, 2,false",
        "2001, 2, 2002, 3,false",})
    void testEquals(int g1, int b1,
            int g2, int b2, boolean exp) {
        s = new Statistika(g1, b1);
        Statistika s1 = new Statistika(g2, b2);

        assertEquals(exp, s.equals(s1));

    }

    @Test
    public void testEqualsNull() {

        assertFalse(s.equals(null));

    }

    @Test
    public void testEqualsDrugaKlasa() {

        assertFalse(s.equals(new Klijent()));

    }

    @Test
    public void testIstaAdresa() {

        assertTrue(s.equals(s));

    }

    @Test
    public void testToString() {
        s = new Statistika(2001, 2);
        List<StavkaStatistike> lista = new ArrayList<>();
        lista.add(new StavkaStatistike(s, new Usluga(), 5));
        s.setStavke(lista);
        String expected = "Statistika{" + "godina=2001, ukupnoRezervacija=2, stavke=" + s.getStavke().toString() + '}';

        assertEquals(expected, s.toString());

    }

    @Test
    public void testVratiNazivTabele() {

        assertEquals("statistika", s.vratiNazivTabele());
    }

    @Test
    public void testVratiListu() throws SQLException, Exception {

        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false); // Assume two rows
        when(rs.getInt("statistika.godina")).thenReturn(2001).thenReturn(2002);
        when(rs.getInt("statistika.ukupnoRez")).thenReturn(10).thenReturn(12);

        Statistika s1 = new Statistika(2001, 10);
        s = new Statistika(2002, 12);

        List<OpstiDomenskiObjekat> lista = s.vratiListu(rs);

        assertEquals(2, lista.size());
        assertTrue(lista.get(0) instanceof Statistika);
        assertTrue(lista.get(1) instanceof Statistika);

        assertEquals(lista.get(0), s1);
        assertEquals(lista.get(1), s);
        assertThrows(java.lang.Exception.class,
                () -> s.vratiListu(null));

    }

    @Test
    public void testVratiKoloneZaInsert() {

        assertEquals("godina,ukupnoRez", s.vratiKoloneZaInsert());
    }

    @Test
    public void testVratiVrednostiZaUpdate() {

        s = new Statistika(2002, 12);

        String expected = "godina=2002, ukupnoRez=12";
        assertEquals(expected, s.vratiVrednostiZaUpdate());
    }

    @Test
    public void testVratiVrednostiZaInsert() {
        s = new Statistika(2002, 12);

        String expected = "2002,12";
        assertEquals(expected, s.vratiVrednostiZaInsert());
    }

    @Test
    public void testVratiPrimarniKljuc() {
        s = new Statistika(2002, 12);
        assertEquals("statistika.godina=2002", s.vratiPrimarniKljuc());
    }

    @Test
    public void testSerijalizacija() {

        Statistika s1 = new Statistika(2021, 2);
        Usluga u1 = new Usluga(1, "klasican manikir", 120, 1200, new TipUsluge(1, "manikir"));
        StavkaStatistike ss1 = new StavkaStatistike(s1, u1, 2);

        List<StavkaStatistike> stavke1 = new ArrayList<>();
        stavke1.add(ss1);
        s1.setStavke(stavke1);

        Statistika s2 = new Statistika(2022, 3);
        Usluga u2 = new Usluga(2, "klasican pedikir", 100, 1000, new TipUsluge(2, "pedikir"));
        StavkaStatistike ss2 = new StavkaStatistike(s2, u2, 3);

        List<StavkaStatistike> stavke2 = new ArrayList<>();
        stavke2.add(ss2);
        s2.setStavke(stavke2);

        List<Statistika> stat = new ArrayList<>();
        stat.add(s1);
        stat.add(s2);
        System.out.println(stat);

        String a = s.serijalizujJSON(stat);

        String expected = "[{\"godina\":2021,\"ukupnoRezervacija\":2,\"stavke\":[{\"usluga\":{},\"brojRezUsluge\":2}]},{\"godina\":2022,\"ukupnoRezervacija\":3,\"stavke\":[{\"usluga\":{},\"brojRezUsluge\":3}]}]";

        assertEquals(expected, a);

        List<Statistika> rez1 = s.deserijalizujJSON("src/test/resources/statistika.json");
        assertEquals(rez1.get(0), stat.get(0));
        assertEquals(rez1.get(1), stat.get(1));

    }

    @Test
    public void testDeserijalizacija() {

        Statistika s1 = new Statistika(2021, 2);
        Usluga u1 = new Usluga(1, "klasican manikir", 120, 1200, new TipUsluge(1, "manikir"));
        StavkaStatistike ss1 = new StavkaStatistike(s1, u1, 2);

        List<StavkaStatistike> stavke1 = new ArrayList<>();
        stavke1.add(ss1);
        s1.setStavke(stavke1);

        Statistika s2 = new Statistika(2022, 3);
        Usluga u2 = new Usluga(2, "klasican pedikir", 100, 1000, new TipUsluge(2, "pedikir"));
        StavkaStatistike ss2 = new StavkaStatistike(s2, u2, 3);

        List<StavkaStatistike> stavke2 = new ArrayList<>();
        stavke2.add(ss2);
        s2.setStavke(stavke2);

        List<Statistika> stat = new ArrayList<>();
        stat.add(s1);
        stat.add(s2);

        String a = s.serijalizujJSON(stat);

        List<Statistika> rez = s.deserijalizujJSON("src/test/resources/statistika.json");
        assertEquals(2, rez.size());
        assertEquals(rez.get(0), stat.get(0));
        assertEquals(rez.get(1), stat.get(1));

    }

}
