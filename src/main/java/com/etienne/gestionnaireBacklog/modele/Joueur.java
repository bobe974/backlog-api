package com.etienne.gestionnaireBacklog.modele;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "joueur")
public class Joueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pseudo;
    private String email;
    private String dateInscription;
    private String sexe;
    private String age;

    @OneToMany(mappedBy = "joueur", cascade = CascadeType.REMOVE)
    private List<JoueurJeu> joueurJeux;

    @ManyToOne(optional = true)
    @JoinColumn(name = "jeu_favoris_id")
    private Jeu jeuFavoris;

    public Jeu getJeuFavoris() {
        return jeuFavoris;
    }

    public void setJeuFavoris(Jeu jeuFavoris) {
        this.jeuFavoris = jeuFavoris;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
