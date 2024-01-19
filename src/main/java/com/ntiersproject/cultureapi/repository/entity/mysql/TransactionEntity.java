package com.ntiersproject.cultureapi.repository.entity.mysql;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private UtilisateurEntity idUtilisateur;

    @ManyToOne
    @JoinColumn(name = "id_film", nullable = false)
    private FilmEntity idFilm;

    @Column(name = "is_achat", nullable = false)
    private Boolean isAchat;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Double prix;

}
