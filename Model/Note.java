package Model;

/**
 * Classe modélisant la Note attribuée à un étudiant
 */
public class Note {
    private final String note;

    public Note(String note) {
        this.note = note;
    }

    /**
     * getteur de la note au format float
     * @return float
     */
    public float getFloatNote(){
        if(this.note.equals("ABI") || this.note.equals("")){
            return 0;
        }
        return Float.parseFloat(this.note);
    }

    @Override
    public String toString() {
        return note;
    }

}
