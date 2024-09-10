/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;

import com.google.gson.annotations.Expose;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja stavku statistike koja sadrži informacije o broju rezervacija
 * određene usluge unutar određene godine.
 * Stavka sadrzi podatke o statistici na koju se odnosi, usluge na koju se odnosi i broja rezervacija usluge.
 * 
 * 
 * Implementira interfejs OpstiDomenskiObjekat, koji definiše metode za 
 * operacije nad bazom podataka.
 * 
 * @author Nikolina Baros
 */
public class StavkaStatistike implements OpstiDomenskiObjekat{
    
    /** 
     * Statistika kojoj pripada stavka statistike.
     */
    
    Statistika statistika;
    /** 
     * Usluga na koju se odnosi stavka statistike.
     */
    @Expose
    Usluga usluga;
    /** 
     * Broj rezervacija usluge u datoj godini.
     */
    @Expose
    int brojRezUsluge=0;

    /**
     * Kreira novu instancu klase StavkaStatistike sa zadatom statistikom, uslugom
     * i brojem rezervacija usluge.
     * 
     * @param statistika Statistika kojoj pripada stavka statistike.
     * @param usluga Usluga koja se prati u statistici.
     * @param broj Broj rezervacija za uslugu.
     */
    public StavkaStatistike(Statistika statistika, Usluga usluga, int broj) {
        this.statistika = statistika;
        this.usluga = usluga;
        this.brojRezUsluge=broj;
    }

     /**
     *Podrazumevani, prazan konstruktor za kreiranje nove instance klase StavkaStatistike.
     */
    public StavkaStatistike() {
    }

    
    /**
     * Vraća statistiku kojoj ova stavka pripada.
     * 
     * @return Statistika kojoj ova stavka pripada kao instanca klase Statistika.
     */
    public Statistika getStatistika() {
        return statistika;
    }

    /**
     * Postavlja statistiku kojoj ova stavka pripada.
     * 
     * @param statistika Nova statistika kojoj ova stavka pripada kao instanca klase Statistika.
     */
    public void setStatistika(Statistika statistika) {
        this.statistika = statistika;
    }

     /**
     * Vraća uslugu na koju se odnosi stavka statistike.
     * 
     * @return Usluga na koju se odnosi stavka statistike kao instanca klase Usluga.
     */
    public Usluga getUsluga() {
        return usluga;
    }

    
     /**
     * Postavlja uslugu na koju se odnosi stavka statistike na prosledjenu vrednost.
     * 
     * @param usluga Nova usluga a koju se odnosi stavka statistike kao instanca klase Usluga.
     */
    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    
     /**
     * Vraća broj rezervacija za uslugu unutar ove stavke statistike.
     * 
     * @return Broj rezervacija za uslugu kao ceo broj.
     */
    public int getBrojRezUsluge() {
        return brojRezUsluge;
    }

    
     /**
     * Postavlja broj rezervacija za uslugu unutar ove stavke statistike na prosledjenu vrednost.
     * 
     * @param brojRezUsluge Novi broj rezervacija za uslugu kao ceo broj.
     */
    public void setBrojRezUsluge(int brojRezUsluge) {
        this.brojRezUsluge = brojRezUsluge;
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
        final StavkaStatistike other = (StavkaStatistike) obj;
        if (!Objects.equals(this.statistika, other.statistika)) {
            return false;
        }
        return Objects.equals(this.usluga, other.usluga);
    }

    @Override
    public String toString() {
        return "StavkaStatistike{godina=" +statistika.getGodina() + ", usluga=" + usluga + ", brojRezUsluge=" + brojRezUsluge + '}';
    }

    
    
    
    
    
 /**
     * Vraca naziv tabele u bazi podataka koja odgovara klasi StavkaStatistike.
     * 
     * @return Naziv tabele "stavkastatistike" kao String.
     */
    @Override
    public String vratiNazivTabele() {
   
        return "stavkastatistike";

    }

    /**
     * Kreira listu stavki statistike na osnovu rezultata upita iz baze podataka.
     * 
     * @param rs Rezultat upita iz baze podataka u obliku ResultSet-a.
     * @return Lista stavki statistike kreirana na osnovu rezultata upita.
     * @throws Exception u slucaju greske tokom obrade rezultata upita.
     */
    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {

 List<OpstiDomenskiObjekat> stavke = new ArrayList<>();
        while (rs.next()) {
           
            int brRezUsl = rs.getInt("stavkastatistike.brojRezUsluge");

            //STATISTIKA
            int godinaS = rs.getInt("statistika.godina");
            int UkRez = rs.getInt("statistika.ukupnoRez");
            Statistika s=new Statistika(godinaS, UkRez);
            

            //USLUGA
            int uslugaId = rs.getInt("usluga.uslugaId");
            String naziv = rs.getString("usluga.naziv");
            int trajanje = rs.getInt("usluga.trajanje");
            int cenaUsluge = rs.getInt("usluga.cena");

            //TIP USLUGE
            TipUsluge tip = new TipUsluge();
            int tipId = rs.getInt("tipusluge.tipId");
            String nazivu = rs.getString("tipusluge.naziv");
            tip.setTipId(tipId);
            tip.setNaziv(nazivu);

            Usluga u = new Usluga(uslugaId, naziv, trajanje, cenaUsluge, tip);

            StavkaStatistike ss=new StavkaStatistike(s, u, brRezUsl);
            stavke.add(ss);
        }

        return stavke;
        

    }

    
    /**
     * Vraca nazive kolona koje se koriste prilikom unosa podataka u tabelu StavkaStatistike u bazi.
     * 
     * @return Nazivi kolona tabele stavkastatistike kao String.
     */
    @Override
    public String vratiKoloneZaInsert() {

         return "godina,uslugaId,brojRezUsluge";

    }

    
    /**
     * Vraca vrednosti koje se koriste prilikom azuriranja podataka u tabeli StavkaStatistike u bazi.
     * 
     * @return Vrednosti za azuriranje podataka u tabeli stavkastatistike kao String.
     */
    @Override
    public String vratiVrednostiZaUpdate() {
 return "godina=" + statistika.getGodina() + ", uslugaId=" + usluga.getUslugaId() + ", brojRezUsluge=" + brojRezUsluge ;    }

    
    
     /**
     * Vraca vrednosti koje se koriste prilikom unosa podataka u tabelu StavkaStatistike u bazi.
     * 
     * @return Vrednosti za unos podataka u tabelu stavkastatistike u bazi kao String.
     */
    
    @Override
    public String vratiVrednostiZaInsert() {
        
 return "" + statistika.getGodina() + "," + usluga.getUslugaId() + "," + brojRezUsluge;    }

    
      /**
     * Vraca primarni kljuc koji se koristi za identifikaciju zapisa u tabeli StavkaStatistike u bazi .
     * 
     * @return Primarni kljuc tabele stavkastatistike kao String.
     */
    @Override
    public String vratiPrimarniKljuc() {
     
            return "stavkastatistike.godina=" + statistika.getGodina() + " AND stavkastatistike.uslugaId=" + usluga.getUslugaId();

    }
    
}
