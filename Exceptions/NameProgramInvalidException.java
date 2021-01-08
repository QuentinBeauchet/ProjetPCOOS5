package Exceptions;

import Model.Programme;

/**
 * Exception sur un nom de programme invalide
 */
public class NameProgramInvalidException extends ProgramException {
    public NameProgramInvalidException(Programme p) {
        super(p,  "le nom :\""+p.getNom()+ "\" du programme : "+ p.getId() +" est invalide.");
    }
}
