/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;

import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja stavku rezervacije u sistemu kozmetičkog salona. Klasa sadrzi podatke
 * o rezervaciji na koju se odnosi, rednom broju stavke, usluzi za koju je kreirana stavka,
 * vremenu pocetka i završetka i ceni stavke rezervacije.
 
 *  
 * Implementira interfejs OpstiDomenskiObjekat, koji definiše metode za 
 * operacije nad bazom podataka.
 * 
 * @author Nikolina Baros
 */
public class StavkaRezervacije implements OpstiDomenskiObjekat {

    /**
 * Redni broj stavke unutar rezervacije.
 */
    private int RBStavke;
    /**
 * Rezervacija kojoj ova stavka pripada.
 */
    private Rezervacija rezervacija;
    /**
 * Vreme početka usluge unutar ove stavke rezervacije.
 */
    private LocalTime vremePocetka;
    /**
 * Vreme zavrsetka usluge unutar ove stavke rezervacije.
 */
    private LocalTime vremeZavrsetka;
    
    /**
 * Cena usluge unutar ove stavke rezervacije.
 */
    private int cena;
    /**
 * Usluga koja se pruža unutar ove stavke rezervacije.
 */
    private Usluga usluga;

    
    
/**
 * Konstruktor koji inicijalizuje objekat klase StavkaRezervacije sa svim potrebnim atributima.
 *
 * @param RBStavke Redni broj stavke unutar rezervacije.
 * @param rezervacija Rezervacija kojoj ova stavka pripada.
 * @param vremePocetka Vreme početka usluge unutar ove stavke rezervacije.
 * @param vremeZavrsetka Vreme završetka usluge unutar ove stavke rezervacije.
 * @param cena Cena usluge unutar ove stavke rezervacije.
 * @param usluga Usluga koja se pruža unutar ove stavke rezervacije.
 */
    public StavkaRezervacije(int RBStavke, Rezervacija rezervacija, 
            LocalTime vremePocetka,  LocalTime vremeZavrsetka, int cena,
            Usluga usluga) {
        this.RBStavke = RBStavke;
        this.rezervacija = rezervacija;
        this.vremePocetka = vremePocetka;
        this.vremeZavrsetka = vremeZavrsetka;
        this.cena = cena;
        this.usluga = usluga;
    }

     /**
     *Podrazumevani, prazan konstruktor za kreiranje nove instance klase StavkaRezervacije.
     */
    public StavkaRezervacije() {
    }
    
    /**
 * Vraća rezervaciju kojoj ova stavka pripada.
 * 
 * @return Rezervacija kojoj ova stavka pripada kao instanca klase Rezervacija.
 */
    public Rezervacija getRezervacija() {
        return rezervacija;
    }

  /**
 * Postavlja rezervaciju kojoj ova stavka pripada na prosledjenu vrednost.
 * 
 * @param rezervacija Nova rezervacija kojoj će ova stavka pripadati kao instanca klase Rezervacija.
 */
    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    
  /**
 * Vraća redni broj stavke unutar rezervacije.
 * 
 * @return Redni broj stavke kao ceo broj.
 */
    public int getRBStavke() {
        return RBStavke;
    }

  /**
 * Postavlja redni broj stavke unutar rezervacije na prosledjenu vrednost.
 * 
 * @param RBStavke Redni broj stavke koji treba postaviti kao ceo broj.
 */
    public void setRBStavke(int RBStavke) {
        this.RBStavke = RBStavke;
    }

    /**
 * Vraća vreme početka usluge unutar ove stavke rezervacije.
 * 
 * @return Vreme početka usluge kao LocalTime.
 */
    public LocalTime getVremePocetka() {
        return vremePocetka;
    }

    /**
 * Postavlja vreme početka usluge unutar ove stavke rezervacije na prosledjenu vrednost.
 * 
 * @param vremePocetka Novo vreme početka usluge koje treba postaviti kao LocalTime.
 */
    public void setVremePocetka(LocalTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

  /**
 * Vraća vreme završetka usluge unutar ove stavke rezervacije.
 * 
 * @return Vreme završetka usluge kao LocalTime.
 */
    public LocalTime getVremeZavrsetka() {
        return vremeZavrsetka;
    }

    /**
 * Postavlja vreme završetka usluge unutar ove stavke rezervacije na prosledjenu vrednost.
 * 
 * @param vremeZavrsetka Novo vreme završetka usluge koje treba postaviti kao LocalTime.
 */
    public void setVremeZavrsetka(LocalTime vremeZavrsetka) {
        this.vremeZavrsetka = vremeZavrsetka;
    }

  /**
 * Vraća cenu usluge unutar ove stavke rezervacije.
 * 
 * @return Cena usluge kao ceo broj.
 */
    public int getCena() {
        return cena;
    }

 /**
 * Postavlja cenu usluge unutar ove stavke rezervacije na prosledjenu vrednost.
 * 
 * @param cena Nova cena usluge koja treba da bude postavljena kao ceo broj.
 */
    public void setCena(int cena) {
        this.cena = cena;
    }

    /**
 * Vraća uslugu koja se pruža unutar ove stavke rezervacije.
 * 
 * @return Usluga koja se pruža unutar ove stavke kao ceo broj kao instanca klase Usluga.
 */
    public Usluga getUsluga() {
        return usluga;
    }

    
/**
 * Postavlja uslugu koja se pruža unutar ove stavke rezervacije na prosledjenu vrednost.
 * 
 * @param usluga Nova usluga koja treba da bude postavljena kao instanca klase Usluga.
 */
    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
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
        final StavkaRezervacije other = (StavkaRezervacije) obj;
        if (this.RBStavke != other.RBStavke) {
            return false;
        }
        if (this.cena != other.cena) {
            return false;
        }
        if (!Objects.equals(this.vremePocetka, other.vremePocetka)) {
            return false;
        }
        if (!Objects.equals(this.vremeZavrsetka, other.vremeZavrsetka)) {
            return false;
        }
        return Objects.equals(this.usluga, other.usluga);
    }

