package com.etienne.gestionnaireBacklog.repository;

import com.etienne.gestionnaireBacklog.modele.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisRepository extends JpaRepository<Avis, Long> {
}
