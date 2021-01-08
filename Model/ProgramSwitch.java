package Model;

import View.Home;
import View.Tableau;

import java.util.ArrayList;

public class ProgramSwitch {
    private final Home home;
    private Tableau tab;

    /**
     * Classe qui permet que changer quels cours sont affichés dans le tableau.
     * @param home Home
     */
    public ProgramSwitch(Home home){
        this.home=home;
    }

    /**
     * Affiche seulement les cours d'un ArrayList<Cours> precedement filtré.
     * @param cours ArrayList<Cours>
     */
    public void Switch(ArrayList<Cours> cours){
        XMLReader xml=home.getXml();
        TabCreation tabCreation=new TabCreation(home,cours,xml.getStudentList());
        tab=new Tableau(tabCreation);
        Switch();

    }

    /**
     * Affiche seulements les cours du Programme a l'index i dans l'ArrayList<Programme>
     * de l'XMLReader.
     * Si l'index vaut -1 tous les cours presents dans l'XMLReader sont affichés.
     * @param index int
     */
    public void Switch(int index){
        if(index==-1){
            XMLReader xml=home.getXml();
            TabCreation tabCreation=new TabCreation(home,xml.getCourseList(),xml.getStudentList());
            tab=new Tableau(tabCreation);

        }
        else{
            ProgramSelection programSelection=new ProgramSelection(home);
            TabCreation[] tabCreations= programSelection.TabProgrammes();
            tab=new Tableau(tabCreations[index]);
        }
        Switch();
    }

    /**
     * Actualisation du tableau.
     */
    private void Switch(){
        home.setTab(tab);
    }
}
