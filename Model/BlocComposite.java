package Model;

/**
 * Un bloc composite est composé de plusieurs cours.
 * Le nombre de crédits du bloc est la somme des crédits des UE qui le composent.
 */
public class BlocComposite extends BlocMultiple {

    public BlocComposite(String id, String nom) {
        super(id, nom);
    }

    public BlocComposite(String id, String nom , Programme p ){
        super(id,nom,p);
    }

    @Override
    public Note calcNote(Etudiant e) {
        boolean isABI = true;
        float somme=0;
        float total=0;
        for (UE ue: this.listUe){
            if(!(ue.calcNote(e).toString().equals("ABI")))isABI=false;
            somme += ue.getCoef()*ue.calcNote(e).getFloatNote();
            total += ue.getCoef();
        }
        return (isABI ? new Note("ABI") : new
                Note(String.valueOf(somme/total)));
    }
    @Override
    public void toXml(StringBuilder sb) {
        sb.append("        <composite>\n")
                .append("            <identifier>").append(this.getId()).append("</identifier>\n")
                .append("            <name>").append(this.getNom()).append("</name>\n");
        for (UE ue: this.getUE()
             ) {
            sb.append("            <item>").append(ue.getId()).append("</item>\n");
        }
        sb.append("        </composite>\n");
    }

    @Override
    public int getCoef() {
        int coef=0;
        for (UE ue: this.listUe
             ) {
            coef+= ue.getCoef();
        }
        return coef;
    }
}
