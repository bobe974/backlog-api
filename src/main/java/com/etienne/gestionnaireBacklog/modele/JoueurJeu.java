package com.etienne.gestionnaireBacklog.modele;
import enums.GameStatus;
import enums.TypeAvis;
import jakarta.persistence.*;

@Entity
@Table(name = "joueur_jeu")
public class JoueurJeu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //chaque occurence JoueurJeu est associée à un seul joueur et un seul jeu
    @ManyToOne
    @JoinColumn(name = "joueur_id")
    private Joueur joueur;

    @ManyToOne
    @JoinColumn(name = "jeu_id")
    private Jeu jeu;

   // @Enumerated(EnumType.STRING)
    private GameStatus etat;

    private double tempsDeJeu;
    private String dateDebut;
    private String dateFin;
    //chaque occurence de JoueurJeu peut avoir un seul a avis sur un jeu
    @ManyToOne
    @JoinColumn(name = "avis_id")
    private Avis avis;


}

