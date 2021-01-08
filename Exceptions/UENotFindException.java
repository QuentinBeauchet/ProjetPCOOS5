package Exceptions;

import Model.UE;

/**
 * Exception lorsque une UE n'a pas été trouvé
 */
public class UENotFindException extends UEException{

    public UENotFindException(UE ue) {
        super(ue, "L'ue "+ue.getNom()+ " n'a pas été trouvée");
    }
}
