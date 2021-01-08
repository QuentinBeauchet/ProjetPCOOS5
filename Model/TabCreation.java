package Model;

import View.Home;

import java.util.ArrayList;

public class TabCreation {
    public static final int NBR_COMPOSANTS_ETUDIANTS=5;
    private static final int NBR_LIGNES_CALCULS=4;
    private Home home;
    private Object[] colones;
    private Object[][] lignes;
    private Object[][] calculs;
    private final ArrayList<Cours> courseList;
    private final ArrayList<Etudiant> studentList;

    /**
     * Classe qui initialise les deux JTable celle des etudiants et celle des calculs.
     *
     * @param cours ArrayList<Cours>
     * @param etudiants ArrayList<Etudiant>
     */

    public TabCreation(Home home, ArrayList<Cours> cours, ArrayList<Etudiant> etudiants){
        this.home=home;
        courseList=cours;
        studentList=etudiants;
        int nbrLignes=studentList.size();
        int nbrColones=courseList.size()+NBR_COMPOSANTS_ETUDIANTS;
        setColones(nbrColones);
        setLignes(nbrColones,nbrLignes);
        setCalculs(nbrColones);
    }

    /**
     * Crée l'Header de la JTable etudiants.
     *
     * @param nbrColones int
     */

    private void setColones(int nbrColones) {
        colones=new Object[nbrColones];
        colones[0]="N° Étudiant";
        colones[1]="Nom";
        colones[2]="Prénom";
        colones[3]="Programme";
        colones[4]="Résultats";
        for (int i = 0; i < courseList.size(); i++) {
            colones[i+NBR_COMPOSANTS_ETUDIANTS]=courseList.get(i).getNom();
        }
    }

    /**
     * Crée les lignes de la JTable etudiants.
     *
     * @param nbrColones int
     * @param nbrLignes int
     */

    private void setLignes(int nbrColones,int nbrLignes) {
        lignes=new Object[nbrLignes][nbrColones];
        for (int i = 0; i < studentList.size(); i++) {
            lignes[i][0]=Integer.parseInt(studentList.get(i).getId());
            lignes[i][1]=studentList.get(i).getNom();
            lignes[i][2]=studentList.get(i).getPrenom();
            if(studentList.get(i).getP()!=null){
                lignes[i][3]=studentList.get(i).getP().getNom();
                lignes[i][4]=studentList.get(i).getP().getNoteProgramme(studentList.get(i));
                for (int j = 0; j < courseList.size(); j++) {
                    lignes[i][j + NBR_COMPOSANTS_ETUDIANTS] = courseList.get(j).calcNote(studentList.get(i));
                }
            }
        }
    }

    /**
     * Crée les lignes de la JTable calculs.
     *
     * @param nbrColones int
     */

    private void setCalculs(int nbrColones) {
        calculs=new Object[NBR_LIGNES_CALCULS][nbrColones];

        calculs[0][0]="Note max";
        calculs[1][0]="Note min";
        calculs[2][0]="Note moyenne";
        calculs[3][0]="Écart-type";

        ArrayList<Float> toolKitFloat = MyTools.getStats(studentList);
        for (int i = 1; i < NBR_COMPOSANTS_ETUDIANTS; i++) {
            for (int j = 0; j < NBR_LIGNES_CALCULS; j++) {
                if(studentList.size()!=0 && i==NBR_COMPOSANTS_ETUDIANTS-1){
                    calculs[j][NBR_COMPOSANTS_ETUDIANTS-1]=toolKitFloat.get(j);
                }
                else{
                    calculs[j][i]="";
                }
            }
        }

        for (int i = 0; i < courseList.size(); i++) {
            ArrayList<Float> toolKit = MyTools.getStats(studentList,courseList.get(i));
            for (int j = 0; j < NBR_LIGNES_CALCULS; j++) {
                calculs[j][i+NBR_COMPOSANTS_ETUDIANTS]=toolKit.get(j);
            }
        }
    }

    /**
     * Renvoit l'Header de la JTable etudiants.
     *
     * @return Object[]
     */

    public Object[] getColones() {
        return colones;
    }

    /**
     * Renvoit les lignes de la JTable etudiants.
     *
     * @return Object[][]
     */

    public Object[][] getLignes() {
        return lignes;
    }

    /**
     * Renvoit les lignes de la JTable calculs.
     *
     * @return Object[][]
     */

    public Object[][] getCalculs() {
        return calculs;
    }

    public Home getHome(){
        return home;
    }

}
