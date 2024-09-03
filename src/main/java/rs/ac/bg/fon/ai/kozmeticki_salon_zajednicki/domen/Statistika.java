/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ninic
 */
public class Statistika implements OpstiDomenskiObjekat {
    
    private int godina;
    private int ukupnoRezervacija;
    List<StavkaStatistike> stavke=new ArrayList<>();

    public Statistika() {
    }

    public Statistika(int godina, int ukupnoRezervacija) {
        this.godina = godina;
        this.ukupnoRezervacija = ukupnoRezervacija;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public int getUkupnoRezervacija() {
        return ukupnoRezervacija;
    }

    public void setUkupnoRezervacija(int ukupnoRezervacija) {
        this.ukupnoRezervacija = ukupnoRezervacija;
    }

    public List<StavkaStatistike> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaStatistike> stavke) {
        this.stavke = stavke;
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
        final Statistika other = (Statistika) obj;
        return this.godina == other.godina;
    }

    @Override
    public String toString() {
        return "Statistika{" + "godina=" + godina + ", ukupnoRezervacija=" + ukupnoRezervacija + ", stavke=" + stavke + '}';
    }

    
    
    
    
    @Override
    public String vratiNazivTabele() {
return "statistika";
    }

    
    
    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {

         List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int godina1 = rs.getInt("statistika.godina");
            int brojRez1 = rs.getInt("statistika.ukupnoRez");
            Statistika s=new Statistika(godina1, brojRez1);
            lista.add(s);


    }
        return lista;
    }

    @Override
    public String vratiKoloneZaInsert() {

     return "godina,ukupnoRez";

    }

    @Override
    public String vratiVrednostiZaUpdate() {
    
        return "godina="+godina+", ukupnoRez="+ukupnoRezervacija;

    }

    @Override
     public String vratiVrednostiZaInsert() {
    
         return godina+","+ukupnoRezervacija;

    }

    @Override
    public String vratiPrimarniKljuc() {
      
        return "statistika.godina="+godina;

    }
    
    
    
    
}
