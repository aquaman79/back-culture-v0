package com.ntiersproject.cultureapi.repository;

import com.ntiersproject.cultureapi.repository.entity.mysql.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurEntity, Integer> {
    UtilisateurEntity findByEmail(String email);
}
