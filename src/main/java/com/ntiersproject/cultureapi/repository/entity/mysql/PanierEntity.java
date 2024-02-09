package com.ntiersproject.cultureapi.repository.entity.mysql;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="panier")
@Data
public class PanierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private UtilisateurEntity idUtilisateur;

    @ManyToOne
    @JoinColumn(name = "id_film", nullable = false)
    private FilmEntity idFilm;
}
