package com.example.gestion_cinetheque.Ejb;


import com.example.gestion_cinetheque.Models.CD;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CinemathequeServiceLocal {
    List<CD> listerCDsDisponibles();
    void emprunterCD(int cdId, String nomEmprunteur);
    void retournerCD(int cdId);
}
