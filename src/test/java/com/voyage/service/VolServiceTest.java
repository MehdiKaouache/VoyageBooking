package com.voyage.service;

import com.voyage.entity.Aeroport;
import com.voyage.entity.Offre;
import com.voyage.entity.Operateur;
import com.voyage.entity.Vol;
import com.voyage.repository.AeroportRepository;
import com.voyage.repository.OffreRepository;
import com.voyage.repository.VolRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VolServiceTest {

    @Test
    @DisplayName("Recuperer tous les vols retourne une liste")
    void recupererTousLesVolsRetourneListe() {
        var depotOffre = mock(OffreRepository.class);
        var depotVol = mock(VolRepository.class);
        var depotAeroport = mock(AeroportRepository.class);

        when(depotVol.findAll()).thenReturn(Arrays.asList(new Vol(), new Vol()));

        var service = new VolService(depotOffre, depotVol, depotAeroport);
        var resultat = service.getAllVols();

        assertEquals(2, resultat.size());
        verify(depotVol).findAll();
    }

    @Test
    @DisplayName("Rechercher les vols retourne les offres")
    void rechercherVolsRetourneOffres() {
        var depotOffre = mock(OffreRepository.class);
        var depotVol = mock(VolRepository.class);
        var depotAeroport = mock(AeroportRepository.class);

        when(depotOffre.findAll()).thenReturn(Collections.singletonList(new Offre()));

        var service = new VolService(depotOffre, depotVol, depotAeroport);
        var resultat = service.rechercherVols();

        assertEquals(1, resultat.size());
        verify(depotOffre).findAll();
    }

    @Test
    @DisplayName("Filtrer les vols retourne les offres correspondantes")
    void filtrerVolsRetourneOffresCorrespondantes() {
        var depotOffre = mock(OffreRepository.class);
        var depotVol = mock(VolRepository.class);
        var depotAeroport = mock(AeroportRepository.class);
        var service = new VolService(depotOffre, depotVol, depotAeroport);

        var aeroportDepart = new Aeroport();
        aeroportDepart.setCode("YUL");
        var aeroportArrivee = new Aeroport();
        aeroportArrivee.setCode("CDG");

        var vol = new Vol();
        vol.setOrigine(aeroportDepart);
        vol.setDestination(aeroportArrivee);

        var offre = new Offre();
        offre.setTrajet(vol);
        offre.setDepart(LocalDateTime.now().plusDays(5));
        offre.setPrixBase(500.0);
        offre.setOperateur(new Operateur());

        var resultat = service.filtrerVols(Collections.singletonList(offre), "YUL", "CDG", LocalDateTime.now()
        );

        assertEquals(1, resultat.size());
    }

    @Test
    @DisplayName("Creer un vol enregistre le vol avec succes")
    void creerVolEnregistreVolQuandAeroportsTrouves() {
        var depotOffre = mock(OffreRepository.class);
        var depotVol = mock(VolRepository.class);
        var depotAeroport = mock(AeroportRepository.class);

        var origine = new Aeroport();
        origine.setId(1);
        var destination = new Aeroport();
        destination.setId(2);

        var vol = new Vol();
        vol.setOrigine(origine);
        vol.setDestination(destination);

        when(depotAeroport.findById(1)).thenReturn(Optional.of(origine));
        when(depotAeroport.findById(2)).thenReturn(Optional.of(destination));
        when(depotVol.save(vol)).thenReturn(vol);

        var service = new VolService(depotOffre, depotVol, depotAeroport);
        var resultat = service.creerVol(vol);

        assertNotNull(resultat);
        verify(depotAeroport).findById(1);
        verify(depotAeroport).findById(2);
        verify(depotVol).save(vol);
    }

    @Test
    @DisplayName("Modifier un vol existant met a jour les attributs")
    void modifierVolMetAJourEtRetourneVol() {
        var depotOffre = mock(OffreRepository.class);
        var depotVol = mock(VolRepository.class);
        var depotAeroport = mock(AeroportRepository.class);

        var volExistant = new Vol();
        volExistant.setId(1);
        volExistant.setNumero("V100");

        var nouvellesInfos = new Vol();
        nouvellesInfos.setNumero("V200");
        nouvellesInfos.setDuree(8.0);

        when(depotVol.findById(1)).thenReturn(Optional.of(volExistant));
        when(depotVol.save(volExistant)).thenReturn(volExistant);

        var service = new VolService(depotOffre, depotVol, depotAeroport);
        var resultat = service.modifierVol(1, nouvellesInfos);

        assertEquals("V200", resultat.getNumero());
        verify(depotVol).save(volExistant);
    }

    @Test
    @DisplayName("Supprimer un vol retourne vrai quand il existe")
    void supprimerVolRetourneVraiQuandExiste() {
        var depotOffre = mock(OffreRepository.class);
        var depotVol = mock(VolRepository.class);
        var depotAeroport = mock(AeroportRepository.class);

        when(depotVol.existsById(1)).thenReturn(true);

        var service = new VolService(depotOffre, depotVol, depotAeroport);
        var resultat = service.supprimerVol(1);

        assertTrue(resultat);
        verify(depotVol).deleteById(1);
    }
}