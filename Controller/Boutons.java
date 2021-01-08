package Controller;

import Model.*;
import View.Home;
import View.StartView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Classe Affectant les différentes actions aux boutons
 * */
public class Boutons implements ActionListener{
    private final String action;
    private final Object obj;
    private final Object[] args;

    public  Boutons(String s, Object obj,Object... args){
        this.obj=obj;
        this.action=s;
        this.args=args;
    }

    /**
     * Selon le String passé dans le constructeur le bouton va effectué differentes actions:
     * "Ouvrir"->Appel d'un JFileChooser dans Home
     * "Start"->Appel de JFileChooser dans StartView
     * "StartNew"->Passage de la StartView a Home avec un XMLReader()
     * "Launch"->Passage de la StartView a Home avec un XMLReader(fichier)
     * "Enregistrer"->Sauvegarde les donnés sous le format xml
     * "Enregister Sous"->Choix de l'emplacement de sauvegarde
     * "CSV"->Sauvegarde les Programmes au format csv
     * "ShortCut"->Appel la classe HierarchieCreation
     * "Quitter"->Appel a la classe PopUpConfirmation
     * "Ajouter un etudiant" | "Ajouter un cours" | "Ajouter un programme"->Appele PopUp avec une action differente chacun
     * "Supprimer l'etudiant selectioné"->Appele SuppressionEtudiant
     * "Ajout Etudiant"->Appele AjoutEtudiant
     * "Ajout Cours"->Appele AjouCours
     * "Ajout Programme"->Appele AjoutProgramme
     * "Switch RadioBoutons"->Change l'affichage du PopUp selon le bouton
     * "Annuler"->Ferme le popup de PopUpConfirmation sans quitter le programme
     * ...->Ferme le programme
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        FileChooser chooser;
        XMLReader xml;
        StartView view;
        Home home;
        JDialog dialog;
        switch (action) {
            case "Ouvrir" -> {
                chooser = new FileChooser();
                if (chooser.getOption() == JFileChooser.APPROVE_OPTION) {
                    xml = new XMLReader(chooser.getChooser().getSelectedFile().toString());
                    new PathSaver(chooser.getChooser().getSelectedFile().toString());
                    home = (Home) obj;
                    home.getFrame().dispose();
                    new Home(xml);
                }
            }
            case "Start" -> {
                chooser = new FileChooser();
                if(chooser.getOption()==JFileChooser.APPROVE_OPTION){
                    view=(StartView)obj;
                    view.showConfirmation(true);
                    view.setText("<html>Vous avez choisit "+chooser.getChooser().getSelectedFile().getName()+", voulez vous confirmer ?</html>");
                    view.setPath(chooser.getChooser().getSelectedFile());
                }
            }
            case "StartNew" -> {
                view = (StartView) obj;
                view.dispose();
                xml = new XMLReader();
                new Home(xml);
            }
            case "Launch" -> {
                view = (StartView) obj;
                view.dispose();
                xml = new XMLReader(view.getFile().toString());
                new Home(xml);
            }
            case "Enregistrer" -> {
                home = (Home) obj;
                xml = home.getXml();
                new XMLMaker(xml);
            }
            case "Enregistrer Sous" -> {
                home = (Home) obj;
                FileChooser fileChooser = new FileChooser("Enregistrer-sous le XML", "Enregistrer");
                if (fileChooser.getOption() == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    String path = fileToSave.getAbsolutePath();
                    new XMLMaker(home.getXml(), path);
                    new PathSaver(path);
                }
            }
            case "CSV" -> {
                home = (Home) obj;
                xml=home.getXml();
                new WriteCsv(xml);
            }
            case "Shortcut" -> {
                home = (Home) obj;
                new HierarchieCreation(home);
            }
            case "Quitter" -> new PopUp(action);
            case "Ajouter un etudiant", "Ajouter un cours", "Ajouter un programme" -> {
                home = (Home) obj;
                new PopUp(action, home);
            }
            case "Supprimer l'etudiant selectioné" -> {
                home = (Home) obj;
                new SuppressionEtudiant(home);
            }
            case "Ajout Etudiant" -> {
                home = (Home) obj;
                new AjoutEtudiant(home, args);
            }
            case "Ajout Cours" -> {
                home = (Home) obj;
                new AjoutCours(home, args);
            }
            case "Ajout Programme" -> {
                home = (Home) obj;
                new AjoutProgramme(home, args);
            }
            case "Switch RadioBoutons" -> {
                PopUp popUp = (PopUp) obj;
                ButtonGroup group = (ButtonGroup) args[0];
                popUp.setBlocVisible(Integer.parseInt(group.getSelection().getActionCommand()));
            }
            case "Annuler" -> {
                dialog = (JDialog) obj;
                dialog.dispose();
            }
            default -> System.exit(0);
        }
    }

}
