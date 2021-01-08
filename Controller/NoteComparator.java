package Controller;

import Model.Note;

import java.util.Comparator;
/**
 * Classe qui permet de comparer deux notes du tableau.
 */
public class NoteComparator implements Comparator<Note> {


    @Override
    public int compare(Note n0, Note n1) {
        String s0=n0.toString();
        String s1=n1.toString();
        if((s0.equals("ABI") || s1.equals("ABI")) && (s0.equals("") || s1.equals(""))){
            return s0.compareTo(s1);
        }
        Float f0=n0.getFloatNote();
        Float f1=n1.getFloatNote();
        return f0.compareTo(f1);
    }
}
