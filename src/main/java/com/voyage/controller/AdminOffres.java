package com.voyage.controller;

import com.voyage.entity.Offre;
import com.voyage.service.OffreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/offres")
public class AdminOffres {
    private final OffreService offreService;

    public AdminOffres(OffreService offreService) {
        this.offreService = offreService;
    }

    @GetMapping("/all")
    public List<Offre> getAllOffres() {
        return offreService.getAllOffres();
    }

    @GetMapping("/{id}")
    public Optional<Offre> getOffreById(@PathVariable("id") int id) {
        return offreService.getOffreById(id);
    }

    @PostMapping("/create")
    public Offre creerOffre(@RequestBody Offre offre) {
        return offreService.creerOffre(offre);
    }

    @PutMapping("/{id}")
    public Offre modifierOffre(@PathVariable("id") int id, @RequestBody Offre offre) {
        return offreService.modifierOffre(id, offre);
    }

    @DeleteMapping("/{id}")
    public void supprimerOffre(@PathVariable("id") int id) {
        offreService.supprimerOffre(id);
    }
}