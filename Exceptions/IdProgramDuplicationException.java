package Exceptions;

import Model.Programme;

/**
 * Exception sur l'id d'un programme déjà existant
 */
public class IdProgramDuplicationException extends ProgramException {
    public IdProgramDuplicationException(Programme p) {
        super(p,"L'id de : "+p+" existe déjà");
    }
}
