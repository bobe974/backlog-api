package com.etienne.gestionnaireBacklog.controller;


import com.etienne.gestionnaireBacklog.modele.Joueur;
import com.etienne.gestionnaireBacklog.modele.JoueurJeu;
import com.etienne.gestionnaireBacklog.services.JoueurJeuImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "joueurjeu")
public class JoueurJeuController {

    JoueurJeuImpl service;

    public JoueurJeuController(JoueurJeuImpl joueurJeuImpl){
        service = joueurJeuImpl;
    }

    @GetMapping(path = "{id}")
    public Optional<JoueurJeu> recupererParId(@PathVariable Long id){
        return service.lireParId(id);
    }

    @GetMapping()
    public List<JoueurJeu> recuperer(){
        return service.lire();
    }

    @PostMapping()
    public ResponseEntity<JoueurJeu> ajouter(@RequestBody JoueurJeu joueurJeu){
        service.ajouter(joueurJeu);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(joueurJeu.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Joueur> modifier(@RequestBody JoueurJeu joueurJeu, @PathVariable Long id){
        if(Objects.isNull(joueurJeu)){
            return ResponseEntity.noContent().build();
        }

        service.modifier(joueurJeu, id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(joueurJeu.getId())
                .toUri();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<JoueurJeu> supprimer(@PathVariable Long id){
        service.supprimer(id);
        return ResponseEntity.noContent().build();
    }

}
