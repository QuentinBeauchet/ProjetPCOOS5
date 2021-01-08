package Model;

import View.Home;

import javax.swing.table.DefaultTableModel;

public class SuppressionEtudiant {

    /**
     * Classe qui permet de supprimer un etudiant de l'xml
     * et du tableau depuis le menu.
     */
    public SuppressionEtudiant(Home home){
        int selectedRow = home.getTab().getTableau().getSelectedRow();
        if(selectedRow!=-1){
            DefaultTableModel model= (DefaultTableModel) home.getTab().getTableau().getModel();
            String id = String.valueOf(model.getValueAt(selectedRow, 0));
            for(Etudiant etudiant: home.getXml().getStudentList()){
                if(etudiant.getId().equals(id)){
                    home.getXml().getStudentList().remove(etudiant);
                    model.removeRow(selectedRow);
                    break;
                }
            }
        }
    }
}
