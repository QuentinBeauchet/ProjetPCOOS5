package Model;

import Exceptions.*;

/**
 * Un cours est l'unité d'enseignement de base d'une université.
 */
public class Cours implements UE{
    private final String id;
    private final int coef;
    private final String nom;

    /**
     * créer l'objet cours et test si tous les paramètres sont valides
     * @param id du cours =! ""
     * @param coef du cours > 0
     * @param nom du cours =! ""
     */
    public Cours(String id, int coef, String nom) {
        this.id = id;
        this.coef = coef;
        this.nom = nom;
        if(id.equals(""))throw new IdUeInvalidException(this);
        if(nom.equals(""))throw new NameUeInvalidException(this);
        if(coef< 0)throw new CoefUeInvalidException(this);
    }
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getCoef() {
        return this.coef;
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public String toString() {
        return this.id + " "+ this.nom;
    }

    @Override
    public Note calcNote(Etudiant e) {
        if(e.getNotes().get(this)==null)return new Note("");
        return e.getNotes().get(this);
    }

    @Override
    public void toXml(StringBuilder sb) {
        sb.append("    <course>\n")
                .append("        <identifier>").append(this.getId()).append("</identifier>\n")
                .append("        <name>").append(this.getNom()).append("</name>\n")
                .append("        <credits>").append(this.getCoef()).append("</credits>\n")
                .append("    </course>\n");
    }

}
