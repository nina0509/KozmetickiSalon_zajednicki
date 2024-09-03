/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.transfer;

import java.io.Serializable;
import rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.config.Operacija;

/**
 * Klasa koja predstavlja zahtev za komunikaciju između klijenta i servera. 
 * Sadrzi operaciju i parametre potrebne za izvrsavanje.
 * 
 * Implementira Serializable kako bi mogla da bude serijalizovana i deserijalizovana za komunikaciju.
 * 
 * @author Nikolina Baros
 */
public class Zahtev implements Serializable {
    
     /**
     * Operacija koja treba da se izvrši.
     */
    private Operacija operacija;
    
     /**
     * Parametar potreban za izvršenje operacije.
     */
    private Object parametar;

     /**
     * Prazan konstruktor za kreiranje instance klase Zahtev.
     */
    public Zahtev() {
    }

     /**
     * Konstruktor koji inicijalizuje objekat Zahtev sa datom operacijom i parametrom.
     * 
     * @param operacija Operacija koja treba da se izvrši.
     * @param parametar Parametar potreban za izvršenje operacije.
     */
    public Zahtev(Operacija operacija, Object parametar) {
        this.operacija = operacija;
        this.parametar = parametar;
    }

    /**
     * Vraća operaciju koja treba da se izvrši.
     * 
     * @return Operacija koja treba da se izvrši kao.
     */
    public Operacija getOperacija() {
        return operacija;
    }

    /**
     * Postavlja operaciju koja treba da se izvrši na prosledjenu vrednost.
     * 
     * @param operacija Operacija koja treba da se izvrši.
     */
    public void setOperacija(Operacija operacija) {
        this.operacija = operacija;
    }

    
    /**
     * Vraća parametar potreban za izvršenje operacije.
     * 
     * @return Parametar potreban za izvršenje operacije kao Object.
     */
    public Object getParametar() {
        return parametar;
    }

     /**
     * Postavlja parametar potreban za izvršenje operacije na prosledjenu vrednost.
     * 
     * @param parametar Parametar potreban za izvršenje operacije kao Object.
     */
    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }
    
    
}
