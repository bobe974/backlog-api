package com.etienne.gestionnaireBacklog.repository;

import com.etienne.gestionnaireBacklog.modele.JoueurJeu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoueurJeuRepository extends JpaRepository<JoueurJeu,Long> {
}
