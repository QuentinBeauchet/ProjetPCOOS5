package Exceptions;

/**
 * Exception lors d'un id non trouvé
 */
public class IdUnknownException extends RuntimeException {
    public IdUnknownException(String id){
        super("l'id : "+id + " n'existe pas.");
    }
}
