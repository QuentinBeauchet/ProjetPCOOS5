package View;

import Controller.HeaderListener;
import Exceptions.LookAndFeelException;
import Model.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

import static java.lang.Math.max;

public class Tableau {
    private final Home home;
    private JTable tableau;
    private JTable calculs;
    private JScrollPane PART1;
    private JScrollPane PART2;
    private JPanel Panel;
    private Sorter sorter;

    /**
     * Classe qui affiche le tableau grace a une JTable a partir d'une TabCreation.
     * @param tab TabCreation
     */
    public Tableau(TabCreation tab){
        home=tab.getHome();
        Object[] colones = tab.getColones();
        Object[][] lignes = tab.getLignes();
        Object[][] rowCalculs = tab.getCalculs();
        setTabLF(lignes,colones);
        setCalculs(rowCalculs,colones);
        setScrollBar();
        setLayout();
        setStyle();
        setEdition();
        setSorter();
    }

    /**
     * Look&Feel de la JTable des etudiants et instanciation de celle ci.
     * @param lignes Object[][]
     * @param colones Object[]
     */
    private void setTabLF(Object[][] lignes,Object[] colones){
        LookAndFeel previousLF=UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
        catch (Exception exception){/*Ne rien faire*/}
        tableau=setStyleLignes(lignes,colones);
        tableau.setName("tableau");
        try {
            UIManager.setLookAndFeel(previousLF);
        } catch (UnsupportedLookAndFeelException e) {
            throw new LookAndFeelException();
        }
    }

    /**
     * Look&Feel des JScroolPane et instanciation de ceux ci.
     */
    private void setScrollPaneLF(){
        LookAndFeel previousLF=UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        }
        catch (Exception exception){/*Ne rien faire*/}
        PART1= new JScrollPane(tableau);
        PART2= new JScrollPane(calculs);
        try {
            UIManager.setLookAndFeel(previousLF);
        } catch (UnsupportedLookAndFeelException e) {
            throw new LookAndFeelException();
        }
    }

    /**
     * Parametrage des JScrollPane.
     */
    private void setScrollBar() {
        setScrollPaneLF();
        tableau.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        calculs.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        PART1.getHorizontalScrollBar().setModel(PART2.getHorizontalScrollBar().getModel());
        PART1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        PART2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        calculs.setTableHeader(null);
    }

    /**
     * Configure le layout.
     */
    private void setLayout(){
        Panel=new JPanel();
        Panel.setLayout(new GridBagLayout());
        calculs.setPreferredScrollableViewportSize(new Dimension((int)calculs.getPreferredScrollableViewportSize().getWidth(),calculs.getRowCount()*calculs.getRowHeight()));
        PART2.setMinimumSize(new Dimension((int)calculs.getPreferredScrollableViewportSize().getWidth(),calculs.getRowHeight()*(calculs.getRowCount()+1)));

        Panel.add(PART1,new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));
        Panel.add(PART2,new GridBagConstraints(0,1,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));
    }

    /**
     * Appele les methodes pour configurer les style de tous les composants.
     */
    private void setStyle(){
        setStyleHeader();
        setStyleColonnes();
        setStyleCalculs();
    }

    /**
     * Configure le style des Header.
     */
    private void setStyleHeader(){
        JTableHeader header=tableau.getTableHeader();
        header.setReorderingAllowed(false);
        header.setForeground(new Color(153, 31, 0));
        header.setFont(header.getFont().deriveFont(Font.BOLD));
        header.addMouseListener(new HeaderListener(tableau));
    }

    /**
     * Configure le Model de la table calculs.
     * @param rowCalculs Object[][]
     * @param colones Object[]
     */
    private void setCalculs(Object[][] rowCalculs, Object[] colones){
        String name="calculs";
        CustomTableModel model=new CustomTableModel(home,rowCalculs,colones,name);
        calculs=new JTable(model);
        calculs.setName(name);
    }

    /**
     * Configure le style de la JTable des calculs.
     */
    private void setStyleCalculs(){
        calculs.setDefaultRenderer(String.class,new CustomRenderer());
        calculs.setDefaultRenderer(Float.class,new CustomRenderer());
        for (int i = 0; i < calculs.getColumnModel().getColumnCount(); i++) {
            int width=tableau.getColumnModel().getColumn(i).getWidth();
            TableColumn column=calculs.getColumnModel().getColumn(i);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setWidth(width);
        }
    }

    /**
     * Appele le CustomStringRenderer qui gere tout le grapique des cellules.
     * @param lignes Object[][]
     * @param colones Object[]
     * @return JTable
     */
    private JTable setStyleLignes(Object[][] lignes,Object[] colones){
        String name="tableau";
        CustomTableModel model=new CustomTableModel(home,lignes,colones,name);
        JTable table=new JTable(model);
        table.setDefaultEditor(Note.class, new CustomCellEditor(new JTextField()));
        table.setDefaultEditor(Integer.class, new CustomCellEditor(new JTextField()));
        return table;
    }

    /**
     * Configure le style des Colones.
     */
    private void setStyleColonnes(){
        tableau.setDefaultRenderer(String.class,new CustomRenderer());
        tableau.setDefaultRenderer(Integer.class,new CustomRenderer());
        tableau.setDefaultRenderer(Note.class,new CustomRenderer());
        tableau.setDefaultRenderer(Float.class,new CustomRenderer());
        TableColumnModel columnModel = tableau.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setResizable(false);
        }
        int maxwidth=tableau.getColumnModel().getColumn(0).getWidth();
        for (int i = 0; i < tableau.getRowCount(); i++) {
            JLabel programme=new JLabel((String)tableau.getModel().getValueAt(i, 3));
            maxwidth=max((int)programme.getPreferredSize().getWidth(),maxwidth);
        }
        TableColumn column=columnModel.getColumn(3);
        column.setMinWidth(maxwidth);
        column.setMaxWidth(maxwidth);
        column.setWidth(maxwidth);
    }

    /**
     * Empeche l'edition de tableau des calculs.
     */
    private void setEdition(){
        calculs.setCellSelectionEnabled(true);
        calculs.setDefaultEditor(Object.class, null);
    }

    /**
     * Configure le Sorter des colones.
     */
    private void setSorter(){
        sorter=new Sorter(tableau);
    }

    /**
     * Configure le sorter des lignes selon le filtre.
     * @param filter String
     */
    public void setSorter(String filter){
        sorter.setSorter(filter);
    }

    /**
     * Renvoit le JPanel qui contient les deux tableaux.
     * @return JPanel
     */
    public JPanel getPanel() {
        return Panel;
    }

    /**
     * Renvoit la JTable du tableau.
     * @return JTable
     */
    public JTable getTableau(){
        return tableau;
    }
}
