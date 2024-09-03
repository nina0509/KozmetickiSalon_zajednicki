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
public class Usluga implements OpstiDomenskiObjekat {

    private int uslugaId;
    private String naziv;
    private int trajanje;
    private int cena;
    private TipUsluge tip;

    public Usluga() {
    }

    public Usluga(int uslugaId, String naziv, int trajanje, int cena, TipUsluge tip) {
        this.uslugaId = uslugaId;
        this.naziv = naziv;
        this.trajanje = trajanje;
        this.cena = cena;
        this.tip = tip;
    }

    public int getUslugaId() {
        return uslugaId;
    }

    public void setUslugaId(int uslugaId) {
        this.uslugaId = uslugaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public TipUsluge getTip() {
        return tip;
    }

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

    @Override
    public String vratiNazivTabele() {
        return "usluga";
    }

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

    @Override
    public String vratiKoloneZaInsert() {
        return "naziv,trajanje,cena,tipId";
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "naziv='" + naziv + "', trajanje=" + trajanje + ", cena=" + cena + ", tipId=" + tip.getTipId();
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + naziv + "'," + trajanje + "," + cena + "," + tip.getTipId();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "usluga.uslugaId=" + uslugaId;
    }

  
}
