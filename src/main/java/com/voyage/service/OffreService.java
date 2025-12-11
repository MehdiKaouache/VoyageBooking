package com.voyage.service;

import com.voyage.entity.Offre;
import com.voyage.entity.Operateur;
import com.voyage.entity.Vol;
import com.voyage.repository.OffreRepository;
import com.voyage.repository.OperateurRepository;
import com.voyage.repository.VolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffreService {

    private final OffreRepository offreRepository;
    private final VolRepository volRepository;
    private final OperateurRepository operateurRepository;

    public OffreService(OffreRepository offreRepository, VolRepository volRepository, OperateurRepository operateurRepository) {
        this.offreRepository = offreRepository;
        this.volRepository = volRepository;
        this.operateurRepository = operateurRepository;
    }

    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    public Offre getOffreById(int id) {
        return offreRepository.findById(id);
    }

    public Offre creerOffre(Offre offre) {
        if (offre.getTrajet() == null || offre.getOperateur() == null) {
            throw new RuntimeException("Le vol et l'opérateur sont obligatoires");
        }

        Vol vol = volRepository.findById(offre.getTrajet().getId())
                .orElseThrow(() -> new RuntimeException("Vol introuvable"));

        Operateur operateur = operateurRepository.findById(offre.getOperateur().getId())
                .orElseThrow(() -> new RuntimeException("Opérateur introuvable"));

        offre.setTrajet(vol);
        offre.setOperateur(operateur);

        return offreRepository.save(offre);
    }

    public Offre modifierOffre(int id, Offre nouvellesInfos) {
        Offre offreAModifier = offreRepository.findById(id);

        if (offreAModifier != null) {
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