package Model;

import Controller.Arbre;
import Controller.Dialog;
import Exceptions.LookAndFeelException;
import View.Home;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;

public class HierarchieCreation {
    private final Home home;

    /**
     * Classe qui affiche le JDialog contenant le JTree de la hierarchie des programmes.
     * @param home Home
     */
    public HierarchieCreation(Home home){
        this.home=home;
        setDialogBox();
    }

    /**
     * Classe qui filtre les cours du tableau.
     * @param home Home
     * @param filtreUE ArrayList<String>
     */
    public HierarchieCreation(Home home,ArrayList<String> filtreUE){
        this.home=home;
        if(filtreUE.size()>0){
            setFiltreUE(filtreUE);
        }
    }

    /**
     * Création du JTree des programmes de l'ArrayList<Programme> de l'XMLReader.
     * @return JTree
     */
    private JTree ArbreProgrammesSelectiones(){
        ArrayList<Programme> programList = home.getXml().getProgramList();
        DefaultMutableTreeNode arbre=new DefaultMutableTreeNode("Programmes");
        for(Programme p:programList){
            HierarchieSelection hierarchie = new HierarchieSelection(p);
            arbre.add(hierarchie.getArbre());
        }
        return setTreeLF(arbre);
    }

    /**
     * Look&Feel du JTree.
     * @param arbre DefaultMutableTreeNode
     * @return JTree
     */
    private JTree setTreeLF(DefaultMutableTreeNode arbre){
        LookAndFeel previousLF=UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        }
        catch (Exception exception){/*Ne rien faire*/}
        JTree tree=new JTree(arbre);
        try {
            UIManager.setLookAndFeel(previousLF);
        } catch (UnsupportedLookAndFeelException e) {
            throw new LookAndFeelException();
        }
        return tree;
    }

    /**
     * Creation de la JDialog contenant le JTree de la hierarchie des programmes.
     */
    private void setDialogBox(){
        JTree arbre=ArbreProgrammesSelectiones();

        JDialog dialog = new JDialog(home.getFrame(), "Hierarchie");
        JScrollPane scroll=new JScrollPane(arbre);

        arbre.addTreeExpansionListener(new Arbre(dialog,arbre));
        dialog.addWindowListener(new Dialog(home,arbre));

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        dialog.add(scroll);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    /**
     * Application du filtre des UE dans la classe ProgramSwitch.
     * @param filtreUE ArrayList<String>
     */
    private void setFiltreUE(ArrayList<String> filtreUE){
        ProgramSwitch programSwitch = new ProgramSwitch(home);
        if(filtreUE.size()>0 && filtreUE.contains("Programmes")){
            programSwitch.Switch(-1);
        }
        else{
            ArrayList<Programme> programmeArrayList=home.getXml().getProgramList();
            if(filtreUE.size()==1) {
                for (int i = 0; i < programmeArrayList.size(); i++) {
                    if (filtreUE.contains(programmeArrayList.get(i).toString())) {
                        programSwitch.Switch(i);
                        return;
                    }
                }
            }
            HashMap<Cours,String> FiltreCoursHashMap = new HashMap<>();
            programmeArrayList=home.getXml().getProgramList();
            for(Programme p:programmeArrayList){
                for(Bloc b:p.getBlocs()){
                    for(UE u:b.getUE()){
                        if(filtreUE.contains(p.getNom()) || filtreUE.contains(b.getNom()) || filtreUE.contains(u.getNom())){
                            FiltreCoursHashMap.put((Cours)u,u.getNom());
                        }
                    }
                }
            }
            ArrayList<Cours> cours=new ArrayList<>();
            FiltreCoursHashMap.forEach((cour,nom)->cours.add(cour));
            programSwitch.Switch(cours);
        }
    }
}
