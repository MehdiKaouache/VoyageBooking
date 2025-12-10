package com.voyage.service;

import com.voyage.entity.Aeroport;
import com.voyage.repository.AeroportRepository;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AeroportServiceTest {
    AeroportRepository aeroportRepository; //C'est encore Null
    AeroportService aeroportService = new AeroportService(aeroportRepository);

    @Test
    public void testCreerAeroport(){
        Aeroport aeroport = new Aeroport();
        aeroport.setCode("ONL");
        aeroport.setId(1);
        aeroport.setVille("Montreal");
        aeroportService.creerAeroport(aeroport);
        List<Aeroport> expected = new ArrayList<>();
        expected.add(aeroport);
        assertEquals(expected, aeroportService.getAllAeroports());
    }
}
