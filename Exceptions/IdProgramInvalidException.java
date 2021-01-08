package Exceptions;

import Model.Programme;

/**
 * Exception sur l'id d'un programme invalide
 */
public class IdProgramInvalidException extends ProgramException {
    public IdProgramInvalidException(Programme p) {
        super(p,"Id de programme invalide.");
    }
}
