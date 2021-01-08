package View;

import Controller.Boutons;
import Controller.HierarchieBouton;
import Controller.RechercheField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;

public class Menu {
    private final Home home;
    private final JMenuBar MenuBar;

    /**
     * Classe qui contient tous les composants de la JMenuBar,
     * c'est aussi elle qui initialise les EventListener pour les boutons du menu
     * et qui defini leurs style.
     * @param home Home
     */
    public Menu(Home home){
        this.home=home;
        MenuBar=new JMenuBar();
        setFichier();
        setEdit();
        setHierarchie();
        setRecherche();
        setStyle();
    }

    /**
     * Defini le style de la JMenuBar.
     */
    private void setStyle(){
        MenuBar.setMargin(new Insets(5,10,5,10));
        MenuBar.setBorder(BorderFactory.createLoweredSoftBevelBorder());
    }

    /**
     * Crée les composants du JMenu Fichier:
     * Ouvrir->Appele la classe Boutons("Ouvrir",Home)->Ouvre JFileChooser pour changer de xml
     * Enregistrer->Appele la classe Boutons("Enregistrer",Home)->Appele les classes XMlMaker et WriteCSV pour sauvegarder les données dans des nouveaux fichier
     * Enregistrer Sous->Permet de choisir la destination de la sauvegarde
     * Quitter->Appele la classe Boutons("Quitter",Home)->Fermer le programme
     */
    private void setFichier(){
        JMenu Fichier=new JMenu("Fichier");
        Fichier.setMnemonic(KeyEvent.VK_F);

        JMenuItem Open=new JMenuItem("Ouvrir");
        Open.addActionListener(new Boutons(Open.getText(),home));
        Open.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
        Fichier.add(Open);

        JMenuItem Save=new JMenuItem("Enregistrer");
        Save.addActionListener(new Boutons(Save.getText(),home));
        Save.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
        Fichier.add(Save);

        JMenuItem newSave=new JMenuItem("Enregistrer Sous");
        newSave.addActionListener(new Boutons(newSave.getText(),home));
        newSave.setAccelerator(KeyStroke.getKeyStroke('L', CTRL_DOWN_MASK));
        Fichier.add(newSave);

        JMenuItem csv=new JMenuItem("CSV");
        csv.addActionListener(new Boutons(csv.getText(),home));
        csv.setAccelerator(KeyStroke.getKeyStroke('J', CTRL_DOWN_MASK));
        Fichier.add(csv);

        JMenuItem Close=new JMenuItem("Quitter");
        Close.addActionListener(new Boutons(Close.getText(),home));
        Close.setAccelerator(KeyStroke.getKeyStroke('Q', CTRL_DOWN_MASK));
        Fichier.add(Close);

        MenuBar.add(Fichier);
    }

    /**
     * Crée le JLabel Hierarchie qui appele l'EventListener HierarchieBouton(Home),
     * celui ci appele la classe Dialog qui ouvre un JDialog contenant un JTree
     * de la hierarchie des programmes du fichier xml.
     * La selection de ceux ci permet de realiser un filtrage sur le tableau.
     */
    private void setHierarchie(){
        JLabel Hierarchie=new JLabel("Hierarchie");
        Hierarchie.addMouseListener(new HierarchieBouton(home));
        Hierarchie.setDisplayedMnemonic(KeyEvent.VK_H);
        Hierarchie.setBorder(new EmptyBorder(0,0,0,10));
        Hierarchie.setHorizontalTextPosition(JLabel.CENTER);

        JMenuItem hidden=new JMenuItem();
        hidden.addActionListener(new Boutons("Shortcut",home));
        hidden.setAccelerator(KeyStroke.getKeyStroke('H', InputEvent.ALT_DOWN_MASK));
        hidden.setMinimumSize(new Dimension(0,0));
        hidden.setMaximumSize(new Dimension(0,0));
        hidden.setSize(new Dimension(0,0));
        MenuBar.add(hidden);

        MenuBar.add(Hierarchie);
    }

    /**
     * Crée le JTextField qui appele L'EventListener RechercheField(JTextField,Home),
     * celui ci permet de realiser un filtrage des Nom/Prenom/Numero Etudiants sur le tableau.
     */
    private void setRecherche(){
        JTextField Recherche=new JTextField();
        Recherche.setMaximumSize(new Dimension(200,20));
        Recherche.addKeyListener(new RechercheField(Recherche,home));

        JLabel bouton=new JLabel("Rechercher:");
        bouton.setBorder(new EmptyBorder(0,0,0,0));
        bouton.setHorizontalTextPosition(JLabel.CENTER);

        MenuBar.add(bouton);
        MenuBar.add(Recherche);
    }

    /**
     * JMenu qui permet d'editer le xml, ajout/suppression etudiant,
     * ajout de cours et de programme.
     */
    private void setEdit(){
        JMenu edit=new JMenu("Edit");
        edit.setMnemonic(KeyEvent.VK_E);

        JMenuItem ajoutEtudiant=new JMenuItem("Ajouter un etudiant");
        ajoutEtudiant.addActionListener(new Boutons(ajoutEtudiant.getText(),home));
        ajoutEtudiant.setAccelerator(KeyStroke.getKeyStroke('E', CTRL_DOWN_MASK));
        edit.add(ajoutEtudiant);

        JMenuItem supprimerEtudiant=new JMenuItem("Supprimer l'etudiant selectioné");
        supprimerEtudiant.addActionListener(new Boutons(supprimerEtudiant.getText(),home));
        supprimerEtudiant.setAccelerator(KeyStroke.getKeyStroke('D', CTRL_DOWN_MASK));
        edit.add(supprimerEtudiant);

        JMenuItem ajoutCours=new JMenuItem("Ajouter un cours");
        ajoutCours.addActionListener(new Boutons(ajoutCours.getText(),home));
        ajoutCours.setAccelerator(KeyStroke.getKeyStroke('U', CTRL_DOWN_MASK));
        edit.add(ajoutCours);

        JMenuItem ajoutProgramme=new JMenuItem("Ajouter un programme");
        ajoutProgramme.addActionListener(new Boutons(ajoutProgramme.getText(),home));
        ajoutProgramme.setAccelerator(KeyStroke.getKeyStroke('P', CTRL_DOWN_MASK));
        edit.add(ajoutProgramme);

        MenuBar.add(edit);
    }

    /**
     * Renvoit la JMenuBar.
     * @return JMenuBar
     */
    public JMenuBar getMenuBar() {
        return MenuBar;
    }
}
