package Controller;

import Model.PopUp;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Classe du popup
 */
public class Window implements WindowListener {

    /**
     * EventListener de la fermeture de la JFrame de home qui appele PopUpConfirmation.
     */
    public Window(){}

    @Override
    public void windowOpened(WindowEvent windowEvent) { }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        new PopUp("Quitter");
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) { }

    @Override
    public void windowIconified(WindowEvent windowEvent) { }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) { }

    @Override
    public void windowActivated(WindowEvent windowEvent) { }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) { }
}
