package com.voyage.controller;

import com.voyage.entity.Offre;
import com.voyage.service.VolService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/recherche")
public class SearchController {
    private final VolService volService;

    public SearchController(VolService volService) {
        this.volService = volService;
    }

    @GetMapping
    public List<Offre> rechercherVols(
            @RequestParam String origine,
            @RequestParam String destination,
            @RequestParam LocalDateTime dateDepart
    ){

        List<Offre> toutesLesOffres = volService.rechercherVols();

        return volService.filtrerVols(toutesLesOffres, origine, destination, dateDepart);
    }
}