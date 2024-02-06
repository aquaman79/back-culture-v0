package com.ntiersproject.cultureapi.repository.entity.mysql;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="genre")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String libelle;

    @ManyToMany(mappedBy = "genres")
    private List<FilmEntity> films;
}
