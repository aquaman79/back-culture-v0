package com.ntiersproject.cultureapi.repository.mysql.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="transaction")
@Data
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
