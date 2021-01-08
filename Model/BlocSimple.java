package Model;

import java.util.ArrayList;

/**
 * Un bloc simple est juste un cours.
 */
public class BlocSimple extends Bloc {
    private final UE ue;

    public BlocSimple(UE ue) {
        super(ue.getId(),ue.getNom());
        this.ue=ue;
    }
    @Override
    public int getCoef(){
        return this.ue.getCoef();
    }

    @Override
    public Note calcNote(Etudiant e) {
        return ue.calcNote(e);
    }

    @Override
    public void toXml(StringBuilder sb) {
        sb.append("        <item>").append(this.getId()).append("</item>\n");
    }

    @Override
    public String toString() {
        return this.getId() + " " + this.getNom();
    }
    @Override
    public ArrayList<UE> getUE(){
        ArrayList<UE> ue= new ArrayList<>();
        ue.add(this.ue);
        return ue;
    }

    @Override
    public void toCSVTtitle(StringBuilder sb) {
        sb.append(",\"").append(this).append("\"");
    }

    @Override
    public void toCsvMoy(StringBuilder sb, Etudiant e) {
       sb.append(",\"").append(this.calcNote(e)).append("\"");
    }

}
