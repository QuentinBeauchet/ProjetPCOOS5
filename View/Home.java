package View;

import Controller.Window;
import Model.TabCreation;
import Model.XMLReader;

import javax.swing.*;
import java.awt.*;

public class Home {
    private final XMLReader xml;
    private final JFrame frame;
    private Tableau tab;

    /**
     * Classe principale qui gere la JFrame qui contient le tableau et le menu,
     * elle est initialisé une fois que StartView est terminé.
     * @param xml XMLReader
     */
    public Home(XMLReader xml) {
        this.xml = xml;

        try {UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");} catch (Exception exception){/*Ne rien faire*/}
        frame = new JFrame("Projet PCOO");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(1445, 800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.addWindowListener(new Window());

        setTab();
        setMenu();

        frame.setVisible(true);
    }

    /**
     * Initialise le tableau selon l'XMLReader.
     */
    private void setTab() {
        TabCreation tabCreation = new TabCreation(this,xml.getCourseList(),xml.getStudentList());
        tab = new Tableau(tabCreation);
        frame.add(tab.getPanel());
    }

    /**
     * Change le tableau actuel d'apres un Tableau passé en parametre.
     * @param t Tableau
     */
    public void setTab(Tableau t) {
        frame.remove(tab.getPanel());
        tab=t;
        frame.add(tab.getPanel());
        frame.validate();
    }

    /**
     * Initialise le Menu.
     */
    private void setMenu() {
        Menu menu=new Menu(this);
        frame.add(menu.getMenuBar(), BorderLayout.NORTH);
    }

    /**
     * Renvoit l'XMLReader.
     * @return XMLReader
     */
    public XMLReader getXml() {
        return xml;
    }

    /**
     * Renvoit la JFrame.
     * @return JFrame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Renvoit la classe qui contient tout les composants du tableau, Tableau.
     * @return Tableau
     */
    public Tableau getTab() {
        return tab;
    }

}
