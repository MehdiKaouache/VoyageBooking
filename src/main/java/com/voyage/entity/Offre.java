package com.voyage.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "offre")
public class Offre {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "trajet_id")
    private Vol trajet;

    @ManyToOne
    @JoinColumn(name = "operateur_id")
    private Operateur operateur;

    private LocalDateTime depart;

    @Column(name = "prix_base")
    private double prixBase;

    public Offre() {}

    public Offre(int id, LocalDateTime depart, Double prixBase,Vol trajet ,Operateur operateur) {
        this.id = id;
        this.depart = depart;
        this.prixBase = prixBase;
        this.trajet = trajet;
        this.operateur = operateur;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDateTime getDepart() {
        return depart;
    }
    public void setDepart(LocalDateTime depart) {
        this.depart = depart;
    }
    public Double getPrixBase() {
        return prixBase;
    }
    public void setPrixBase(Double prixBase) {
        this.prixBase = prixBase;
    }
    public Vol getTrajet() {
        return trajet;
    }
    public void setTrajet(Vol trajet) {
        this.trajet = trajet;
    }
    public Operateur getOperateur() {
        return operateur;
    }
    public void setOperateur(Operateur operateur) {
        this.operateur = operateur;
    }
}
