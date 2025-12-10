package com.voyage.service;

import com.voyage.entity.Aeroport;
import com.voyage.repository.AeroportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AeroportService {
    private final AeroportRepository aeroportRepository;

    public AeroportService(AeroportRepository aeroportRepository) {
        this.aeroportRepository = aeroportRepository;
    }

    public List<Aeroport> getAllAeroports() {
        return aeroportRepository.findAll();
    }

    public Aeroport creerAeroport(Aeroport aeroport) {
        Aeroport existant =  aeroportRepository.findByCode(aeroport.getCode());
        if (existant != null) {
            throw new RuntimeException("Cet aéroport existe déjà");
        }
        return aeroportRepository.save(aeroport);
    }
}
