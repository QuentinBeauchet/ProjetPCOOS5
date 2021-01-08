package Exceptions;

import Model.UE;

/**
 * Exception sur l'id D'une UE invalide
 */
public class IdUeInvalidException extends UEException {
    public IdUeInvalidException(UE ue) {
        super(ue,"Id ue incorrect.");
    }
}
