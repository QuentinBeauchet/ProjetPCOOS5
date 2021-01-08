package Model;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class HierarchieSelection {
    private final DefaultMutableTreeNode arbre;

    /**
     * Classe qui crée une DefaultMutableTreeNode a partir d'un Programme.
     * @param programme Programme
     */
    public HierarchieSelection(Programme programme){
        arbre=CreationArbre(programme);
    }

    /**
     * Creation de la DefaultMutableTreeNode pour un Programme.
     * @param CurrentProgramme Programme
     * @return DefaultMutableTreeNode
     */
    private DefaultMutableTreeNode CreationArbre(Programme CurrentProgramme){
        ArrayList<Bloc> blocs=CurrentProgramme.getBlocs();
        DefaultMutableTreeNode Racine=new DefaultMutableTreeNode(CurrentProgramme.getNom());
        for(Bloc b:blocs){
            ArrayList<UE> ue=b.getUE();
            if(ue.size()==1){
                Racine.add(new DefaultMutableTreeNode(b.getNom()));
            }
            else{
                DefaultMutableTreeNode Branche=new DefaultMutableTreeNode(b.getNom());
                for(UE u:ue){
                    Branche.add(new DefaultMutableTreeNode(u.getNom()));
                }
                Racine.add(Branche);
            }
        }
        return Racine;
    }

    /**
     * Renvoit la DefaultMutableTreeNode racine de tous les programmes.
     * @return DefaultMutableTreeNode
     */
    public DefaultMutableTreeNode getArbre() {
        return arbre;
    }

}
