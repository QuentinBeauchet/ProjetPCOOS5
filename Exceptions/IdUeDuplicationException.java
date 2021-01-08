package Exceptions;

import Model.UE;

/**
 * Exception sur l'id d'une UE invalide
 */
public class IdUeDuplicationException extends UEException {

    public IdUeDuplicationException(UE ue) {
        super(ue,"L'id de : " +ue + " existe déjà.");
    }
}
