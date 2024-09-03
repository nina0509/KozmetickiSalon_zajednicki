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
 *
 * @author ninic
 */
public class StavkaRezervacije implements OpstiDomenskiObjekat {

    private int RBStavke;
    private Rezervacija rezervacija;
    private LocalTime vremePocetka;
    private LocalTime vremeZavrsetka;
    private int cena;
    private Usluga usluga;


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

    public StavkaRezervacije() {
    }
    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public int getRBStavke() {
        return RBStavke;
    }

    public void setRBStavke(int RBStavke) {
        this.RBStavke = RBStavke;
    }

    public LocalTime getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(LocalTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public LocalTime getVremeZavrsetka() {
        return vremeZavrsetka;
    }

    public void setVremeZavrsetka(LocalTime vremeZavrsetka) {
        this.vremeZavrsetka = vremeZavrsetka;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public Usluga getUsluga() {
        return usluga;
    }

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

    @Override
    public String vratiNazivTabele() {
        return "stavkarezervacije";
    }

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

    @Override
    public String vratiKoloneZaInsert() {

        return "RBstavke,rezervacijaId,vremePocetka,vremeZavrsetka,cena,uslugaId";
    }

    @Override

    public String vratiVrednostiZaUpdate() {
        return "vremePocetka='" + vremePocetka + "', vremeZavrsetka='" + vremeZavrsetka + "', cena=" + cena + ", uslugaId=" + usluga.getUslugaId();
    }

    @Override
    public String vratiVrednostiZaInsert() {

        java.sql.Time vreme1 = java.sql.Time.valueOf(vremePocetka);
        java.sql.Time vreme2 = java.sql.Time.valueOf(vremeZavrsetka);

        return "" + RBStavke + "," + rezervacija.getRezervacijaId() + ",'" + vreme1 + "','" + vreme2 + "'," + cena + "," + usluga.getUslugaId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "stavkarezervacije.RBstavke=" + RBStavke + " AND stavkarezervacije.rezervacijaId=" + rezervacija.getRezervacijaId();
    }

  

}
