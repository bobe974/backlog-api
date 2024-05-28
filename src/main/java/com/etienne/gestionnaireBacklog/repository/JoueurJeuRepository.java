package com.etienne.gestionnaireBacklog.repository;

import com.etienne.gestionnaireBacklog.modele.JoueurJeu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoueurJeuRepository extends JpaRepository<JoueurJeu,Long> {
    List<JoueurJeu> findByJoueurId(Long joueurId);
    JoueurJeu findJeuJoueurIdByJoueurIdAndJeuId(Long joueurId, Long jeuId);

}
