package com.etienne.gestionnaireBacklog.modele;
import com.etienne.gestionnaireBacklog.enums.GameStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "joueur_jeu")
public class JoueurJeu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //chaque occurence JoueurJeu est associée à un seul joueur et un seul jeu
    @ManyToOne()
    @JoinColumn(name = "joueur_id")
    private Joueur joueur;

    @ManyToOne
    @JoinColumn(name = "jeu_id")
    private Jeu jeu;
    private GameStatus etat;
    private Double tempsDeJeu;
    private String dateDebut;
    private String dateFin;
    //chaque occurence de JoueurJeu peut avoir un seul a avis sur un jeu
    @ManyToOne(optional = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "avis_id")
    private Avis avis;

    public Long getId() {
        return id;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public GameStatus getEtat() {
        return etat;
    }

    public void setEtat(GameStatus etat) {
        this.etat = etat;
    }

    public Double getTempsDeJeu() {
        return tempsDeJeu;
    }

    public void setTempsDeJeu(Double tempsDeJeu) {
        this.tempsDeJeu = tempsDeJeu;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public Avis getAvis() {
        return avis;
    }

    public void setAvis(Avis avis) {
        this.avis = avis;
    }
}

