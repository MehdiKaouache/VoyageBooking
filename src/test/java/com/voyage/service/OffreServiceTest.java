package com.voyage.service;

import com.voyage.entity.Offre;
import com.voyage.entity.Operateur;
import com.voyage.entity.Vol;
import com.voyage.repository.OffreRepository;
import com.voyage.repository.OperateurRepository;
import com.voyage.repository.VolRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OffreServiceTest {

    @Test
    @DisplayName("Recuperer toutes les offres retourne une liste")
    void recupererToutesLesOffresRetourneListe() {
        var depotOffre = mock(OffreRepository.class);
        var depotVol = mock(VolRepository.class);
        var depotOperateur = mock(OperateurRepository.class);

        when(depotOffre.findAll()).thenReturn(Arrays.asList(new Offre(), new Offre()));

        var service = new OffreService(depotOffre, depotVol, depotOperateur);
        var resultat = service.getAllOffres();

        assertEquals(2, resultat.size());
        verify(depotOffre).findAll();
    }

    @Test
    @DisplayName("Recuperer une offre par ID retourne l'offre")
    void recupererOffreParIdRetourneOffre() {
        var depotOffre = mock(OffreRepository.class);
        var depotVol = mock(VolRepository.class);
        var depotOperateur = mock(OperateurRepository.class);

        var offre = new Offre();
        offre.setId(1);
        when(depotOffre.findById(1)).thenReturn(offre);

        var service = new OffreService(depotOffre, depotVol, depotOperateur);
        var resultat = service.getOffreById(1);

        assertEquals(1, resultat.getId());
        verify(depotOffre).findById(1);
    }

    @Test
    @DisplayName("Creer une offre avec succes enregistre l'offre")
    void creerOffreEnregistreOffreQuandValide() {
        var depotOffre = mock(OffreRepository.class);
        var depotVol = mock(VolRepository.class);
        var depotOperateur = mock(OperateurRepository.class);

        var vol = new Vol();
        vol.setId(10);
        var operateur = new Operateur();
        operateur.setId(20);

        var offre = new Offre();
        offre.setTrajet(vol);
        offre.setOperateur(operateur);

        when(depotVol.findById(10)).thenReturn(Optional.of(vol));
        when(depotOperateur.findById(20)).thenReturn(Optional.of(operateur));
        when(depotOffre.save(offre)).thenReturn(offre);

        var service = new OffreService(depotOffre, depotVol, depotOperateur);
        var resultat = service.creerOffre(offre);

        assertNotNull(resultat);
        verify(depotVol).findById(10);
        verify(depotOperateur).findById(20);
        verify(depotOffre).save(offre);
    }

    @Test
    @DisplayName("Creer une offre echoue si le vol est introuvable")
    void creerOffreLanceExceptionQuandVolIntrouvable() {
        var depotOffre = mock(OffreRepository.class);
        var depotVol = mock(VolRepository.class);
        var depotOperateur = mock(OperateurRepository.class);

        var vol = new Vol();
        vol.setId(99);
        var offre = new Offre();
        offre.setTrajet(vol);
        offre.setOperateur(new Operateur());

        when(depotVol.findById(99)).thenReturn(Optional.empty());

        var service = new OffreService(depotOffre, depotVol, depotOperateur);

        assertThrows(RuntimeException.class, () -> service.creerOffre(offre));
        verify(depotOffre, never()).save(any());
    }

    @Test
    @DisplayName("Modifier une offre existante met a jour les champs")
    void modifierOffreMetAJourEtRetourneOffre() {
        var depotOffre = mock(OffreRepository.class);
        var depotVol = mock(VolRepository.class);
        var depotOperateur = mock(OperateurRepository.class);

        var offreExistante = new Offre();
        offreExistante.setId(1);
        offreExistante.setPrixBase(50.0);

        var nouvellesInfos = new Offre();
        nouvellesInfos.setPrixBase(100.0);
        nouvellesInfos.setDepart(LocalDateTime.now());

        when(depotOffre.findById(1)).thenReturn(offreExistante);
        when(depotOffre.save(offreExistante)).thenReturn(offreExistante);

        var service = new OffreService(depotOffre, depotVol, depotOperateur);
        var resultat = service.modifierOffre(1, nouvellesInfos);

        assertEquals(100.0, resultat.getPrixBase());
        verify(depotOffre).save(offreExistante);
    }

    @Test
    @DisplayName("Supprimer une offre appelle la methode de suppression du depot")
    void supprimerOffreAppelleDelete() {
        var depotOffre = mock(OffreRepository.class);
        var depotVol = mock(VolRepository.class);
        var depotOperateur = mock(OperateurRepository.class);

        var service = new OffreService(depotOffre, depotVol, depotOperateur);
        service.supprimerOffre(1);

        verify(depotOffre).deleteById(1);
    }
}