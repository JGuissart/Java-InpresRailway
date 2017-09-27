/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FenLogin.java
 *
 * Created on 20-mars-2014, 12:21:58
 */
package Login;

import InterfaceGraphique.FenApplicGare;
import Authenticate.User;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import serialize.ObjectLoader;

/**
 *
 * @author Julien
 */
public class FenLogin extends Frame implements ActionListener
{
    private FenApplicGare fenGare;
    private HashMap HM;
    /** Creates new form FenLogin */
    public FenLogin()
    {
        initComponents();
        this.setTitle("Connexion");
        btOk.addActionListener(this);
        btAnnuler.addActionListener(this);
        //vctUser = new Vector<User>();
        HM = new HashMap();
        LoadUsers();
    }
    
    private void LoadUsers()
    {
        String sep = System.getProperty("file.separator");
        String cheminFichier = System.getProperty("user.home") + sep + "Documents" + sep + "NetBeansProjects" + sep + "Applic_Gare" + sep;
        
        File f = new File(cheminFichier + "Users.dat");
        
        if (f.exists())
        {
            ObjectLoader OL = new ObjectLoader(cheminFichier + "Users.dat");
            try
            {
                HM = (HashMap)OL.load();
            }
            catch (IOException ex) 
            {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            catch (ClassNotFoundException ex)
            {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        else
            HM.put("Admin", new User("Admin", "Admin", "Admin"));
    }
    
    public void actionPerformed(ActionEvent e)
    {      
        if (e.getSource() == btOk)
        {
            CritereLPArray CLPA = new CritereLPArray(tfLogin.getText(), tfMDP.getText(), HM);
            if (CLPA.isOk())
            {
                fenGare = new FenApplicGare(tfLogin.getText(), this, HM);
                fenGare.setVisible(true);
            }
            else
                System.out.println("Connexion refusée");
        }
        if (e.getSource() == btAnnuler)
        {
            tfLogin.setText(null);
            tfMDP.setText(null);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbLogin = new java.awt.Label();
        tfLogin = new java.awt.TextField();
        lbMDP = new java.awt.Label();
        tfMDP = new java.awt.TextField();
        btOk = new java.awt.Button();
        btAnnuler = new java.awt.Button();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new java.awt.GridLayout(3, 2));

        lbLogin.setName("lbLogin"); // NOI18N
        lbLogin.setText("Login");
        add(lbLogin);
        add(tfLogin);

        lbMDP.setName("lbPass"); // NOI18N
        lbMDP.setText("Mot de Passe");
        add(lbMDP);
        add(tfMDP);

        btOk.setLabel("Ok");
        btOk.setName("btOk"); // NOI18N
        add(btOk);

        btAnnuler.setLabel("Annuler");
        btAnnuler.setName("btAnnuler"); // NOI18N
        add(btAnnuler);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new FenLogin().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btAnnuler;
    private java.awt.Button btOk;
    private java.awt.Label lbLogin;
    private java.awt.Label lbMDP;
    private java.awt.TextField tfLogin;
    private java.awt.TextField tfMDP;
    // End of variables declaration//GEN-END:variables
}
