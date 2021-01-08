package Model;

import Exceptions.IdEtudiantInvalidException;
import Exceptions.NameEtudiantInvalidException;
import Exceptions.SurnameEtudiantInvalidException;
import View.Home;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AjoutEtudiant {
    private Etudiant etudiant;
    private final Home home;

    /**
     * Ajoute un Etudiant a l'xml et au tableau.
     * @param home Home
     * @param args Object[]
     */
    public AjoutEtudiant(Home home,Object[] args){
        this.home=home;
        JDialog dialog = (JDialog) args[0];
        if(isEtudiantValid((JTextField) args[3],(JTextField) args[1],(JTextField) args[2])){
            JList<Programme> listProgrammes=(JList<Programme>)args[4];
            Programme programme=listProgrammes.getModel().getElementAt(listProgrammes.getFirstVisibleIndex());
            etudiant.inscris(programme);
            home.getXml().getStudentList().add(etudiant);
            DefaultTableModel model = (DefaultTableModel) home.getTab().getTableau().getModel();
            model.addRow(EtudiantToRow(etudiant,model.getColumnCount()));
            dialog.dispose();
        }
    }

    /**
     * Verifie si l'Etudiant peut etre instancier avec les champs fournis.
     * @param id JTextField
     * @param nom JTextField
     * @param prenom JTextField
     * @return boolean
     */
    private boolean isEtudiantValid(JTextField id,JTextField nom,JTextField prenom){
        boolean isValid=false;
        try {
            etudiant = new Etudiant(id.getText(), nom.getText(), prenom.getText());
            MyTools.isIdEtudiantAlreadyExist(home.getXml().getStudentList(),etudiant.getId());
            isValid=true;
            nom.setBorder(PopUp.normalBorder);
            id.setBorder(PopUp.normalBorder);
            prenom.setBorder(PopUp.normalBorder);
        }
        catch (IdEtudiantInvalidException exception){
            id.setBorder(PopUp.erreurBorder);
        }
        catch (NameEtudiantInvalidException exception){
            id.setBorder(PopUp.normalBorder);
            nom.setBorder(PopUp.erreurBorder);
        }
        catch (SurnameEtudiantInvalidException exception){
            nom.setBorder(PopUp.normalBorder);
            prenom.setBorder(PopUp.erreurBorder);
        }
        return isValid;
    }

    /**
     * Insere l'etudiant dans le tableau.
     * @param etudiant Etudiant
     * @param nbrCol int
     * @return Object[]
     */
    private Object[] EtudiantToRow(Etudiant etudiant,int nbrCol){
        Object[] row=new Object[nbrCol];
        row[0]=Integer.parseInt(etudiant.getId());
        row[1]=etudiant.getNom();
        row[2]=etudiant.getPrenom();
        row[3]=etudiant.getP().getNom();
        row[4]=etudiant.getP().getNoteProgramme(etudiant);
        for (int i = TabCreation.NBR_COMPOSANTS_ETUDIANTS; i < nbrCol; i++) {
            row[i]=new Note("");
        }
        return row;
    }
}
