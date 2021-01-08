package Model;

import Exceptions.CoefUeInvalidException;
import Exceptions.IdUeDuplicationException;

/**
 * Un bloc à options est composé de plusieurs cours portant le même nombre de crédits.
 * C'est aussi le nombre de crédits du bloc.
 */
public class BlocOptions extends BlocMultiple {
    private final int coef;

    /**
     * construit l'objet bloc option en fixant la valeur du coef que devrait avoir les sous blocs
     * @param id du bloc
     * @param coef fixe du bloc
     * @param nom du bloc
     */
    public BlocOptions(String id, int coef, String nom) {
        super(id,nom);
        this.coef = coef;
    }

    public BlocOptions(String id, int coef, String nom , Programme p ){
        super(id,nom,p);
        this.coef= coef;
    }

    @Override
    public void add(UE ue){
        if(ue.getCoef() == this.getCoef()){
            for(UE u:listUe){
                if(u.getId().equals(ue.getId())){
                    throw new IdUeDuplicationException(ue);
                }
            }
            this.listUe.add(ue);
        }
        else{
            throw new CoefUeInvalidException(ue);
        }
    }

    @Override
    public int getCoef() {
        return this.coef;
    }

    @Override
    public Note calcNote(Etudiant e) {
        Note nMax = new Note("0");
        boolean isBlocABI=true;
        for (UE ue : this.listUe) {
            if(e.getNotes().get(ue) != null){
                if (nMax.getFloatNote() < e.getNotes().get(ue).getFloatNote()){
                    nMax = e.getNotes().get(ue);
                }
                if (!(e.getNotes().get(ue).toString().equals("ABI"))) {
                    isBlocABI = false;
                }
            }
        }
        if(isBlocABI){
            return new Note("ABI");
        }
        else{
            return new Note(nMax.toString());
        }
    }

    @Override
    public void toXml(StringBuilder sb) {
        sb.append("        <option>\n")
                .append("            <identifier>").append(this.getId()).append("</identifier>\n")
                .append("            <name>").append(this.getNom()).append("</name>\n");
        for (UE ue: this.getUE()
        ) {
           sb.append("            <item>").append(ue.getId()).append("</item>\n");
        }
        sb.append("        </option>\n");
    }
}
