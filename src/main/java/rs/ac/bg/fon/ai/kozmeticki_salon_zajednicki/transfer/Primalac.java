/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.transfer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
/**
 * Klasa koja predstavlja primaoca i omogućava prijem objekata sa mreže koristeći socket.
 * 
 * Omogućava deserijalizaciju objekta sa ulaznog toka socket-a.
 * 
 * @author Nikolina Baros
 */
public class Primalac {

    /**
     * Socket preko kojeg se primaju podaci.
     */
    private Socket socket;

     /**
     * Konstruktor koji inicijalizuje objekat Primalac sa datim socket-om.
     * 
     * @param socket Socket preko kojeg će se vršiti prijem podataka.
     */
    public Primalac(Socket socket) {
        this.socket = socket;
    }

      /**
     * Prima objekat sa socket-a koristeći ObjectInputStream.
     * Deserijalizuje objekat sa ulaznog toka socket-a.
     * 
     * @return Null ako dođe do greške u suprotnom objekat primljen sa socket-a.
     */
    public Object primi() {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (IOException ex) {
            
        } catch (ClassNotFoundException ex) {
           
        }
        return null;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    

}
