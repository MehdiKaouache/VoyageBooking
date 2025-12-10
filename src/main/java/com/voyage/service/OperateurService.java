package com.voyage.service;

import com.voyage.entity.Operateur;
import com.voyage.repository.OperateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateurService {
    private final OperateurRepository operateurRepository;

    public OperateurService(OperateurRepository operateurRepository) {
        this.operateurRepository = operateurRepository;
    }

    public List<Operateur> getAllOperateurs() {
        return operateurRepository.findAll();
    }

    public Operateur creerOperateur(Operateur operateur) {
        if (operateurRepository.findByCode(operateur.getCode()) != null) {
            throw new RuntimeException("Cet opérateur existe déjà");
        }
        return operateurRepository.save(operateur);
    }
}
