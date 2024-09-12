/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja rezervaciju u sistemu kozmetičkog salona. Klasa sadrzi podatke o
 * klijentu za koga se pravi rezervacija, datum rezervacije, ukupnoj ceni
 * rezervacije, status pojavljivanja i listu stavki rezervacije.
 *
 * Implementira interfejs OpstiDomenskiObjekat, koji definiše metode za
 * operacije nad bazom podataka.
 *
 * @author Nikolina Baros
 */
public class Rezervacija implements OpstiDomenskiObjekat {

    /**
     * Jedinstveni identifikator rezervacije.
     */
    private int rezervacijaId;
    /**
     * Datum za koji je kreirana rezervacija.
     */
    private Date datum;
    /**
     * Ukupna cena rezervacije.
     */
    private int ukupnaCena;

    /**
     * Status koji pokazuje da li se klijent pojavio na zakazanom terminu.
     */
    private boolean pojavljivanje;
    /**
     * Klijent koji je napravio rezervaciju.
     */
    private Klijent klijent;

    /**
     * Lista stavki rezervacije koje su deo ove rezervacije.
     */
    private List<StavkaRezervacije> stavke = new ArrayList<>();

    /**
     * Podrazumevani, prazan konstruktor za kreiranje nove instance klase
     * Rezervacija.
     */
    public Rezervacija() {
    }

    /**
     * Konstruktor koji inicijalizuje objekat klase Rezervacija sa prosleđenim
     * vrednostima.
     *
     * @param rezervacijaId Jedinstveni identifikator rezervacije.
     * @param datum Datum za koji je kreirana rezervacija.
     * @param ukupnaCena Ukupna cena rezervacije.
     * @param pojavljivanje Status pojavljivanja klijenta na zakazanom terminu.
     * @param klijent Klijent koji je napravio rezervaciju.
     */
    public Rezervacija(int rezervacijaId, Date datum, int ukupnaCena, boolean pojavljivanje, Klijent klijent) {

        this.setRezervacijaId(rezervacijaId);
        this.setDatum(datum);
        this.setUkupnaCena(ukupnaCena);
        this.setPojavljivanje(pojavljivanje);
        this.setKlijent(klijent);

    }

    /**
     * Vraća jedinstveni identifikator rezervacije.
     *
     * @return Jedinstveni identifikator rezervacije.
     */
    public int getRezervacijaId() {
        return rezervacijaId;
    }

    /**
     * Postavlja jedinstveni identifikator rezervacije na prosledjenu vrednost.
     *
     * @param rezervacijaId Novi jedinstveni identifikator rezervacije kao ceo
     * broj.
     * @throws java.lang.IllegalArgumentException Ako je novi Id negativan broj.
     */
    public void setRezervacijaId(int rezervacijaId) {
        if (rezervacijaId < 0) {
            throw new IllegalArgumentException();
        }
        this.rezervacijaId = rezervacijaId;
    }

    /**
     * Vraća datum za koji je kreirana rezervacija.
     *
     * @return Datum za koji je kreirana rezervacija kao Date.
     */
    public Date getDatum() {
        return datum;
    }

    /**
     * Postavlja datum za koji je kreirana rezervacija.
     *
     * @param datum Novi datum za koji je kreirana rezervacija kao Date.
     * @throws java.lang.IllegalArgumentException Ako je novi datum u proslosti.
     * @throws java.lang.NullPointerException Ako je novi datum null.
     */
    public void setDatum(Date datum) {
        if (datum == null) {
            throw new NullPointerException();
        }
        if (datum.before(new Date())) {
            throw new IllegalArgumentException();
        }
        this.datum = datum;
    }

    /**
     * Vraća ukupnu cenu rezervacije.
     *
     * @return Ukupna cena rezervacije kao ceo broj.
     */
    public int getUkupnaCena() {
        return ukupnaCena;
    }

    /**
     * Postavlja ukupnu cenu rezervacije na prosledjenu vrednost.
     *
     * @param ukupnaCena Nova ukupna cena rezervacije kao ceo broj.
     * @throws java.lang.IllegalArgumentException Ako je nova ukupna cena
     * negativan broj.
     */
    public void setUkupnaCena(int ukupnaCena) {
        if (ukupnaCena < 0) {
            throw new IllegalArgumentException();
        }
        this.ukupnaCena = ukupnaCena;
    }

    /**
     * Vraća status pojavljivanja klijenta na zakazanom terminu.
     *
     * @return true ako se klijent pojavio, false ako se nije pojavio.
     */
    public boolean isPojavljivanje() {
        return pojavljivanje;
    }

    /**
     * Postavlja status pojavljivanja klijenta na zakazanom terminu na
     * prosledjenu vrednost.
     *
     * @param pojavljivanje true ako se klijent pojavio, false ako se nije
     * pojavio.
     */
    public void setPojavljivanje(boolean pojavljivanje) {
        this.pojavljivanje = pojavljivanje;
    }