    @Override
    public String toString() {
        return "StavkaRezervacije{" + "RBStavke=" + RBStavke + ", vremePocetka=" + vremePocetka + ", vremeZavrsetka=" + vremeZavrsetka + ", cena=" + cena + ", usluga=" + usluga + '}';
    }

    
     /**
     * Vraca naziv tabele u bazi podataka koja odgovara klasi StavkaRezervacije.
     * 
     * @return Naziv tabele "stavkarezervacije" kao String.
     */
    @Override
    public String vratiNazivTabele() {
        return "stavkarezervacije";
    }

    
    /**
     * Kreira listu rezervacija na osnovu rezultata upita iz baze podataka.
     * 
     * @param rs Rezultat upita iz baze podataka u obliku ResultSet-a.
     * @return Lista stavki rezervacije kreirana na osnovu rezultata upita.
     * @throws Exception u slucaju greske tokom obrade rezultata upita.
     */
    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {

        List<OpstiDomenskiObjekat> stavke = new ArrayList<>();
        while (rs.next()) {
            int rb = rs.getInt("stavkarezervacije.RBstavke");
            int cenastavke = rs.getInt("stavkarezervacije.cena");

            java.sql.Time pocetakSQL = rs.getTime("stavkarezervacije.vremePocetka");
            LocalTime pocetak = pocetakSQL.toLocalTime();

            java.sql.Time krajSQL = rs.getTime("stavkarezervacije.vremeZavrsetka");
            LocalTime kraj = krajSQL.toLocalTime();

            //KLIJENT
            int klijentId = rs.getInt("klijent.klijentId");
            String ime = rs.getString("klijent.ime");
            String prezime = rs.getString("klijent.prezime");
            String brtel = rs.getString("klijent.brTel");
            java.sql.Date datRodj = rs.getDate("klijent.datRodj");
            java.util.Date datumRodj = new java.util.Date(datRodj.getTime());
            Klijent k = new Klijent(klijentId, ime, prezime, brtel, datumRodj);

            //REZERVACIJA
            int rezId = rs.getInt("rezervacija.rezervacijaId");
            int Ukcena = rs.getInt("rezervacija.ukupnaCena");
            boolean poj = rs.getBoolean("rezervacija.pojavljivanje");
            java.sql.Date datum1 = rs.getDate("rezervacija.datum");
            java.util.Date datum = new java.util.Date(datum1.getTime());
            Rezervacija r = new Rezervacija(rezId, datum, Ukcena, poj, k);

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

            StavkaRezervacije nova = new StavkaRezervacije(rb, r, pocetak, kraj, cenastavke, u);
            stavke.add(nova);
        }

        return stavke;

    }

    /**
     * Vraca nazive kolona koje se koriste prilikom unosa podataka u tabelu StavkaRezervacije u bazi.
     * 
     * @return Nazivi kolona tabele stavkarezervacije kao String.
     */
    @Override
    public String vratiKoloneZaInsert() {

        return "RBstavke,rezervacijaId,vremePocetka,vremeZavrsetka,cena,uslugaId";
    }

    
    
    /**
     * Vraca vrednosti koje se koriste prilikom azuriranja podataka u tabeli StavkaRezervacije u bazi.
     * 
     * @return Vrednosti za azuriranje podataka u tabeli stavkarezervacije kao String.
     */
    @Override

    public String vratiVrednostiZaUpdate() {
        return "vremePocetka='" + vremePocetka + "', vremeZavrsetka='" + vremeZavrsetka + "', cena=" + cena + ", uslugaId=" + usluga.getUslugaId();
    }

    
    /**
     * Vraca vrednosti koje se koriste prilikom unosa podataka u tabelu StavkaRezervacije u bazi.
     * 
     * @return Vrednosti za unos podataka u tabelu stavkarezervacije u bazi kao String.
     */
    @Override
    public String vratiVrednostiZaInsert() {

        java.sql.Time vreme1 = java.sql.Time.valueOf(vremePocetka);
        java.sql.Time vreme2 = java.sql.Time.valueOf(vremeZavrsetka);

        return "" + RBStavke + "," + rezervacija.getRezervacijaId() + ",'" + vreme1 + "','" + vreme2 + "'," + cena + "," + usluga.getUslugaId();
    }

    
     /**
     * Vraca primarni kljuc koji se koristi za identifikaciju zapisa u tabeli StavkaRezervacije u bazi .
     * 
     * @return Primarni kljuc tabele stavkarezervacije kao String.
     */
    @Override
    public String vratiPrimarniKljuc() {
        return "stavkarezervacije.RBstavke=" + RBStavke + " AND stavkarezervacije.rezervacijaId=" + rezervacija.getRezervacijaId();
    }

  

}
