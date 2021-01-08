package Model;

import Exceptions.*;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Un programme est une liste de blocs à obtenir pour valider une étape.
 */
public class Programme {
    private final ArrayList<Bloc> blocs = new ArrayList<>();
    private final String nom;
    private final String id;

    public Programme(String nom, String id) {
        this.nom = nom;
        this.id = id;
        if(id.equals(""))throw new IdProgramInvalidException(this);
        if(nom.equals(""))throw new NameProgramInvalidException(this);
    }

    /**
     * Getteur du nom d'un programme
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getteur de l'ID d'un programme
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Getteur des blocs qui composent un programme
     * @return ArrayList de bloc
     */
    public ArrayList<Bloc> getBlocs() {
        return blocs;
    }

    /**
     * Ajoute un bloc à un programme
     * @param b le bloc
     */
    public void add(Bloc b){
        this.blocs.add(b);
    }

    /**
     * Renvoit la note d'un Etudiant pour ce Programme.
     *
     * @param e Etudiant
     * @return float
     */

    public float getNoteProgramme(Etudiant e){
        if(nom.equals("Aucun")){
            return Float.NaN;
        }
        float somme=0;
        float total=0;
        for (Bloc b : this.blocs) {
            somme += b.getCoef() * b.calcNote(e).getFloatNote();
            total += b.getCoef();
        }
        return MyTools.arondit(somme/total);
    }

    /**
     * Retourne une version XML d'un Programme
     * @param sb le StringBuilder auquel ajouter le programme
     */
    public void toXml(StringBuilder sb){
        sb.append("    <program>\n")
                .append("        <identifier>").append(this.getId()).append("</identifier>\n")
                .append("        <name>").append(this.getNom()).append("</name>\n");
        for (Bloc b: this.getBlocs()
             ) {
            b.toXml(sb);
        }
        sb.append("    </program>\n");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programme programme = (Programme) o;
        return Objects.equals(id, programme.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return nom;
    }

}
