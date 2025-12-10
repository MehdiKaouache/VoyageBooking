package com.voyage.repository;

import com.voyage.entity.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Integer> {
    List<Offre> findByTrajetOrigineCodeAndTrajetDestinationCodeAndDepartAfter(
            String origineCode,
            String destinationCode,
            LocalDateTime dateDepart
    );
}
