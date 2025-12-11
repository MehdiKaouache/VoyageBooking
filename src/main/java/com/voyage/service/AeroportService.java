package com.voyage.service;

import com.voyage.entity.Aeroport;
import com.voyage.repository.AeroportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AeroportService {
    private final AeroportRepository aeroportRepository;

    public AeroportService(AeroportRepository aeroportRepository) {
        this.aeroportRepository = aeroportRepository;
    }

    public List<Aeroport> getAllAeroports() {
        return aeroportRepository.findAll();
    }

    public Aeroport creerAeroport(Aeroport aeroportRecu) {
        Aeroport existant = aeroportRepository.findByCode(aeroportRecu.getCode());
        if (existant != null) {
            throw new RuntimeException("Cet aéroport existe déjà");
        }

        Aeroport nouvelAeroport = new Aeroport();
        nouvelAeroport.setCode(aeroportRecu.getCode());
        nouvelAeroport.setVille(aeroportRecu.getVille());

        return aeroportRepository.save(nouvelAeroport);
    }

    public Aeroport modifierAeroport(int id, Aeroport nouvellesInfos) {
        Aeroport aeroportAModifier = aeroportRepository.findById(id).orElse(null);
        if (aeroportAModifier != null) {
            aeroportAModifier.setVille(nouvellesInfos.getVille());
            return aeroportRepository.save(aeroportAModifier);
        }
        return null;
    }

    public void supprimerAeroport(int id) {
        aeroportRepository.deleteById(id);
    }
}