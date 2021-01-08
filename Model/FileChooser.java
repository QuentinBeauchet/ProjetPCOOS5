package Model;

import Exceptions.LookAndFeelException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileChooser {
    private JFileChooser chooser;
    private final int option;

    /**
     * Classe qui instancie le JFileChooser pour choisir un nouveau fichier xml.
     */



    public FileChooser(){
        initialise();
        option = chooser.showOpenDialog(null);
    }

    public FileChooser(String dialog,String buttonName){
        initialise();
        chooser.setDialogTitle(dialog);
        option = chooser.showDialog(null,buttonName);

    }

    /**
     * initalise le fileChooser
     */
    public void initialise(){
        setLF();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers .xml", "xml");
        chooser.setFileFilter(filter);
    }
    /**
     * Look&Feel du JFileChooser.
     */

    private void setLF(){
        LookAndFeel previousLF=UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            this.setCurrentDirectory();
            chooser = new JFileChooser(setCurrentDirectory());
        }
        catch (Exception exception){/*Ne rien faire*/}
        try {
            UIManager.setLookAndFeel(previousLF);
        }
        catch (Exception exception){
            throw new LookAndFeelException();
        }
    }

    /**
     * Renvoit le JFileChooser.
     *
     * @return JFileChooser
     */

    public JFileChooser getChooser(){
        return chooser;
    }

    /**
     * Renvoit le status du JFileChooser.
     *
     * @return int
     */

    public int getOption(){
        return option;
    }

    public File getSelectedFile() {
        return chooser.getSelectedFile();
    }

    public File setCurrentDirectory(){
        String originPath = PathSaver.readPath();
        return new File(originPath);
    }
}
