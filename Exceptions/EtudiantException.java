package Exceptions;

import Model.Etudiant;

/**
 * Exception abstraite lors d'une erreure sur un étudiant
 */
public abstract class EtudiantException extends RuntimeException {
    Etudiant e;

    EtudiantException(Etudiant e,String message){
        super(message);
        this.e=e;
    }

    public Etudiant getE() {
        return e;
    }
}
