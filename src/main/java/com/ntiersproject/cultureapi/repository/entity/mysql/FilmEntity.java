package com.ntiersproject.cultureapi.repository.entity.mysql;

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

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "genre_film",
            joinColumns = { @JoinColumn(name = "id_film") },
            inverseJoinColumns = { @JoinColumn(name = "id_genre") }
    )
    private List<GenreEntity> genres;

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
