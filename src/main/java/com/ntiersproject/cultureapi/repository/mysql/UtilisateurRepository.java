package com.ntiersproject.cultureapi.repository.mysql;

import com.ntiersproject.cultureapi.repository.mysql.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurEntity, Long> {
    boolean existsByPseudo(String pseudo);

    boolean existsByEmail(String email);

    UtilisateurEntity findByPseudoOrEmail(String pseudo, String email);

    @Override
    List<UtilisateurEntity> findAllById(Iterable<Long> ids);
}
