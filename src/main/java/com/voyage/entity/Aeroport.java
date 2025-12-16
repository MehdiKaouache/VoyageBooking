package com.voyage.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "aeroport")
public class Aeroport {
    @Id
    private int id;
    private String code;
    private String ville;

    public Aeroport() {}

    public Aeroport(int id, String code, String nom, String ville) {
        this.id = id;
        this.code = code;
        this.ville = ville;
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
    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
}
