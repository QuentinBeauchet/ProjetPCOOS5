package Exceptions;

import Model.Etudiant;

/**
 * Exception sur l'id d'un étudiant déjà existant
 */
public class IdEtudiantDuplicationException extends EtudiantException {

    public IdEtudiantDuplicationException(Etudiant e) {
        super(e,"L'id de : "+e+" existe déjà");
    }
}
