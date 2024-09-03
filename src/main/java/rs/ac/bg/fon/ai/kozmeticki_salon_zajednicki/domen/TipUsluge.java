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
 * Predstavlja tip usluge koji sadrzi podatke o identifikatoru i nazivu tipa.
 * 
 * Implementira interfejs OpstiDomenskiObjekat, koji definiše metode za 
 * operacije nad bazom podataka.
 * 
 * @author Nikolina Baros
 */

public class TipUsluge implements OpstiDomenskiObjekat {

    /** 
     * Jedinstveni identifikator tipa usluge.
     */
    private int tipId;
    /** 
     *  Naziv tipa usluge.
     */
    private String naziv;

   
     /**
     * Kreira novi tip usluge sa zadatim identifikatorom i nazivom.
     * 
     * @param tipId Identifikator tipa usluge.
     * @param naziv Naziv tipa usluge.
     */
    public TipUsluge(int tipId, String naziv) {
        this.tipId = tipId;
        this.naziv = naziv;
    }
    
    
    /**
     * Kreira novi tip usluge bez zadatih vrednosti.
     */
 public TipUsluge() {
    }

 
    /**
     * Vraća jedinstveni identifikator tipa usluge.
     * 
     * @return Identifikator tipa usluge kao ceo broj.
     */
    public int getTipId() {
        return tipId;
    }

    /**
     * Postavlja identifikator tipa usluge na prosledjenu vrednost.
     * 
     * @param tipId Identifikator tipa usluge kao ceo broj.
     */
    public void setTipId(int tipId) {
        this.tipId = tipId;
    }

     /**
     * Vraća naziv tipa usluge.
     * 
     * @return Naziv tipa usluge kao string.
     */
    public String getNaziv() {
        return naziv;
    }
/**
     * Postavlja naziv tipa usluge na prosledjenu vrednost.
     * 
     * @param naziv Naziv tipa usluge kao String.
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
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
        final TipUsluge other = (TipUsluge) obj;
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String toString() {
        return naziv;
    }
 /**
     * Vraca naziv tabele u bazi podataka koja odgovara klasi TipUsluge.
     * 
     * @return Naziv tabele "tipusluge" kao String.
     */
    @Override
    public String vratiNazivTabele() {
        return "tipusluge";
    }

       
 /**
     * Kreira listu tipova usluge na osnovu rezultata upita iz baze podataka.
     * 
     * @param rs Rezultat upita iz baze podataka u obliku ResultSet-a.
     * @return Lista tipova usluge kreirana na osnovu rezultata upita.
     * @throws Exception u slucaju greske tokom obrade rezultata upita.
     */
    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {

        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int tipId = rs.getInt("tipusluge.tipId");
            String naziv = rs.getString("tipusluge.naziv");

            TipUsluge k = new TipUsluge(tipId, naziv);
            lista.add(k);
        }

        return lista;
    }

     /**
     * Vraca nazive kolona koje se koriste prilikom unosa podataka u tabelu TipUsluge u bazi.
     * 
     * @return Nazivi kolona tabele tipusluge kao String.
     */
    @Override
    public String vratiKoloneZaInsert() {
        return "naziv";
    }

    
    /**
     * Vraca vrednosti koje se koriste prilikom azuriranja podataka u tabeli TipUsluge u bazi.
     * 
     * @return Vrednosti za azuriranje podataka u tabeli tipusluge kao String.
     */
    @Override
    public String vratiVrednostiZaUpdate() {
        return "naziv='" + naziv + "'";
    }

    
    /**
     * Vraca vrednosti koje se koriste prilikom unosa podataka u tabelu TipUsluge u bazi.
     * 
     * @return Vrednosti za unos podataka u tabelu tipusluge u bazi kao String.
     */
    
    @Override
    public String vratiVrednostiZaInsert() {
        return naziv;
    }

    
      /**
     * Vraca primarni kljuc koji se koristi za identifikaciju zapisa u tabeli TipUsluge u bazi .
     * 
     * @return Primarni kljuc tabele tipusluge kao String.
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "tipusluge.tipId=" + tipId;
    }


}
