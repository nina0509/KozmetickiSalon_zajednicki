



package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.config;

import java.io.Serializable;

/**
 * Enumeracija koja predstavlja razlicite operacije koje klijent moze izvršiti u okviru
 * softvera za upravljanje radom kozmetickog salona.
 * 
 * Svaka konstanta u ovoj enumeraciji predstavlja specifičnu operaciju koja
 * se koristi unutar sistema.
 * 
 * @author Nikolina Baros
 */
public enum Operacija implements Serializable{

    /**
     * Operacija prijave korisnika.
     */
    
    LOGIN,
    /**
     * Operacija pretrage klijenata na osnovu zadatih kriterijuma.
     */
 
    NADJI_KLIJENTE, 
    /**
     * Operacija učitavanja podataka o određenom klijentu.
     */
    UCITAJ_KLIJENTA,
    
    /**
     * Operacija brisanja klijenta iz sistema.
     */
    IZBRISI_KLIJENTA,
    
     /**
     * Operacija čuvanja (ažuriranja ili dodavanja) podataka o klijentu.
     */
    ZAPAMTI_KLIJENTA,
    
    /**
     * Operacija pretrage usluga na osnovu zadatih kriterijuma.
     */
    NADJI_USLUGE,
    
     /**
     * Operacija učitavanja podataka o određenoj usluzi.
     */
    UCITAJ_USLUGU,
    
    /**
     * Operacija učitavanja podataka o određenoj rezervaciji.
     */
    UCITAJ_REZERVACIJU,
    
    /**
     * Operacija brisanja usluge iz sistema.
     */
    IZBRISI_USLUGU, 
    
     /**
     * Operacija učitavanja liste svih tipova usluga.
     */
    UCITAJ_LISTU_TIPOVA_USLUGE, 
    
    /**
     * Operacija čuvanja (ažuriranja ili dodavanja) podataka o usluzi.
     */
    ZAPAMTI_USLUGU, 
    
     /**
     * Operacija pretrage rezervacija na osnovu zadatih kriterijuma.
     */
    NADJI_REZERVACIJE, 
    
     /**
     * Operacija učitavanja liste svih popusta.
     */
    UCITAJ_LISTU_POPUSTA, 
    
     /**
     * Operacija čuvanja (ažuriranja ili dodavanja) podataka o rezervaciji.
     */
    ZAPAMTI_REZERVACIJU,
    
    /**
     * Operacija brisanja rezervacije iz sistema.
     */
    IZBRISI_REZERVACIJU, 
    
      /**
     * Operacija učitavanja liste svih usluga.
     */
    UCITAJ_LISTU_USLUGA,
    
    /**
     * Operacija učitavanja liste svih klijenata.
     */
    UCITAJ_LISTU_KLIJENATA, 
    
    /**
     * Operacija odjave korisnika.
     */
    LOGOUT,
    
    /**
     * Operacija učitavanja svih statističkih podataka.
     */
    UCITAJ_SVE_STATISTIKE, 
    
    /**
     * Operacija čuvanja (ažuriranja ili dodavanja) statističkih podataka.
     */
    ZAPAMTI_STATISTIKE
    
   
    
    
}
