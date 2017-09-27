/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FenApplicGare.java
 *
 * Created on 30-mars-2014, 15:52:29
 */
package InterfaceGraphique;

import ClassesVehicule.HoraireTrain;
import Login.FenLogin;
import Authenticate.User;
import Bean.AlarmBean;
import Bean.ReportBean;
import Bean.StateBean;
import ClassesVehicule.Locomotive;
import ClassesVehicule.Train;
import ClassesVehicule.Wagon;
import Exceptions.TrainWithoutLocomotiveException;
import Outils.FichierLog;
import Thread.ThreadAttenteReponse;
import Thread.ThreadChangeProperty;
import Thread.ThreadVerification;
import java.awt.BorderLayout;
import java.beans.Beans;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import network.NetworkStringSender;
import serialize.ObjectLoader;
import serialize.ObjectSaver;

/**
 *
 * @author Julien
 */

@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "OverridableMethodCallInConstructor"})
public class FenApplicGare extends javax.swing.JFrame
{
    private String login;
    private HoraireTrain HoraireTrainEnCours;
    private FenLogin fenLog;
    private Vector<HoraireTrain> vctHoraireTrain;
    private Enumeration e;
    private HashMap HM;
    
    private Vector<Wagon> vctWagon;
    private Vector<Locomotive> vctLoco;
    private Vector<Train> vctTrain;
    private Vector<Train> vctTrainDepot;
    
    private ThreadAttenteReponse tarIN;
    private ThreadAttenteReponse tarOUT;
    private ThreadAttenteReponse tarHG;
    private NetworkStringSender nssIN;
    private NetworkStringSender nssOUT;
    private NetworkStringSender nssHG;
    
    private LinkedList<Train> llTrainPartit;
    private Properties PropertyFile;
    private FichierLog FL;
    
    private AlarmBean AB;
    private StateBean SB;
    private ReportBean RB;
    
    private String sep;
    private String repHome;
    private String cheminFichier;

    /** Creates new form FenApplicGare */
    
    public FenApplicGare()
    {
        initComponents();
    }
    
    //@SuppressWarnings("OverridableMethodCallInConstructor")
    public FenApplicGare(String log, FenLogin fL, HashMap HMUsers)
    {
        initComponents();
        
        this.setTitle("Applic_gare");
        login = log;
        fenLog = fL;
        fenLog.setVisible(false);
        
        /***** Un peu de portabilité ne fait pas de mal *****/
        sep = System.getProperty("file.separator");
        repHome = System.getProperty("user.home");
        cheminFichier = repHome + sep + "Documents" + sep + "NetBeansProjects" + sep + "Applic_Gare" + sep;
        
        FL = new FichierLog("FichierLog.txt");
        FL.ecritLigne("Lancement de l'application Gare");
        
        lbNomResponsableGare.setText(login);
        HM = HMUsers;
        
        JLabel img = new JLabel(new ImageIcon(cheminFichier + "FrontImage.png"));
        panelImage.setLayout(new BorderLayout());
        panelImage.add(img, BorderLayout.CENTER);
        
        llTrainPartit = new LinkedList<Train>();
        
        /***** Si chef de gare -> pas accès aux fonctions d'admin *****/
        User u = (User)HM.get(login);
        if (u.getRole().equals("Chef"))
        {
            sMenuAjoutUser.setVisible(false);
            sMenuListeUsers.setVisible(false);
            jSeparator1.setVisible(false);
        }
        
        /***** Redéfinition largeur colonne de la jtable *****/
        tableOccupationVoies.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn col;
        col = tableOccupationVoies.getColumnModel().getColumn(0);
        col.setPreferredWidth(50);
        col = tableOccupationVoies.getColumnModel().getColumn(1);
        col.setPreferredWidth(100);
        col = tableOccupationVoies.getColumnModel().getColumn(2);
        col.setPreferredWidth(100);
        col = tableOccupationVoies.getColumnModel().getColumn(3);
        col.setPreferredWidth(100);
        col = tableOccupationVoies.getColumnModel().getColumn(4);
        col.setPreferredWidth(100);
        col = tableOccupationVoies.getColumnModel().getColumn(5);
        col.setPreferredWidth(115); 
        
        tfReponseControleIn.setEnabled(false);
        tfReponseControleOut.setEnabled(false);
        tfReponseDepot.setEnabled(false);
        cbTrainsArrivesRepartis.removeAllItems();
        
        vctTrainDepot = new Vector<Train>();
        
        /***** Récupération fichier Locomotive *****/
        LoadLocomotive();

        /***** Récupération fichier Wagon *****/  
        LoadWagon();

        /***** Récupération fichier Train *****/
        LoadTrain(vctWagon, vctLoco);

        /***** Récupération fichier HoraireTrain *****/
        LoadHoraireTrain(vctTrain);    
        
        /***** Chargement du fichier Properties *****/
        LoadPropertiesFile();
        
        /***** Création des beans *****/
        CreateBeans();
    }
    
