package com.etienne.gestionnaireBacklog.controller;

import com.etienne.gestionnaireBacklog.modele.Joueur;
import com.etienne.gestionnaireBacklog.services.JoueurImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "joueur")
public class JoueurController {

    JoueurImpl joueurService;

    public JoueurController(JoueurImpl joueurService){
        this.joueurService = joueurService;
    }

    @GetMapping(path = "{id}")
    public Optional<Joueur> recupererJoueur(@PathVariable Long id){
        return joueurService.lireJoueur(id);
    }

    @GetMapping()
    public List<Joueur> recupererLesJoueurs(){
        return joueurService.lireJoueurs();
    }

    @PostMapping()
    public ResponseEntity<Joueur> ajouterJoueur(@RequestBody Joueur joueur){
        joueurService.ajouterJoueur(joueur);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(joueur.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    @PutMapping(path = "{id}")
    public ResponseEntity<Joueur> modifierJoueur(@RequestBody Joueur joueur, @PathVariable Long id){
        if(Objects.isNull(joueur)){
            return ResponseEntity.noContent().build();
        }

        joueurService.modifierJoueur(joueur, id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(joueur.getId())
                .toUri();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Joueur> supprimerJoueur(@PathVariable Long id){
        joueurService.supprimerJoueur(id);
        return ResponseEntity.noContent().build();
    }


}
