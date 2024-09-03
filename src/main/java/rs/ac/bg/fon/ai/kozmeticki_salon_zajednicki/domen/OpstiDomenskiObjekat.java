/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.ai.kozmeticki_salon_zajednicki.domen;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author ninic
 */
public interface OpstiDomenskiObjekat extends Serializable {

    public String vratiNazivTabele();

    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;

    public String vratiKoloneZaInsert();

    public String vratiVrednostiZaUpdate();

    public String vratiVrednostiZaInsert();

    public String vratiPrimarniKljuc();



}
