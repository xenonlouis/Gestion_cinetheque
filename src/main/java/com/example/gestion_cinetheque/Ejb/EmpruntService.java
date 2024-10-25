package com.example.gestion_cinetheque.Ejb;


import com.example.gestion_cinetheque.Models.CD;
import com.example.gestion_cinetheque.Models.Emprunt;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@Stateless
public class EmpruntService {

    @PersistenceContext
    private EntityManager em;

    public void emprunterCD(Long cdId, String utilisateur) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null && cd.isDisponible()) {
            cd.setDisponible(false);
            Emprunt emprunt = new Emprunt(cd, utilisateur, LocalDate.now());
            em.persist(emprunt);
            em.merge(cd);
        } else {
            throw new IllegalArgumentException("CD non disponible");
        }
    }

    public void retournerCD(Long empruntId) {
        Emprunt emprunt = em.find(Emprunt.class, empruntId);
        if (emprunt != null && emprunt.getDateRetour() == null) {
            emprunt.setDateRetour(LocalDate.now());
            CD cd = emprunt.getCd();
            cd.setDisponible(true);
            em.merge(emprunt);
            em.merge(cd);
        } else {
            throw new IllegalArgumentException("Emprunt non trouvé ou déjà retourné");
        }
    }
}
