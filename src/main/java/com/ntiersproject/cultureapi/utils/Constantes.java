package com.ntiersproject.cultureapi.utils;

public class Constantes {

    public static final String REGEX_PSEUDO = "^[a-zA-Z0-9_-]{4,}$";

    public static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static final String REGEX_MOT_DE_PASSE = "(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

    public static final String MESSAGE_PROBlEME_AUTHENTIFICATION = "L'authentification a échoué";

    public static final String MESSAGE_ACCES_REFUSE = "Accès à cette ressource refusé";

    public static final String MESSAGE_UTILISATEUR_NON_TROUVE = "Utilisateur non trouvé";

    public static final String MESSAGE_GENRE_NON_TROUVE = "Genre non trouvé";

    public static final String MESSAGE_MAUVAIS_FORMAT_REQUETE = "Mauvais format de la requête";
}
