package com.etienne.gestionnaireBacklog.services;

import com.etienne.gestionnaireBacklog.exceptions.JoueurIntrouvableException;
import com.etienne.gestionnaireBacklog.exceptions.JoueurJeuIntrouvableException;
import com.etienne.gestionnaireBacklog.modele.Joueur;
import com.etienne.gestionnaireBacklog.modele.JoueurJeu;
import com.etienne.gestionnaireBacklog.repository.JoueurJeuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JoueurJeuImpl {
     JoueurJeuRepository joueurJeuRepository;

    public JoueurJeuImpl(JoueurJeuRepository joueurJeuRepository){
        this.joueurJeuRepository = joueurJeuRepository;
    }

    public List<JoueurJeu> lire(){
        return joueurJeuRepository.findAll();
    }

    public Optional<JoueurJeu> lireParId(Long id){
        Optional<JoueurJeu> joueurJeu = joueurJeuRepository.findById(id);
        if(joueurJeu.isPresent()){
            return joueurJeu;
        }else {
            throw new JoueurJeuIntrouvableException("L'occurence Joueur-Jeu avec l'ID " + id + " n'a pas été trouvé");
        }
    }

    public List<JoueurJeu> lireParIdJoueur(Long id){
       return  joueurJeuRepository.findByJoueurId(id);
    }
    public void ajouter(JoueurJeu joueurJeu){
        joueurJeuRepository.save(joueurJeu);
    }

    public void modifier(JoueurJeu joueurJeu, Long id){
        JoueurJeu joueurJeuBdd;

        if(joueurJeuRepository.findById(id).isPresent()){
            joueurJeuBdd = joueurJeuRepository.findById(id).get();
            joueurJeuBdd.setAvis((joueurJeu.getAvis() != null) ? joueurJeu.getAvis() : joueurJeuBdd.getAvis());
            joueurJeuBdd.setEtat((joueurJeu.getEtat() != null) ? joueurJeu.getEtat() : joueurJeuBdd.getEtat());
            joueurJeuBdd.setTempsDeJeu((joueurJeu.getTempsDeJeu() != null) ? joueurJeu.getTempsDeJeu() : joueurJeuBdd.getTempsDeJeu());
            joueurJeuBdd.setDateDebut((joueurJeu.getDateDebut() != null) ? joueurJeu.getDateDebut() : joueurJeuBdd.getDateDebut());
            joueurJeuBdd.setDateFin((joueurJeu.getDateFin() != null) ? joueurJeu.getDateFin() : joueurJeuBdd.getDateFin());

            joueurJeuRepository.save(joueurJeuBdd);
        }else{
            throw new JoueurIntrouvableException("Joueur introuvable");
        }
    }
    public void supprimer(Long id){
        Optional<JoueurJeu> joueurJeu = joueurJeuRepository.findById(id);
        if(joueurJeu.isPresent()){
            joueurJeuRepository.delete(joueurJeu.get());
        }else {
            throw new JoueurJeuIntrouvableException("L'occurence Joueur-Jeu avec l'ID " + id + " n'a pas été trouvé");
        }
    }
}
