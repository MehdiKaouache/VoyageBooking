package com.voyage.controller;

import com.voyage.entity.Aeroport;
import com.voyage.entity.Offre;
import com.voyage.entity.Operateur;
import com.voyage.entity.Vol;
import com.voyage.service.AeroportService;
import com.voyage.service.OffreService;
import com.voyage.service.OperateurService;
import com.voyage.service.VolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminOffres {
    private final OffreService offreService;
    private final AeroportService aeroportService;
    private final OperateurService operateurService;
    private final VolService volService;

    public AdminOffres(OffreService offreService, AeroportService aeroportService, OperateurService operateurService, VolService volService) {
        this.offreService = offreService;
        this.aeroportService = aeroportService;
        this.operateurService = operateurService;
        this.volService = volService;
    }

    @GetMapping("/offres/all")
    public List<Offre> getAllOffres() {
        return offreService.getAllOffres();
    }

    @GetMapping("/offres/{id}")
    public Offre getOffreById(@PathVariable("id") int id) {
        return offreService.getOffreById(id);
    }

    @PostMapping("/offres/create")
    public Offre creerOffre(@RequestBody Offre offre) {
        return offreService.creerOffre(offre);
    }

    @PutMapping("/offres/{id}")
    public Offre modifierOffre(@PathVariable("id") int id, @RequestBody Offre offre) {
        return offreService.modifierOffre(id, offre);
    }

    @DeleteMapping("/offres/{id}")
    public void supprimerOffre(@PathVariable("id") int id) {
        offreService.supprimerOffre(id);
    }

    @PostMapping("/aeroports")
    public Aeroport creerAeroport(@RequestBody Aeroport aeroport) {
        return aeroportService.creerAeroport(aeroport);
    }

    @GetMapping("/aeroports")
    public List<Aeroport> getAeroports() {
        return aeroportService.getAllAeroports();
    }

    @PutMapping("/aeroports/{id}")
    public Aeroport modifierAeroport(@PathVariable("id") int id, @RequestBody Aeroport aeroport) {
        return aeroportService.modifierAeroport(id, aeroport);
    }

    @DeleteMapping("/aeroports/{id}")
    public void supprimerAeroport(@PathVariable("id") int id) {
        aeroportService.supprimerAeroport(id);
    }

    @PostMapping("/operateurs")
    public Operateur creerOperateur(@RequestBody Operateur operateur) {
        return operateurService.creerOperateur(operateur);
    }

    @GetMapping("/operateurs")
    public List<Operateur> getOperateurs() {
        return operateurService.getAllOperateurs();
    }

    @PutMapping("/operateurs/{id}")
    public Operateur modifierOperateur(@PathVariable("id") int id, @RequestBody Operateur operateur) {
        return operateurService.modifierOperateur(id, operateur);
    }

    @DeleteMapping("/operateurs/{id}")
    public void supprimerOperateur(@PathVariable("id") int id) {
        operateurService.supprimerOperateur(id);
    }

    @PostMapping("/vols")
    public Vol creerVol(@RequestBody Vol vol) {
        return volService.creerVol(vol);
    }

    @GetMapping("/vols")
    public List<Vol> getVols() {
        return volService.getAllVols();
    }

    @PutMapping("/vols/{id}")
    public Vol modifierVol(@PathVariable("id") int id, @RequestBody Vol vol) {
        return volService.modifierVol(id, vol);
    }

    @DeleteMapping("/vols/{id}")
    public void supprimerVol(@PathVariable("id") int id) {
        volService.supprimerVol(id);
    }
}