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
 * Predstavlja uslugu sa jedinstvenim identifikatorom, nazivom usluge, trajanjem, cenom i tipom usluge.
 * 
 * Implementira interfejs OpstiDomenskiObjekat, koji definiše metode za 
 * operacije nad bazom podataka.
 * 
 * @author Nikolina Baros
 */
public class Usluga implements OpstiDomenskiObjekat {

    /**
 * Jedinstveni identifikator usluge.
 */
    private int uslugaId;
    
    /**
 * Naziv usluge.
 */
    private String naziv;
    
    /**
 * Trajanje usluge u minutima.
 */
    private int trajanje;
    
    /**
 * Cena usluge.
 */
    private int cena;
    /**
 * Tip usluge, kao što je manikir, pedikir...
 */
    private TipUsluge tip;

    /**
     * Kreira novu instancu klas Usluga bez zadatih vrednosti.
     */
    public Usluga() {
    }

     /**
     * Kreira novu uslugu sa zadatim parametrima.
     * 
     * @param uslugaId Identifikator usluge.
     * @param naziv Naziv usluge.
     * @param trajanje Trajanje usluge u minutima.
     * @param cena Cena usluge.
     * @param tip Tip usluge.
     */
    public Usluga(int uslugaId, String naziv, int trajanje, int cena, TipUsluge tip) {
        this.uslugaId = uslugaId;
        this.naziv = naziv;
        this.trajanje = trajanje;
        this.cena = cena;
        this.tip = tip;
    }

    /**
     * Vraća identifikator usluge.
     * 
     * @return Identifikator usluge kao ceo broj.
     */
    public int getUslugaId() {
        return uslugaId;
    }

    /**
     * Postavlja identifikator usluge na prosledjenu vrednost.
     * 
     * @param uslugaId Novi identifikator usluge kao ceo broj.
     */
    public void setUslugaId(int uslugaId) {
        this.uslugaId = uslugaId;
    }
/**
     * Vraća naziv usluge.
     * 
     * @return Naziv usluge kao String.
     */
    public String getNaziv() {
        return naziv;
    }

      /**
     * Postavlja naziv usluge na prosledjenu vrednost.
     * 
     * @param naziv Novi naziv usluge kao String.
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * Vraća trajanje usluge u minutima.
     * 
     * @return Trajanje usluge u minutima kao ceo broj.
     */
    public int getTrajanje() {
        return trajanje;
    }

     /**
     * Postavlja trajanje usluge u minutima na prosledjenu vrednost.
     * 
     * @param trajanje Novo trajanje usluge u minutima kao ceo broj.
     */
    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    /**
     * Vraća cenu usluge.
     * 
     * @return Cena usluge kao ceo broj.
     */
    public int getCena() {
        return cena;
    }

      /**
     * Postavlja cenu usluge na prosledjenu vrednost.
     * 
     * @param cena Nova cena usluge kao ceo broj.
     */
    public void setCena(int cena) {
        this.cena = cena;
    }

    /**
     * Vraća tip usluge.
     * 
     * @return Tip usluge kao instanca klase TipUsluge.
     */
    public TipUsluge getTip() {
        return tip;
    }

    /**
     * Postavlja tip usluge na prosledjenu vrednost.
     * 
     * @param tip Novi tip usluge kao instanca klase TipUsluge.
     */
    public void setTip(TipUsluge tip) {
        this.tip = tip;
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
        final Usluga other = (Usluga) obj;
        if (this.uslugaId != other.uslugaId) {
            return false;
        }
      
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String toString() {
        return naziv;
    }

    /**
     * Vraca naziv tabele u bazi podataka koja odgovara klasi Usluga.
     * 
     * @return Naziv tabele "usluga" kao String.
     */
    @Override
    public String vratiNazivTabele() {
        return "usluga";
    }

    
    /**
     * Kreira listu usluga na osnovu rezultata upita iz baze podataka.
     * 
     * @param rs Rezultat upita iz baze podataka u obliku ResultSet-a.
     * @return Lista usluga kreirana na osnovu rezultata upita.
     * @throws Exception u slucaju greske tokom obrade rezultata upita.
     */
    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {

        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
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
            lista.add(u);
        }

        return lista;
    }

    
     /**
     * Vraca nazive kolona koje se koriste prilikom unosa podataka u tabelu Usluga u bazi.
     * 
     * @return Nazivi kolona tabele usluga kao String.
     */
    @Override
    public String vratiKoloneZaInsert() {
        return "naziv,trajanje,cena,tipId";
    }

     /**
     * Vraca vrednosti koje se koriste prilikom azuriranja podataka u tabeli Usluga u bazi.
     * 
     * @return Vrednosti za azuriranje podataka u tabeli usluga kao String.
     */
    @Override
    public String vratiVrednostiZaUpdate() {
        return "naziv='" + naziv + "', trajanje=" + trajanje + ", cena=" + cena + ", tipId=" + tip.getTipId();
    }

     /**
     * Vraca vrednosti koje se koriste prilikom unosa podataka u tabelu Usluga u bazi.
     * 
     * @return Vrednosti za unos podataka u tabelu usluga u bazi kao String.
     */
    
    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + naziv + "'," + trajanje + "," + cena + "," + tip.getTipId();
    }

    /**
     * Vraca primarni kljuc koji se koristi za identifikaciju zapisa u tabeli Usluga u bazi .
     * 
     * @return Primarni kljuc tabele usluga kao String.
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "usluga.uslugaId=" + uslugaId;
    }

  
}
