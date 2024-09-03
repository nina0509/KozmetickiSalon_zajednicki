
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * Predstavlja klijenta u sistemu kozmetičkog salona sa id-jem imenom,
 * prezimenom, brojem telefona i datumom rodjenja.
 *  
 * Implementira interfejs OpstiDomenskiObjekat, koji definiše metode za 
 * operacije nad bazom podataka.
 * 
 * @author Nikolina Baros
 */
public class Klijent implements OpstiDomenskiObjekat {

     /**
     * Jedinstveni identifikator klijenta.
     * 
     */
    private int klijentId;
    
    /**
     * Ime klijenta.
     * 
     */
    private String ime;
    
     /**
     * Prezime klijenta.
     * 
     */
    
    private String prezime;
    
    
     /**
     * Broj telefona klijenta.
     * 
     */
    private String brTel;
    
     /**
     * Datum rodjenja klijenta.
     * 
     */
    private Date datRodj;
    
    /**
     * Datum rodjenja klijenta.
     * 
     */
   

    /**
     * Konstruktor sa parametrima za kreiranje novog Klijenta.
     * 
     * @param klijentId Jedinstveni identifikator klijenta.
     * @param ime Ime klijenta.
     * @param prezime Prezime klijenta.
     * @param brTel Broj telefona klijenta.
     * @param datRodj Datum rođenja klijenta.
     */
    
    public Klijent(int klijentId, String ime, String prezime, String brTel, 
            Date datRodj) {
        
        this.klijentId = klijentId;
        this.ime = ime;
        this.prezime = prezime;
        this.brTel = brTel;
        this.datRodj = datRodj;
    }

    
    /**
     *Podrazumevani, prazan konstruktor za kreiranje nove instance Klijenta.
     */
    public Klijent() {
    }
    
    /**
     * Vraca ID klijenta.
     * @return ID klijenta kao ceo broj.
     */
    
    public int getKlijentId() {
        return klijentId;
    }

    
    /**
     * Postavlja ID klijenta na prosledjenu vrednost.
     * 
     * @param klijentId Novi ID klijenta kao ceo broj.
     */
    
    public void setKlijentId(int klijentId) {
        this.klijentId = klijentId;
    }

    
     /**
     * Vraca ime klijenta.
     * @return Ime klijenta kao string.
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime klijenta na prosledjenu vrednost.
     * @param ime Novo ime klijenta kao String.
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

     
     /**
     * Vraca prezime klijenta.
     * @return Prezime klijenta kao string.
     */
    public String getPrezime() {
        return prezime;
    }

     /**
     * Postavlja prezime klijenta na prosledjenu vrednost.
     * @param prezime Novo prezime klijenta kao String.
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    
     /**
     * Vraca broj telefona klijenta.
     * @return Broj telefona klijenta kao string.
     */
    public String getBrTel() {
        return brTel;
    }

    
    /**
     * Postavlja broj telefona klijenta na prosledjenu vrednost.
     * @param brTel Novi broj telefona klijenta kao String.
     */
    public void setBrTel(String brTel) {
        this.brTel = brTel;
    }

    
     /**
     * Vraca datum rodjenja klijenta.
     * @return Datum rodjenja klijenta kao Date.
     */
    public Date getDatRodj() {
        return datRodj;
    }

    
    /**
     * Postavlja datum rodjenja  klijenta na prosledjenu vrednost.
     * @param datRodj Novi datum rodjenja klijenta kao Date.
     */
    public void setDatRodj(Date datRodj) {
        this.datRodj = datRodj;
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

    /**
     * Vraća string reprezentaciju klijenta sa imenom i prezimenom.
     * 
     * @return String reprezentacija Klijenta.
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    
     /**
     * Vraca naziv tabele u bazi podataka koja odgovara klasi Klijent.
     * 
     * @return Naziv tabele "klijent" kao String.
     */
    @Override
    public String vratiNazivTabele() {
        return "klijent";
    }

    /**
     * Kreira listu klijenata na osnovu rezultata upita iz baze podataka.
     * 
     * @param rs Rezultat upita iz baze podataka u obliku ResultSet-a.
     * @return Lista klijenata kreirana na osnovu rezultata upita.
     * @throws Exception u slucaju greske tokom obrade rezultata upita.
     */
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

    /**
     * Vraca nazive kolona koje se koriste prilikom unosa podataka u tabelu Klijent u bazi.
     * 
     * @return Nazivi kolona tabele klijent kao String.
     */
    @Override
    public String vratiKoloneZaInsert() {
        return "ime,prezime,brTel,datRodj";
    }

    /**
     * Vraca vrednosti koje se koriste prilikom azuriranja podataka u tabeli Klijent u bazi.
     * 
     * @return Vrednosti za azuriranje podataka u tabeli Klijent kao String.
     */
    @Override
    public String vratiVrednostiZaUpdate() {
        java.sql.Date datum = new java.sql.Date(datRodj.getTime());
        return "ime='" + ime + "', prezime='" + prezime + "', brTel='" + brTel + "', datRodj='" + datum + "'";
    }

    /**
     * Vraca vrednosti koje se koriste prilikom unosa podataka u tabelu Klijent u bazi.
     * 
     * @return Vrednosti za unos podataka u tabelu Klijent u bazi kao String.
     */
    @Override
    public String vratiVrednostiZaInsert() {

        java.sql.Date datum = new java.sql.Date(datRodj.getTime());
        return "'" + ime + "','" + prezime + "','" + brTel + "','" + datum + "'";
    }

    
    /**
     * Vraca primarni kljuc koji se koristi za identifikaciju zapisa u tabeli Klijent u bazi .
     * 
     * @return Primarni kljuc tabele klijent kao String.
     */
    @Override
   
    public String vratiPrimarniKljuc() {

        return "klijent.klijentId=" + klijentId;

    }
    
    


}
