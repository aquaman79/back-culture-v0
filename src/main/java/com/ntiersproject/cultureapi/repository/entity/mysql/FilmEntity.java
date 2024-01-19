package com.ntiersproject.cultureapi.repository.entity.mysql;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="film")
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "idFilm", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions;

    @OneToMany(mappedBy = "idFilm", cascade = CascadeType.ALL)
    private List<FavoriEntity> favoris;

    @OneToMany(mappedBy = "idFilm", cascade = CascadeType.ALL)
    private List<PanierEntity> paniers;

    @Column(nullable = false)
    private String titre;

    private String description;

    @Column(name = "date_sortie")
    private LocalDate dateSortie;

    private String genre;

    private Float duree;

    @Column(name = "url_bande_annonce")
    private String urlBandeAnnonce;
}
