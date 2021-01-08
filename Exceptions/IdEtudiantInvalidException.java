package Exceptions;

import Model.Etudiant;

/**
 * Exception sur l'id d'un étudiant incorrect
 */
public class IdEtudiantInvalidException extends EtudiantException {

    public IdEtudiantInvalidException(Etudiant e) {
        super(e, "L'id de l'etudiant est invalide.");
    }
}
