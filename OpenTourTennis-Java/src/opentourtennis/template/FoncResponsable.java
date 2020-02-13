/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opentourtennis.template;

import ConnexionBD.DbArbitreDAO;
import ConnexionBD.DbDuoDAO;
import ConnexionBD.DbJoueurDAO;
import ConnexionBD.DbMatchDAO;
import static ConnexionBD.DbMatchDAO.lesMatchs;
import ConnexionBD.DbPlanningDAO;
import ConnexionBD.MonMariaDbDataSource;
import Entities.Arbitres;
import Entities.Enum.Court;
import Entities.Duo;
import Entities.Enum.Horaire;
import Entities.Joueurs;
import Entities.Enum.Jour;
import Entities.Matchs.Simple;
import Entities.Enum.Type_Match;
import Entities.Matchs.Doubles;
import Entities.Matchs.Match;
import Entities.Matchs.Planning;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author p1805285
 */
public final class FoncResponsable extends javax.swing.JFrame {

    private static Connection c;
    private static DbJoueurDAO BDJ;
    private static DbArbitreDAO BDA;
    private static DbDuoDAO BDD;
    private static DbMatchDAO BDM;
    private static DbPlanningDAO BDP;
    int an;
    String nom;
    
    private JButton boutSelect;
    private JPanel PanelSelect;
    private ArrayList<Duo> listeD;
    private ArrayList<Arbitres> listeALigne = new ArrayList();
    private int nbJourEv;
    private String date;
    String NomP;
    int idP;
    Planning p;
    /**
     * Creates new form NewJFrame
     * @param date
     * @param nbJour
     * @param NomT
     * @param an
     */
    public FoncResponsable(String date, int nbJour, String NomT, int an, int idP) {
        try {
            
            int[] coord;
            int i = 0;
            initComponents();
            
            if(c == null){
                MonMariaDbDataSource MMDDS = MonMariaDbDataSource.getMdbDataSource();
                c = MMDDS.getConnection();
            }
            BDJ = new DbJoueurDAO(c);
            BDA = new DbArbitreDAO(c);
            BDD = new DbDuoDAO(c,BDJ);
            BDP = new DbPlanningDAO(c);
            this.idP = idP;
            this.NomP = NomT;
            nbJourEv = nbJour;
            this.date = date;
            
            boolean simple = !(DbMatchDAO.lesSimples.isEmpty());
            boolean Double = !(DbMatchDAO.lesDoubles.isEmpty());
            boolean effectue = false;
            
            p = new Planning(this.NomP,this.date.trim(),nbJourEv,Joueurs.count-1,simple,Double,effectue);
            BDM = new DbMatchDAO(c,p.getId());
            
            boutSelect = AjouterMatch;
            PanelSelect = PanelAjoutM;
            
            PanelModifM.setVisible(false);
            PanelSupprimM.setVisible(false);
            PanelplanningM.setVisible(false);
            this.setListCBJoueur();
            this.setListCBArbiChaise();
            this.setListCBArbiLigne();
            this.setListCBRamaseur();
            this.an = an;
            
            Court[] court = Court.values();
            for(Court c : court){
                ComboCourtA.addItem(c.toString());
                ComboCourtM.addItem(c.toString());
            }
            
            ArrayList<Jour> lesJours = new ArrayList();
            Jour[] jour = Jour.values();
            for(Jour j : jour){
                if( i < nbJour){
                    lesJours.add(j);
                    i++;
                }
            }
            lesJours.sort(Comparator.reverseOrder());
            i = 0;
            for(Jour j : lesJours){
                if( i < nbJour){
                    ComboJourA.addItem(j.toString());
                    ComboJourM.addItem(j.toString());
                    i++;
                }
            }
            
            Horaire[] horaire = Horaire.values();
            for(Horaire h : horaire){
                ComboCreneauA.addItem(h.toString());
                ComboCreneauM.addItem(h.toString());
            }
            
            Type_Match[] type_match = Type_Match.values();
            for(Type_Match tm : type_match){
                ComboTypeMatchA.addItem(tm.toString());
                ComboTypeMatchM.addItem(tm.toString());
            }
            
            JourSelect.setText("Jour 1 : "+date);
            Planning.setValueAt(Horaire.h11, 0, 0);
            Planning.setValueAt(Horaire.h15, 1, 0);
            Planning.setValueAt(Horaire.h18, 2, 0);
            Planning.setValueAt(Horaire.h21, 3, 0);
            int col = Planning.getColumnCount();
            int row = Planning.getRowCount();
            for(int j = 1 ; j < col ; j++){
                for(int k = 0 ; k < row ; k++){
                    Planning.setValueAt(" ", k, j);
                }
            }
            for(Match m : DbMatchDAO.lesMatchs){
                coord = this.setCoordonees(m);
                if(m instanceof Simple){
                    Simple s = (Simple) m;
                    Planning.setValueAt(s.toString(), coord[0], coord[1]);
                } else if(m instanceof Doubles){
                    Doubles d = (Doubles) m;
                    Planning.setValueAt(d.toString(), coord[0], coord[1]);
                }
            }
        } catch (SQLException | InterruptedException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Menu = new javax.swing.JPanel();
        AjouterMatch = new javax.swing.JButton();
        ModifierMatch = new javax.swing.JButton();
        SupprimerMatch = new javax.swing.JButton();
        VoirPlanning = new javax.swing.JButton();
        PanelSupprimM = new javax.swing.JPanel();
        SimpleS = new javax.swing.JButton();
        DoubleS = new javax.swing.JButton();
        ValiderS = new javax.swing.JButton();
        SeparatorS = new javax.swing.JSeparator();
        CreneauS = new javax.swing.JLabel();
        DateS = new javax.swing.JLabel();
        ArbChaiseS = new javax.swing.JLabel();
        TerrainS = new javax.swing.JLabel();
        ArbLigneS = new javax.swing.JLabel();
        JoueursS = new javax.swing.JLabel();
        TFJoueur1S = new javax.swing.JTextField();
        TFJoueur2S = new javax.swing.JTextField();
        TFDateS = new javax.swing.JTextField();
        TFCreneauS = new javax.swing.JTextField();
        TFTerrainS = new javax.swing.JTextField();
        TFArbChaiseS = new javax.swing.JTextField();
        CBArbLigneS = new javax.swing.JComboBox<>();
        SeklectMatchS = new javax.swing.JComboBox<>();
        RamasseurS = new javax.swing.JLabel();
        RamasseurCBS = new javax.swing.JComboBox<>();
        PanelplanningM = new javax.swing.JPanel();
        ValiderP = new javax.swing.JButton();
        JourSelect = new javax.swing.JLabel();
        DateNext = new javax.swing.JButton();
        DatePrevious = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Planning = new javax.swing.JTable();
        PanelModifM = new javax.swing.JPanel();
        SimpleM = new javax.swing.JButton();
        DoubleM = new javax.swing.JButton();
        ComboJourM = new javax.swing.JComboBox<>();
        ModifArbiLigne = new javax.swing.JButton();
        ModifRamasseur = new javax.swing.JButton();
        ValiderM = new javax.swing.JButton();
        ComboCourtM = new javax.swing.JComboBox<>();
        ComboTypeMatchM = new javax.swing.JComboBox<>();
        ComboJoueur1M = new javax.swing.JComboBox<>();
        ComboJoueur2M = new javax.swing.JComboBox<>();
        ComboArbiChaiseM = new javax.swing.JComboBox<>();
        ComboArbiLigneM = new javax.swing.JComboBox<>();
        ComboRamasseurM = new javax.swing.JComboBox<>();
        TextJour1 = new javax.swing.JLabel();
        TextCreneau1 = new javax.swing.JLabel();
        ComboCreneauM = new javax.swing.JComboBox<>();
        TextCourt1 = new javax.swing.JLabel();
        TextTypeMatch1 = new javax.swing.JLabel();
        SelectMatchM = new javax.swing.JComboBox<>();
        SeparatorM = new javax.swing.JSeparator();
        ArbChaise = new javax.swing.JLabel();
        Joueur = new javax.swing.JLabel();
        PanelAjoutM = new javax.swing.JPanel();
        GénéAuto = new javax.swing.JButton();
        SimpleA = new javax.swing.JButton();
        DoubleA = new javax.swing.JButton();
        ComboJourA = new javax.swing.JComboBox<>();
        GénéJoueur = new javax.swing.JButton();
        GénéArbiChaise = new javax.swing.JButton();
        GénéArbiLigne = new javax.swing.JButton();
        GénéRamasseur = new javax.swing.JButton();
        Ajouter = new javax.swing.JButton();
        ComboCourtA = new javax.swing.JComboBox<>();
        ComboTypeMatchA = new javax.swing.JComboBox<>();
        ComboJoueur1A = new javax.swing.JComboBox<>();
        ComboJoueur2A = new javax.swing.JComboBox<>();
        ComboArbiChaiseA = new javax.swing.JComboBox<>();
        ComboArbiLigneA = new javax.swing.JComboBox<>();
        ComboRamasseurA = new javax.swing.JComboBox<>();
        TextJour = new javax.swing.JLabel();
        TextCreneau = new javax.swing.JLabel();
        ComboCreneauA = new javax.swing.JComboBox<>();
        TextCourt = new javax.swing.JLabel();
        TextTypeMatch = new javax.swing.JLabel();
        SelectManArbiLigneA = new javax.swing.JButton();
        SelectManRamasseurA = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        AjouterMatch.setText("Ajouter Match");
        AjouterMatch.setEnabled(false);
        AjouterMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterMatchActionPerformed(evt);
            }
        });

        ModifierMatch.setText("Modifier Match");
        ModifierMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifierMatchActionPerformed(evt);
            }
        });

        SupprimerMatch.setText("Supprimer Match");
        SupprimerMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupprimerMatchActionPerformed(evt);
            }
        });

        VoirPlanning.setText("Planning");
        VoirPlanning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoirPlanningActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AjouterMatch, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ModifierMatch, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SupprimerMatch, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VoirPlanning, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AjouterMatch)
                    .addComponent(ModifierMatch)
                    .addComponent(SupprimerMatch)
                    .addComponent(VoirPlanning))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        SimpleS.setText("Simple");
        SimpleS.setEnabled(false);
        SimpleS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpleSActionPerformed(evt);
            }
        });

        DoubleS.setText("Double");
        DoubleS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoubleSActionPerformed(evt);
            }
        });

        ValiderS.setText("Supprimer");
        ValiderS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValiderSActionPerformed(evt);
            }
        });

        SeparatorS.setBackground(new java.awt.Color(0, 0, 0));

        CreneauS.setText("Créneau");

        DateS.setText("Date");

        ArbChaiseS.setText("Arbitre de chaise");

        TerrainS.setText("Terrain");

        ArbLigneS.setText("Arbitre de lignes");

        JoueursS.setText("Joueurs");

        TFJoueur1S.setEditable(false);
        TFJoueur1S.setFocusable(false);

        TFJoueur2S.setEditable(false);
        TFJoueur2S.setFocusable(false);

        TFDateS.setEditable(false);
        TFDateS.setFocusable(false);

        TFCreneauS.setEditable(false);
        TFCreneauS.setFocusable(false);

        TFTerrainS.setEditable(false);
        TFTerrainS.setFocusable(false);

        TFArbChaiseS.setEditable(false);
        TFArbChaiseS.setFocusable(false);

        SeklectMatchS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeklectMatchSActionPerformed(evt);
            }
        });

        RamasseurS.setText("Ramasseurs");

        javax.swing.GroupLayout PanelSupprimMLayout = new javax.swing.GroupLayout(PanelSupprimM);
        PanelSupprimM.setLayout(PanelSupprimMLayout);
        PanelSupprimMLayout.setHorizontalGroup(
            PanelSupprimMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SeparatorS)
            .addGroup(PanelSupprimMLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(SimpleS)
                .addGap(41, 41, 41)
                .addComponent(DoubleS)
                .addGap(96, 96, 96)
                .addComponent(SeklectMatchS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSupprimMLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(PanelSupprimMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSupprimMLayout.createSequentialGroup()
                        .addGroup(PanelSupprimMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DateS)
                            .addComponent(TerrainS))
                        .addGap(53, 53, 53)
                        .addGroup(PanelSupprimMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelSupprimMLayout.createSequentialGroup()
                                .addComponent(TFCreneauS, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSupprimMLayout.createSequentialGroup()
                                .addGroup(PanelSupprimMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TFTerrainS, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                    .addComponent(TFDateS))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JoueursS)
                                .addGap(236, 236, 236))
                            .addGroup(PanelSupprimMLayout.createSequentialGroup()
                                .addGap(321, 321, 321)
                                .addComponent(ArbLigneS)
                                .addGap(45, 45, 45)
                                .addGroup(PanelSupprimMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelSupprimMLayout.createSequentialGroup()
                                        .addComponent(RamasseurS)
                                        .addContainerGap(76, Short.MAX_VALUE))
                                    .addGroup(PanelSupprimMLayout.createSequentialGroup()
                                        .addComponent(RamasseurCBS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(PanelSupprimMLayout.createSequentialGroup()
                        .addComponent(CreneauS)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSupprimMLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelSupprimMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSupprimMLayout.createSequentialGroup()
                        .addComponent(TFArbChaiseS, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(CBArbLigneS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ValiderS)
                    .addGroup(PanelSupprimMLayout.createSequentialGroup()
                        .addComponent(TFJoueur1S, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(TFJoueur2S, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ArbChaiseS))
                .addGap(96, 96, 96))
        );
        PanelSupprimMLayout.setVerticalGroup(
            PanelSupprimMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSupprimMLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(PanelSupprimMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DoubleS)
                    .addComponent(SimpleS)
                    .addComponent(SeklectMatchS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(SeparatorS, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(PanelSupprimMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DateS)
                    .addComponent(JoueursS)
                    .addComponent(TFDateS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(PanelSupprimMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CreneauS)
                    .addComponent(TFJoueur1S, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFJoueur2S, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFCreneauS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(PanelSupprimMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TerrainS)
                    .addComponent(TFTerrainS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(PanelSupprimMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ArbLigneS)
                    .addComponent(ArbChaiseS)
                    .addComponent(RamasseurS))
                .addGap(29, 29, 29)
                .addGroup(PanelSupprimMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFArbChaiseS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBArbLigneS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RamasseurCBS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addComponent(ValiderS)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        ValiderP.setText("Valider le planning");
        ValiderP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValiderPActionPerformed(evt);
            }
        });

        DateNext.setText("-->");
        DateNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateNextActionPerformed(evt);
            }
        });

        DatePrevious.setText("<--");
        DatePrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DatePreviousActionPerformed(evt);
            }
        });

        Planning.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Horaire", "Principale", "Secondaire 1", "Secondaire 2", "Secondaire 3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Planning.setMinimumSize(new java.awt.Dimension(90, 64));
        Planning.setRowHeight(100);
        Planning.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Planning);
        if (Planning.getColumnModel().getColumnCount() > 0) {
            Planning.getColumnModel().getColumn(0).setResizable(false);
            Planning.getColumnModel().getColumn(0).setHeaderValue("Horaire");
            Planning.getColumnModel().getColumn(1).setResizable(false);
            Planning.getColumnModel().getColumn(1).setHeaderValue("Principale");
            Planning.getColumnModel().getColumn(2).setResizable(false);
            Planning.getColumnModel().getColumn(2).setHeaderValue("Secondaire 1");
            Planning.getColumnModel().getColumn(3).setResizable(false);
            Planning.getColumnModel().getColumn(3).setHeaderValue("Secondaire 2");
            Planning.getColumnModel().getColumn(4).setResizable(false);
            Planning.getColumnModel().getColumn(4).setHeaderValue("Secondaire 3");
        }

        javax.swing.GroupLayout PanelplanningMLayout = new javax.swing.GroupLayout(PanelplanningM);
        PanelplanningM.setLayout(PanelplanningMLayout);
        PanelplanningMLayout.setHorizontalGroup(
            PanelplanningMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelplanningMLayout.createSequentialGroup()
                .addContainerGap(396, Short.MAX_VALUE)
                .addComponent(DatePrevious)
                .addGap(18, 18, 18)
                .addGroup(PanelplanningMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ValiderP)
                    .addGroup(PanelplanningMLayout.createSequentialGroup()
                        .addComponent(JourSelect)
                        .addGap(18, 18, 18)
                        .addComponent(DateNext)))
                .addGap(262, 262, 262))
            .addGroup(PanelplanningMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelplanningMLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        PanelplanningMLayout.setVerticalGroup(
            PanelplanningMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelplanningMLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(PanelplanningMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JourSelect)
                    .addComponent(DateNext)
                    .addComponent(DatePrevious))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 463, Short.MAX_VALUE)
                .addComponent(ValiderP)
                .addContainerGap())
            .addGroup(PanelplanningMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelplanningMLayout.createSequentialGroup()
                    .addContainerGap(60, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(61, Short.MAX_VALUE)))
        );

        SimpleM.setText("Simple");
        SimpleM.setEnabled(false);
        SimpleM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpleMActionPerformed(evt);
            }
        });

        DoubleM.setText("Double");
        DoubleM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoubleMActionPerformed(evt);
            }
        });

        ModifArbiLigne.setText("Modifier Arbitres de ligne");

        ModifRamasseur.setText("Modifier Ramasseurs");

        ValiderM.setText("Valider");
        ValiderM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValiderMActionPerformed(evt);
            }
        });

        TextJour1.setText("Jour");

        TextCreneau1.setText("Créneau");

        TextCourt1.setText("Court");

        TextTypeMatch1.setText("Type de match");

        SelectMatchM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Match 1", "Match 2", "Match 3", "Match 4" }));
        SelectMatchM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectMatchMActionPerformed(evt);
            }
        });

        SeparatorM.setBackground(new java.awt.Color(0, 0, 0));

        ArbChaise.setText("Arbitre de chaise");

        Joueur.setText("Joueurs");

        javax.swing.GroupLayout PanelModifMLayout = new javax.swing.GroupLayout(PanelModifM);
        PanelModifM.setLayout(PanelModifMLayout);
        PanelModifMLayout.setHorizontalGroup(
            PanelModifMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SeparatorM)
            .addGroup(PanelModifMLayout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(SimpleM)
                .addGap(26, 26, 26)
                .addComponent(DoubleM)
                .addGap(176, 176, 176)
                .addComponent(SelectMatchM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelModifMLayout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addGroup(PanelModifMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelModifMLayout.createSequentialGroup()
                        .addGroup(PanelModifMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ModifArbiLigne)
                            .addComponent(ModifRamasseur)
                            .addGroup(PanelModifMLayout.createSequentialGroup()
                                .addGroup(PanelModifMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TextCreneau1)
                                    .addComponent(TextJour1))
                                .addGap(18, 18, 18)
                                .addGroup(PanelModifMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ComboJourM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ComboCreneauM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(ArbChaise)
                            .addComponent(Joueur))
                        .addGap(183, 183, 183)
                        .addGroup(PanelModifMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboCourtM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboArbiChaiseM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboArbiLigneM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboRamasseurM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextCourt1)
                            .addComponent(ComboJoueur1M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(82, 82, 82)
                        .addGroup(PanelModifMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboJoueur2M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboTypeMatchM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextTypeMatch1))
                        .addGap(112, 112, 112))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelModifMLayout.createSequentialGroup()
                        .addComponent(ValiderM)
                        .addGap(359, 359, 359))))
        );
        PanelModifMLayout.setVerticalGroup(
            PanelModifMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelModifMLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(PanelModifMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DoubleM)
                    .addComponent(SimpleM)
                    .addComponent(SelectMatchM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(SeparatorM, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(PanelModifMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboJourM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextJour1)
                    .addComponent(TextCourt1)
                    .addComponent(TextTypeMatch1))
                .addGap(18, 18, 18)
                .addGroup(PanelModifMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextCreneau1)
                    .addComponent(ComboCreneauM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboCourtM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboTypeMatchM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(PanelModifMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboJoueur1M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboJoueur2M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Joueur))
                .addGap(34, 34, 34)
                .addGroup(PanelModifMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboArbiChaiseM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ArbChaise))
                .addGap(18, 18, 18)
                .addGroup(PanelModifMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ModifArbiLigne)
                    .addComponent(ComboArbiLigneM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelModifMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ModifRamasseur)
                    .addComponent(ComboRamasseurM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(ValiderM)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        GénéAuto.setText("Génération Automatique");
        GénéAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GénéAutoActionPerformed(evt);
            }
        });

        SimpleA.setText("Simple");
        SimpleA.setEnabled(false);
        SimpleA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpleAActionPerformed(evt);
            }
        });

        DoubleA.setText("Double");
        DoubleA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoubleAActionPerformed(evt);
            }
        });

        GénéJoueur.setText("Génerer Joueurs");
        GénéJoueur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GénéJoueurActionPerformed(evt);
            }
        });

        GénéArbiChaise.setText("Génerer Arbitre de chaise");
        GénéArbiChaise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GénéArbiChaiseActionPerformed(evt);
            }
        });

        GénéArbiLigne.setText("Génerer arbitres de ligne");
        GénéArbiLigne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GénéArbiLigneActionPerformed(evt);
            }
        });

        GénéRamasseur.setText("Génerer ramasseurs");
        GénéRamasseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GénéRamasseurActionPerformed(evt);
            }
        });

        Ajouter.setText("Ajouter");
        Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjouterActionPerformed(evt);
            }
        });

        ComboJoueur1A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboJoueur1AActionPerformed(evt);
            }
        });

        ComboJoueur2A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboJoueur2AActionPerformed(evt);
            }
        });

        TextJour.setText("Jour");

        TextCreneau.setText("Créneau");

        TextCourt.setText("Court");

        TextTypeMatch.setText("Type de match");

        SelectManArbiLigneA.setText("Selection manuelle");

        SelectManRamasseurA.setText("Selection manuelle");

        javax.swing.GroupLayout PanelAjoutMLayout = new javax.swing.GroupLayout(PanelAjoutM);
        PanelAjoutM.setLayout(PanelAjoutMLayout);
        PanelAjoutMLayout.setHorizontalGroup(
            PanelAjoutMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(GénéAuto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelAjoutMLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(PanelAjoutMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GénéArbiLigne)
                    .addComponent(GénéRamasseur)
                    .addComponent(GénéArbiChaise)
                    .addComponent(GénéJoueur)
                    .addGroup(PanelAjoutMLayout.createSequentialGroup()
                        .addGroup(PanelAjoutMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TextCreneau)
                            .addComponent(TextJour))
                        .addGap(18, 18, 18)
                        .addGroup(PanelAjoutMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboJourA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboCreneauA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelAjoutMLayout.createSequentialGroup()
                        .addComponent(SimpleA)
                        .addGap(27, 27, 27)
                        .addComponent(DoubleA)))
                .addGap(179, 179, 179)
                .addGroup(PanelAjoutMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboCourtA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboArbiChaiseA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboArbiLigneA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboRamasseurA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextCourt)
                    .addComponent(ComboJoueur1A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82)
                .addGroup(PanelAjoutMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboJoueur2A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelAjoutMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(SelectManRamasseurA)
                        .addComponent(SelectManArbiLigneA))
                    .addComponent(ComboTypeMatchA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextTypeMatch))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAjoutMLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Ajouter)
                .addGap(361, 361, 361))
        );
        PanelAjoutMLayout.setVerticalGroup(
            PanelAjoutMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAjoutMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GénéAuto)
                .addGap(41, 41, 41)
                .addGroup(PanelAjoutMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DoubleA)
                    .addComponent(SimpleA))
                .addGap(46, 46, 46)
                .addGroup(PanelAjoutMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboJourA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextJour)
                    .addComponent(TextCourt)
                    .addComponent(TextTypeMatch))
                .addGap(18, 18, 18)
                .addGroup(PanelAjoutMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextCreneau)
                    .addComponent(ComboCreneauA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboCourtA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboTypeMatchA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(PanelAjoutMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GénéJoueur)
                    .addComponent(ComboJoueur1A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboJoueur2A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(PanelAjoutMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GénéArbiChaise)
                    .addComponent(ComboArbiChaiseA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelAjoutMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GénéArbiLigne)
                    .addComponent(ComboArbiLigneA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SelectManArbiLigneA))
                .addGap(18, 18, 18)
                .addGroup(PanelAjoutMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GénéRamasseur)
                    .addComponent(ComboRamasseurA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SelectManRamasseurA))
                .addGap(18, 18, 18)
                .addComponent(Ajouter)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 55, Short.MAX_VALUE)
                    .addComponent(PanelSupprimM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 56, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PanelplanningM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PanelModifM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PanelAjoutM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(580, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PanelSupprimM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PanelplanningM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PanelModifM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PanelAjoutM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SimpleMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpleMActionPerformed
        SimpleM.setEnabled(false);
        DoubleM.setEnabled(true);
        Joueur.setText("Joueurs");
        try {
            setListMtachModif();
            setListCBJoueur();
            updateMatchInfoModif((String) SelectMatchM.getSelectedItem());
        } catch (SQLException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SimpleMActionPerformed

    private void DoubleMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoubleMActionPerformed
        DoubleM.setEnabled(false);
        SimpleM.setEnabled(true);
        Joueur.setText("Duos");
        try {
            setListMtachModif();
            setListCBDuos();
            updateMatchInfoModif((String) SelectMatchM.getSelectedItem());
        } catch (SQLException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_DoubleMActionPerformed

    private void AjouterMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjouterMatchActionPerformed
            // TODO add your handling code here:
            boutSelect.setEnabled(true);
            boutSelect = AjouterMatch;
            boutSelect.setEnabled(false);
            PanelSelect.setVisible(false);
            PanelSelect = PanelAjoutM;
            PanelSelect.setVisible(true);
        
    }//GEN-LAST:event_AjouterMatchActionPerformed

    private void ModifierMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifierMatchActionPerformed
        // TODO add your handling code here:
        boutSelect.setEnabled(true);
        boutSelect = ModifierMatch;
        boutSelect.setEnabled(false);
        PanelSelect.setVisible(false);
        PanelSelect = PanelModifM;
        PanelSelect.setVisible(true);
        try {
            setListMtachModif();
            if(SimpleM.isEnabled()){
                setListCBDuos();
            }else{
                setListCBJoueur();
            }
            updateMatchInfoModif((String) SelectMatchM.getSelectedItem());
        } catch (SQLException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_ModifierMatchActionPerformed

    private void SupprimerMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupprimerMatchActionPerformed
        // TODO add your handling code here:
        boutSelect.setEnabled(true);
        boutSelect = SupprimerMatch;
        boutSelect.setEnabled(false);
        PanelSelect.setVisible(false);
        PanelSelect = PanelSupprimM;
        PanelSelect.setVisible(true);
        try {
            setListMtachSuppr();
        } catch (SQLException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SupprimerMatchActionPerformed

    private void DoubleAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoubleAActionPerformed
        try {
            DoubleA.setEnabled(false);
            SimpleA.setEnabled(true);
            GénéJoueur.setText("Générer Duo");
            this.setListCBDuos();
            this.setListCBDuos();
        } catch (SQLException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_DoubleAActionPerformed

    private void SimpleAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpleAActionPerformed
        try {
            SimpleA.setEnabled(false);
            DoubleA.setEnabled(true);
            GénéJoueur.setText("Générer Joueurs");
            this.setListCBJoueur();
            this.setListCBJoueur();
        } catch (SQLException | InterruptedException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SimpleAActionPerformed

    private void GénéAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GénéAutoActionPerformed
        // TODO add your handling code here:
        boutSelect.setEnabled(true);
        boutSelect = VoirPlanning;
        boutSelect.setEnabled(false);
        PanelSelect.setVisible(false);
        PanelSelect = PanelplanningM;
        PanelSelect.setVisible(true);
    }//GEN-LAST:event_GénéAutoActionPerformed

    private void DoubleSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoubleSActionPerformed
        SimpleS.setEnabled(true);
        DoubleS.setEnabled(false);
        try {
            setListMtachSuppr();
        } catch (SQLException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_DoubleSActionPerformed

    private void SimpleSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpleSActionPerformed
        SimpleS.setEnabled(false);
        DoubleS.setEnabled(true);
        try {
            setListMtachSuppr();
        } catch (SQLException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SimpleSActionPerformed

    private void ValiderPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValiderPActionPerformed
        
        try {
            p.setDouble(!DbMatchDAO.lesDoubles.isEmpty());
            p.setSimple(!DbMatchDAO.lesSimples.isEmpty());
            BDP.ajouterPlanning(p);
            for(Match m : DbMatchDAO.lesMatchs){
                BDM.ajouterMatch(m,p);
            }
            this.dispose();
            String[] args = null;
            Responsable.main(args);
        } catch (SQLException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ValiderPActionPerformed

    private void VoirPlanningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoirPlanningActionPerformed
        boutSelect.setEnabled(true);
        boutSelect = VoirPlanning;
        boutSelect.setEnabled(false);
        PanelSelect.setVisible(false);
        PanelSelect = PanelplanningM;
        PanelSelect.setVisible(true);
    }//GEN-LAST:event_VoirPlanningActionPerformed

    private void AjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjouterActionPerformed
        
        ArrayList<Arbitres> lesArbiLigne = Arbitres.getLesLignes(ComboArbiLigneA);
        ArrayList<Arbitres> lesRammasseur = Arbitres.getLesRammasseurs(ComboRamasseurA);
        Arbitres ArbiChaise = Arbitres.getArbitre((String) ComboArbiChaiseA.getSelectedItem());
        Court C = Court.getCourt((String) ComboCourtA.getSelectedItem());
        Type_Match TM = Type_Match.getTypeMatch((String) ComboTypeMatchA.getSelectedItem());
        Horaire H =  Horaire.getHoraire((String) ComboCreneauA.getSelectedItem());
        Jour J =  Jour.getJour((String) ComboJourA.getSelectedItem());
        
        if(DoubleA.isEnabled()){
            Joueurs J1 = Joueurs.getJoueur((String) ComboJoueur1A.getSelectedItem());
            Joueurs J2 = Joueurs.getJoueur((String) ComboJoueur2A.getSelectedItem());
            Simple s = new Simple(J1,J2,C,TM,H,J,ArbiChaise,lesArbiLigne,lesRammasseur);
            int[] coord = this.setCoordonees(s);
            if(s.getJ()==Jour.getJour("j"+JourSelect.getText().split(" ")[1])){
                if(Planning.getValueAt(coord[0], coord[1])!=" "){
                    JOptionPane.showMessageDialog(null, "Il y a déjà un match à cette emplacement", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    lesMatchs.add(s);
                    Planning.setValueAt(s.toString(), coord[0], coord[1]);
                    JOptionPane.showMessageDialog(null, "Match ajouté avec succés!", "Succés", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                lesMatchs.add(s);
                JOptionPane.showMessageDialog(null, "Match ajouté avec succés!", "Succés", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if(SimpleA.isEnabled()){
            Duo D1 = Duo.getDuo((String) ComboJoueur1A.getSelectedItem());
            Duo D2 = Duo.getDuo((String) ComboJoueur2A.getSelectedItem());
            Doubles d = new Doubles(D1,D2,C,TM,H,J,ArbiChaise,lesArbiLigne,lesRammasseur);
            int[] coord = this.setCoordonees(d);
            if(d.getJ()==Jour.getJour("j"+JourSelect.getText().split(" ")[1])){
                if(Planning.getValueAt(coord[0], coord[1])!=" "){
                    JOptionPane.showMessageDialog(null, "Il y a déjà un match à cette emplacement", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    lesMatchs.add(d);
                    Planning.setValueAt(d.toString(), coord[0], coord[1]);
                    JOptionPane.showMessageDialog(null, "Match ajouté avec succés!", "Succés", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                lesMatchs.add(d);
                JOptionPane.showMessageDialog(null, "Match ajouté avec succés!", "Succés", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_AjouterActionPerformed

    private void GénéJoueurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GénéJoueurActionPerformed
        ComboJoueur1A.setSelectedItem(ComboJoueur1A.getItemAt((int) (Math.random() * ( ComboJoueur1A.getItemCount()-1))));
        ComboJoueur2A.setSelectedItem(ComboJoueur1A.getItemAt((int) (Math.random() * ( ComboJoueur1A.getItemCount()-1))));
    }//GEN-LAST:event_GénéJoueurActionPerformed

    private void GénéArbiChaiseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GénéArbiChaiseActionPerformed
        int taille =  (int) ComboArbiChaiseA.getItemCount()-1;
        int random = (int) (Math.random() * taille);
        ComboArbiChaiseA.setSelectedItem(ComboArbiChaiseA.getItemAt(random));
    }//GEN-LAST:event_GénéArbiChaiseActionPerformed

    private void DateNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DateNextActionPerformed
        int row = Planning.getRowCount();
        int col = Planning.getColumnCount();
        for(int i = 0 ; i < row;i++){
            for(int j = 1 ; j < col ; j++){
                Planning.setValueAt(" ", i, j);
            }
        }
        String jourPrecedent = JourSelect.getText();
        String[] lesS = jourPrecedent.split(" ");
        int dateJ = Integer.parseInt(lesS[4])+1;
        lesS[4] = String.valueOf(dateJ);
        int nbJ = Integer.parseInt(lesS[1])+1;
        lesS[1] = String.valueOf(nbJ);
        int[] coord;
        switch(lesS[3]){
                case "Lundi":
                    lesS[3] = "Mardi";
                    break;
                case "Mardi":
                    lesS[3] = "Mercredi";
                    break;
                case "Mercredi":
                    lesS[3] = "Jeudi";
                    break;
                case "Jeudi":
                    lesS[3] = "Vendredi";
                    break;
                case "Vendredi":
                    lesS[3] = "Samedi";
                    break;
                case "Samedi":
                    lesS[3] = "Dimanche";
                    break;
                case "Dimanche":
                    lesS[3] = "Lundi";
                    break;
        }
        if(nbJ <= nbJourEv && dateJ <= this.getNbJourMois(lesS[5])){
            JourSelect.setText(lesS[0]+" "+lesS[1]+" "+lesS[2]+" "+lesS[3]+" "+lesS[4]+" "+lesS[5]);
        } else if(dateJ > this.getNbJourMois(lesS[5])){
            JourSelect.setText(lesS[0]+" "+lesS[1]+" "+lesS[2]+" "+lesS[3]+" 1 "+this.getMoisSuivant(lesS[5]));
        }
        for(Match m : DbMatchDAO.lesMatchs){
            if(m.getJ().toString().equals("j"+JourSelect.getText().split(" ")[1])){
                coord = this.setCoordonees(m);
                if(m instanceof Simple){
                    Simple s = (Simple) m;
                    Planning.setValueAt(s.toString(),coord[0], coord[1]);
                } else if(m instanceof Doubles){
                    Doubles d = (Doubles) m;
                    Planning.setValueAt(d.toString(),coord[0], coord[1]);
                }
            }
        }
    }//GEN-LAST:event_DateNextActionPerformed

    private void DatePreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DatePreviousActionPerformed
        int row = Planning.getRowCount();
        int col = Planning.getColumnCount();
        for(int i = 0 ; i < row;i++){
            for(int j = 1 ; j < col ; j++){
                Planning.setValueAt(" ", i, j);
            }
        }
        String jourPrecedent = JourSelect.getText();
        String[] lesS = jourPrecedent.split(" ");
        int nbJ = Integer.parseInt(lesS[1])-1;
        lesS[1] = String.valueOf(nbJ);
        int dateJ = Integer.parseInt(lesS[4])-1;
        lesS[4] = String.valueOf(dateJ);
        int[] coord;
        switch(lesS[3]){
                case "Lundi":
                    lesS[3] = "Dimanche";
                    break;
                case "Mardi":
                    lesS[3] = "Lundi";
                    break;
                case "Mercredi":
                    lesS[3] = "Mardi";
                    break;
                case "Jeudi":
                    lesS[3] = "Mercredi";
                    break;
                case "Vendredi":
                    lesS[3] = "Jeudi";
                    break;
                case "Samedi":
                    lesS[3] = "Vendredi";
                    break;
                case "Dimanche":
                    lesS[3] = "Samedi";
                    break;
        }
        if(nbJ > 0 && dateJ >= 1){
            JourSelect.setText(lesS[0]+" "+lesS[1]+" "+lesS[2]+" "+lesS[3]+" "+lesS[4]+" "+lesS[5]);
        } else if(dateJ < 1){
            String moisPrecedent = this.getMoisPrecedent(lesS[5]);
            JourSelect.setText(lesS[0]+" "+lesS[1]+" "+lesS[2]+" "+lesS[3]+" "+this.getNbJourMois(moisPrecedent)+" "+moisPrecedent);
        }
        for(Match m : DbMatchDAO.lesMatchs){
            if(m.getJ().toString().equals("j"+JourSelect.getText().split(" ")[1])){
                coord = this.setCoordonees(m);
                if(m instanceof Simple){
                    Simple s = (Simple) m;
                    Planning.setValueAt(s.toString(),coord[0], coord[1]);
                } else if(m instanceof Doubles){
                    Doubles d = (Doubles) m;
                    Planning.setValueAt(d.toString(),coord[0], coord[1]);
                }
            }
        }
    }//GEN-LAST:event_DatePreviousActionPerformed

    private void GénéArbiLigneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GénéArbiLigneActionPerformed
        ComboArbiLigneA.removeAllItems();
        listeALigne.removeAll(listeALigne);
        int i = 0;
        String Ligne;
        Arbitres a;
        ArrayList<Arbitres> listetmpLigne = DbArbitreDAO.lesArbitres;
        ArrayList<String> tmpAs = new ArrayList();
        while(i < 8){
            a = listetmpLigne.get((int) (Math.random() * ( listetmpLigne.size()-1)));
            Ligne = a.getNom()+" "+a.getPrenom();
            if(!Ligne.equals(ComboArbiChaiseA.getSelectedItem().toString()) && !tmpAs.contains(Ligne)){
               tmpAs.add(Ligne);
               listeALigne.add(a);
               i++; 
            }

        }
        Collections.sort(tmpAs);
        tmpAs.forEach((s) -> {
            ComboArbiLigneA.addItem(s);
        });
    }//GEN-LAST:event_GénéArbiLigneActionPerformed

    private void ComboJoueur1AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboJoueur1AActionPerformed
        try {
            if(DoubleA.isEnabled()){
                this.updateListCBJoueur(ComboJoueur1A);
            } else {
                this.updateListCBDuo(ComboJoueur1A);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {

        }
    }//GEN-LAST:event_ComboJoueur1AActionPerformed

    private void ComboJoueur2AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboJoueur2AActionPerformed
        try {
            if(DoubleA.isEnabled()){
                this.updateListCBJoueur(ComboJoueur2A);
            } else {
                this.updateListCBDuo(ComboJoueur2A);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {

        }
    }//GEN-LAST:event_ComboJoueur2AActionPerformed

    private void GénéRamasseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GénéRamasseurActionPerformed
        ComboRamasseurA.removeAllItems();
        int i = 0;
        String Ligne;
        Arbitres a;
        ArrayList<Arbitres> listeRams = (ArrayList<Arbitres>) DbArbitreDAO.lesArbitres.clone();
        listeRams.removeAll(listeALigne);
        ArrayList<String> tmpAs = new ArrayList();
        while(i < 12){
            a = listeRams.get((int) (Math.random() * ( listeRams.size()-1)));
            Ligne = a.getNom()+" "+a.getPrenom();
            if(!Ligne.equals(ComboArbiChaiseA.getSelectedItem().toString()) && !tmpAs.contains(Ligne)){
               tmpAs.add(Ligne);
               i++; 
            }

        }
        Collections.sort(tmpAs);
        tmpAs.forEach((s) -> {
            ComboRamasseurA.addItem(s);
        });
    }//GEN-LAST:event_GénéRamasseurActionPerformed

    private void SeklectMatchSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeklectMatchSActionPerformed
        this.updateMatchInfoSuppr((String) SeklectMatchS.getSelectedItem());
    }//GEN-LAST:event_SeklectMatchSActionPerformed

    private void SelectMatchMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectMatchMActionPerformed
        try {
            this.updateMatchInfoModif((String) SelectMatchM.getSelectedItem());
        } catch (SQLException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SelectMatchMActionPerformed

    private void ValiderSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValiderSActionPerformed
        String s = (String) SeklectMatchS.getSelectedItem();
        Match match;
        if(s == null) return;
        String[] infos = s.split(" ");
        if(infos[1].split("/").length==1){
            match = BDM.findSimpleByPlayer(infos[1],infos[2],infos[4],infos[5]);
        }else{
            match = BDM.findDoubleByDuos(infos[1], infos[3]);
        }
        try {
            BDM.supprimerMatch(match, p);
            setListMtachSuppr();
            JOptionPane.showMessageDialog(null, "Match correctement supprimé", "Suppression de match", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ValiderSActionPerformed

    private void ValiderMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValiderMActionPerformed
        ArrayList<Arbitres> lesArbiLigne = Arbitres.getLesLignes(ComboArbiLigneM);
        ArrayList<Arbitres> lesRammasseur = Arbitres.getLesRammasseurs(ComboRamasseurM);
        Arbitres ArbiChaise = Arbitres.getArbitre((String) ComboArbiChaiseM.getSelectedItem());
        
        Court C = Court.getCourt((String) ComboCourtM.getSelectedItem());
        Type_Match TM = Type_Match.getTypeMatch((String) ComboTypeMatchM.getSelectedItem());
        Horaire H =  Horaire.getHoraire((String) ComboCreneauM.getSelectedItem());
        Jour J =  Jour.getJour((String) ComboJourM.getSelectedItem());
        
        String s = (String) SelectMatchM.getSelectedItem();
        if(s == null) return;
        Simple simple = null;
        Doubles doubles = null;
        String[] infos = s.split(" ");
        if(infos[1].split("/").length==1){
            simple = BDM.findSimpleByPlayer(infos[1],infos[2],infos[4],infos[5]);
        }else{
            doubles = BDM.findDoubleByDuos(infos[1], infos[3]);
        }
        
        if(DoubleA.isEnabled()){
            Joueurs J1 = Joueurs.getJoueur((String) ComboJoueur1M.getSelectedItem());
            Joueurs J2 = Joueurs.getJoueur((String) ComboJoueur2M.getSelectedItem());
            if(C!=null) simple.setC(C);
            if(H!=null) simple.setH(H);
            if(TM!=null) simple.setTm(TM);
            if(J!=null) simple.setJ(J);
            if(J1!=null) simple.setJ1(J1);
            if(J2!=null) simple.setJ2(J2);
            if(lesRammasseur!=null) simple.setRamasseur(lesRammasseur);
            if(ArbiChaise!=null) simple.setaChaise(ArbiChaise);
            if(lesArbiLigne!=null) simple.setaLigne(lesArbiLigne);
            
            try {
                BDM.modifierMatch(simple, p);
            } catch (SQLException ex) {
                Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Match modifier avec succés!", "Succés", JOptionPane.INFORMATION_MESSAGE);
        try {
            setListMtachModif();
        } catch (SQLException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        } else if(SimpleA.isEnabled()){
            Duo D1 = Duo.getDuo((String) ComboJoueur1A.getSelectedItem());
            Duo D2 = Duo.getDuo((String) ComboJoueur2A.getSelectedItem());
            if(C!=null) doubles.setC(C);
            if(H!=null) doubles.setH(H);
            if(TM!=null) doubles.setTm(TM);
            if(J!=null) doubles.setJ(J);
            if(D1!=null) doubles.setD1(D1);
            if(D2!=null) doubles.setD2(D2);
            if(lesRammasseur!=null) doubles.setRamasseur(lesRammasseur);
            if(ArbiChaise!=null) doubles.setaChaise(ArbiChaise);
            if(lesArbiLigne!=null) doubles.setaLigne(lesArbiLigne);
            
            try {
                BDM.modifierMatch(doubles, p);
            } catch (SQLException ex) {
                Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Match modifier avec succés!", "Succés", JOptionPane.INFORMATION_MESSAGE);
            
        } 
    }//GEN-LAST:event_ValiderMActionPerformed
   
    private void updateMatchInfoModif(String selectedItem) throws SQLException, InterruptedException {
        String s = selectedItem;
        Match match;
        if(s == null) return;
        String[] infos = s.split(" ");
        if(infos[1].split("/").length==1){
            match = BDM.findSimpleByPlayer(infos[1],infos[2],infos[4],infos[5]);
        }else{
            match = BDM.findDoubleByDuos(infos[1], infos[3]);
        }
        
        setListCBArbiChaise();
        setListCBArbiLigne();
        setListCBRamaseur();
        
        ComboJourM.setSelectedItem(match.getJ().toString());
        ComboCreneauM.setSelectedItem(match.getH().toString());
        ComboCourtM.setSelectedItem(match.getC().toString());
        ComboTypeMatchM.setSelectedItem(match.getTm().toString());
        ComboArbiChaiseM.setSelectedItem(match.getaChaise().getNom()+" "+match.getaChaise().getPrenom());
        ComboArbiLigneM.removeAllItems();
        
        for(Arbitres a:match.getaLigne()){
            ComboArbiLigneM.addItem(a.getNom()+" "+a.getPrenom());
        }
        ComboRamasseurM.removeAllItems();
        for(Arbitres a:match.getRamasseur()){
            ComboRamasseurM.addItem(a.getNom()+" "+a.getPrenom());
        }
        if(match instanceof Simple){
            ComboJoueur1M.setSelectedItem(((Simple) match).getJ1().getNom()+" "+((Simple) match).getJ1().getPrenom());
            ComboJoueur2M.setSelectedItem(((Simple) match).getJ2().getNom()+" "+((Simple) match).getJ2().getPrenom());
        }else{
            ComboJoueur1M.setSelectedItem(((Doubles)match).getD1().compositionDuo());
            ComboJoueur2M.setSelectedItem(((Doubles)match).getD2().compositionDuo());
        }
    }
    
    private void updateMatchInfoSuppr(String selectedItem) {
        String s = selectedItem;
        Match match;
        if(s == null) return;
        String[] infos = s.split(" ");
        if(infos[1].split("/").length==1){
            match = BDM.findSimpleByPlayer(infos[1],infos[2],infos[4],infos[5]);
        }else{
            match = BDM.findDoubleByDuos(infos[1], infos[3]);
        }
        
        TFDateS.setText(match.getJ().toString());
        TFCreneauS.setText(match.getH().toString());
        TFTerrainS.setText(match.getC().toString());
        TFArbChaiseS.setText(match.getaChaise().getNom()+" "+match.getaChaise().getPrenom());
        CBArbLigneS.removeAllItems();
        for(Arbitres a:match.getaLigne()){
            CBArbLigneS.addItem(a.getNom()+" "+a.getPrenom());
        }
        RamasseurCBS.removeAllItems();
        for(Arbitres a:match.getRamasseur()){
            RamasseurCBS.addItem(a.getNom()+" "+a.getPrenom());
        }
        if(match instanceof Simple){
            TFJoueur1S.setText(((Simple) match).getJ1().getNom()+" "+((Simple) match).getJ1().getPrenom());
            TFJoueur2S.setText(((Simple) match).getJ2().getNom()+" "+((Simple) match).getJ2().getPrenom());
        }else{
            TFJoueur1S.setText(((Doubles)match).getD1().compositionDuo());
            TFJoueur2S.setText(((Doubles)match).getD2().compositionDuo());
        }
    }
    
    public void setListMtachModif() throws SQLException, InterruptedException{
        SelectMatchM.removeAllItems();
        ArrayList<Match> listeMatchs = (ArrayList<Match>) DbMatchDAO.lesMatchs.clone();
        String ligne;
        for(Match m:listeMatchs){
            if(DoubleM.isEnabled()){
                if(m instanceof Simple){
                    ligne = ((Simple)m).toString();
                    SelectMatchM.addItem(ligne);
                }
            }else{
                if(m instanceof Doubles){
                    ligne = ((Doubles)m).toString();
                    SelectMatchM.addItem(ligne);
                }
            }
        }
    }
    
    public void setListMtachSuppr() throws SQLException, InterruptedException{
        SeklectMatchS.removeAllItems();
        ArrayList<Match> listeMatchs = (ArrayList<Match>) DbMatchDAO.lesMatchs.clone();
        String ligne;
        for(Match m:listeMatchs){
            if(DoubleS.isEnabled()){
                if(m instanceof Simple){
                    ligne = ((Simple)m).toString();
                    SeklectMatchS.addItem(ligne);
                }
            }else{
                if(m instanceof Doubles){
                    ligne = ((Doubles)m).toString();
                    SeklectMatchS.addItem(ligne);
                }
            }
        }
            
        }
    
    public void setListCBJoueur() throws SQLException, InterruptedException{
        ComboJoueur1A.removeAllItems();
        ComboJoueur2A.removeAllItems();
        ComboJoueur1M.removeAllItems();
        ComboJoueur2M.removeAllItems();
        ArrayList<Joueurs> listeJoueurs = DbJoueurDAO.lesJoueurs;
        String Ligne;
        for(Joueurs j:listeJoueurs){
            Ligne = j.getNom()+" "+j.getPrenom();
            ComboJoueur1A.addItem(Ligne);
            ComboJoueur1M.addItem(Ligne);
        }
        for(Joueurs j:listeJoueurs){
            Ligne = j.getNom()+" "+j.getPrenom();
            if(!ComboJoueur1A.getSelectedItem().toString().equals(Ligne)){
                ComboJoueur2A.addItem(Ligne);
                ComboJoueur2M.addItem(Ligne);
            }

        }
    }
    
    public void updateListCBJoueur(JComboBox Cb) throws SQLException{
        JComboBox CbAutre;
        if(Cb == ComboJoueur1A){
            CbAutre = ComboJoueur2A;
        } else {
            CbAutre = ComboJoueur1A;
        }
        String Sel1 = Cb.getSelectedItem().toString();
        String Sel2 = CbAutre.getSelectedItem().toString();
        Joueurs J1 = Joueurs.getJoueur(Sel1);
        ArrayList<Joueurs> liste2Joueurs = (ArrayList<Joueurs>) DbJoueurDAO.lesJoueurs.clone();
        liste2Joueurs.remove(J1);
        CbAutre.removeAllItems();
        liste2Joueurs.forEach((j) -> {
            CbAutre.addItem(j.getNom()+" "+j.getPrenom());
        });
        CbAutre.setSelectedItem(Sel2);
        this.setListCBArbiChaise();
    }
    
    public void setListCBDuos() throws SQLException{
        ComboJoueur1A.removeAllItems();
        ComboJoueur2A.removeAllItems();
        ComboJoueur1M.removeAllItems();
        ComboJoueur2M.removeAllItems();
        
        ArrayList<Duo> listeDuos= DbDuoDAO.lesDuos;
        String Ligne;
        for(Duo d:listeDuos){
            Ligne = d.compositionDuo();
            ComboJoueur1A.addItem(Ligne);
            ComboJoueur1M.addItem(Ligne);
        }
        for(Duo d:listeDuos){
            Ligne = d.compositionDuo();
            if(!ComboJoueur1A.getSelectedItem().toString().equals(Ligne)){
                ComboJoueur2A.addItem(Ligne);
                ComboJoueur2M.addItem(Ligne);
            }

        }
    }
    
    public void updateListCBDuo(JComboBox Cb) throws SQLException{
        JComboBox CbAutre;
        if(Cb == ComboJoueur1A){
            CbAutre = ComboJoueur2A;
        } else {
            CbAutre = ComboJoueur1A;
        }
        String Sel1 = Cb.getSelectedItem().toString();
        String Sel2 = CbAutre.getSelectedItem().toString();
        Duo D1 = Duo.getDuo(Sel1);
        ArrayList<Duo> liste2Duos = (ArrayList<Duo>) DbDuoDAO.lesDuos.clone();
        liste2Duos.remove(D1);
        CbAutre.removeAllItems();
        liste2Duos.forEach((d) -> {
            CbAutre.addItem(d.compositionDuo());
        });
        CbAutre.setSelectedItem(Sel2);
        this.setListCBArbiChaise();
    }
    
    public void setListCBRamaseur() throws SQLException{
        ArrayList<Arbitres> listeRams = (ArrayList<Arbitres>) DbArbitreDAO.lesArbitres.clone();
        listeRams.removeAll(listeALigne);
        int i = 0;
        String Ligne;
        for(Arbitres a : listeRams){
            if(i < 12){
                Ligne = a.getNom()+" "+a.getPrenom();
                if(!Ligne.equals(ComboArbiChaiseA.getSelectedItem().toString())){
                   ComboRamasseurA.addItem(Ligne);
                   ComboRamasseurM.addItem(Ligne);
                   i++; 
                }
            }
        }
    }
    
    public void setListCBArbiChaise() throws SQLException{
        ComboArbiChaiseA.removeAllItems();
        ComboArbiChaiseM.removeAllItems();
        Joueurs E1 = null;
        Joueurs E2 = null;
        Joueurs E3 = null;
        Joueurs E4 = null;
        ArrayList<String> lesNationalite = new ArrayList();
        String Js2 = ". .";
        String Js1 = ". .";
        if(ComboJoueur1A.getSelectedItem()!=null){
            Js1= ComboJoueur1A.getSelectedItem().toString();
        }
        if(ComboJoueur2A.getSelectedItem()!=null){
            Js2= ComboJoueur2A.getSelectedItem().toString();
        }
        
        if(DoubleA.isEnabled()){
            String[] les1S = Js1.split("/");
            String[] les2S = Js2.split("/");
            for(Joueurs j : DbJoueurDAO.lesJoueurs){
                if(j.getNom().equals(les1S[0]) && j.getPrenom().equals(les1S[1])){
                    E1 = j;
                } else {
                    if(j.getNom().equals(les2S[0]) && j.getPrenom().equals(les2S[1])){
                        E2 = j;
                    }
                }
            }
        } else if(SimpleA.isEnabled()){
            String[] les1S = Js1.split("/");
            String[] les2S = Js2.split("/");
            for(Duo d : DbDuoDAO.lesDuos){
                if(d.getJ1().getNom().equals(les1S[0])){
                    E1 = d.getJ1();
                }
                if(d.getJ2().getNom().equals(les1S[1])){
                    E2 = d.getJ2();
                }
                if(d.getJ1().getNom().equals(les2S[0])){
                    E3 = d.getJ1();
                }
                if(d.getJ2().getNom().equals(les2S[1])) {
                    E4 = d.getJ2();
                }
            }
        }
        
        if(E1 != null){
            lesNationalite.add(E1.getNationalite());
        } else {
            lesNationalite.add("");
        }
        if(E2 != null){
        lesNationalite.add(E2.getNationalite());
        } else {
            lesNationalite.add("");
        }
        if(E3 != null){
            lesNationalite.add(E3.getNationalite());
        } else {
            lesNationalite.add("");
        }
        if(E4 != null){
        lesNationalite.add(E4.getNationalite());
        } else {
            lesNationalite.add("");
        }
        
        DbArbitreDAO.lesArbiChaises.stream().filter((a) -> (!lesNationalite.contains(a.getNationalite()))).map((a) -> {
            ComboArbiChaiseA.addItem(a.getNom()+" "+a.getPrenom());
            return a;
        }).forEachOrdered((a) -> {
            ComboArbiChaiseM.addItem(a.getNom()+" "+a.getPrenom());
        });
    }
    
    public void setListCBArbiLigne() throws SQLException{
        ComboArbiLigneA.removeAllItems();
        ComboArbiLigneM.removeAllItems();
        listeALigne.removeAll(listeALigne);
        int i = 0;
        String Ligne;
        listeALigne = new ArrayList();
        for(Arbitres a : DbArbitreDAO.lesArbiLignes){
            if(i < 8){
                Ligne = a.getNom()+" "+a.getPrenom();
                if(!Ligne.equals(ComboArbiChaiseA.getSelectedItem().toString())){
                    listeALigne.add(a);
                    ComboArbiLigneA.addItem(Ligne);
                    ComboArbiLigneM.addItem(Ligne);
                    i++; 
                }
                
            }
        }
    }
    
    public int[] setCoordonees(Match m){
        int[] coord = {1,1};
        int row = 1;
        int col = 1;
        switch(m.getH()){
            case h11:
                row = 0;
                break;
            case h15:
                row = 1;
                break;
            case h18:
                row = 2;
                break;
            case h21:
                row = 3;
                break;
        }
        switch(m.getC()){
            case Principale:
                col = 1;
                break;
            case Secondaire1:
                col = 2;
                break;
            case Secondaire2:
                col = 3;
                break;
            case Secondaire3:
                col = 4;
                break;
        }
        coord[0] = row;
        coord[1] = col;
        return coord;
    }
    
    public  String getMoisSuivant(String moisActuel){
        String moisSuivant;
        switch(moisActuel){
            case "Janvier":
                moisSuivant = "Fevrier";
                break;
            case "Fevrier":
                moisSuivant = "Mars";
                break;
            case "Mars":
                moisSuivant = "Avril";
                break;
            case "Avril":
                moisSuivant = "Mai";
                break;
            case "Mai":
                moisSuivant = "Juin";
                break;
            case "Juin":
                moisSuivant = "Juillet";
                break;
            case "Juillet":
                moisSuivant = "Aout";
                break;
            case "Aout":
                moisSuivant = "Septembre";
                break;
            case "Septembre":
                moisSuivant = "Octobre";
                break;
            case "Octobre":
                moisSuivant = "Novembre";
                break;
            case "Novembre":
                moisSuivant = "Decembre";
                break;
            case "Decembre":
                moisSuivant = "Janvier";
                break;
            default:
                moisSuivant = "Erreur";
                break;
        }
        return moisSuivant;
    }
    
    public  String getMoisPrecedent(String moisActuel){
        String moisPrecedent;
        switch(moisActuel){
            case "Janvier":
                moisPrecedent = "Decembre";
                break;
            case "Fevrier":
                moisPrecedent = "Janvier";
                break;
            case "Mars":
                moisPrecedent = "Fevrier";
                break;
            case "Avril":
                moisPrecedent = "Mars";
                break;
            case "Mai":
                moisPrecedent = "Avril";
                break;
            case "Juin":
                moisPrecedent = "Mai";
                break;
            case "Juillet":
                moisPrecedent = "Juin";
                break;
            case "Aout":
                moisPrecedent = "Juillet";
                break;
            case "Septembre":
                moisPrecedent = "Aout";
                break;
            case "Octobre":
                moisPrecedent = "Septembre";
                break;
            case "Novembre":
                moisPrecedent = "Octobre";
                break;
            case "Decembre":
                moisPrecedent = "Novembre";
                break;
            default:
                moisPrecedent = "Erreur";
                break;
        }
        return moisPrecedent;
    }
    
    public  int getNbJourMois(String moisActuel){
        int nbJourMois;
        switch(moisActuel){
            case "Janvier":
                nbJourMois = 31;
                break;
            case "Fevrier":
                nbJourMois = 28;
                if(an%4 == 0){
                    if(an%100 == 0){
                        if(an%400 == 0){
                            nbJourMois = 29;
                        } else {
                            nbJourMois = 28;
                        }
                    } else {
                        nbJourMois = 29;
                    }
                }
                break;
            case "Mars":
                nbJourMois = 31;
                break;
            case "Avril":
                nbJourMois = 30;
                break;
            case "Mai":
                nbJourMois = 31;
                break;
            case "Juin":
                nbJourMois = 30;
                break;
            case "Juillet":
                nbJourMois = 31;
                break;
            case "Aout":
                nbJourMois = 31;
                break;
            case "Septembre":
                nbJourMois = 30;
                break;
            case "Octobre":
                nbJourMois = 31;
                break;
            case "Novembre":
                nbJourMois = 30;
                break;
            case "Decembre":
                nbJourMois = 31;
                break;
            default:
                nbJourMois = 0;
                break;
        }
        return nbJourMois;
    }
    
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(ArrayList<Object> args) throws SQLException {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FoncResponsable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        String date = "";
        final String s;
        
        for(int i = 1 ; i < args.size()-2 ; i++){
            date = date+args.get(i)+" ";
        }
        
        s = date;
        
        java.awt.EventQueue.invokeLater(() -> {
            try {
                int  idP = 0;
                if(args.size()==8){
                    idP = (int) args.get(7);
                }
                
                String nbJourS = (String) args.get(6);
                int nbJourI = Integer.parseInt(nbJourS.split("j")[1]);
                String nomT = (String) args.get(5);
                int an = Integer.parseInt((String)args.get(4));
                
                if(args.get(0) instanceof Connection){
                    c = (Connection) args.get(0);
                }
                
                FoncResponsable fr = new FoncResponsable(s, nbJourI, nomT, an,idP);
                
                fr.setIdP(BDP.getNbPl());
                fr.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(FoncResponsable.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }
    //<editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ajouter;
    private javax.swing.JButton AjouterMatch;
    private javax.swing.JLabel ArbChaise;
    private javax.swing.JLabel ArbChaiseS;
    private javax.swing.JLabel ArbLigneS;
    private javax.swing.JComboBox<String> CBArbLigneS;
    private javax.swing.JComboBox<String> ComboArbiChaiseA;
    private javax.swing.JComboBox<String> ComboArbiChaiseM;
    private javax.swing.JComboBox<String> ComboArbiLigneA;
    private javax.swing.JComboBox<String> ComboArbiLigneM;
    private javax.swing.JComboBox<String> ComboCourtA;
    private javax.swing.JComboBox<String> ComboCourtM;
    private javax.swing.JComboBox<String> ComboCreneauA;
    private javax.swing.JComboBox<String> ComboCreneauM;
    private javax.swing.JComboBox<String> ComboJoueur1A;
    private javax.swing.JComboBox<String> ComboJoueur1M;
    private javax.swing.JComboBox<String> ComboJoueur2A;
    private javax.swing.JComboBox<String> ComboJoueur2M;
    private javax.swing.JComboBox<String> ComboJourA;
    private javax.swing.JComboBox<String> ComboJourM;
    private javax.swing.JComboBox<String> ComboRamasseurA;
    private javax.swing.JComboBox<String> ComboRamasseurM;
    private javax.swing.JComboBox<String> ComboTypeMatchA;
    private javax.swing.JComboBox<String> ComboTypeMatchM;
    private javax.swing.JLabel CreneauS;
    private javax.swing.JButton DateNext;
    private javax.swing.JButton DatePrevious;
    private javax.swing.JLabel DateS;
    private javax.swing.JButton DoubleA;
    private javax.swing.JButton DoubleM;
    private javax.swing.JButton DoubleS;
    private javax.swing.JButton GénéArbiChaise;
    private javax.swing.JButton GénéArbiLigne;
    private javax.swing.JButton GénéAuto;
    private javax.swing.JButton GénéJoueur;
    private javax.swing.JButton GénéRamasseur;
    private javax.swing.JLabel Joueur;
    private javax.swing.JLabel JoueursS;
    private javax.swing.JLabel JourSelect;
    private javax.swing.JPanel Menu;
    private javax.swing.JButton ModifArbiLigne;
    private javax.swing.JButton ModifRamasseur;
    private javax.swing.JButton ModifierMatch;
    private javax.swing.JPanel PanelAjoutM;
    private javax.swing.JPanel PanelModifM;
    private javax.swing.JPanel PanelSupprimM;
    private javax.swing.JPanel PanelplanningM;
    private javax.swing.JTable Planning;
    private javax.swing.JComboBox<String> RamasseurCBS;
    private javax.swing.JLabel RamasseurS;
    private javax.swing.JComboBox<String> SeklectMatchS;
    private javax.swing.JButton SelectManArbiLigneA;
    private javax.swing.JButton SelectManRamasseurA;
    private javax.swing.JComboBox<String> SelectMatchM;
    private javax.swing.JSeparator SeparatorM;
    private javax.swing.JSeparator SeparatorS;
    private javax.swing.JButton SimpleA;
    private javax.swing.JButton SimpleM;
    private javax.swing.JButton SimpleS;
    private javax.swing.JButton SupprimerMatch;
    private javax.swing.JTextField TFArbChaiseS;
    private javax.swing.JTextField TFCreneauS;
    private javax.swing.JTextField TFDateS;
    private javax.swing.JTextField TFJoueur1S;
    private javax.swing.JTextField TFJoueur2S;
    private javax.swing.JTextField TFTerrainS;
    private javax.swing.JLabel TerrainS;
    private javax.swing.JLabel TextCourt;
    private javax.swing.JLabel TextCourt1;
    private javax.swing.JLabel TextCreneau;
    private javax.swing.JLabel TextCreneau1;
    private javax.swing.JLabel TextJour;
    private javax.swing.JLabel TextJour1;
    private javax.swing.JLabel TextTypeMatch;
    private javax.swing.JLabel TextTypeMatch1;
    private javax.swing.JButton ValiderM;
    private javax.swing.JButton ValiderP;
    private javax.swing.JButton ValiderS;
    private javax.swing.JButton VoirPlanning;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}