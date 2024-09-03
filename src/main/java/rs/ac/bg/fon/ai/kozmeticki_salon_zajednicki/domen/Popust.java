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
 *
 * @author ninic
 */
public class Popust implements OpstiDomenskiObjekat {

    private Klijent klijent;
    private Usluga usluga;
    private int brojRezUsluge;
    //popust u procentima
    private int popust;

    public Popust() {
    }

    public Popust(Klijent klijent, Usluga usluga, int brojRezUsluge, int popust) {
        this.klijent = klijent;
        this.usluga = usluga;
        this.brojRezUsluge = brojRezUsluge;
        this.popust = popust;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    public int getBrojRezUsluge() {
        return brojRezUsluge;
    }

    public void setBrojRezUsluge(int brojRezUsluge) {
        this.brojRezUsluge = brojRezUsluge;
    }

    public int getPopust() {
        return popust;
    }

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

    @Override
    public String vratiNazivTabele() {
        return "popust";
    }

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

    @Override
    public String vratiKoloneZaInsert() {
        return "klijentId,uslugaId,brojRezUsluge,popust";
    }

    @Override
    public String vratiVrednostiZaUpdate() {

        return "klijentId=" + klijent.getKlijentId() + ", uslugaId=" + usluga.getUslugaId() + ", brojRezUsluge=" + brojRezUsluge + ", popust=" + popust;

    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "" + klijent.getKlijentId() + "," + usluga.getUslugaId() + "," + brojRezUsluge + "," + popust;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "popust.klijentId=" + klijent.getKlijentId() + " AND popust.uslugaId=" + usluga.getUslugaId();
    }

   
}
