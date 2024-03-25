package com.etienne.gestionnaireBacklog.repository;

import com.etienne.gestionnaireBacklog.modele.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JeuRepository extends JpaRepository<Jeu,Long> {

}
