package com.voyage.service;

import com.voyage.entity.Offre;
import com.voyage.repository.OffreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VolService {
    private final OffreRepository offreRepository;

    public VolService(OffreRepository offreRepository) {
        this.offreRepository = offreRepository;
    }

    public List<Offre> rechercherVols(String origine, String destination, LocalDateTime dateDepart) {
        return offreRepository.findByTrajetOrigineCodeAndTrajetDestinationCodeAndDepartAfter(origine, destination, dateDepart);
    }
}
