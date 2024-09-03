
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;


/**
 * Interfejs koji predstavlja opšti domenski objekat u sistemu.
 *
 * Ovaj interfejs definiše osnovne metode koje svaka klasa koja implementira
 * interfejs mora imati, koje su vezane za rad sa bazom podataka.
 * Klase koje implementiraju ovaj interfejs treba da obezbede konkretne
 * implementacije za rad sa različitim tabelama u bazi podataka.
 * 
 * 
 * @author Nikolina Baros
 */
public interface OpstiDomenskiObjekat extends Serializable {

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara domenskom objektu.
     * 
     * @return Naziv tabele kao string.
     */
    public String vratiNazivTabele();

    
     /**
     * Kreira listu objekata tipa OpstiDomenskiObjekat na osnovu rezultata upita iz baze podataka.
     * 
     * @param rs Rezultat upita iz baze podataka.
     * @return Lista objekata tipa OpstiDomenskiObjekat.
     * @throws Exception u slučaju greške tokom obrade rezultata upita.
     */
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;

    
    /**
     * Vraća nazive kolona koje se koriste za unos podataka u odgovarajuću tabelu baze podataka.
     * 
     * @return Nazivi kolona kao string.
     */
    public String vratiKoloneZaInsert();

    /**
     * Vraća vrednosti koje se koriste za ažuriranje podataka u odgovarajućoj tabeli baze podataka.
     * 
     * @return Vrednosti za ažuriranje kao string.
     */
    public String vratiVrednostiZaUpdate();
/**
     * Vraća vrednosti koje se koriste za unos podataka u odgovarajuću tabelu baze podataka.
     * 
     * @return Vrednosti za unos kao string.
     */
    public String vratiVrednostiZaInsert();

   /**
     * Vraca primarni kljuc koji se koristi za identifikaciju zapisa u odgovarajućoj tabeli u bazi .
     * 
     * @return Primarni kljuc kao String.
     */
    public String vratiPrimarniKljuc();



}
