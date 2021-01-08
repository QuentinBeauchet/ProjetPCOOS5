package Exceptions;

import Model.Programme;

/**
 * Exception abstraite sur un programme
 */
public abstract class ProgramException extends RuntimeException {
    Programme p;
    public ProgramException(Programme p,String message) {
        super(message);
        this.p = p;
    }

    public Programme getP() {
        return p;
    }
}
