package Exceptions;

import Model.UE;

/**
 * Exception lorsque le coef est invalide
 */
public class CoefUeInvalidException extends UEException {
    public CoefUeInvalidException(UE ue) {
        super(ue, "le coef : "+ue.getCoef()+"  de l'UE : "+ ue.getId() +" est invalide.");
    }
}
