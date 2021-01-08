package Model;

import View.Home;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class CustomTableModel extends DefaultTableModel{
    private final String name;
    private final Home home;

    /**
     * TableModel de swing custom pour avoir le tableau aves
     * des classes specifiques par ca colones.
     * @param home Home
     * @param lignes Object[][]
     * @param colones Object[]
     * @param name String
     */
    public CustomTableModel(Home home, Object[][] lignes, Object[] colones, String name) {
        super(lignes,colones);
        this.name=name;
        this.home=home;
    }

    @Override
    public void setValueAt(Object obj, int row, int col) {
        if(name.equals("tableau")){
            updateXML(obj,row,col);
        }
        if(name.equals("tableau") && getColumnClass(col)==Note.class){
            super.setValueAt(new Note((String) obj),row,col);
        }
        else{
            if(getColumnClass(col)==Integer.class){
                super.setValueAt(Integer.valueOf((String)obj),row,col);
            }
            else if(getColumnClass(col)==Float.class){
                super.setValueAt(Float.parseFloat((String) obj),row,col);
            }
            else{
                super.setValueAt(obj,row,col);
            }
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if ("tableau".equals(name)) {
            return switch (columnIndex) {
                case 0 -> Integer.class;
                case 1, 2, 3 -> String.class;
                case 4 -> Float.class;
                default -> Note.class;
            };
        }
        return switch (columnIndex) {
            case 0, 1, 2, 3 -> String.class;
            default -> Float.class;
        };
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return !(column==3 || column==4 || name.equals("calculs"));
    }

    /**
     * Actualise l'xml apres chaque modification du tableau.
     * @param obj Object
     * @param row int
     * @param col int
     */
    private void updateXML(Object obj,int row,int col){
        XMLReader xmlReader=home.getXml();
        ArrayList<Etudiant> students=xmlReader.getStudentList();
        ArrayList<Cours> cours=xmlReader.getCourseList();
        for (int i = 0; i < students.size(); i++) {
            Etudiant etudiant=students.get(i);
            String rowID= String.valueOf(getValueAt(row,0));
            if(rowID.equals(etudiant.getId())){
                switch (col){
                    case 0:
                        etudiant.setId((String)obj);
                        xmlReader.getStudentList().set(i,etudiant);
                        break;
                    case 1:
                        etudiant.setNom((String)obj);
                        break;
                    case 2:
                        etudiant.setPrenom((String)obj);
                    case 3: case 4:
                        break;
                    default:
                        for (Cours currentcours : cours) {
                            if (currentcours.getNom().equals(getColumnName(col))) {
                                etudiant.addNote(currentcours, new Note((String) obj));
                                updateCalculs(students, col, currentcours);
                                break;
                            }
                        }
                        super.setValueAt(etudiant.getP().getNoteProgramme(etudiant),row,4);
                        updateCalculs(students,col);
                }
                break;
            }
        }
    }

    /**
     * Actualise le tableau calcul apres chaque modification du tableau.
     * @param students ArrayList<Etudiant>
     * @param col int
     * @param currentcours Cours...
     */
    private void updateCalculs(ArrayList<Etudiant> students,int col,Cours... currentcours){
        JScrollPane pane = (JScrollPane) home.getTab().getPanel().getComponent(1);
        JViewport viewport = pane.getViewport();
        JTable calculs = (JTable)viewport.getView();
        CustomTableModel model= (CustomTableModel) calculs.getModel();

        ArrayList<Float> updatedCalculs;
        if(currentcours.length!=0){
            updatedCalculs=MyTools.getStats(students,currentcours[0]);
        }
        else{
            updatedCalculs=MyTools.getStats(students);
            col=4;

        }
        for (int k = 0; k < updatedCalculs.size(); k++) {
            model.setValueAt(updatedCalculs.get(k).toString(),k,col);
        }
    }

}