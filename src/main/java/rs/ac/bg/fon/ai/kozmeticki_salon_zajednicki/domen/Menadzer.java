/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja menadzer u sistemu kozmetičkog salona sa id-jem, korisnickim
 * imenom, lozinkom, imenom i prezimenom.
 *
 * Implementira interfejs OpstiDomenskiObjekat, koji definiše metode za
 * operacije nad bazom podataka.
 *
 * @author Nikolina Baros
 */
public class Menadzer implements OpstiDomenskiObjekat {

    /**
     * Jedinstveni identifikator menadžera.
     */
    private int id;

    /**
     * Korisničko ime menadžera.
     */
    private String username;
    /**
     * Lozinka menadžera.
     */
    private String password;
    /**
     * Ime menadžera.
     */
    private String ime;
    /**
     * Prezime menadžera.
     */
    private String prezime;

    /**
     * Podrazumevani, prazan konstruktor za kreiranje nove instance klase
     * Menadzer.
     */
    public Menadzer() {
    }

    /**
     * Konstruktor sa parametrima za kreiranje novog Menadzera.
     *
     * @param id Jedinstveni identifikator menadžera.
     * @param username Korisničko ime menadžera.
     * @param password Lozinka menadžera.
     * @param ime Ime menadžera.
     * @param prezime Prezime menadžera.
     */
    public Menadzer(int id, String username, String password, String ime, String prezime) {
        this.setId(id);
        this.setUsername(username);
        this.setPassword(password);
        this.setIme(ime);
        this.setPrezime(prezime);
    }

    /**
     * Vraća jedinstveni identifikator menadžera.
     *
     * @return Jedinstveni identifikator menadžera kao ceo broj.
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja ID menadzera na prosledjenu vrednost.
     *
     * @param id Novi ID menadzera kao ceo broj.
     * @throws java.lang.IllegalArgumentException Ako je novi Id negativan broj.
     */
    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    /**
     * Vraća ime menadžera.
     *
     * @return Ime menadžera kao String.
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime menadzera na prosledjenu vrednost.
     *
     * @param ime Novo ime menadzera kao String.
     * @throws java.lang.IllegalArgumentException Ako novo ime duzine manje od 2
     * karaktera.
     * @throws java.lang.NullPointerException Ako je novo ime null.
     */
    public void setIme(String ime) {
        if (ime == null) {
            throw new NullPointerException();
        }
        if (ime.length() < 2) {
            throw new IllegalArgumentException();
        }
        this.ime = ime;
    }

    /**
     * Vraća prezime menadžera.
     *
     * @return Prezime menadžera kao String.
     */
    public String getPrezime() {

        return prezime;
    }

    /**
     * Postavlja prezime menadzera na prosledjenu vrednost.
     *
     * @param prezime Novo prezime menadzera kao String.
     * @throws java.lang.IllegalArgumentException Ako novo prezime duzine manje
     * od 2 karaktera.
     * @throws java.lang.NullPointerException Ako je novo prezime null.
     *
     */
    public void setPrezime(String prezime) {
        if (prezime == null) {
            throw new NullPointerException();
        }
        if (prezime.length() < 2) {
            throw new IllegalArgumentException();
        }
        this.prezime = prezime;
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
        final Menadzer other = (Menadzer) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    /**
     * Vraća korisničko ime menadžera.
     *
     * @return Korisničko ime menadžera kao String.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Postavlja korisničko ime menadzera na prosledjenu vrednost.
     *
     * @param username Novo korisničko ime menadzera kao String.
     * @throws java.lang.IllegalArgumentException Ako novi username duzine manje
     * od 2 karaktera.
     * @throws java.lang.NullPointerException Ako je novi username null.
     *
     */
    public void setUsername(String username) {
        if (username == null) {
            throw new NullPointerException();
        }
        if (username.length() < 2) {
            throw new IllegalArgumentException();
        }
        this.username = username;
    }

    /**
     * Vraća lozinku menadžera.
     *
     * @return Lozinka menadžera kao String.
     *
     */
    public String getPassword() {
        return password;
    }

    /**
     * Postavlja lozinku menadzera na prosledjenu vrednost.
     *
     * @param password Nova lozinka menadzera kao String.
     * @throws java.lang.IllegalArgumentException Ako nova lozinka duzine manje
     * od 2 karaktera.
     * @throws java.lang.NullPointerException Ako je nova lozinka null.
     *
     */
    public void setPassword(String password) {
        if (password == null) {
            throw new NullPointerException();
        }
        if (password.length() < 2) {
            throw new IllegalArgumentException();
        }
        this.password = password;
    }

    /**
     * Vraca naziv tabele u bazi podataka koja odgovara klasi Menadzer.
     *
     * @return Naziv tabele "menadzer" kao String.
     */
    @Override
    public String vratiNazivTabele() {
        return "menadzer";
    }

    /**
     * Kreira listu menadzera na osnovu rezultata upita iz baze podataka.
     *
     * @param rs Rezultat upita iz baze podataka u obliku ResultSet-a.
     * @return Lista menadžera kreirana na osnovu rezultata upita.
     * @throws Exception u slucaju greske tokom obrade rezultata upita.
     */
    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("menadzer.menadzerId");
            String ime = rs.getString("menadzer.ime");
            String prezime = rs.getString("menadzer.prezime");
            String username = rs.getString("menadzer.username");
            String password = rs.getString("menadzer.password");

            Menadzer m = new Menadzer(id, username, password, ime, prezime);
            lista.add(m);
        }

        return lista;
    }

    /**
     * Vraca nazive kolona koje se koriste prilikom unosa podataka u tabelu
     * Menadžer u bazi.
     *
     * @return Nazivi kolona tabele menadzer kao String.
     */
    @Override
    public String vratiKoloneZaInsert() {
        return null;
    }

    /**
     * Vraca vrednosti koje se koriste prilikom azuriranja podataka u tabeli
     * Menadžer u bazi.
     *
     * @return Vrednosti za azuriranje podataka u tabeli Menadžer kao String.
     */
    @Override
    public String vratiVrednostiZaUpdate() {
        return null;

    }

    /**
     * Vraca vrednosti koje se koriste prilikom unosa podataka u tabelu Menadžer
     * u bazi.
     *
     * @return Vrednosti za unos podataka u tabelu Menadžer u bazi kao String.
     */
    @Override
    public String vratiVrednostiZaInsert() {
        return null;

    }

    /**
     * Vraca primarni kljuc koji se koristi za identifikaciju zapisa u tabeli
     * Menadžer u bazi .
     *
     * @return Primarni kljuc tabele menadžer kao String.
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "menadzer.username=" + username;
    }

    /**
     * Vraća string reprezentaciju menadžer sa imenom i prezimenom.
     *
     * @return String reprezentacija menadžera.
     *
     */
    @Override
    public String toString() {
        return "Menadzer{" + "id=" + id + ", username=" + username + ", password=" + password + ", ime=" + ime + ", prezime=" + prezime + '}';
    }

}
