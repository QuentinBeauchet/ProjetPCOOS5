package Model;


import Exceptions.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Lecture d'un fichier XML
 */
public class XMLReader {
    private String directory ="";
    private String filename ="";
    private Element file ;
    private final ArrayList<Cours> courseList;
    private final ArrayList<Programme> programList;
    private final ArrayList<Etudiant> studentList;

    /**
     * Getter de la liste de cours
     * @return ArrayList
     */
    public ArrayList<Cours> getCourseList() {
        return courseList;
    }

    /**
     * Getter du nom du fichier
     * @return string
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Getter de la liste de programmes
     * @return ArrayList
     */
    public ArrayList<Programme> getProgramList() {
        return programList;
    }
    /**
     * Getter de la liste d'étudiants
     * @return ArrayList
     */
    public ArrayList<Etudiant> getStudentList() {
        return studentList;
    }

    public XMLReader(){
        this.courseList = new ArrayList<>();
        this.programList = new ArrayList<>();
        this.studentList = new ArrayList<>();
    }

    public XMLReader(String fileName) {
        defineDirectoryAndFileName(fileName);
        new PathSaver(fileName);
        this.file = readFile(fileName);
        this.courseList = fillCourses();
        this.programList = fillPrograms();
        this.studentList = fillStudents();
    }

    /**
     * Recuperes la racine du fichier xml
     * @param fileName nom du fichier
     * @return la racine de l'xml (Element)
     */
    private Element readFile (String fileName){
        Document doc = null;
        try {
            File file = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder ;
            dBuilder = dbFactory.newDocumentBuilder();
             doc = dBuilder.parse(file); // ouverture et lecture du fichier XML
            doc.getDocumentElement().normalize(); // normalise le contenu du fichier, opération très conseillée

            // c'est parti pour l'exploration de l'arbre
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            return doc.getDocumentElement();
        }
        else{
            throw new NullPointerException();
        }
    }


    /**
     * Extrait la liste des fils de l'élément item dont le tag est name
     * @param item Parent
     * @param name le tag à rechercher
     * @return la liste des fils avec le tag correspondant
     */
    private static List<Element> getChildren(Element item, String name) {
        NodeList nodeList = item.getChildNodes();
        List<Element> children = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodeList.item(i); // cas particulier pour nous où tous les noeuds sont des éléments
                if (element.getTagName().equals(name)) {
                    children.add(element);
                }
            }
        }
        return children;
    }

    /**
     * Rempli la liste de cours
     * @return ArrayList
     */
    private ArrayList<Cours> fillCourses (){
        ArrayList<Cours> cours = new ArrayList<>();
        for (Element e: getChildren(this.file,"course")
             ) {
            Cours temp = new Cours(
                    e.getElementsByTagName("identifier").item(0).getTextContent(),
                    Integer.parseInt(e.getElementsByTagName("credits").item(0).getTextContent()),
                    e.getElementsByTagName("name").item(0).getTextContent()
            );

            if( cours.size()>0 && MyTools.isIdCourseAlreadyExist(cours,temp.getId())){
                throw new IdUeDuplicationException(temp);
            }
            cours.add(temp);


        }
        return cours;
    }

    /**
     * Rempli la liste de programmes
     * @return ArrayList
     */
    private ArrayList<Programme> fillPrograms(){
        ArrayList<Programme> programs = new ArrayList<>();
        for (Element e: getChildren(this.file,"program")
             ) {
            Programme temp = new Programme(
                    e.getElementsByTagName("name").item(0).getTextContent(),
                    e.getElementsByTagName("identifier").item(0).getTextContent()
            );
            if(programs.size()>0 && MyTools.isIdProgramAlreadyExist(programs,temp.getId()))throw new IdProgramDuplicationException(temp);
            fillOneProgram(e,temp);
            programs.add(temp);

        }
        return programs;
    }

    /**
     * Rempli la liste d'etudiants
     * @return ArrayList
     */
    private ArrayList<Etudiant> fillStudents(){
        ArrayList<Etudiant> etudiants= new ArrayList<>();
        for (Element e : getChildren(this.file,"student")){

            Etudiant etu = new Etudiant(
                   e.getElementsByTagName("identifier").item(0).getTextContent(),
                    e.getElementsByTagName("name").item(0).getTextContent(),
                    e.getElementsByTagName("surname").item(0).getTextContent()
            );

            if(etudiants.size()>1 && MyTools.isIdEtudiantAlreadyExist(etudiants,etu.getId()))throw new IdEtudiantDuplicationException(etu);
            if(!e.getElementsByTagName("program").item(0).getTextContent().equals("0")){
                etu.inscris(findProgramById(e.getElementsByTagName("program").item(0).getTextContent()));
                fillNotesToStudent(e,etu);
            }
            etudiants.add(etu);
        }

        return etudiants;
    }

    /**
     * Rempli la liste des notes d'un etudiant
     * @param e l'etudiant au format xml
     * @param t l'etudiant
     */
    private void fillNotesToStudent(Element e , Etudiant t ){
        for (Element s : getChildren(e,"grade")){
            t.addNote(
                    findCourseById(s.getElementsByTagName("item").item(0).getTextContent()),
                    new Note(s.getElementsByTagName("value").item(0).getTextContent())
            );
        }
    }

    /**
     * Rempli un programme
     * @param e le programme au format xml
     * @param p le programme
     */
    private void fillOneProgram( Element e,Programme p){
        for (Element s: getChildren(e,"item")//bloc Simple
        ) {
            p.add(new BlocSimple(findCourseById(s.getTextContent())));
        }
        for(Element s: getChildren(e,"option")//bloc option
        ){
            BlocOptions temp = new BlocOptions(
                    s.getElementsByTagName("identifier").item(0).getTextContent(),
                    findCourseById(getChildren(s,"item").get(0).getTextContent()).getCoef(),
                    s.getElementsByTagName("name").item(0).getTextContent()
            );
            fillABlocMul(s,temp);
            p.add(temp);

        }
        for(Element s: getChildren(e,"composite")//bloc composite
        ){
            BlocComposite temp = new BlocComposite(
                    s.getElementsByTagName("identifier").item(0).getTextContent(),
                    s.getElementsByTagName("name").item(0).getTextContent()
            );
            fillABlocMul(s,temp);
            p.add(temp);
        }
    }

    /**
     * Rempli un bloc multiple
     * @param e le bloc au format xml
     * @param b le BlocMultiple
     */
    private void fillABlocMul(Element e, BlocMultiple b){
        for (Element s: getChildren(e,"item")
        ) {
            b.add(findCourseById(s.getTextContent()));
        }
    }

    /**
     * renvoi un cours depuis un id donné
     * @param id l'id du cours recherché
     * @return le cours
     */
    public Cours findCourseById(String id){
        for (Cours u: this.courseList
             ) {
            if(u.getId().equals(id))return u;
        }
        throw new IdUnknownException(id);
    }

    /**
     * renvoi un programme depuis un id donné
     * @param id l'id du programme recherché
     * @return le programme
     */
    public Programme findProgramById(String id){
        for (Programme p : this.programList
        ){
            if(p.getId().equals(id))return p;
        }
        throw new IdUnknownException(id);
    }


    /**
     * Getteur de directory
     * @return string
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * défini le string directory et filename
     * @param filename chemin complet
     */
    public void defineDirectoryAndFileName(String filename){
        for (int i = filename.length()-1 ; i > 0 ; i--) {
            if(filename.charAt(i) == '\\'){
                this.directory = filename.substring(0,i);
                this.filename = filename.substring(i+1);
                break;
            }
        }
    }
}
