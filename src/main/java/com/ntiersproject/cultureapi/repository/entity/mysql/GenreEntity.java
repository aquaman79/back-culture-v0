package com.ntiersproject.cultureapi.repository.entity.mysql;

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

    @ManyToMany(mappedBy = "genres")
    private List<FilmEntity> films;
}
