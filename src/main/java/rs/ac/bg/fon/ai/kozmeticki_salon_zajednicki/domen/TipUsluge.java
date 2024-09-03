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
public class TipUsluge implements OpstiDomenskiObjekat {

    private int tipId;
    private String naziv;

   
    public TipUsluge(int tipId, String naziv) {
        this.tipId = tipId;
        this.naziv = naziv;
    }
    
    
    
 public TipUsluge() {
    }

    public int getTipId() {
        return tipId;
    }

    public void setTipId(int tipId) {
        this.tipId = tipId;
    }

    public String getNaziv() {
        return naziv;
    }

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

    @Override
    public String vratiNazivTabele() {
        return "tipusluge";
    }

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

    @Override
    public String vratiKoloneZaInsert() {
        return "naziv";
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "naziv='" + naziv + "'";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return naziv;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "tipusluge.tipId=" + tipId;
    }


}
