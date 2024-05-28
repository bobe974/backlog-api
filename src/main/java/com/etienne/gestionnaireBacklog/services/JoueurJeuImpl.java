package com.etienne.gestionnaireBacklog.services;

import com.etienne.gestionnaireBacklog.exceptions.JeuIntrouvableException;
import com.etienne.gestionnaireBacklog.exceptions.JoueurIntrouvableException;
import com.etienne.gestionnaireBacklog.exceptions.JoueurJeuIntrouvableException;
import com.etienne.gestionnaireBacklog.modele.Jeu;
import com.etienne.gestionnaireBacklog.modele.Joueur;
import com.etienne.gestionnaireBacklog.modele.JoueurJeu;
import com.etienne.gestionnaireBacklog.repository.JeuRepository;
import com.etienne.gestionnaireBacklog.repository.JoueurJeuRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JoueurJeuImpl {
     JoueurJeuRepository joueurJeuRepository;
     JeuRepository jeuRepository;

    public JoueurJeuImpl(JoueurJeuRepository joueurJeuRepository, JeuRepository jeuRepository){
        this.joueurJeuRepository = joueurJeuRepository;
        this.jeuRepository = jeuRepository;
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

    /**
     * Associe manuellement un jeu à JoueurJeu pour éviter "PersistentObjectException".
     * Si l'utilisateur ajoute un jeu existant, il peut être détaché du contexte de persistance.
     * En associant le jeu récupéré à JoueurJeu, on assure sa gestion par le contexte actuel.
     * @param joueurJeu
     */
    @Transactional
    public void ajouter(JoueurJeu joueurJeu) {
        Long jeuId = joueurJeu.getJeu().getId();
        Jeu jeu = jeuRepository.findById(jeuId)
                .orElseThrow(() -> new JeuIntrouvableException("Jeu non trouvé, id: " + jeuId));
        joueurJeu.setJeu(jeu);

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
