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
public class Klijent implements OpstiDomenskiObjekat {

    private int klijentId;
    private String ime;
    private String prezime;
    private String brTel;
    private Date datRodj;
    int brojRezervacija=0;

    public Klijent(int klijentId, String ime, String prezime, String brTel, 
            Date datRodj) {
        
        this.klijentId = klijentId;
        this.ime = ime;
        this.prezime = prezime;
        this.brTel = brTel;
        this.datRodj = datRodj;
    }

    public Klijent() {
    }
    public int getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(int klijentId) {
        this.klijentId = klijentId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrTel() {
        return brTel;
    }

    public void setBrTel(String brTel) {
        this.brTel = brTel;
    }

    public Date getDatRodj() {
        return datRodj;
    }

    public void setDatRodj(Date datRodj) {
        this.datRodj = datRodj;
    }

    public int getBrojRezervacija() {
        return brojRezervacija;
    }

    public void setBrojRezervacija(int brojRezervacija) {
        this.brojRezervacija = brojRezervacija;
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
        final Klijent other = (Klijent) obj;
        if (this.klijentId != other.klijentId) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        return Objects.equals(this.prezime, other.prezime);
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String vratiNazivTabele() {
        return "klijent";
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
            lista.add(k);
        }

        return lista;

    }

    @Override
    public String vratiKoloneZaInsert() {
        return "ime,prezime,brTel,datRodj";
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        java.sql.Date datum = new java.sql.Date(datRodj.getTime());
        return "ime='" + ime + "', prezime='" + prezime + "', brTel='" + brTel + "', datRodj='" + datum + "'";
    }

    @Override
    public String vratiVrednostiZaInsert() {

        java.sql.Date datum = new java.sql.Date(datRodj.getTime());
        return "'" + ime + "','" + prezime + "','" + brTel + "','" + datum + "'";
    }

    @Override
    //za where
    public String vratiPrimarniKljuc() {

        return "klijent.klijentId=" + klijentId;

    }
    
    


}
