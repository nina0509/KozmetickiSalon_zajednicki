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
public class StavkaStatistike implements OpstiDomenskiObjekat{
    
    Statistika statistika;
    Usluga usluga;
    int brojRezUsluge=0;

    public StavkaStatistike(Statistika statistika, Usluga usluga, int broj) {
        this.statistika = statistika;
        this.usluga = usluga;
        this.brojRezUsluge=broj;
    }

    public StavkaStatistike() {
    }

    public Statistika getStatistika() {
        return statistika;
    }

    public void setStatistika(Statistika statistika) {
        this.statistika = statistika;
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

    
    
    
    
    

    @Override
    public String vratiNazivTabele() {
   
        return "stavkastatistike";

    }

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

    @Override
    public String vratiKoloneZaInsert() {

         return "godina,uslugaId,brojRezUsluge";

    }

    @Override
    public String vratiVrednostiZaUpdate() {
 return "godina=" + statistika.getGodina() + ", uslugaId=" + usluga.getUslugaId() + ", brojRezUsluge=" + brojRezUsluge ;    }

    @Override
    public String vratiVrednostiZaInsert() {
        
 return "" + statistika.getGodina() + "," + usluga.getUslugaId() + "," + brojRezUsluge;    }

    @Override
    public String vratiPrimarniKljuc() {
     
            return "stavkastatistike.godina=" + statistika.getGodina() + " AND stavkastatistike.uslugaId=" + usluga.getUslugaId();

    }
    
}
