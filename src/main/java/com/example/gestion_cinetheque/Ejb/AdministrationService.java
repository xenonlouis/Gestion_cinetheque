package com.example.gestion_cinetheque.Ejb;


import com.example.gestion_cinetheque.Models.CD;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class AdministrationService {

    @PersistenceContext
    private EntityManager em;

    public void ajouterCD(String titre, String auteur) {
        CD cd = new CD(titre, auteur);
        em.persist(cd);
    }

    public void modifierCD(Long cdId, String titre, String auteur) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) {
            cd.setTitre(titre);
            cd.setAuteur(auteur);
            em.merge(cd);
        } else {
            throw new IllegalArgumentException("CD non trouvé");
        }
    }

    public void supprimerCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) {
            em.remove(cd);
        } else {
            throw new IllegalArgumentException("CD non trouvé");
        }
    }

    public List<CD> listerTousLesCD() {
        return em.createQuery("SELECT c FROM CD c", CD.class).getResultList();
    }
}
