package com.voyage.service;

import com.voyage.entity.Operateur;
import com.voyage.repository.OperateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperateurService {
    private final OperateurRepository operateurRepository;

    public OperateurService(OperateurRepository operateurRepository) {
        this.operateurRepository = operateurRepository;
    }

    public List<Operateur> getAllOperateurs() {
        return operateurRepository.findAll();
    }

    public Operateur creerOperateur(Operateur operateurRecu) {
        if (operateurRepository.findByCode(operateurRecu.getCode()) != null) {
            throw new RuntimeException("Cet opérateur existe déjà");
        }

        Operateur nouvelOperateur = new Operateur();
        nouvelOperateur.setCode(operateurRecu.getCode());
        nouvelOperateur.setNom(operateurRecu.getNom());

        return operateurRepository.save(nouvelOperateur);
    }

    public Operateur modifierOperateur(int id, Operateur nouvellesInfos) {
        Operateur operateurAModifier = operateurRepository.findById(id).orElse(null);
        if (operateurAModifier != null) {
            operateurAModifier.setNom(nouvellesInfos.getNom());
            return operateurRepository.save(operateurAModifier);
        }
        return null;
    }

    public void supprimerOperateur(int id) {
        operateurRepository.deleteById(id);
    }
}