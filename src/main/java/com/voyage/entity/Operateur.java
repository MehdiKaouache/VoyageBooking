package com.voyage.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "operateur")
public class Operateur {
    @Id
    private int id;
    private String code;
    private String nom;

    public Operateur() {}

    public Operateur(int id, String code, String nom) {
        this.id = id;
        this.code = code;
        this.nom = nom;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getNom() {
        return nom;
    }
}
