package com.voyage.service;

import com.voyage.entity.Offre;
import com.voyage.repository.OffreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OffreService {
    private final OffreRepository offreRepository;

    public OffreService(OffreRepository offreRepository) {
        this.offreRepository = offreRepository;
    }

    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    public Optional<Offre> getOffreById(int id) {
        return offreRepository.findById(id);
    }

    public Offre creerOffre(Offre offre) {
        return offreRepository.save(offre);
    }

    public Offre modifierOffre(int id, Offre nouvellesInfos) {
        Optional<Offre> offreExistante = offreRepository.findById(id);

        if (offreExistante.isPresent()) {
            Offre offreAModifier = offreExistante.get();
            offreAModifier.setPrixBase(nouvellesInfos.getPrixBase());
            offreAModifier.setDepart(nouvellesInfos.getDepart());
            return offreRepository.save(offreAModifier);
        }
        return null;
    }

    public void supprimerOffre(int id) {
        offreRepository.deleteById(id);
    }
}