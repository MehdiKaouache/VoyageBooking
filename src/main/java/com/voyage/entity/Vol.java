package com.voyage.entity;

import jakarta.persistence.*;

@Entity
public class Vol {
    @Id
    private int id;

    private String numero;

    @ManyToOne(optional = false)
    @JoinColumn(name = "origine_id")
    private Aeroport origine;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Aeroport destination;

    private double duree;

    public Vol(){}

    public Vol(int id, String numero, Double duree, Aeroport origine, Aeroport destination) {
        this.id = id;
        this.numero = numero;
        this.duree = duree;
        this.origine = origine;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public Double getDuree() {
        return duree;
    }
    public void setDuree(Double duree) {
        this.duree = duree;
    }
    public Aeroport getOrigine() {
        return origine;
    }
    public void setOrigine(Aeroport origine) {
        this.origine = origine;
    }
    public Aeroport getDestination() {
        return destination;
    }
    public void setDestination(Aeroport destination) {
        this.destination = destination;
    }
}
