package com.etienne.gestionnaireBacklog.services;

import com.etienne.gestionnaireBacklog.exceptions.JeuIntrouvableException;
import com.etienne.gestionnaireBacklog.modele.Jeu;
import com.etienne.gestionnaireBacklog.repository.JeuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JeuImpl {
    private JeuRepository jeuRepository;

    public JeuImpl(JeuRepository jeuRepository){
        this.jeuRepository = jeuRepository;
    }

    public List<Jeu> recupererLesJeux(){
        return jeuRepository.findAll();
    }

    public Optional<Jeu> recupererJeu(Long id){
        return  jeuRepository.findById(id);
    }

    public void ajouterJeu(Jeu jeu){
        jeuRepository.save(jeu);
    }

    public void modifierJeu(Jeu jeuModifier, Long id){
        Optional<Jeu> jeuExistant = jeuRepository.findById(id);
        if (jeuExistant.isPresent()){
            Jeu jeu = jeuExistant.get();
            jeu.setTitre(jeuModifier.getTitre());
            jeu.setDateSortie(jeuModifier.getDateSortie());
            jeu.setDescription(jeuModifier.getDescription());
            jeu.setEtat(jeuModifier.getEtat());
            jeuRepository.save(jeu);
        }else{
            throw new JeuIntrouvableException("Jeu introuvable");
        }

    }
    public void supprimerJeu(Long id){
        Optional<Jeu> jeuOptional = jeuRepository.findById(id);
        if (jeuOptional.isPresent()) {
            jeuRepository.deleteById(id);
        } else {
            throw new JeuIntrouvableException("Le jeu avec l'ID " + id + " n'a pas été trouvé");
        }
    }
}
