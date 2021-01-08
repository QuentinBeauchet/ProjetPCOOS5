package Model;

/**
 * Interfact définissant les fonctions de base d'une UE
 */
public interface UE {
    /**
     * getteur de l'id d'une UE
     * @return String
     */
    String getId();

    /**
     * getteur d'un coef d'une UE
     * @return int
     */
    int getCoef();

    /**
     * getteur d'un nom d'une UE
     * @return String
     */
    String getNom();

    /**
     * convertie une UE en String
     * @return string
     */
    String toString();

    /**
     * calcule la note d'un étudiant dans l'UE
     * @param e l'etudiant
     * @return la Note
     */
    Note calcNote(Etudiant e);

    default boolean equals(UE ue){
        return ue.getId().equals(this.getId());
    }

    /**
     * Retourne une version XML par défaut d'une UE
     * @param sb le StringBuilder auquel ajouter l'UE
     */
    default void toXml(StringBuilder sb){
        sb.append("            <item>").append(this.getId()).append("</item>\n");
    }
}
