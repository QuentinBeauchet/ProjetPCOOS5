package Model;

import Controller.Boutons;
import Exceptions.LookAndFeelException;
import View.Home;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class PopUp {
    private final JDialog dialog;
    private Home home;
    private final JPanel[] blocs=new JPanel[3];
    private ArrayList<Bloc> blocOptionsArrayList;
    private ArrayList<Bloc> blocCompositeArrayList;
    private JPanel optionPanel;
    private JPanel compositePanel;
    private JPanel multiplePanel;
    private final JList<Object> listeBlocs=new JList<>();
    private JList<Programme> choixProgramme;
    private ButtonGroup buttonGroup;
    public static final Border normalBorder = (new JTextField(20)).getBorder();
    public static final Border erreurBorder = BorderFactory.createLineBorder(Color.red);

    /**
     * Classe du JDialog qui permet de confirmer quand on quitte le programme.
     */
    public PopUp(String action, Home... home){
        if(home.length!=0){
            this.home=home[0];
        }
        dialog=new JDialog(Window.getWindows()[0]);
        setPanel(action);
        dialog.setVisible(true);
    }

    /**
     * Change le panel selon l'action.
     * @param action String
     */
    private void setPanel(String action){
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setResizable(false);
        switch (action){
            case "Quitter":
                dialog.setSize(new Dimension(200,200));
                dialog.setUndecorated(true);
                dialog.setBackground(new Color(0,0,0,0));
                setPanelQuitter();
                break;
            case "Ajouter un etudiant":
                dialog.setSize(new Dimension(500,230));
                dialog.setUndecorated(false);
                dialog.setBackground(new Color(0,0,0,255));
                dialog.setTitle("Ajouter un Etudiant");
                setPanelEtudiant();
                break;
            case "Ajouter un cours":
                dialog.setSize(new Dimension(500,460));
                dialog.setUndecorated(false);
                dialog.setBackground(new Color(0,0,0,255));
                dialog.setTitle("Ajouter un Cours");
                setPanelCours();
                break;
            case "Ajouter un programme":
                dialog.setSize(new Dimension(200,150));
                dialog.setUndecorated(false);
                dialog.setBackground(new Color(0,0,0,255));
                dialog.setTitle("Ajouter un Programme");
                setPanelProgramme();
                break;
            default:
        }
        dialog.setLocationRelativeTo(null);
    }

    /**
     * Panel etudiant.
     */
    private void setPanelEtudiant(){
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel nom=new JLabel("NOM:");
        panel.add(nom,new GridBagConstraints(0,0,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,20,0,0),0,0));

        JTextField fieldNom=new JTextField(20);
        panel.add(fieldNom,new GridBagConstraints(1,0,1,1,1,0,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(20,-150,0,20),0,0));

        JLabel prenom=new JLabel("PRENOM:");
        panel.add(prenom,new GridBagConstraints(0,1,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,20,0,0),0,0));

        JTextField fieldPrenom=new JTextField(20);
        panel.add(fieldPrenom,new GridBagConstraints(1,1,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,-150,0,20),0,0));

        JLabel id=new JLabel("NUMERO ETUDIANT:");
        panel.add(id,new GridBagConstraints(0,2,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,20,0,0),0,0));

        JTextField fieldId=new JTextField(20);
        panel.add(fieldId,new GridBagConstraints(1,2,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,-150,0,20),0,0));

        JLabel programme=new JLabel("CHOIX PROGRAMME:");
        panel.add(programme,new GridBagConstraints(0,3,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,20,0,0),0,0));

        int len=home.getXml().getProgramList().size();
        Object[] programmes=new Object[len+1];
        programmes[0]=new Programme("Aucun","0");
        for (int i = 1; i < len+1; i++) {
            programmes[i]=home.getXml().getProgramList().get(i-1);
        }

        JList<?> choixProgramme=new JList<>(programmes);
        choixProgramme.setVisibleRowCount(1);
        panel.add(new JScrollPane(choixProgramme),new GridBagConstraints(1,3,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,-150,0,20),0,0));

        JButton confirmation=new JButton("Confirmer");
        confirmation.addActionListener(new Boutons("Ajout Etudiant",home,dialog,fieldNom,fieldPrenom,fieldId,choixProgramme));
        panel.add(confirmation,new GridBagConstraints(0,4,2,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(30,150,20,150),0,0));

        dialog.add(panel);
    }

    /**
     * Panel Cours.
     */
    private void setPanelCours(){
        JPanel panel=new JPanel(new GridBagLayout());

        //COURS
        JLabel nom=new JLabel("NOM:");
        panel.add(nom,new GridBagConstraints(0,0,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,20,0,0),0,0));

        JTextField fieldNom=new JTextField(20);
        panel.add(fieldNom,new GridBagConstraints(1,0,2,1,1,0,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,new Insets(20,-50,0,20),0,0));

        JLabel coeff=new JLabel("COEFFICIENT:");
        panel.add(coeff,new GridBagConstraints(0,1,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,20,0,0),0,0));

        JTextField fieldCoeff=new JTextField(20);
        panel.add(fieldCoeff,new GridBagConstraints(1,1,2,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,-50,0,20),0,0));

        JLabel id=new JLabel("IDENTIFIANT:");
        panel.add(id,new GridBagConstraints(0,2,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,20,0,0),0,0));

        JTextField fieldId=new JTextField(20);
        panel.add(fieldId,new GridBagConstraints(1,2,2,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,-50,0,20),0,0));

        //PROGRAMME
        JPanel progPanel=new JPanel(new GridBagLayout());

        JLabel programme=new JLabel("PROGRAMME:");
        progPanel.add(programme,new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));

        progPanel.add(setProgrammeList(),new GridBagConstraints(1,0,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,26,0,20),0,0));

        panel.add(progPanel,new GridBagConstraints(0,3,3,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,20,0,0),0,0));

        //BOUTONS BLOC
        buttonGroup=new ButtonGroup();

        JRadioButton simple=new JRadioButton("Bloc Simple");
        buttonGroup.add(simple);
        simple.setActionCommand("0");
        simple.addActionListener(new Boutons("Switch RadioBoutons",this,buttonGroup));
        panel.add(simple,new GridBagConstraints(0,4,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(15,50,0,0),0,0));

        JRadioButton option=new JRadioButton("Bloc a Options");
        buttonGroup.add(option);
        option.setActionCommand("1");
        option.addActionListener(new Boutons("Switch RadioBoutons",this,buttonGroup));
        panel.add(option,new GridBagConstraints(1,4,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(15,0,0,0),0,0));

        JRadioButton composite=new JRadioButton("Bloc Composite");
        buttonGroup.add(composite);
        composite.setActionCommand("2");
        composite.addActionListener(new Boutons("Switch RadioBoutons",this,buttonGroup));
        panel.add(composite,new GridBagConstraints(2,4,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(15,0,0,20),0,0));

        //PANEL BLOC SIMPLE
        JPanel simplePanel=new JPanel();
        blocs[0]=simplePanel;
        panel.add(simplePanel,new GridBagConstraints(0,5,3,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,0,0,0),0,0));
        simplePanel.add(new JLabel("<html><div style='text-align: center;'>Un <B><i>bloc simple</B></i> est juste un cours.</div></html>"));

        //PANEL BLOC OPTION
        optionPanel=new JPanel();
        blocs[1]=optionPanel;
        panel.add(optionPanel,new GridBagConstraints(0,5,3,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,0,0,0),0,0));
        optionPanel.add(new JLabel("<html><div style='text-align: center;'><p>Un <B><i>bloc à options</B></i> est composé de plusieurs cours portant <br/>le même nombre de crédits. C'est aussi le nombre de crédits du bloc.</p></div></html>"));

        //PANEL BLOC COMPOSITE
        compositePanel=new JPanel();
        blocs[2]=compositePanel;
        panel.add(compositePanel,new GridBagConstraints(0,5,3,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(5,0,0,0),0,0));
        compositePanel.add(new JLabel("<html><div style='text-align: center;'><p>Un <B><i>bloc composite</B></i> est composé de plusieurs cours.<br/>Le nombre de crédits du bloc est la somme des crédits des UE qui le composent.</p></div></html>"));

        //BLOC MULTIPLE
        multiplePanel=new JPanel(new GridBagLayout());
        ButtonGroup multipleButtonGroup=new ButtonGroup();

        //BOUTONS BLOC MULTIPLE
        JRadioButton nouveauBloc = new JRadioButton("Nouveau Bloc");
        nouveauBloc.setActionCommand("nouveau");
        multipleButtonGroup.add(nouveauBloc);
        multiplePanel.add(nouveauBloc,new GridBagConstraints(0,0,2,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,0,0,0),0,0));

        JRadioButton ancienBloc = new JRadioButton("Bloc deja existant");
        ancienBloc.setActionCommand("ancien");
        multipleButtonGroup.add(ancienBloc);
        multiplePanel.add(ancienBloc,new GridBagConstraints(0,3,2,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,0,0,0),0,0));

        JLabel compositeNom=new JLabel("NOM DU BLOC:");
        multiplePanel.add(compositeNom,new GridBagConstraints(0,1,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,0,0,0),0,0));

        JTextField compositeNomField=new JTextField(20);
        multiplePanel.add(compositeNomField,new GridBagConstraints(1,1,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,0,0),0,0));

        JLabel compositeID=new JLabel("ID DU BLOC:");
        multiplePanel.add(compositeID,new GridBagConstraints(0,2,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,0,0,0),0,0));

        JTextField compositeIDField=new JTextField(20);
        multiplePanel.add(compositeIDField,new GridBagConstraints(1,2,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,0,0),0,0));

        setBlocVisible(-1);

        JButton confirmation=new JButton("Confirmation");
        confirmation.addActionListener(new Boutons("Ajout Cours",home,dialog,fieldNom,fieldCoeff,fieldId,buttonGroup,multipleButtonGroup,compositeNomField,compositeIDField,listeBlocs,choixProgramme));
        panel.add(confirmation,new GridBagConstraints(0,6,3,1,1,1,GridBagConstraints.SOUTH,GridBagConstraints.HORIZONTAL,new Insets(-10,150,15,150),0,0));

        dialog.add(panel);
    }

    /**
     * Panel Programme.
     */
    private void setPanelProgramme(){
        JPanel panel=new JPanel(new GridBagLayout());

        JLabel nom=new JLabel("NOM:");
        panel.add(nom,new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,20,0,5),0,0));

        JTextField fieldNom=new JTextField(20);
        panel.add(fieldNom,new GridBagConstraints(1,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,0,0,20),0,0));

        JLabel id=new JLabel("ID:");
        panel.add(id,new GridBagConstraints(0,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,20,0,5),0,0));

        JTextField fieldID=new JTextField(20);
        panel.add(fieldID,new GridBagConstraints(1,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,0,0,20),0,0));

        JButton confirmation=new JButton("Confirmer");
        confirmation.addActionListener(new Boutons("Ajout Programme",home,dialog,fieldNom,fieldID));
        panel.add(confirmation,new GridBagConstraints(0,2,2,1,1,1,GridBagConstraints.SOUTH,GridBagConstraints.HORIZONTAL,new Insets(0,40,15,40),0,0));

        dialog.add(panel);

    }


    /**
     * Panel Quitter.
     */
    private void setPanelQuitter(){
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createLineBorder(Color.lightGray,5)));

        JPanel text=new JPanel();
        text.setLayout(new BorderLayout());
        text.setBackground(new Color(230,230,230));
        text.setBorder(BorderFactory.createEtchedBorder());

        JLabel label=new JLabel("<html><div style='text-align: center;'><b>Les modifications apportés ne seront pas sauvegardées, voulez vous continuer ?<b></div></html>");
        text.add(label,BorderLayout.CENTER);
        label.setForeground(Color.darkGray);

        panel.add(text,new GridBagConstraints(0,0,2,1,0,1,GridBagConstraints.NORTH,GridBagConstraints.BOTH,new Insets(10,10,0,10),0,0));

        JButton oui=setBoutonLF("OUI");
        panel.add(oui,new GridBagConstraints(0,1,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10),0,0));
        setStyleBouton(oui);
        oui.addActionListener(new Boutons("STOP",this));

        JButton non=setBoutonLF("NON");
        panel.add(non,new GridBagConstraints(1,1,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10),0,0));
        setStyleBouton(non);
        non.addActionListener(new Boutons("Annuler",dialog));

        dialog.add(panel);
    }

    /**
     * Configure le style des boutons du JPanel.
     * @param bouton JButton
     */
    private void setStyleBouton(JButton bouton){
        bouton.setBackground(new Color(59, 89, 182));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
        bouton.setFont(new Font("Tahoma", Font.BOLD, 12));
    }

    /**
     * Configure le Look&Feel des boutons du JPanel.
     * @param s String
     * @return JButton
     */
    private JButton setBoutonLF(String s){
        JButton bouton;
        LookAndFeel previousLF = UIManager.getLookAndFeel();
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
        catch (Exception exception){/*Ne rien faire*/}
        bouton=new JButton(s);
        try {
            UIManager.setLookAndFeel(previousLF);
        } catch (UnsupportedLookAndFeelException e) {
            throw new LookAndFeelException();
        }
        return bouton;
    }

    /**
     * Filtre les blocs multiple selon leurs type pour chaque programme.
     */
    private void filterBlocsType(){
        ArrayList<Programme> programmes=home.getXml().getProgramList();
        blocOptionsArrayList=new ArrayList<>();
        blocCompositeArrayList=new ArrayList<>();
        if(!(choixProgramme.getModel().getElementAt(0).getNom().equals("Aucun programme disponible"))){
            int index=choixProgramme.getLastVisibleIndex();
            ArrayList<Bloc> blocsArrayList=programmes.get(index).getBlocs();
            for(Bloc b:blocsArrayList){
                if (BlocComposite.class.equals(b.getClass())) {
                    blocCompositeArrayList.add(b);
                }
                else if(BlocOptions.class.equals(b.getClass())){
                    blocOptionsArrayList.add(b);
                }
            }
        }
    }

    /**
     * Permet de cacher le contenu selon le type de bloc
     * lors de l'ajout de cours.
     * @param index int
     */
    public void setBlocVisible(int index){
        for (int i = 0; i < blocs.length; i++) {
            JPanel panel=blocs[i];
            panel.setVisible(false);
            if(i==index){
                panel.setVisible(true);
                filterBlocsType();
                if(index==1){
                    ArrayToJList(blocOptionsArrayList, optionPanel);
                }
                else if(index==2){
                    ArrayToJList(blocCompositeArrayList, compositePanel);
                }
                else{
                    multiplePanel.setVisible(false);
                }
            }
            else{
                panel.setVisible(false);
            }
        }
    }

    /**
     * Crée une JList selon une ArrayList<Bloc> et l'ajoute au JPanel.
     * @param blocOptionsArrayList ArrayList<Bloc>
     * @param Panel JPanel
     */
    private void ArrayToJList(ArrayList<Bloc> blocOptionsArrayList, JPanel Panel) {
        for(Component c:multiplePanel.getComponents()){
            if(c.getClass().equals(JScrollPane.class)){
                multiplePanel.remove(c);
            }
        }
        multiplePanel.setVisible(true);
        CustomListModel model=new CustomListModel(blocOptionsArrayList.toArray());
        listeBlocs.setModel(model);
        listeBlocs.setVisibleRowCount(1);

        multiplePanel.add(new JScrollPane(listeBlocs),new GridBagConstraints(0,4,2,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,0,0,0),0,0));
        Panel.add(multiplePanel);
    }

    /**
     * Crée la JList des programmes de l'xml.
     * @return JScrollPane
     */
    private JScrollPane setProgrammeList(){
        int len=home.getXml().getProgramList().size();
        Object[] programmes;
        if(len>0){
            programmes=new Programme[len];
            for (int i = 0; i < len; i++) {
                programmes[i]=home.getXml().getProgramList().get(i);
            }
        }
        else{
            programmes=new Programme[1];
            programmes[0]=new Programme("Aucun programme disponible","111111111111111111111");
        }

        choixProgramme=new JList(programmes);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) choixProgramme.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        choixProgramme.setVisibleRowCount(1);
        JScrollPane jScrollPane = new JScrollPane(choixProgramme);
        JScrollBar bar=jScrollPane.getVerticalScrollBar();
        bar.addAdjustmentListener(e -> {
            if(buttonGroup.getSelection()!=null){
                setBlocVisible(Integer.parseInt(buttonGroup.getSelection().getActionCommand()));
            }
        });
        return jScrollPane;
    }
}
