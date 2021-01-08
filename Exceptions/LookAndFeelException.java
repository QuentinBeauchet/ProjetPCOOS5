package Exceptions;

/**
 * Exception sur le lookAndFeel
 */
public class LookAndFeelException extends NullPointerException {

    /**
     * Lancé quand il y a un probleme de Look&Feel.
     */

    public LookAndFeelException(){
        System.out.println("Le Programme a rencontré un problème lors du réglage de son Look&Feel.");
    }

}
