package com.voyage.repository;

import com.voyage.entity.Aeroport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AeroportRepository extends JpaRepository<Aeroport, Integer> {
    Aeroport findByCode(String code);
}
