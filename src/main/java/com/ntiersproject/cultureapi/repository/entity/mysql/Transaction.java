package com.ntiersproject.cultureapi.repository.entity.mysql;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur idUtilisateur;

    @ManyToOne
    @JoinColumn(name = "id_film", nullable = false)
    private Film idFilm;

    @Column(name = "is_achat", nullable = false)
    private Boolean isAchat;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Double prix;

}
