/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.transfer;

import java.io.Serializable;
/**
 * Klasa koja predstavlja odgovor u komunikaciji između klijenta i servera. 
 * Koristi se za prenos podataka ili rezultata operacija od servera ka klijentu.
 * 
 * Implementira interfejs Serializable kako bi omogućila serijalizaciju objekta.
 * 
 * @author Nikolina Baros
 */
public class Odgovor implements Serializable {
    
    /**
     * Objekat koji predstavlja odgovor servera.
     */
    private Object odgovor;
    

     /**
     * Podrazumevani konstruktor koji inicijalizuje objekat Odgovor.
     */
    public Odgovor() {
    }

    /**
     * Konstruktor koji inicijalizuje objekat Odgovor sa prosledjenom vrednosću.
     * 
     * @param odgovor Objekat koji predstavlja odgovor servera.
     */
    public Odgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    /**
     * Vraća objekat odgovor.
     * 
     * @return Objekat koji predstavlja odgovor servera kao Object.
     */
    public Object getOdgovor() {
        return odgovor;
    }

     /**
     * Postavlja vrednost odgovora na prosledjenu vrednost.
     * 
     * @param odgovor Objekat koji predstavlja novi odgovor servera kao object.
     */
    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }
    
    
    
}
