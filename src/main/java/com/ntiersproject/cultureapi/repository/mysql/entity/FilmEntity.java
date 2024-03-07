package com.ntiersproject.cultureapi.repository.mysql.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="film")
@Data
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<FavoriEntity> favoris;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<PanierEntity> paniers;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "film", cascade = CascadeType.ALL)
    private List<CommentaireEntity> commentaires;

    @Column(unique = true, nullable = false)
    private String titre;

    private String genre;

    private String description;

    @Column(name = "date_sortie")
    private LocalDate dateSortie;

    private Float duree;

    @Column(nullable = false)
    private String urlBandeAnnonce;
}
