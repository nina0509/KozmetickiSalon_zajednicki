package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import junit.framework.TestCase;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static junit.framework.Assert.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KlijentTest extends TestCase {

    public Klijent k;

    public KlijentTest(String testName) {

        super(testName);
    }

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        k = new Klijent();
    }

    @AfterEach
    @Override
    public void tearDown() throws Exception {
        k = null;
    }

    @Test
    public void testKonstruktorSaParametrima() {
        Date datumRodj = new Date();
        k = new Klijent(1, "Marko", "Markovic", "123456789", datumRodj);
        assertEquals(1, k.getKlijentId());
        assertEquals("Marko", k.getIme());
        assertEquals("Markovic", k.getPrezime());
        assertEquals("123456789", k.getBrTel());
        assertEquals(datumRodj, k.getDatRodj());
    }

    @Test
    public void testKonstruktorBezParametara() {
        k = new Klijent();
        assertNotNull(k);
    }

    @Test
    public void testSetGetKlijentId() {

        k.setKlijentId(1);
        assertEquals(1, k.getKlijentId());
    }

    @Test
    public void testSetGetKlijentIdNegativan() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> k.setKlijentId(-1));
    }

    @Test
    public void testSetGetIme() {

        k.setIme("Ana");
        assertEquals("Ana", k.getIme());
    }

    @Test
    public void testSetImeKraceOd2() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> k.setIme("A"));
    }

    @Test
    public void testSetImeNULL() {
        assertThrows(java.lang.NullPointerException.class,
                () -> k.setIme(null));
    }

    @Test
    public void testSetGetPrezime() {
        Klijent klijent = new Klijent();
        klijent.setPrezime("Jovanovic");
        assertEquals("Jovanovic", klijent.getPrezime());
    }

    @Test
    public void testSetPrezimeKraceOd2() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> k.setPrezime("A"));
    }

    @Test
    public void testSetPrezimeNULL() {
        assertThrows(java.lang.NullPointerException.class,
                () -> k.setPrezime(null));
    }

    @Test
    public void testSetGetBrTel() {

        k.setBrTel("987654321");
        assertEquals("987654321", k.getBrTel());
    }

    @Test
    public void testSetBrTelKraceOd9() {
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> k.setBrTel(""));
    }

    @Test
    public void testSetBrTelNull() {
        assertThrows(java.lang.NullPointerException.class,
                () -> k.setBrTel(null));
    }

    @Test
    public void testSetGetDatRodj() {
        Date datumRodj = new Date();
        k.setDatRodj(datumRodj);
        assertEquals(datumRodj, k.getDatRodj());
    }

    @Test
    public void testSetDatRodjNull() {
        assertThrows(java.lang.NullPointerException.class,
                () -> k.setDatRodj(null));
    }

    @Test
    public void testSetDatRodjUBuducnosti() {
        Calendar myCalendar = new GregorianCalendar(2025, 2, 11);
        Date datum = myCalendar.getTime();
        assertThrows(java.lang.IllegalArgumentException.class,
                () -> k.setDatRodj(datum));
    }

    @Test
    public void testToString() {
        k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
        String expected = "Marko Markovic";
        assertEquals(expected, k.toString());
    }

    @Test
    public void testEquals() {
        Klijent klijent1 = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
        Klijent klijent2 = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
        Klijent klijent3 = new Klijent(2, "Ana", "Jovanovic", "987654321", new Date());

        assertEquals(klijent1, klijent1);
        assertEquals(klijent1, klijent2);
        assertNotEquals(klijent1, klijent3);
        assertNotEquals(klijent2, klijent3);
        assertNotEquals(klijent1, null);
        assertNotEquals(klijent1, new Menadzer());

    }

    @Test
    public void testVratiNazivTabele() {

        assertEquals("klijent", k.vratiNazivTabele());
    }

    @Test
    public void testVratiListu() throws SQLException, Exception {

        Date datum = new Date();
        java.sql.Date datumSQL = new java.sql.Date(datum.getTime());

        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true, true, false);
        when(rs.getInt("klijent.klijentId")).thenReturn(1).thenReturn(2);
        when(rs.getString("klijent.ime")).thenReturn("Marko").thenReturn("Ana");
        when(rs.getString("klijent.prezime")).thenReturn("Markovic").thenReturn("Jovanovic");
        when(rs.getString("klijent.brTel")).thenReturn("123456789").thenReturn("987654321");
        when(rs.getDate("klijent.datRodj")).thenReturn(datumSQL);

        Klijent k1 = new Klijent(1, "Marko", "Markovic", "123456789", datum);
        Klijent k2 = new Klijent(2, "Ana", "Jovanovic", "987654321", datum);

        List<OpstiDomenskiObjekat> lista = k.vratiListu(rs);

        assertEquals(2, lista.size());
        assertTrue(lista.get(0) instanceof Klijent);
        assertTrue(lista.get(1) instanceof Klijent);
        assertEquals(lista.get(0), k1);
        assertEquals(lista.get(1), k2);

        assertThrows(java.lang.Exception.class,
                () -> k.vratiListu(null));

    }

    @Test
    public void testVratiKoloneZaInsert() {

        assertEquals("ime,prezime,brTel,datRodj", k.vratiKoloneZaInsert());
    }

    @Test
    public void testVratiVrednostiZaUpdate() {
        k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
        java.sql.Date datum = new java.sql.Date(k.getDatRodj().getTime());
        String expected = "ime='Marko', prezime='Markovic', brTel='123456789', datRodj='" + datum + "'";
        assertEquals(expected, k.vratiVrednostiZaUpdate());
    }

    @Test
    public void testVratiVrednostiZaInsert() {
        k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
        java.sql.Date datum = new java.sql.Date(k.getDatRodj().getTime());
        String expected = "'Marko','Markovic','123456789','" + datum + "'";
        assertEquals(expected, k.vratiVrednostiZaInsert());
    }

    @Test
    public void testVratiPrimarniKljuc() {
        k = new Klijent(1, "Marko", "Markovic", "123456789", new Date());
        assertEquals("klijent.klijentId=1", k.vratiPrimarniKljuc());
    }

    @Test
    public void testSerijalizacija() {

        LocalDateTime localDateTime = LocalDateTime.of(2024, 9, 5, 14, 30, 0); // Godina, mesec, dan, sati, minuti, sekunde
        Date datum = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Klijent k1 = new Klijent(1, "Marko", "Markovic", "123456789", datum);
        Klijent k2 = new Klijent(2, "Ana", "Jovanovic", "987654321", datum);

        List<Klijent> rez = new ArrayList<>();
        rez.add(k1);
        rez.add(k2);

        String a = k.serijalizujJSON(rez);
        System.out.println(a);
        String expected = "[{\"klijentId\":1,\"ime\":\"Marko\",\"prezime\":\"Markovic\",\"brTel\":\"123456789\",\"datRodj\":\"Sep 5, 2024, 2:30:00 PM\"},{\"klijentId\":2,\"ime\":\"Ana\",\"prezime\":\"Jovanovic\",\"brTel\":\"987654321\",\"datRodj\":\"Sep 5, 2024, 2:30:00 PM\"}]";

        assertEquals(expected, a);

        List<Klijent> rez1 = k.deserijalizujJSON("src/test/resources/klijenti.json");
        assertEquals(rez1.get(0), rez.get(0));
        assertEquals(rez1.get(1), rez.get(1));

    }

    @Test
    public void testDeserijalizacija() {

        LocalDateTime localDateTime = LocalDateTime.of(2024, 9, 5, 14, 30, 0); // Godina, mesec, dan, sati, minuti, sekunde
        Date datum = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Klijent k1 = new Klijent(1, "Marko", "Markovic", "123456789", datum);
        Klijent k2 = new Klijent(2, "Ana", "Jovanovic", "987654321", datum);

        List<Klijent> rez1 = new ArrayList<>();
        rez1.add(k1);
        rez1.add(k2);

        List<Klijent> rez = k.deserijalizujJSON("src/test/resources/klijenti.json");

        assertEquals(2, rez.size());
        assertEquals(rez1.get(0), rez.get(0));
        assertEquals(rez1.get(1), rez.get(1));

    }

}
