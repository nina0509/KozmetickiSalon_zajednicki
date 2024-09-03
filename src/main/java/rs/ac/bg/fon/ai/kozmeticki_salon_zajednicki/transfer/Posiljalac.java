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
 * @author ninic
 */
public class Posiljalac {

    private Socket socket;

    public Posiljalac(Socket socket) {
        this.socket = socket;
    }

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
