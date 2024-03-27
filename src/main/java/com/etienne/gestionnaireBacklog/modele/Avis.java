package com.etienne.gestionnaireBacklog.modele;
import com.etienne.gestionnaireBacklog.enums.TypeAvis;
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

    public Long getId() {
        return id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public TypeAvis getTypeAvis() {
        return typeAvis;
    }

    public void setTypeAvis(TypeAvis typeAvis) {
        this.typeAvis = typeAvis;
    }
}
