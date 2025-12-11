package com.voyage.service;

import com.voyage.entity.Aeroport;
import com.voyage.entity.Offre;
import com.voyage.entity.Vol;
import com.voyage.repository.AeroportRepository;
import com.voyage.repository.OffreRepository;
import com.voyage.repository.VolRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolService {
    private final OffreRepository offreRepository;
    private final VolRepository volRepository;
    private final AeroportRepository aeroportRepository;

    public VolService(OffreRepository offreRepository, VolRepository volRepository, AeroportRepository aeroportRepository) {
        this.offreRepository = offreRepository;
        this.volRepository = volRepository;
        this.aeroportRepository = aeroportRepository;
    }

    public List<Vol> getAllVols() {
        return volRepository.findAll();
    }

    public List<Offre> rechercherVols() {
        return offreRepository.findAll();
    }

    public List<Offre> filtrerVols(List<Offre> offres, String origine, String destination, LocalDateTime dateDepart) {
        return offres.stream()
                .filter(offre -> offre.getTrajet() != null)
                .filter(offre -> offre.getTrajet().getOrigine().getCode().equalsIgnoreCase(origine))
                .filter(offre -> offre.getTrajet().getDestination().getCode().equalsIgnoreCase(destination))
                .filter(offre -> offre.getDepart().isAfter(dateDepart))
                .filter(offre -> offre.getPrixBase() > 0)
                .filter(offre -> offre.getOperateur() != null)
                .collect(Collectors.toList());
    }

    public Vol creerVol(Vol vol) {
        Aeroport origine = aeroportRepository.findById(vol.getOrigine().getId())
                .orElseThrow(() -> new RuntimeException("Aéroport d'origine introuvable"));

        Aeroport destination = aeroportRepository.findById(vol.getDestination().getId())
                .orElseThrow(() -> new RuntimeException("Aéroport de destination introuvable"));

        vol.setOrigine(origine);
        vol.setDestination(destination);
        return volRepository.save(vol);
    }

    public Vol modifierVol(int id, Vol nouvellesInfos) {
        Vol volAModifier = volRepository.findById(id).orElse(null);

        if (volAModifier != null) {
            volAModifier.setNumero(nouvellesInfos.getNumero());
            volAModifier.setDuree(nouvellesInfos.getDuree());

            if (nouvellesInfos.getOrigine() != null) {
                volAModifier.setOrigine(nouvellesInfos.getOrigine());
            }
            if (nouvellesInfos.getDestination() != null) {
                volAModifier.setDestination(nouvellesInfos.getDestination());
            }

            return volRepository.save(volAModifier);
        }
        return null;
    }

    public boolean supprimerVol(int id) {
        if (volRepository.existsById(id)) {
            volRepository.deleteById(id);
            return true;
        }
        return false;
    }
}