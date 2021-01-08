package Controller;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

public class HeaderListener implements MouseListener{
    private final JTable tableau;
    private JDialog dialog;
    private JLabel texte;

    /**
     * Classe de l'EventListener de l'Header du tableau qui permet d'afficher
     * les nom des cours quand on laisse la souris dessus au bout de 0.2s.
     * @param table JTable
     */
    public HeaderListener(JTable table){
        tableau=table;
    }

    /**
     * Creation du JDialog avec le nom du cours a la position de la souris.
     * @param mouseEvent MouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dialog=new JDialog();
        TableColumnModel columnModel = tableau.getTableHeader().getColumnModel();
        int index=columnModel.getColumnIndexAtX(mouseEvent.getX());
        if(index!=-1){
            TableColumn currentcolumn = columnModel.getColumn(index);
            setLabel(currentcolumn);
            setDialog(mouseEvent.getLocationOnScreen());
        }
    }

    /**
     * Supression du JDialog quand on sors de l'Header.
     * @param mouseEvent MouseEvent
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        dialog.dispose();
    }

    /**
     * Application du nom du cours dans le JDialog.
     * @param currentcolumn TableColumn
     */
    private void setLabel(TableColumn currentcolumn){
        texte=new JLabel((String) currentcolumn.getHeaderValue());
        texte.setFont(new Font("Verdana", Font.BOLD, 12));
        texte.setOpaque(true);
        texte.setHorizontalAlignment(SwingConstants.CENTER);
        texte.setVerticalAlignment(SwingConstants.CENTER);
        dialog=new JDialog();
        texte.setBorder(BorderFactory.createLoweredBevelBorder());
        dialog.add(texte);
    }

    /**
     * Style du dialog.
     * @param pos Point
     */
    private void setDialog(Point pos){
        dialog.setUndecorated(true);
        dialog.setMinimumSize(new Dimension((int)texte.getPreferredSize().getWidth()+10,(int)texte.getPreferredSize().getHeight()));
        dialog.setResizable(false);
        dialog.setLocation((int)pos.getX(),(int)pos.getY()-20);
        dialog.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) { }

    @Override
    public void mousePressed(MouseEvent mouseEvent) { }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) { }

}