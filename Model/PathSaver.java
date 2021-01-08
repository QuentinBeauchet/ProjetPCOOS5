package Model;

import java.io.*;

public class PathSaver {

    /**
     * Classe servant à charger le chemin dans les fichiers
     * pour simplifier l'ouverture et l'enregister sous
     * @param path chemin jusqu'au fichier (à couper pour prendre le dossier parent)
     */
    public PathSaver(String path) {
        for (int i = path.length()-1 ; i > 0 ; i--) {
            if(path.charAt(i) == '\\'){
                path = path.substring(0,i);
                break;
            }
        }
        try {
            write(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Ecris sur le fichier pathToSave.txt le chemin à sauvegarder
     * @param path le chemin jusqu'au dossier
     * @throws IOException Erreure
     */
    private void write(String path) throws IOException {
        FileWriter myWriter = new FileWriter("data/pathToSave.txt");
        myWriter.write(path);
        myWriter.close();
    }

    /**
     * lis le chemin sauvegardé dans pathToSave.txt
     * en cas d'erreure retourne ""
     * @return String
     */
    public static String readPath(){
        try {
            File file = new File("data/pathToSave.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));

            return br.readLine();
        }catch (IOException e) {
            return "";
        }
    }
}
