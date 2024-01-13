package com.ntiersproject.cultureapi.repository.entity.mysql;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToMany(mappedBy = "idUtilisateur", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "idUtilisateur", cascade = CascadeType.ALL)
    private List<Favori> favoris;

    @OneToMany(mappedBy = "idUtilisateur", cascade = CascadeType.ALL)
    private List<Panier> paniers;

    @Column(nullable = false)
    private String nom;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "mot_de_passe", nullable = false)
    private String MotDePasse;

    @Column(name = "date_inscription", nullable = false)
    private LocalDate dateInscription;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;


}
