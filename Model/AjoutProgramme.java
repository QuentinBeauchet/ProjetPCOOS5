package Model;

import Exceptions.IdProgramDuplicationException;
import Exceptions.IdProgramInvalidException;
import Exceptions.NameProgramInvalidException;
import View.Home;

import javax.swing.*;
import java.util.ArrayList;

public class AjoutProgramme {

    /**
     * Ajoute un Programme a l'xml et au tableau.
     * @param home Home
     * @param args Object[]
     */
    public AjoutProgramme(Home home, Object[] args){
        JDialog dialog=(JDialog)args[0];
        JTextField nom=((JTextField)args[1]);
        JTextField id=((JTextField)args[2]);
        try{
            Programme programme=new Programme(nom.getText(),id.getText());
            ArrayList<Programme> programmeArrayList=home.getXml().getProgramList();
            if(MyTools.isIdProgramAlreadyExist(programmeArrayList,programme.getId())){
                throw new IdProgramDuplicationException(programme);
            }
            programmeArrayList.add(programme);
            nom.setBorder(PopUp.normalBorder);
            id.setBorder(PopUp.normalBorder);
            dialog.dispose();
        }
        catch (IdProgramInvalidException exception){
            nom.setBorder(PopUp.normalBorder);
            id.setBorder(PopUp.erreurBorder);
        }
        catch (NameProgramInvalidException exception){
            id.setBorder(PopUp.normalBorder);
            nom.setBorder(PopUp.erreurBorder);
        }
    }
}