    private void LoadLocomotive()
    {
        File f = new File(cheminFichier + "Locomotive.dat");
        vctLoco = new Vector<Locomotive>();
        
        try 
        {
            if (f.exists())
            {
                ObjectLoader OL = new ObjectLoader(cheminFichier + "Locomotive.dat");
                vctLoco = (Vector<Locomotive>)OL.load(); 
                FL.ecritLigne("Chargement des locomotives");
            }
            else
            {
                FL.ecritLigne("Creation du fichier Locomotive.dat");

                vctLoco.add(new Locomotive("1500", 1990, 501));
                vctLoco.add(new Locomotive("1600", 1989, 502));
                vctLoco.add(new Locomotive("1700", 1985, 503));
                vctLoco.add(new Locomotive("2500", 1994, 504));
                vctLoco.add(new Locomotive("2600", 1996, 505));
                vctLoco.add(new Locomotive("2700", 1993, 506));
                vctLoco.add(new Locomotive("3500", 2000, 507));
                vctLoco.add(new Locomotive("3600", 1999, 508));
                vctLoco.add(new Locomotive("3700", 1994, 509));
                vctLoco.add(new Locomotive("4500", 1992, 510));
                vctLoco.add(new Locomotive("4600", 1990, 511));
                vctLoco.add(new Locomotive("4700", 1989, 512));
                vctLoco.add(new Locomotive("5500", 1985, 513));
                vctLoco.add(new Locomotive("5600", 1994, 514));
                vctLoco.add(new Locomotive("5700", 1996, 515));
                vctLoco.add(new Locomotive("6500", 1993, 516));
                vctLoco.add(new Locomotive("6600", 2000, 517));
                vctLoco.add(new Locomotive("6700", 1999, 518));
                vctLoco.add(new Locomotive("7500", 1994, 519));
                vctLoco.add(new Locomotive("7600", 1992, 520));

                ObjectSaver OS = new ObjectSaver(cheminFichier + "Locomotive.dat");
                OS.save(vctLoco);
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(FenApplicGare.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(FenApplicGare.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void LoadWagon()
    {
        File f = new File(cheminFichier + "Wagon.dat");
        vctWagon = new Vector<Wagon>();
        
        try 
        {
            if (f.exists())
            {
                ObjectLoader OL = new ObjectLoader(cheminFichier + "Wagon.dat");
                vctWagon = (Vector<Wagon>)OL.load();
                FL.ecritLigne("Chargement des Wagons");
            }
            else
            {
                FL.ecritLigne("Creation du fichier Wagon.dat");

                vctWagon.add(new Wagon("101", 2010, 26, "VO"));
                vctWagon.add(new Wagon("102", 2009, 26, "VO"));
                vctWagon.add(new Wagon("103", 2010, 26, "VO"));
                vctWagon.add(new Wagon("104", 2010, 26, "VO"));
                vctWagon.add(new Wagon("105", 2009, 26, "VO"));
                vctWagon.add(new Wagon("106", 2009, 26, "VO"));
                vctWagon.add(new Wagon("107", 2007, 26, "VO"));
                vctWagon.add(new Wagon("108", 2004, 26, "VO"));
                vctWagon.add(new Wagon("109", 2009, 26, "VO"));
                vctWagon.add(new Wagon("110", 2011, 26, "VO"));
                vctWagon.add(new Wagon("201", 2001, 24, "MA"));
                vctWagon.add(new Wagon("202", 1999, 28, "MA"));
                vctWagon.add(new Wagon("203", 2000, 28, "MA"));
                vctWagon.add(new Wagon("204", 2000, 26, "MA"));
                vctWagon.add(new Wagon("205", 1999, 28, "MA"));
                vctWagon.add(new Wagon("206", 2001, 26, "MA"));
                vctWagon.add(new Wagon("207", 1999, 24, "MA"));
                vctWagon.add(new Wagon("208", 2002, 24, "MA"));
                vctWagon.add(new Wagon("209", 1999, 28, "MA"));
                vctWagon.add(new Wagon("210", 2000, 26, "MA"));
                vctWagon.add(new Wagon("301", 2001, 24, "VO"));
                vctWagon.add(new Wagon("302", 1999, 28, "VO"));
                vctWagon.add(new Wagon("303", 2000, 28, "VO"));
                vctWagon.add(new Wagon("304", 2000, 26, "VO"));
                vctWagon.add(new Wagon("305", 1999, 28, "VO"));
                vctWagon.add(new Wagon("306", 2001, 26, "VO"));
                vctWagon.add(new Wagon("307", 1999, 24, "VO"));
                vctWagon.add(new Wagon("308", 2002, 24, "VO"));
                vctWagon.add(new Wagon("309", 1999, 28, "VO"));
                vctWagon.add(new Wagon("310", 2000, 26, "VO"));
                vctWagon.add(new Wagon("401", 2010, 26, "MA"));
                vctWagon.add(new Wagon("402", 2009, 26, "MA"));
                vctWagon.add(new Wagon("403", 2010, 26, "MA"));
                vctWagon.add(new Wagon("404", 2010, 26, "MA"));
                vctWagon.add(new Wagon("405", 2009, 26, "MA"));
                vctWagon.add(new Wagon("406", 2009, 26, "MA"));
                vctWagon.add(new Wagon("407", 2007, 26, "MA"));
                vctWagon.add(new Wagon("408", 2004, 26, "MA"));
                vctWagon.add(new Wagon("409", 2009, 26, "MA"));
                vctWagon.add(new Wagon("410", 2011, 26, "MA"));

                ObjectSaver OS = new ObjectSaver(cheminFichier + "Wagon.dat");
                OS.save(vctWagon);
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(FenApplicGare.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(FenApplicGare.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void LoadTrain(Vector<Wagon> vecW, Vector<Locomotive> vecL)
    {
        File f = new File(cheminFichier + "Train.dat");
        vctTrain = new Vector<Train>();
        
        try 
        {
            if (f.exists())
            {
                ObjectLoader OL = new ObjectLoader(cheminFichier + "Train.dat");
                vctTrain = (Vector<Train>)OL.load(); 
                FL.ecritLigne("Chargement des trains");
            }
            else
            {
                try
                {
                    FL.ecritLigne("Creation du fichier Train.dat");

                    Vector<Wagon> tmp = new Vector<Wagon>();
                    tmp.add(vecW.get(0));
                    tmp.add(vecW.get(1));
                    vctTrain.add(new Train("IC20254", vecL.get(0), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(2));
                    tmp.add(vecW.get(3));
                    vctTrain.add(new Train("IC20154", vecL.get(1), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(4));
                    tmp.add(vecW.get(5));
                    vctTrain.add(new Train("IC51103", vecL.get(2), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(6));
                    tmp.add(vecW.get(7));
                    vctTrain.add(new Train("IC40157", vecL.get(3), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(8));
                    tmp.add(vecW.get(9));
                    vctTrain.add(new Train("IC29124", vecL.get(4), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(10));
                    tmp.add(vecW.get(11));
                    vctTrain.add(new Train("IC28150", vecL.get(5), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(12));
                    tmp.add(vecW.get(13));
                    vctTrain.add(new Train("IC31538", vecL.get(6), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(14));
                    tmp.add(vecW.get(15));
                    vctTrain.add(new Train("IC54154", vecL.get(7), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(16));
                    tmp.add(vecW.get(17));
                    vctTrain.add(new Train("IC25351", vecL.get(8), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(18));
                    tmp.add(vecW.get(19));
                    vctTrain.add(new Train("IC35257", vecL.get(9), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();               
                    tmp.add(vecW.get(20));
                    tmp.add(vecW.get(21));
                    vctTrain.add(new Train("IC52554", vecL.get(10), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(22));
                    tmp.add(vecW.get(23));
                    vctTrain.add(new Train("IC20104", vecL.get(11), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(24));
                    tmp.add(vecW.get(25));
                    vctTrain.add(new Train("IC58294", vecL.get(12), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(26));
                    tmp.add(vecW.get(27));
                    vctTrain.add(new Train("IC92594", vecL.get(13), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(28));
                    tmp.add(vecW.get(29));
                    vctTrain.add(new Train("IC20524", vecL.get(14), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(30));
                    tmp.add(vecW.get(31));
                    vctTrain.add(new Train("IC35284", vecL.get(15), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(32));
                    tmp.add(vecW.get(33));
                    vctTrain.add(new Train("IC95238", vecL.get(16), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(34));
                    tmp.add(vecW.get(35));
                    vctTrain.add(new Train("IC52488", vecL.get(17), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(36));
                    tmp.add(vecW.get(37));
                    vctTrain.add(new Train("IC36247", vecL.get(18), new Vector<Wagon>(tmp)));
                    tmp.removeAllElements();
                    tmp.add(vecW.get(38));
                    tmp.add(vecW.get(39));
                    vctTrain.add(new Train("IC52418", vecL.get(19), new Vector<Wagon>(tmp)));

                    ObjectSaver OS = new ObjectSaver(cheminFichier + "Train.dat");
                    OS.save(vctTrain);
                }
                catch(TrainWithoutLocomotiveException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(FenApplicGare.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(FenApplicGare.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void LoadHoraireTrain(Vector<Train> vctT)
    {
        File f = new File(cheminFichier + "HoraireTrainLiege.dat");
        vctHoraireTrain = new Vector<HoraireTrain>();
        
        try 
        {
            if (f.exists())
            {
                ObjectLoader OL = new ObjectLoader(cheminFichier + "HoraireTrainLiege.dat");
                vctHoraireTrain = (Vector<HoraireTrain>)OL.load();
//                HoraireTrainEnCours = vctHoraireTrain.get(0);
//                e = vctHoraireTrain.elements();                
//                lbIdentiteTrainSuivant.setText(e.nextElement().toString());
                FL.ecritLigne("Chargement des HoraireTrain");
            }
            else
            {
                FL.ecritLigne("Création du fichier HoraireTrainLiege.dat");

                vctHoraireTrain.add(new HoraireTrain(vctT.get(0), "09:33", "09:36", "VO", "Liege", "aachen", 6));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(1), "09:39", "09:44", "VO", "Maastricht", "bruxelles-midi", 2));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(2), "09:36", "09h40", "MA", "Verviers", "Liege-G", 1));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(3), "09:40", "09:44", "VO", "Visé", "herstal", 4));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(4), "09:42", "09:52", "VO", "Huy", "lille", 3));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(5), "09:48", "09:52", "VO", "Eupen", "liers", 6));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(6), "09:50", "09h55", "MA", "Herstal", "Liege-G", 5));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(7), "10:00", "10:04", "VO", "Liege-P", "ostende",2));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(8), "10:05", "10:10", "VO", "Namur", "Aachen", 4));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(9), "10:07", "10:13", "VO", "Liège-G", "waremme", 3));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(10), "10:09", "10:14", "VO", "Bruxelles-midi", "saint-ghislain", 6));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(11), "10:10", "10:15", "VO", "Liège-G", "maastricht", 5));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(12), "10:20", "10:24", "VO", "Visé", "vervier-central", 2));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(13), "10:22", "11:26", "VO", "Namur", "jemelle", 4));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(14), "10:25", "10:30", "VO", "Bruxelles-midi", "vise", 1));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(15), "10:29", "10:34", "VO", "Verviers", "statte", 4));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(16), "10:36", "10:40", "VO", "Liege-G", "lille", 6));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(17), "10:45", "10:50", "VO", "Liers", "waremme", 3));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(18), "10:47", "10:52", "VO", "Bruxelles-midi", "lille", 5));
                vctHoraireTrain.add(new HoraireTrain(vctT.get(19), "10:59", "11:05", "VO", "Maastricht", "bruxelles-midi", 1));
                
//                HoraireTrainEnCours = vctHoraireTrain.get(0);
//                e = vctHoraireTrain.elements();                
//                lbIdentiteTrainSuivant.setText(e.nextElement().toString());

                ObjectSaver OS = new ObjectSaver(cheminFichier + "HoraireTrainLiege.dat");
                OS.save(vctHoraireTrain);
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(FenApplicGare.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(FenApplicGare.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HoraireTrainEnCours = vctHoraireTrain.get(0);
        e = vctHoraireTrain.elements();                
        lbIdentiteTrainSuivant.setText(e.nextElement().toString());
    }  
    
    private void LoadPropertiesFile()
    {
        PropertyFile = new Properties();
        
        try
        {
            FileInputStream FIS = new FileInputStream(cheminFichier + "ressources" + sep + "ApplicGareProperties.properties");
            try
            {
                PropertyFile.load(FIS);
                FL.ecritLigne("Chargement du fichier Properties");
            }
            catch (IOException ex)
            {
                Logger.getLogger(FenApplicGare.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(FenApplicGare.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void CreateBeans()
    {
        try 
        {
            SB = (StateBean)Beans.instantiate(null, "Bean.StateBean");
            SB.setIdentifiant(login);
            SB.setPropertyFile(PropertyFile);
            AB = (AlarmBean)Beans.instantiate(null, "Bean.AlarmBean");
            RB = (ReportBean)Beans.instantiate(null, "Bean.ReportBean");
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            Logger.getLogger(FenApplicGare.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
            Logger.getLogger(FenApplicGare.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RB.setFL(FL); // Fichier Log
        SB.addPropertyChangeListener(AB);
        SB.setFichierLog(FL);
        AB.addListener(RB); // On ajoute le Bean "ReportBean" dans le Vector<EventEventListener>

        SB.run();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbResponsableGare = new javax.swing.JLabel();
        lbNomResponsableGare = new javax.swing.JLabel();
        lbTrainsArrivesRepartis = new javax.swing.JLabel();
        lbIdentiteTrainSuivant = new javax.swing.JLabel();
        cbTrainsArrivesRepartis = new javax.swing.JComboBox();
        lbTrainSuivant = new javax.swing.JLabel();
        btTrainSuivant = new javax.swing.JButton();
        btPrevenirControleIn = new javax.swing.JButton();
        btPrevenirHangar = new javax.swing.JButton();
        btPrevenirControleOut = new javax.swing.JButton();
        lbReponses = new javax.swing.JLabel();
        tfReponseControleIn = new javax.swing.JTextField();
        lbControleIN = new javax.swing.JLabel();
        lbDepot = new javax.swing.JLabel();
        tfReponseDepot = new javax.swing.JTextField();
        tfReponseControleOut = new javax.swing.JTextField();
        lbControleOut = new javax.swing.JLabel();
        lbOccupationVoies = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableOccupationVoies = new javax.swing.JTable();
        panelImage = new javax.swing.JPanel();
        lbDateHeureFormat = new javax.swing.JLabel();
        menuBarApplicGare = new javax.swing.JMenuBar();
        menuUsers = new javax.swing.JMenu();
        sMenuLogout = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        sMenuAjoutUser = new javax.swing.JMenuItem();
        sMenuListeUsers = new javax.swing.JMenuItem();
        menuTrains = new javax.swing.JMenu();
        sMenuListeJour = new javax.swing.JMenuItem();
        sMenuFormation = new javax.swing.JMenuItem();
        menuConfiguration = new javax.swing.JMenu();
        sMenuParamReseaux = new javax.swing.JMenuItem();
        sMenuReglageTemps = new javax.swing.JMenuItem();
        menuIncidents = new javax.swing.JMenu();
        sMenuListeIncidents = new javax.swing.JMenuItem();
        sMenuEnregistrer = new javax.swing.JMenuItem();
        menuAide = new javax.swing.JMenu();
        sMenuParamDates = new javax.swing.JMenuItem();
        sMenuAffLog = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        sMenuAPropos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 630));

        lbResponsableGare.setText("Responsable gare: ");
        lbResponsableGare.setName("lbResponsableGare"); // NOI18N

        lbNomResponsableGare.setText("Default");
        lbNomResponsableGare.setName("lbNomResponsableGare"); // NOI18N

        lbTrainsArrivesRepartis.setText("Trains arrivés et repartis : ");
        lbTrainsArrivesRepartis.setName("lbTrainsArrivesRepartis"); // NOI18N

        lbIdentiteTrainSuivant.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cbTrainsArrivesRepartis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbTrainSuivant.setText("Prochain train: ");
        lbTrainSuivant.setName("lbProchainTrain"); // NOI18N

        btTrainSuivant.setText("Train suivant");
        btTrainSuivant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTrainSuivantActionPerformed(evt);
            }
        });

        btPrevenirControleIn.setText("Prévenir poste contrôle in");
        btPrevenirControleIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrevenirControleInActionPerformed(evt);
            }
        });

        btPrevenirHangar.setText("Prévenir hangar");
        btPrevenirHangar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrevenirHangarActionPerformed(evt);
            }
        });

        btPrevenirControleOut.setText("Prévenir poste contrôle out");
        btPrevenirControleOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrevenirControleOutActionPerformed(evt);
            }
        });

        lbReponses.setText("Réponse: ");
        lbReponses.setName("lbReponse"); // NOI18N

        tfReponseControleIn.setEnabled(false);
        tfReponseControleIn.setName("tfReponseControleIn"); // NOI18N

        lbControleIN.setText("Contrôle IN");
        lbControleIN.setName("lbControleIN"); // NOI18N

        lbDepot.setText("Dépôt");
        lbDepot.setName("lbDepot"); // NOI18N

        tfReponseDepot.setEnabled(false);
        tfReponseDepot.setName("tfReponseDepot"); // NOI18N

        tfReponseControleOut.setEnabled(false);
        tfReponseControleOut.setName("tfReponseControleOut"); // NOI18N

        lbControleOut.setText("Contrôle OUT");
        lbControleOut.setName("lbControleOut"); // NOI18N

        lbOccupationVoies.setText("Occupation des voies: ");
        lbOccupationVoies.setName("lbOccupationVoies"); // NOI18N

        tableOccupationVoies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Voie", "Train N°", "Arrivée", "Départ prévu", "Train présent", "Minutes de retard"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableOccupationVoies.setName("tableOccupationVoies"); // NOI18N
        jScrollPane1.setViewportView(tableOccupationVoies);

        javax.swing.GroupLayout panelImageLayout = new javax.swing.GroupLayout(panelImage);
        panelImage.setLayout(panelImageLayout);
        panelImageLayout.setHorizontalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 141, Short.MAX_VALUE)
        );
        panelImageLayout.setVerticalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        menuBarApplicGare.setName("menuBareApplicGare"); // NOI18N

        menuUsers.setText("Utilisateurs");

        sMenuLogout.setText("Logout");
        sMenuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMenuLogoutActionPerformed(evt);
            }
        });
        menuUsers.add(sMenuLogout);
        menuUsers.add(jSeparator1);

        sMenuAjoutUser.setText("Nouvel utilisateur");
        sMenuAjoutUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMenuAjoutUserActionPerformed(evt);
            }
        });
        menuUsers.add(sMenuAjoutUser);

        sMenuListeUsers.setText("Liste");
        sMenuListeUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMenuListeUsersActionPerformed(evt);
            }
        });
        menuUsers.add(sMenuListeUsers);

        menuBarApplicGare.add(menuUsers);

        menuTrains.setText("Trains");

        sMenuListeJour.setText("Liste du jour");
        sMenuListeJour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMenuListeJourActionPerformed(evt);
            }
        });
        menuTrains.add(sMenuListeJour);

        sMenuFormation.setText("Formation");
        sMenuFormation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMenuFormationActionPerformed(evt);
            }
        });
        menuTrains.add(sMenuFormation);

        menuConfiguration.setText("Configuration");

        sMenuParamReseaux.setText("Paramètres réseaux");
        sMenuParamReseaux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMenuParamReseauxActionPerformed(evt);
            }
        });
        menuConfiguration.add(sMenuParamReseaux);

        sMenuReglageTemps.setText("Réglages des temps");
        sMenuReglageTemps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMenuReglageTempsActionPerformed(evt);
            }
        });
        menuConfiguration.add(sMenuReglageTemps);

        menuTrains.add(menuConfiguration);

        menuBarApplicGare.add(menuTrains);

        menuIncidents.setText("Incidents");

        sMenuListeIncidents.setText("Liste");
        sMenuListeIncidents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMenuListeIncidentsActionPerformed(evt);
            }
        });
        menuIncidents.add(sMenuListeIncidents);

        sMenuEnregistrer.setText("Enregistrer");
        sMenuEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMenuEnregistrerActionPerformed(evt);
            }
        });
        menuIncidents.add(sMenuEnregistrer);

        menuBarApplicGare.add(menuIncidents);

        menuAide.setText("Aide");

        sMenuParamDates.setText("Paramètres dates");
        sMenuParamDates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMenuParamDatesActionPerformed(evt);
            }
        });
        menuAide.add(sMenuParamDates);

        sMenuAffLog.setText("Afficher log");
        sMenuAffLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMenuAffLogActionPerformed(evt);
            }
        });
        menuAide.add(sMenuAffLog);
        menuAide.add(jSeparator2);

        sMenuAPropos.setText("A propos");
        sMenuAPropos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMenuAProposActionPerformed(evt);
            }
        });
        menuAide.add(sMenuAPropos);

        menuBarApplicGare.add(menuAide);

        setJMenuBar(menuBarApplicGare);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTrainSuivant)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbDateHeureFormat, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbOccupationVoies)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbResponsableGare)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbNomResponsableGare))
                                    .addComponent(lbTrainsArrivesRepartis)
                                    .addComponent(cbTrainsArrivesRepartis, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(panelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btPrevenirHangar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbIdentiteTrainSuivant, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addComponent(btTrainSuivant))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btPrevenirControleIn, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(184, 184, 184))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbReponses)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfReponseControleIn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbControleIN))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfReponseDepot, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbDepot))
                                        .addGap(57, 57, 57)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btPrevenirControleOut, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbControleOut)
                                            .addComponent(tfReponseControleOut, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jScrollPane1))
                        .addContainerGap(27, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbResponsableGare)
                            .addComponent(lbNomResponsableGare))
                        .addGap(18, 18, 18)
                        .addComponent(lbTrainsArrivesRepartis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTrainsArrivesRepartis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbDateHeureFormat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(lbTrainSuivant)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbIdentiteTrainSuivant, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTrainSuivant))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btPrevenirControleIn)
                    .addComponent(btPrevenirHangar)
                    .addComponent(btPrevenirControleOut))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbControleOut)
                    .addComponent(lbControleIN)
                    .addComponent(lbDepot))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbReponses)
                    .addComponent(tfReponseControleOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfReponseDepot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfReponseControleIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbOccupationVoies)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        lbTrainsArrivesRepartis.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sMenuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMenuLogoutActionPerformed
        if (!llTrainPartit.isEmpty()) // Si la liste des trains partit n'est pas vide, on la sauvegarde
        {
            try 
            {
                ObjectSaver OS = new ObjectSaver(cheminFichier + "TrainsPartit.dat");
                OS.save(llTrainPartit);
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(FenApplicGare.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        fenLog.setVisible(true);
        FL.ecritLigne("Fermeture de l'application Gare");
        this.dispose();
    }//GEN-LAST:event_sMenuLogoutActionPerformed

    private void btTrainSuivantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTrainSuivantActionPerformed
        FL.ecritLigne("Clic sur << Train suivant >> ");
        if (e.hasMoreElements())
        {
            HoraireTrainEnCours = (HoraireTrain)e.nextElement();
            lbIdentiteTrainSuivant.setText(HoraireTrainEnCours.toString());
            btPrevenirControleIn.setEnabled(true);
            btPrevenirControleOut.setEnabled(true);
            btPrevenirHangar.setEnabled(true);
            tfReponseControleIn.setText(null);
            tfReponseControleOut.setText(null);
            tfReponseDepot.setText(null);
        }
        else
        {
            btTrainSuivant.setEnabled(false);
            btPrevenirControleIn.setEnabled(false);
            btPrevenirControleOut.setEnabled(false);
            btPrevenirHangar.setEnabled(false);
        }        
    }//GEN-LAST:event_btTrainSuivantActionPerformed

    private void sMenuParamDatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMenuParamDatesActionPerformed
        FL.ecritLigne("Parametrage de la date et de l'heure");
        FenParametreDates fenDate = new FenParametreDates(this, true);
        fenDate.setVisible(true);
        lbDateHeureFormat.setText(fenDate.getDate());
    }//GEN-LAST:event_sMenuParamDatesActionPerformed

    private void sMenuAjoutUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMenuAjoutUserActionPerformed
        FL.ecritLigne("Ajout d'un utilisateur");
        FenAjoutUser FAU = new FenAjoutUser(this, true, HM);
        FAU.setVisible(true);
    }//GEN-LAST:event_sMenuAjoutUserActionPerformed

    private void sMenuListeUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMenuListeUsersActionPerformed
        FL.ecritLigne("Affichage des utilisateurs");
        Vector<User> vUser = new Vector<User>();
        
        for (Iterator i = HM.keySet().iterator(); i.hasNext();)
        {
            String key = (String)i.next();
            vUser.add((User)HM.get(key));
        }
        
        FenListeUser FLU = new FenListeUser(this, true, vUser);
        FLU.setVisible(true);
    }//GEN-LAST:event_sMenuListeUsersActionPerformed

    private void sMenuAProposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMenuAProposActionPerformed
        FL.ecritLigne("Affichage << à propos >>");
        FenAPropos FAP = new FenAPropos(this, true);
        FAP.setVisible(true);
    }//GEN-LAST:event_sMenuAProposActionPerformed

    private void btPrevenirControleInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrevenirControleInActionPerformed
        FL.ecritLigne("Prévenir poste de contrôle IN");
        if (nssIN == null)
            nssIN = new NetworkStringSender((String)getPropertyFile().get("AdresseReseau"), Integer.valueOf((String)getPropertyFile().get("PortNSSIN")));

        nssIN.sendString(lbIdentiteTrainSuivant.getText());
        
        if (tarIN == null)
        {
            tarIN = new ThreadAttenteReponse(Integer.valueOf((String)getPropertyFile().get("PortTARIN")), tfReponseControleIn);
            tarIN.start();
        }
        
        btTrainSuivant.setEnabled(false); // Reste à false tant que le train sera dans la gare
        btPrevenirControleOut.setEnabled(false);
        btPrevenirHangar.setEnabled(false);
        
        ThreadChangeProperty TCP = new ThreadChangeProperty(tfReponseControleIn, btPrevenirHangar, btPrevenirControleOut, tableOccupationVoies, HoraireTrainEnCours, (String)SB.getFIFO().poll());
        TCP.start();
        
        btPrevenirControleIn.setEnabled(false);
    }//GEN-LAST:event_btPrevenirControleInActionPerformed

    private void btPrevenirControleOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrevenirControleOutActionPerformed
        FL.ecritLigne("Prévenir poste de contrôle OUT");
        System.out.println(tfReponseControleIn.getText());
        if (tfReponseControleIn.getText().equals("Train passé!"))
        {
            if (nssOUT == null)
                nssOUT = new NetworkStringSender((String)getPropertyFile().get("AdresseReseau"), Integer.valueOf((String)getPropertyFile().get("PortNSSOUT")));
            
            nssOUT.sendString(HoraireTrainEnCours.toString());

            if (tarOUT == null)
            {
                tarOUT = new ThreadAttenteReponse(Integer.valueOf((String)getPropertyFile().get("PortTAROUT")), tfReponseControleOut);
                tarOUT.start();
            }

            btTrainSuivant.setEnabled(false);
            // Permet qu'on attende que le train soit passé au poste de contrôle 
            ThreadVerification TV = new ThreadVerification(tfReponseControleOut, btTrainSuivant, cbTrainsArrivesRepartis, HoraireTrainEnCours, tableOccupationVoies, llTrainPartit);
            TV.start();
            btPrevenirControleOut.setEnabled(false);
            btPrevenirHangar.setEnabled(false);
        }
    }//GEN-LAST:event_btPrevenirControleOutActionPerformed

    private void btPrevenirHangarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrevenirHangarActionPerformed
        FL.ecritLigne("Prévenir Hangar");
        if (nssHG == null)
            nssHG = new NetworkStringSender((String)getPropertyFile().get("AdresseReseau"), Integer.valueOf((String)getPropertyFile().get("PortNSSHG")));
        nssHG.sendString(HoraireTrainEnCours.toString());
        
        vctTrainDepot.add(HoraireTrainEnCours.getTrain());
        
        if (tarHG == null)
        {
            tarHG = new ThreadAttenteReponse(Integer.valueOf((String)getPropertyFile().get("PortTARHG")), tfReponseDepot);
            tarHG.start();
        }
        
        btTrainSuivant.setEnabled(false);
        ThreadVerification TV = new ThreadVerification(tfReponseDepot, btTrainSuivant, cbTrainsArrivesRepartis, HoraireTrainEnCours, tableOccupationVoies, llTrainPartit);
        TV.start();
        btPrevenirControleOut.setEnabled(false);
        btPrevenirHangar.setEnabled(false);
    }//GEN-LAST:event_btPrevenirHangarActionPerformed

    private void sMenuFormationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMenuFormationActionPerformed
        FL.ecritLigne("Formation d'un train");
        FenFormationTrain FFT = new FenFormationTrain(this, true, vctTrainDepot);
        FFT.setVisible(true);
        
        if (FFT.isValide())
        {
            vctHoraireTrain.add(FFT.getNewHoraireTrain());
            nssHG.sendString("*" + FFT.getNewHoraireTrain().getTrain().getIdentifiant()); // "*" signifie train formé
        }
    }//GEN-LAST:event_sMenuFormationActionPerformed

    private void sMenuAffLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMenuAffLogActionPerformed
        String sLog = FL.litFichier();
        FenAfficherLog FAL = new FenAfficherLog(this, true, sLog);
        FAL.setVisible(true);
    }//GEN-LAST:event_sMenuAffLogActionPerformed

    private void sMenuListeJourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMenuListeJourActionPerformed
        FenAfficheTrain FAT = new FenAfficheTrain(this, true, vctHoraireTrain);
        FAT.setVisible(true);
    }//GEN-LAST:event_sMenuListeJourActionPerformed
    
    private void sMenuParamReseauxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMenuParamReseauxActionPerformed
        FenParametresReseaux FPR = new FenParametresReseaux(this, true, PropertyFile);
        FPR.setVisible(true);
    }//GEN-LAST:event_sMenuParamReseauxActionPerformed

    private void sMenuListeIncidentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMenuListeIncidentsActionPerformed
        FenListeIncidents FLI = new FenListeIncidents(this, true);
        FLI.setVisible(true);
    }//GEN-LAST:event_sMenuListeIncidentsActionPerformed

    private void sMenuEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMenuEnregistrerActionPerformed
        FenRechercheIncident FRI = new FenRechercheIncident(this, true, AB.getVecteurEvent());
        FRI.setVisible(true);
    }//GEN-LAST:event_sMenuEnregistrerActionPerformed

    private void sMenuReglageTempsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMenuReglageTempsActionPerformed
        FenReglageTemps FRT = new FenReglageTemps(this, true);
        FRT.setVisible(true);
        
        if (FRT.isValide())
        {
            SB.setTempsAttente(FRT.getTemps());
            
            if (FRT.getRetard() != 0)
                SB.setRetard(FRT.getRetard());
            
            if (FRT.getManifestation() != 0)
                SB.setManifestation(FRT.getManifestation());
            
            if (FRT.getGreve() != 0)
                SB.setGreve(FRT.getGreve());
        }
    }//GEN-LAST:event_sMenuReglageTempsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run()
            {
                //new FenApplicGare().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btPrevenirControleIn;
    private javax.swing.JButton btPrevenirControleOut;
    private javax.swing.JButton btPrevenirHangar;
    private javax.swing.JButton btTrainSuivant;
    private javax.swing.JComboBox cbTrainsArrivesRepartis;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel lbControleIN;
    private javax.swing.JLabel lbControleOut;
    private javax.swing.JLabel lbDateHeureFormat;
    private javax.swing.JLabel lbDepot;
    private javax.swing.JLabel lbIdentiteTrainSuivant;
    private javax.swing.JLabel lbNomResponsableGare;
    private javax.swing.JLabel lbOccupationVoies;
    private javax.swing.JLabel lbReponses;
    private javax.swing.JLabel lbResponsableGare;
    private javax.swing.JLabel lbTrainSuivant;
    private javax.swing.JLabel lbTrainsArrivesRepartis;
    private javax.swing.JMenu menuAide;
    private javax.swing.JMenuBar menuBarApplicGare;
    private javax.swing.JMenu menuConfiguration;
    private javax.swing.JMenu menuIncidents;
    private javax.swing.JMenu menuTrains;
    private javax.swing.JMenu menuUsers;
    private javax.swing.JPanel panelImage;
    private javax.swing.JMenuItem sMenuAPropos;
    private javax.swing.JMenuItem sMenuAffLog;
    private javax.swing.JMenuItem sMenuAjoutUser;
    private javax.swing.JMenuItem sMenuEnregistrer;
    private javax.swing.JMenuItem sMenuFormation;
    private javax.swing.JMenuItem sMenuListeIncidents;
    private javax.swing.JMenuItem sMenuListeJour;
    private javax.swing.JMenuItem sMenuListeUsers;
    private javax.swing.JMenuItem sMenuLogout;
    private javax.swing.JMenuItem sMenuParamDates;
    private javax.swing.JMenuItem sMenuParamReseaux;
    private javax.swing.JMenuItem sMenuReglageTemps;
    private javax.swing.JTable tableOccupationVoies;
    private javax.swing.JTextField tfReponseControleIn;
    private javax.swing.JTextField tfReponseControleOut;
    private javax.swing.JTextField tfReponseDepot;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the PropertyFile
     */
    public Properties getPropertyFile() {
        return PropertyFile;
    }

    /**
     * @param PropertyFile the PropertyFile to set
     */
    public void setPropertyFile(Properties PropertyFile) {
        this.PropertyFile = PropertyFile;
    }

}
