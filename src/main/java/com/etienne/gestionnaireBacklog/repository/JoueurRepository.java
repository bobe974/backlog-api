package com.etienne.gestionnaireBacklog.repository;

import com.etienne.gestionnaireBacklog.modele.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur,Long> {
}
