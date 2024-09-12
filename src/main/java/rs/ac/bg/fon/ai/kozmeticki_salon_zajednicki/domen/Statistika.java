/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja statistiku u sistemu kozmetičkog salona za određenu godinu. Klasa
 * sadrži informacije o ukupnom broju rezervacija za određenu godinu, kao i
 * listu stavki statistike. Implementira interfejs OpstiDomenskiObjekat, koji
 * definiše metode za rad sa bazom podataka.
 *
 * @author Nikolina Baros
 */
public class Statistika implements OpstiDomenskiObjekat {

    /**
     * Godina na koju se odnose statistički podaci.
     */
    @Expose
    private int godina;

    /**
     * Ukupan broj rezervacija za zadatu godinu.
     */
    @Expose
    private int ukupnoRezervacija;
    /**
     * Lista stavki statistike koje su deo statistike za tu godinu.
     */
    @Expose
    List<StavkaStatistike> stavke = new ArrayList<>();

    /**
     * Podrazumevani, prazan konstruktor bez parametara za kreiranje nove
     * instance klase Statistika.
     */
    public Statistika() {
    }

    /**
     * Konstruktor koji inicijalizuje objekat klase Statistika sa prosleđenim
     * vrednostima.
     *
     * @param godina Godina za koju se prikupljaju statistički podaci.
     * @param ukupnoRezervacija Ukupan broj rezervacija za zadatu godinu.
     */
    public Statistika(int godina, int ukupnoRezervacija) {
        this.setGodina(godina);
        this.setUkupnoRezervacija(ukupnoRezervacija);
    }

    /**
     * Vraća godinu na koju se odnosi statistika.
     *
     * @return Godina na koju se odnosi statistika kao ceo broj.
     */
    public int getGodina() {
        return godina;
    }

    /**
     * Postavlja godinu na koju se odnosi statistika na prosledjenu vrednost.
     *
     * @param godina Nova vrednost godine na koju se odnosi statistika kao ceo
     * broj.
     * @throws java.lang.IllegalArgumentException Ako je nova ukupna cena
     * negativan broj ili veci broj od trenutne godine.
     *
     */
    public void setGodina(int godina) {
        int tren = LocalDate.now().getYear();
        if (godina < 0 || godina > tren) {
            throw new IllegalArgumentException();
        }
        this.godina = godina;
    }

    /**
     * Vraća ukupan broj rezervacija za zadatu godinu.
     *
     * @return Ukupan broj rezervacija za zadatu godinu kao ceo broj.
     */
    public int getUkupnoRezervacija() {
        return ukupnoRezervacija;
    }

    /**
     * Postavlja ukupan broj rezervacija za zadatu godinu na prosledjenu
     * vrednost.
     *
     * @param ukupnoRezervacija Ukupan broj rezervacija za zadatu godinu kao ceo
     * broj.
     * @throws java.lang.IllegalArgumentException Ako je novi broj rezervacija
     * negativan broj.
     *
     */
    public void setUkupnoRezervacija(int ukupnoRezervacija) {
        if (ukupnoRezervacija < 0) {
            throw new IllegalArgumentException();
        }
        this.ukupnoRezervacija = ukupnoRezervacija;
    }

    /**
     * Vraća listu stavki statistike koje su deo statistike.
     *
     * @return Lista stavki statistike tipa List koja sadrzi elemente tipa
     * StavkaStatistike.
     */
    public List<StavkaStatistike> getStavke() {
        return stavke;
    }

    /**
     * Postavlja listu stavki statistike koje su deo statistike na prosledjenu
     * vrednost.
     *
     * @param stavke Nova lista stavki statistike tipa List koja sadrzi elemente
     * tipa StavkaStatistike..
     * @throws java.lang.NullPointerException Ako je nova lista stavki null.
     *
     */
    public void setStavke(List<StavkaStatistike> stavke) {
        if (stavke == null) {
            throw new NullPointerException();
        }
        this.stavke = stavke;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Statistika other = (Statistika) obj;
        return this.godina == other.godina;
    }

    @Override
    public String toString() {
        return "Statistika{" + "godina=" + godina + ", ukupnoRezervacija=" + ukupnoRezervacija + ", stavke=" + stavke + '}';
    }

    /**
     * Vraca naziv tabele u bazi podataka koja odgovara klasi Statistika.
     *
     * @return Naziv tabele "statistika" kao String.
     */
    @Override
    public String vratiNazivTabele() {
        return "statistika";
    }

    /**
     * Kreira listu statistika na osnovu rezultata upita iz baze podataka.
     *
     * @param rs Rezultat upita iz baze podataka u obliku ResultSet-a.
     * @return Lista statistika kreirana na osnovu rezultata upita.
     * @throws Exception u slucaju greske tokom obrade rezultata upita.
     */
    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {

        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int godina1 = rs.getInt("statistika.godina");
            int brojRez1 = rs.getInt("statistika.ukupnoRez");
            Statistika s = new Statistika(godina1, brojRez1);
            lista.add(s);

        }
        return lista;
    }

    /**
     * Vraca nazive kolona koje se koriste prilikom unosa podataka u tabelu
     * Statistika u bazi.
     *
     * @return Nazivi kolona tabele statistika kao String.
     */
    @Override
    public String vratiKoloneZaInsert() {

        return "godina,ukupnoRez";

    }

    /**
     * Vraca vrednosti koje se koriste prilikom azuriranja podataka u tabeli
     * Statistika u bazi.
     *
     * @return Vrednosti za azuriranje podataka u tabeli statistika kao String.
     */
    @Override
    public String vratiVrednostiZaUpdate() {

        return "godina=" + godina + ", ukupnoRez=" + ukupnoRezervacija;

    }

    /**
     * Vraca vrednosti koje se koriste prilikom unosa podataka u tabelu
     * statistika u bazi.
     *
     * @return Vrednosti za unos podataka u tabelu statistika u bazi kao String.
     */
    @Override
    public String vratiVrednostiZaInsert() {

        return godina + "," + ukupnoRezervacija;

    }

    /**
     * Vraca primarni kljuc koji se koristi za identifikaciju zapisa u tabeli
     * statistika u bazi .
     *
     * @return Primarni kljuc tabele statistika kao String.
     */
    @Override
    public String vratiPrimarniKljuc() {

        return "statistika.godina=" + godina;

    }

    /**
     * Vraca JSON reprezentaciju liste statistika i upisuje je u odgovarajuci
     * fajl .
     *
     * @param lista Lista statistika za upisivanje u fajl i kreiranje JSON
     * reprezentacije.
     * @return JSON reprezentacija liste statistika kao String.
     */
    public String serijalizujJSON(List<Statistika> lista) {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String a = null;
        try ( FileWriter writer = new FileWriter("src/test/resources/statistika.json")) {
            a = gson.toJson(lista);
            writer.write(a);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }

    /**
     * Ucitava listu statistika iz JSON fajla sa prosledjene putanje.
     *
     * @param putanja Putanja na kojoj se nalazi JSON fajl sa rezervacijama.
     * @return JSON reprezentacija liste statistika kao String.
     */
    public List<Statistika> deserijalizujJSON(String putanja) {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        List<Statistika> statistike = null;

        try ( FileReader reader = new FileReader(putanja)) {

            Type reservationListType = new TypeToken<List<Statistika>>() {
            }.getType();
            statistike = gson.fromJson(reader, reservationListType);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return statistike;

    }

}
