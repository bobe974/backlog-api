package com.etienne.gestionnaireBacklog.controller;

import com.etienne.gestionnaireBacklog.exceptions.JeuIntrouvableException;
import com.etienne.gestionnaireBacklog.modele.Jeu;
import com.etienne.gestionnaireBacklog.services.JeuImpl;
import jakarta.persistence.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "jeu")
public class JeuController {
    private JeuImpl jeuService;

    public JeuController(JeuImpl jeuService){
        this.jeuService = jeuService;
    }
    @CrossOrigin
    @GetMapping
    public List<Jeu> recupererLesJeux(){
        return jeuService.recupererLesJeux();
    }
    @CrossOrigin
    @GetMapping(path = "{id}")
    public Optional<Jeu> recupererJeu(@PathVariable Long id){
        Optional<Jeu> jeu = jeuService.recupererJeu(id);
        if (jeu.isEmpty()){
            throw  new JeuIntrouvableException("Le jeu avec l'id "+ id + " est introuvable !");
        }
        return jeu;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Jeu> ajouterJeu(@RequestBody Jeu jeu){
        if (Objects.isNull(jeu)) {
            return ResponseEntity.noContent().build();
        }
        jeuService.ajouterJeu(jeu);
        //renvoyer l'uri du jeu ajouté au client
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(jeu.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @CrossOrigin
    @PutMapping(path = "{id}")
    public ResponseEntity<Jeu> modifierJeu(@RequestBody Jeu jeu, @PathVariable Long id){
        if(Objects.isNull(jeu)){
            return ResponseEntity.noContent().build();
        }
        jeuService.modifierJeu(jeu, id);
        //renvoyer l'uri du jeu ajouté au client
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(jeu.getId())
                .toUri();
        return ResponseEntity.ok().build();
    }
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerJeu(@PathVariable Long id){
        try {
            jeuService.supprimerJeu(id);
            return ResponseEntity.noContent().build();
        }catch (JeuIntrouvableException e){
            return ResponseEntity.notFound().build();
        }

    }
}
