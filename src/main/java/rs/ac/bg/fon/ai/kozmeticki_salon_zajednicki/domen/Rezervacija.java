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
 *
 * @author ninic
 */
public class Rezervacija implements OpstiDomenskiObjekat {

    private int rezervacijaId;
    private Date datum;
    private int ukupnaCena;
    private boolean pojavljivanje;
    private Klijent klijent;
    private List<StavkaRezervacije> stavke = new ArrayList<>();

    public Rezervacija() {
    }

    public Rezervacija(int rezervacijaId, Date datum, int ukupnaCena, boolean pojavljivanje, Klijent klijent) {
        this.rezervacijaId = rezervacijaId;
        this.datum = datum;
        this.ukupnaCena = ukupnaCena;
        this.pojavljivanje = pojavljivanje;
        this.klijent = klijent;
    }

    public int getRezervacijaId() {
        return rezervacijaId;
    }

    public void setRezervacijaId(int rezervacijaId) {
        this.rezervacijaId = rezervacijaId;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(int ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public boolean isPojavljivanje() {
        return pojavljivanje;
    }

    public void setPojavljivanje(boolean pojavljivanje) {
        this.pojavljivanje = pojavljivanje;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRezervacije> stavke) {
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

    @Override
    public String vratiNazivTabele() {
        return "rezervacija";
    }

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

    @Override
    public String vratiKoloneZaInsert() {
        return "datum,ukupnaCena,pojavljivanje,klijentId";
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        java.sql.Date datum1 = new java.sql.Date(datum.getTime());
        return "datum='" + datum1 + "', ukupnaCena=" + ukupnaCena + ", pojavljivanje=" + pojavljivanje + ", klijentId=" + klijent.getKlijentId();
    }

    @Override
    public String vratiVrednostiZaInsert() {
        java.sql.Date datum1 = new java.sql.Date(datum.getTime());
        return "'" + datum1 + "'," + ukupnaCena + "," + pojavljivanje + "," + klijent.getKlijentId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "rezervacija.rezervacijaId=" + rezervacijaId;
    }

 

}
