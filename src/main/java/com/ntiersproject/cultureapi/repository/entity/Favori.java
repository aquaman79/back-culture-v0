package com.ntiersproject.cultureapi.repository.entity;

import jakarta.persistence.*;

@Entity
public class Favori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur idUtilisateur;

    @ManyToOne
    @JoinColumn(name = "id_film", nullable = false)
    private Film idFilm;

}
