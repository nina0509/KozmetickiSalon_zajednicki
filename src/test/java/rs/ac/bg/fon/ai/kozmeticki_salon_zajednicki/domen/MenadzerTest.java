/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static junit.framework.Assert.assertEquals;
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
public class MenadzerTest extends TestCase {

    public Menadzer m;

    public MenadzerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        m = new Menadzer();

    }

    @Override
    protected void tearDown() throws Exception {
        m = null;
    }

    @Test
    public void testGettersSetters() {
        m.setId(2);
        m.setUsername("newuser");
        m.setPassword("newpass");
        m.setIme("Petar");
        m.setPrezime("Petrovic");

        assertEquals(2, m.getId());
        assertEquals("newuser", m.getUsername());
        assertEquals("newpass", m.getPassword());
        assertEquals("Petar", m.getIme());
        assertEquals("Petrovic", m.getPrezime());
    }

    @Test
    public void testSetIdNegativan() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> m.setId(-1));
    }

    @Test
    public void testUsernameKraciOd2() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> m.setUsername("n"));
    }

    @Test
    public void testUsernameNULL() {
        assertThrows(java.lang.NullPointerException.class,
                () -> m.setUsername(null));
    }

    @Test
    public void testLozinkaKracaOd2() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> m.setPassword("n"));
    }

    @Test
    public void testLozinkaNull() {
        assertThrows(java.lang.NullPointerException.class,
                () -> m.setPassword(null));
    }

    @Test
    public void testSetImeKraceOd2() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> m.setIme("A"));
    }

    @Test
    public void testSetImeNULL() {
        assertThrows(java.lang.NullPointerException.class,
                () -> m.setIme(null));
    }

    @Test
    public void testSetPrezimeKraceOd2() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> m.setPrezime("A"));
    }

    @Test
    public void testSetPrezimeNULL() {
        assertThrows(java.lang.NullPointerException.class,
                () -> m.setPrezime(null));
    }

    @Test
    public void testEquals() {

        Menadzer menadzer1 = new Menadzer(1, "user123", "pass123", "Marko", "Markovic");
        Menadzer menadzer2 = new Menadzer(1, "user123", "pass123", "Marko", "Markovic");
        assertTrue(menadzer1.equals(menadzer2));
        assertTrue(menadzer1.equals(menadzer1));
        menadzer2.setUsername("user12");
        assertFalse(menadzer1.equals(menadzer2));
        assertFalse(menadzer1.equals(new Klijent()));
        assertFalse(menadzer1.equals(null));

    }

    @Test
    public void testToString() {
        m = new Menadzer(1, "user123", "pass123", "Marko", "Markovic");
        String expected = "Menadzer{id=1, username=user123, password=pass123, ime=Marko, prezime=Markovic}";
        assertEquals(expected, m.toString());
    }

    @Test
    public void testVratiNazivTabele() {

        assertEquals("menadzer", m.vratiNazivTabele());
    }

    @Test
    public void testVratiListu() throws SQLException, Exception {

        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true, true, false);
        when(rs.getInt("menadzer.menadzerId")).thenReturn(1).thenReturn(2);
        when(rs.getString("menadzer.ime")).thenReturn("Marko").thenReturn("Ana");
        when(rs.getString("menadzer.prezime")).thenReturn("Markovic").thenReturn("Jovanovic");
        when(rs.getString("menadzer.username")).thenReturn("marko").thenReturn("ana");
        when(rs.getString("menadzer.password")).thenReturn("marko").thenReturn("ana");

        Menadzer m1 = new Menadzer(1, "marko", "marko", "Marko", "Markovic");
        Menadzer m2 = new Menadzer(2, "ana", "ana", "Ana", "Jovanovic");

        List<OpstiDomenskiObjekat> lista = m.vratiListu(rs);

        assertEquals(2, lista.size());
        assertTrue(lista.get(0) instanceof Menadzer);
        assertTrue(lista.get(1) instanceof Menadzer);
        assertEquals(lista.get(0), m1);
        assertEquals(lista.get(1), m2);
        assertThrows(java.lang.Exception.class,
                () -> m.vratiListu(null));

    }

    @Test
    public void testVratiKoloneZaInsert() {

        assertEquals(null, m.vratiKoloneZaInsert());
    }

    @Test
    public void testVratiVrednostiZaUpdate() {

        assertEquals(null, m.vratiVrednostiZaUpdate());
    }

    @Test
    public void testVratiVrednostiZaInsert() {
        assertEquals(null, m.vratiVrednostiZaInsert());
    }

    @Test
    public void testVratiPrimarniKljuc() {
        m = new Menadzer(1, "Marko", "Markovic", "marko", "marko");
        assertEquals("menadzer.username=Marko", m.vratiPrimarniKljuc());
    }

}
