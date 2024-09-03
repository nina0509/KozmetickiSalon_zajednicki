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
public class Menadzer implements OpstiDomenskiObjekat {

    private int id;
    private String username;
    private String password;
    private String ime;
    private String prezime;

    public Menadzer() {
    }

    public Menadzer(int id, String username, String password, String ime, String prezime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
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
        final Menadzer other = (Menadzer) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String vratiNazivTabele() {
        return "menadzer";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("menadzer.menadzerId");
            String ime = rs.getString("menadzer.ime");
            String prezime = rs.getString("menadzer.prezime");
            String username = rs.getString("menadzer.username");
            String password = rs.getString("menadzer.password");

            Menadzer m = new Menadzer(id, username, password, ime, prezime);
            lista.add(m);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaInsert() {
        return null;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return null;

    }

    @Override
    public String vratiVrednostiZaInsert() {
        return null;

    }

    @Override
    public String vratiPrimarniKljuc() {
        return "menadzer.username=" + username;
    }

 

    @Override
    public String toString() {
        return "Menadzer{" + "id=" + id + ", username=" + username + ", password=" + password + ", ime=" + ime + ", prezime=" + prezime + '}';
    }

}
