package com.ntiersproject.cultureapi.repository.mysql.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="utilisateur")
@Data
public class UtilisateurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idUtilisateur", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idUtilisateur", cascade = CascadeType.ALL)
    private List<FavoriEntity> favoris;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idUtilisateur", cascade = CascadeType.ALL)
    private List<PanierEntity> paniers;

    @Column(nullable = false)
    private String nom;

    @Column(unique = true, nullable = false)
    private String pseudo;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;

    @Column(name = "date_inscription", nullable = false)
    private LocalDate dateInscription;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;


}
