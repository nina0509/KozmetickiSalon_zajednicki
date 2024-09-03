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
 * Predstavlja popust u sistemu kozmetičkog salona. Sadrzi podatke o klijentu koji je ostvario popust, 
 * usluzi na koju se popust odnosi, broj rezervacija usluge i popust u procentima.
 *  
 * Implementira interfejs OpstiDomenskiObjekat, koji definiše metode za 
 * operacije nad bazom podataka.
 * 
 * @author Nikolina Baros
 */
public class Popust implements OpstiDomenskiObjekat {

    /**
     * Klijent koji je ostvario popust.
     * 
     */
    private Klijent klijent;
    /** 
     * Usluga na koju se popust odnosi. 
     */
    private Usluga usluga;
     /** 
      * Broj rezervacija za uslugu na koju se popust odnosi.
      */
    private int brojRezUsluge;
    /** 
      * Iznos popusta izrazen u procentima.
      */
    private int popust;

     
    /**
     *Podrazumevani, prazan konstruktor za kreiranje nove instance klase Popust.
     */
    
    public Popust() {
    }

      
    /**
     * Konstruktor sa parametrima za kreiranje novog Popusta.
     * 
     * @param klijent Klijent koji je ostvario popust.
     * @param usluga Usluga na koju se odnosi popust.
     * @param brojRezUsluge Broj rezervacija za uslugu.
     * @param popust Iznos popusta u procentima.
     */
     
    public Popust(Klijent klijent, Usluga usluga, int brojRezUsluge, int popust) {
        this.klijent = klijent;
        this.usluga = usluga;
        this.brojRezUsluge = brojRezUsluge;
        this.popust = popust;
    }

     /**
     * Vraca klijenta koji je ostvario popust.
     * @return Klijent koji je ostvario popust kao instanca klase Klijent.
     */
    
    public Klijent getKlijent() {
        return klijent;
    }
/**
     * Postavlja klijenta koji je ostvario popust na prosledjenu vrednost.
     * 
     * @param klijent Novi klijent koji je ostvario popust kao instanca klase Klijent.
     */
    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }
    
/**
     * Vraca uslugu na koju se popust odnosi.
     * @return Usluga na koju se popust odnosi kao instanca klase Usluga.
     */
    public Usluga getUsluga() {
        return usluga;
    }

    /**
     * Postavlja uslugu na koju se popust odnosi na prosledjenu vrednost.
     * 
     * @param usluga Nova usluga na koju se popust odnosi kao instanca klase Usluga.
     */
    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    
     /**
     * Vraca broj rezervacija za uslugu i klijenta na koje se popust odnosi.
     * @return Broj rezervacija za uslugu i klijenta na koje se popust odnosi kao ceo broj.
     */
    public int getBrojRezUsluge() {
        return brojRezUsluge;
    }

    
    /**
     * Postavlja broj rezervacija za uslugu i klijenta na koje se popust odnosi na prosledjenu vrednost.
     * 
     * @param brojRezUsluge Novi broj rezervacija za uslugu i klijenta na koje se popust odnosi kao ceo broj.
     */
    public void setBrojRezUsluge(int brojRezUsluge) {
        this.brojRezUsluge = brojRezUsluge;
    }

    
    /**
     * Vraca iznos popusta u procentima.
     * @return Iznos popusta u procentima.
     */
    public int getPopust() {
        return popust;
    }
 /**
     * Postavlja iznos popusta na prosledjenu vrednost.
     * @param popust Novo iznos popusta u procentima.
     */
    public void setPopust(int popust) {
        this.popust = popust;
    }

    @Override
    public String toString() {
        return "Popust{" + "klijent=" + klijent + ", usluga=" + usluga + ", brojRezUsluge=" + brojRezUsluge + ", popust=" + popust + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Popust other = (Popust) obj;
        if (!Objects.equals(this.klijent, other.klijent)) {
            return false;
        }
        return Objects.equals(this.usluga, other.usluga);
    }

    
     /**
     * Vraca naziv tabele u bazi podataka koja odgovara klasi Popust.
     * 
     * @return Naziv tabele "popust" kao String.
     */
    @Override
    public String vratiNazivTabele() {
        return "popust";
    }

    /**
     * Kreira listu popusta na osnovu rezultata upita iz baze podataka.
     * 
     * @param rs Rezultat upita iz baze podataka u obliku ResultSet-a.
     * @return Lista popusta kreirana na osnovu rezultata upita.
     * @throws Exception u slucaju greske tokom obrade rezultata upita.
     */
    
    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {

        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int klijentId = rs.getInt("klijent.klijentId");
            String ime = rs.getString("klijent.ime");
            String prezime = rs.getString("klijent.prezime");
            String brtel = rs.getString("klijent.brTel");
            java.sql.Date datRodj = rs.getDate("klijent.datRodj");

            java.util.Date datum = new java.util.Date(datRodj.getTime());

            Klijent k = new Klijent(klijentId, ime, prezime, brtel, datum);

            int uslugaId = rs.getInt("usluga.uslugaId");
            String naziv = rs.getString("usluga.naziv");
            int trajanje = rs.getInt("usluga.trajanje");
            int cena = rs.getInt("usluga.cena");

            TipUsluge tip = new TipUsluge();
            int tipId = rs.getInt("tipusluge.tipId");
            String nazivu = rs.getString("tipusluge.naziv");
            tip.setTipId(tipId);
            tip.setNaziv(nazivu);

            Usluga u = new Usluga(uslugaId, naziv, trajanje, cena, tip);

            int brojRez = rs.getInt("popust.brojRezUsluge");
            int pop = rs.getInt("popust.popust");

            Popust p = new Popust(k, u, brojRez, pop);
            lista.add(p);

        }

        return lista;

    }

    
    /**
     * Vraca nazive kolona koje se koriste prilikom unosa podataka u tabelu Popust u bazi.
     * 
     * @return Nazivi kolona tabele popust kao String.
     */
    @Override
    public String vratiKoloneZaInsert() {
        return "klijentId,uslugaId,brojRezUsluge,popust";
    }

    /**
     * Vraca vrednosti koje se koriste prilikom azuriranja podataka u tabeli Popust u bazi.
     * 
     * @return Vrednosti za azuriranje podataka u tabeli Popust kao String.
     */
    @Override
    public String vratiVrednostiZaUpdate() {

        return "klijentId=" + klijent.getKlijentId() + ", uslugaId=" + usluga.getUslugaId() + ", brojRezUsluge=" + brojRezUsluge + ", popust=" + popust;

    }
/**
     * Vraca vrednosti koje se koriste prilikom unosa podataka u tabelu Popust u bazi.
     * 
     * @return Vrednosti za unos podataka u tabelu Popust u bazi kao String.
     */
    @Override
    public String vratiVrednostiZaInsert() {
        return "" + klijent.getKlijentId() + "," + usluga.getUslugaId() + "," + brojRezUsluge + "," + popust;
    }

    /**
     * Vraca primarni kljuc koji se koristi za identifikaciju zapisa u tabeli Popust u bazi .
     * 
     * @return Primarni kljuc tabele popust kao String.
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "popust.klijentId=" + klijent.getKlijentId() + " AND popust.uslugaId=" + usluga.getUslugaId();
    }

   
}
