package Model;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CustomCellEditor extends DefaultCellEditor {
    private final JTextField textField;
    private Class<?> currentclass;

    /**
     * Permet de forcer le typage lors de la saisie de texte dans le tableau.
     * @param textField JTextField
     */
    public CustomCellEditor(JTextField textField) {
        super(textField);
        this.textField = textField;
    }

    @Override
    public boolean stopCellEditing() {
        String txt=textField.getText();
        if(currentclass == Note.class){
            try {
                float value = Float.parseFloat(txt);
                if (value < 0 || value>20) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                if(txt.equals("") || txt.equals("ABI")){
                    textField.setBorder(null);
                    this.fireEditingStopped();
                    return true;
                }
                else{
                    textField.setBorder(new LineBorder(Color.red));
                    return false;
                }
            }
        }
        else if(currentclass==Integer.class){
            try{
                int value=Integer.parseInt(txt);
                if(!(txt.length()==8) || value<10000000){
                    throw new NumberFormatException();
                }
            }
            catch (NumberFormatException e){
                textField.setBorder(new LineBorder(Color.red));
                return false;
            }
            textField.setBorder(null);
            this.fireEditingStopped();
            return true;
        }
        else if(currentclass==Float.class){
            try{
                Float.valueOf(txt);
            }
            catch (NumberFormatException e){
                textField.setBorder(new LineBorder(Color.red));
                return false;
            }
            textField.setBorder(null);
            this.fireEditingStopped();
            return true;
        }
        return super.stopCellEditing();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        currentclass=value.getClass();
        textField.setBorder(new LineBorder(Color.black));
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

}