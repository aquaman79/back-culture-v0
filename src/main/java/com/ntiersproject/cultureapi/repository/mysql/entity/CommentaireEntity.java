package com.ntiersproject.cultureapi.repository.mysql.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="commentaire")
@Data
public class CommentaireEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private UtilisateurEntity utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_film", nullable = false)
    private FilmEntity film;

    @Column(nullable = false)
    private String texte;

    @Column(nullable = false)
    private LocalDate date;
}
