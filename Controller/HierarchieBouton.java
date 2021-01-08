package Controller;

import Model.HierarchieCreation;
import View.Home;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HierarchieBouton implements MouseListener{
    private final Home home;

    /**
     * Classe qui instancie une nouvelle HierarchieCreation
     * quand click sur le boutton hierarchie.
     * @param home Home
     */
    public HierarchieBouton(Home home) {
        this.home=home;
    }

    /**
     * Instanciation de la HierarchieCreation.
     * @param mouseEvent MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        new HierarchieCreation(home);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) { }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) { }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) { }

    @Override
    public void mouseExited(MouseEvent mouseEvent) { }
}
