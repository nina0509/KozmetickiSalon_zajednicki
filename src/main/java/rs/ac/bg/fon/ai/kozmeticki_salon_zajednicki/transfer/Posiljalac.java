/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.transfer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 
 * Klasa koja predstavlja posiljaoca i omogućava slanje objekata preko mreže koristeći socket.
 * Omogućava serijalizaciju objekta i slanje putem izlaznog toka socket-a.
 * 
 * @author ninic
 */
public class Posiljalac {

     /**
     * Socket preko kojeg se šalju podaci.
     */
    private Socket socket;

    /**
     * Konstruktor koji inicijalizuje objekat Posiljalac sa datim socket-om.
     * 
     * @param socket Socket preko kojeg će se vršiti slanje podataka.
     */
    public Posiljalac(Socket socket) {
        this.socket = socket;
    }
 /**
     * Šalje objekat putem socket-a koristeći ObjectOutputStream.
     * Serijalizuje objekat i šalje ga preko izlaznog toka socket-a.
     * 
     * @param obj Objekat koji se šalje preko socket-a.
     */
    public void posalji(Object obj) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(obj);
            out.flush();
            } 
        catch (IOException ex) {
         ex.printStackTrace();
        }
    }

}
