package Exceptions;

import Model.UE;

/**
 * Exception de nom d'UE invalide
 */
public class NameUeInvalidException extends UEException {
    public NameUeInvalidException(UE ue) {
        super(ue,  "le nom :\""+ue.getNom()+ "\" de l'UE : "+ ue.getId() +" est invalide.");
    }
}
