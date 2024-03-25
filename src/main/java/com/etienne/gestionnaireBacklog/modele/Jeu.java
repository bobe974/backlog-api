package com.etienne.gestionnaireBacklog.modele;

import jakarta.persistence.*;

@Entity
@Table(name = "jeux")
public class Jeu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJeu;
    private String titre;
    private String dateSortie;
    private String description;
    private String etat;

    public Jeu() {
    }

    public Long getIdJeu() {
        return idJeu;
    }

    public void setIdJeu(Long idJeu) {
        this.idJeu = idJeu;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

}
