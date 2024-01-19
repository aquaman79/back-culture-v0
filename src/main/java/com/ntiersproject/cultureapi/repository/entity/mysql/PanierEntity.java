package com.ntiersproject.cultureapi.repository.entity.mysql;

import jakarta.persistence.*;

@Entity
@Table(name="panier")
public class PanierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private UtilisateurEntity idUtilisateur;

    @ManyToOne
    @JoinColumn(name = "id_film", nullable = false)
    private FilmEntity idFilm;
}