    /**
     * Vraća klijenta koji je napravio rezervaciju.
     *
     * @return Klijent koji je napravio rezervaciju kao instanca klase Klijent.
     */
    public Klijent getKlijent() {
        return klijent;
    }

    /**
     * Postavlja klijenta koji je napravio rezervaciju na prosledjenu vrednost.
     *
     * @param klijent Klijent koji je napravio rezervaciju kao instanca klase
     * Klijent.
     * @throws java.lang.NullPointerException Ako je novi klijent null.
     */
    public void setKlijent(Klijent klijent) {
        if (klijent == null) {
            throw new NullPointerException();
        }
        this.klijent = klijent;
    }

    /**
     * Vraća listu stavki rezervacije koje su deo ove rezervacije.
     *
     * @return Lista stavki rezervacije kao List sa elementima koji su instanca
     * klase StavkaRezervacije.
     */
    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }

    /**
     * Postavlja listu stavki rezervacije koje su deo ove rezervacije na
     * prosledjenu vrednost.
     *
     * @param stavke Lista stavki rezervacije kao List koju cine elementi koji
     * su instance klase StavkaRezervacije.
     * @throws java.lang.NullPointerException Ako je nova lista stavki null.
     */
    public void setStavke(List<StavkaRezervacije> stavke) {
        if (stavke == null) {
            throw new NullPointerException();
        }
        this.stavke = stavke;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rezervacija other = (Rezervacija) obj;
        if (this.rezervacijaId != other.rezervacijaId) {
            return false;
        }
        return Objects.equals(this.datum, other.datum);
    }

    @Override
    public String toString() {
        return "Rezervacija{" + "rezervacijaId=" + rezervacijaId + ", datum=" + datum + ", ukupnaCena=" + ukupnaCena + ", pojavljivanje=" + pojavljivanje + ", klijent=" + klijent + ", stavke=" + stavke + '}';
    }

    /**
     * Vraca naziv tabele u bazi podataka koja odgovara klasi Rezervacija.
     *
     * @return Naziv tabele "rezervacija" kao String.
     */
    @Override
    public String vratiNazivTabele() {
        return "rezervacija";
    }

    /**
     * Kreira listu rezervacija na osnovu rezultata upita iz baze podataka.
     *
     * @param rs Rezultat upita iz baze podataka u obliku ResultSet-a.
     * @return Lista rezervacija kreirana na osnovu rezultata upita.
     * @throws Exception u slucaju greske tokom obrade rezultata upita.
     */
    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {

        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int rezId = rs.getInt("rezervacija.rezervacijaId");
            int cena = rs.getInt("rezervacija.ukupnaCena");
            boolean poj = rs.getBoolean("rezervacija.pojavljivanje");
            java.sql.Date datum1 = rs.getDate("rezervacija.datum");
            java.util.Date datumNovi = new java.util.Date(datum1.getTime());

            int klijentId = rs.getInt("klijent.klijentId");
            String ime = rs.getString("klijent.ime");
            String prezime = rs.getString("klijent.prezime");
            String brtel = rs.getString("klijent.brTel");
            java.sql.Date datRodj = rs.getDate("klijent.datRodj");
            java.util.Date datumr = new java.util.Date(datRodj.getTime());

            Klijent k = new Klijent(klijentId, ime, prezime, brtel, datumr);
            Rezervacija r = new Rezervacija(rezId, datumNovi, cena, poj, k);
            lista.add(r);
        }

        return lista;
    }

    /**
     * Vraca nazive kolona koje se koriste prilikom unosa podataka u tabelu
     * Rezervacija u bazi.
     *
     * @return Nazivi kolona tabele rezervacija kao String.
     */
    @Override
    public String vratiKoloneZaInsert() {
        return "datum,ukupnaCena,pojavljivanje,klijentId";
    }

    /**
     * Vraca vrednosti koje se koriste prilikom azuriranja podataka u tabeli
     * Rezervacija u bazi.
     *
     * @return Vrednosti za azuriranje podataka u tabeli rezervacija kao String.
     */
    @Override
    public String vratiVrednostiZaUpdate() {
        java.sql.Date datum1 = new java.sql.Date(datum.getTime());
        return "datum='" + datum1 + "', ukupnaCena=" + ukupnaCena + ", pojavljivanje=" + pojavljivanje + ", klijentId=" + klijent.getKlijentId();
    }

    /**
     * Vraca vrednosti koje se koriste prilikom unosa podataka u tabelu
     * rezervacija u bazi.
     *
     * @return Vrednosti za unos podataka u tabelu rezervacija u bazi kao
     * String.
     */
    @Override
    public String vratiVrednostiZaInsert() {
        java.sql.Date datum1 = new java.sql.Date(datum.getTime());
        return "'" + datum1 + "'," + ukupnaCena + "," + pojavljivanje + "," + klijent.getKlijentId();
    }

    /**
     * Vraca primarni kljuc koji se koristi za identifikaciju zapisa u tabeli
     * Rezervacija u bazi .
     *
     * @return Primarni kljuc tabele rezervacija kao String.
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "rezervacija.rezervacijaId=" + rezervacijaId;
    }

}
