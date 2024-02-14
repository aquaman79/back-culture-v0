package com.ntiersproject.cultureapi.repository.mysql.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="genre")
@Data
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String libelle;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "genres", cascade = CascadeType.REMOVE)
    private List<FilmEntity> films;
}
