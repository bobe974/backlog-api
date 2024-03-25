package com.etienne.gestionnaireBacklog.modele;
import enums.TypeAvis;
import jakarta.persistence.*;

@Entity
@Table(name = "avis")
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int note;
    private String justification;

    @Enumerated(EnumType.STRING)
    private TypeAvis typeAvis;

    // Getters et Setters
}
