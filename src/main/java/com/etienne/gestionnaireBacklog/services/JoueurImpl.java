package com.etienne.gestionnaireBacklog.services;

import com.etienne.gestionnaireBacklog.exceptions.JeuIntrouvableException;
import com.etienne.gestionnaireBacklog.exceptions.JoueurIntrouvableException;
import com.etienne.gestionnaireBacklog.modele.Jeu;
import com.etienne.gestionnaireBacklog.modele.Joueur;
import com.etienne.gestionnaireBacklog.repository.JoueurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JoueurImpl {
    JoueurRepository joueurRepository;

    public JoueurImpl(JoueurRepository joueurRepository){
        this.joueurRepository = joueurRepository;
    }

    public Optional<Joueur> lireJoueur(Long id ){
        Optional<Joueur> joueur = joueurRepository.findById(id);
        if(joueur.isPresent()){
            return joueur;
        }else {
            throw new JoueurIntrouvableException("Le joueur avec l'ID " + id + " n'a pas été trouvé");
        }
    }
    public List<Joueur>lireJoueurs(){
        return joueurRepository.findAll();
    }

    public void ajouterJoueur(Joueur joueur){
        joueurRepository.save(joueur);
    }

    public void modifierJoueur(Joueur joueur, long id){
        Joueur joueurBdd;

        if(joueurRepository.findById(id).isPresent()){
            joueurBdd = joueurRepository.findById(id).get();
            joueurBdd.setAge((joueur.getAge() != null) ? joueur.getAge() : joueurBdd.getAge());
            joueurBdd.setJeuFavoris((joueur.getJeuFavoris() != null) ? joueur.getJeuFavoris() : joueurBdd.getJeuFavoris());
            joueurRepository.save(joueurBdd);
        }else{
            throw new JoueurIntrouvableException("Joueur introuvable");
        }
    }

    public void supprimerJoueur(Long id){
        Optional<Joueur> joueurOptional = joueurRepository.findById(id);
        if (joueurOptional.isPresent()) {
            joueurRepository.deleteById(id);
        } else {
            throw new JoueurIntrouvableException("Le joueur avec l'ID " + id + " n'a pas été trouvé");
        }
    }
}
