package com.voyage.repository;

import com.voyage.entity.Vol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolRepository extends JpaRepository<Vol, Integer> {
    Vol findByNumero(String numero);
}
