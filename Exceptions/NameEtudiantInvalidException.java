package Exceptions;

import Model.Etudiant;

/**
 * Exception sur le nom d'un étudiant invalide
 */
public class NameEtudiantInvalidException extends EtudiantException {
    public NameEtudiantInvalidException(Etudiant e) {
        super(e, "le nom :\""+e.getNom()+ "\" de l'etudiant : "+ e.getId() +" est invalide." );
    }
}
