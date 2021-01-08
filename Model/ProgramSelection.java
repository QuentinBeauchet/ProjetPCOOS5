package Model;

import View.Home;

import java.util.ArrayList;

/**
 * Classe qui crée un TabCreation[] de tout les Programmes du XMLreader
 * pour facilement passer de l'affichage d'un programme a un autre.
 */
public class ProgramSelection {
    private final Home home;
    private final ArrayList<Programme> programList;
    private final ArrayList<Etudiant> studentList;


    public ProgramSelection(Home home){
        this.home=home;
        XMLReader xml=home.getXml();
        programList=xml.getProgramList();
        studentList=xml.getStudentList();
    }

    /**
     * Renvoit l'ArrayList des tous les etudiants inscrit dans le programme
     * a l'index i dans l'ArrayList<Programme> de l'XMLReader.
     * @param index int
     * @return ArrayList<Etudiant>
     */
    private ArrayList<Etudiant> ListeEtudiantsIndex(int index){
        ArrayList<Etudiant> etudiants=new ArrayList<>();
        for(Etudiant e:studentList){
            if(e.estInscris(programList.get(index))){
                etudiants.add(e);
            }
        }
        return etudiants;
    }

    /**
     * Renvoit l'ArrayList de tous les cours presents dans le programme
     * a l'index i dans l'ArrayList<Programme> de l'XMLReader.
     * @param index int
     * @return ArrayList<Cours>
     */
    private ArrayList<Cours> ListeCoursIndex(int index){
        ArrayList<UE> ue=new ArrayList<>();
        ArrayList<Bloc> blocs=programList.get(index).getBlocs();
        for(Bloc b:blocs){
            ue.addAll(b.getUE());
        }
        ArrayList<Cours> cours=new ArrayList<>();
        for(UE u:ue){
            cours.add((Cours)u);
        }
        return cours;
    }

    /**
     * Renvoit le TabCreation pour les etudiants et les cours du programme
     * a l'index i dans l'ArrayList<Programme> de l'XMLReader.
     * @param index int
     * @return TabCreation
     */
    private TabCreation TabProgrammeIndex(int index){
        return new TabCreation(home,ListeCoursIndex(index),ListeEtudiantsIndex(index));
    }

    /**
     * Renvoit le tableau TabCreation[] qui contient les TabCreation pour chaque
     * programme dans l'ArrayList<Programme> de l'XMLReader.
     * @return TabCreation[]
     */
    public TabCreation[] TabProgrammes(){
        TabCreation[] tab=new TabCreation[programList.size()];
        for (int i = 0; i < programList.size(); i++) {
            tab[i]=TabProgrammeIndex(i);
        }
        return tab;
    }
}
