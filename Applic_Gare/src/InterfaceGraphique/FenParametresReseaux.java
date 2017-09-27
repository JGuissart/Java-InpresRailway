/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfaceGraphique;

import Exceptions.NotANumberException;
import Exceptions.NullOrEmptyFieldException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Julien
 */

@SuppressWarnings("OverridableMethodCallInConstructor")
public class FenParametresReseaux extends javax.swing.JDialog 
{
    private Properties PropertyFileGare;
    private Properties PropertyFileDepot;
    private Properties PropertyFilePoste;
    private String repHome;
    private String sep;

    /**
     * Creates new form FenParametresReseaux
     */
    public FenParametresReseaux(java.awt.Frame parent, boolean modal) 
    {
        super(parent, modal);
        initComponents();
        setTitle("Paramètres configuration réseaux");
    }
    
    
    public FenParametresReseaux(java.awt.Frame parent, boolean modal, Properties PF) 
    {
        super(parent, modal);
        initComponents();
        setTitle("Paramètres configuration réseaux");
        repHome = System.getProperty("user.home");
        sep = System.getProperty("file.separator");
        PropertyFileGare = PF;
        LoadPropertiesFiles();
        RemplirTextFields();        
    }
    
    public void LoadPropertiesFiles()
    {
        PropertyFileDepot = new Properties();
        PropertyFilePoste = new Properties();
        
        try
        {
            FileInputStream FISDepot = new FileInputStream(repHome + sep + "Documents" + sep + "NetBeansProjects" + sep + "Applic_Depot" + sep + "ressources" + sep + "ApplicDepotProperties.properties");
            FileInputStream FISPoste = new FileInputStream(repHome + sep + "Documents" + sep + "NetBeansProjects" + sep +  "Applic_Poste" + sep + "ressources" + sep + "ApplicPosteProperties.properties");

            try
            {
                PropertyFileDepot.load(FISDepot);
                PropertyFilePoste.load(FISPoste);
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
    
    public void RemplirTextFields()
    {
        tfPortEcouteSenderIN.setText((String)PropertyFileGare.getProperty("PortNSSIN"));
        tfPortEcouteReceiverIN.setText((String)PropertyFileGare.getProperty("PortTARIN"));
        tfPortEcouteSenderOUT.setText((String)PropertyFileGare.getProperty("PortNSSOUT"));
        tfPortEcouteReceiverOUT.setText((String)PropertyFileGare.getProperty("PortTAROUT"));
        tfPortEcouteSenderHG.setText((String)PropertyFileGare.getProperty("PortNSSHG"));
        tfPortEcouteReceiverHG.setText((String)PropertyFileGare.getProperty("PortTARHG"));
    }
    
    public void VerificationTextFields() throws NullOrEmptyFieldException, NotANumberException
    {
        /* Vérifications des champs vides ou espacés */
        if (tfPortEcouteSenderIN.getText().equals(""))
            throw new NullOrEmptyFieldException("Le champ \"Port d'écoute Sender\" du poste de contrôle IN est vide ...");
        if (tfPortEcouteSenderIN.getText().contains(" "))
            throw new NullOrEmptyFieldException("Le champ \"Port d'écoute Sender\" du poste de contrôle IN ne doit pas contenir d'espace ...");
        
        if (tfPortEcouteReceiverIN.getText().equals(""))
            throw new NullOrEmptyFieldException("Le champ \"Port d'écoute Receiver\" du poste de contrôle IN est vide ...");
        if (tfPortEcouteReceiverIN.getText().contains(" "))
            throw new NullOrEmptyFieldException("Le champ \"Port d'écoute Receiver\" du poste de contrôle IN ne doit pas contenir d'espace ...");
        
        if (tfPortEcouteSenderOUT.getText().equals(""))
            throw new NullOrEmptyFieldException("Le champ \"Port d'écoute Sender\" du poste de contrôle OUT est vide ...");
        if (tfPortEcouteSenderOUT.getText().contains(" "))
            throw new NullOrEmptyFieldException("Le champ \"Port d'écoute Sender\" du poste de contrôle OUT ne doit pas contenir d'espace ...");
        
        if (tfPortEcouteReceiverOUT.getText().equals(""))
            throw new NullOrEmptyFieldException("Le champ \"Port d'écoute Receiver\" du poste de contrôle OUT est vide ...");
        if (tfPortEcouteReceiverOUT.getText().contains(" "))
            throw new NullOrEmptyFieldException("Le champ \"Port d'écoute Receiver\" du poste de contrôle OUT ne doit pas contenir d'espace ...");
        
        if (tfPortEcouteSenderHG.getText().equals(""))
            throw new NullOrEmptyFieldException("Le champ \"Port d'écoute Sender\" du dépôt est vide ...");
        if (tfPortEcouteSenderHG.getText().contains(" "))
            throw new NullOrEmptyFieldException("Le champ \"Port d'écoute Sender\" du dépôt ne doit pas contenir d'espace ...");
        
        if (tfPortEcouteReceiverHG.getText().equals(""))
            throw new NullOrEmptyFieldException("Le champ \"Port d'écoute Receiver\" du dépôt est vide ...");
        if (tfPortEcouteReceiverHG.getText().contains(" "))
            throw new NullOrEmptyFieldException("Le champ \"Port d'écoute Receiver\" du dépôt ne doit pas contenir d'espace ...");
        
        /* Vérification des numéros entrés */
        
        try
        {
            int a = Integer.valueOf(tfPortEcouteSenderIN.getText());
            int b = Integer.valueOf(tfPortEcouteReceiverIN.getText());
            int c = Integer.valueOf(tfPortEcouteSenderOUT.getText());
            int d = Integer.valueOf(tfPortEcouteReceiverOUT.getText());
            int e = Integer.valueOf(tfPortEcouteSenderHG.getText());
            int f = Integer.valueOf(tfPortEcouteReceiverHG.getText());
        }
        catch(NumberFormatException ex)
        {
            throw new NotANumberException("Un des champs spécifié n'est pas un int ...");
        }
        
        if (50000 > Integer.valueOf(tfPortEcouteSenderIN.getText()))
            throw new NotANumberException("Le champ \"Port d'écoute Sender\" du poste de contrôle IN doit être supérieur ou égal à 50000 ...");
        
        if (50000 > Integer.valueOf(tfPortEcouteReceiverIN.getText()))
            throw new NullOrEmptyFieldException("Le champ \"Port d'écoute Receiver\" du poste de contrôle IN doit être supérieur ou égal à 50000 ...");
        
        if (50000 > Integer.valueOf(tfPortEcouteSenderOUT.getText()))
            throw new NullOrEmptyFieldException("Le champ \"Port d'écoute Sender\" du poste de contrôle OUT doit être supérieur ou égal à 50000 ...");
        
        if (50000 > Integer.valueOf(tfPortEcouteReceiverOUT.getText()))
            throw new NullOrEmptyFieldException("Le champ \"Port d'écoute Receiver\" du poste de contrôle OUT doit être supérieur ou égal à 50000 ...");
        
        if (50000 > Integer.valueOf(tfPortEcouteSenderHG.getText()))
            throw new NullOrEmptyFieldException("Le champ \"Port d'écoute Sender\" du dépôt doit être supérieur ou égal à 50000 ...");
        
        if (50000 > Integer.valueOf(tfPortEcouteReceiverHG.getText()))
            throw new NullOrEmptyFieldException("Le champ \"Port d'écoute Receiver\" du dépôt doit être supérieur ou égal à 50000 ...");
    }
    
    public int VerificationDonnees() // Permet de savoir si les valeurs entrées dans les champs sont les mêmes que ceux déjà enregistrés dans le fichier de properties
    {
        int iNombreModif = 0;
        
        if (!tfPortEcouteSenderIN.getText().equals((String)PropertyFileGare.getProperty("PortNSSIN")))
            iNombreModif++;
        
        if (!tfPortEcouteReceiverIN.getText().equals((String)PropertyFileGare.getProperty("PortTARIN")))
            iNombreModif++;
        
        if (!tfPortEcouteSenderOUT.getText().equals((String)PropertyFileGare.getProperty("PortNSSOUT")))
            iNombreModif++;
        
        if (!tfPortEcouteReceiverOUT.getText().equals((String)PropertyFileGare.getProperty("PortTAROUT")))
            iNombreModif++;
        
        if (!tfPortEcouteSenderHG.getText().equals((String)PropertyFileGare.getProperty("PortNSSHG")))
            iNombreModif++;
        
        if (!tfPortEcouteReceiverHG.getText().equals((String)PropertyFileGare.getProperty("PortTARHG")))
            iNombreModif++;
        
        return iNombreModif;
    }
    
    public void EnregistrementPropertyFileGare()
    {
        try
        {
            FileOutputStream FOS = new FileOutputStream(repHome + sep + "Documents" + sep + "NetBeansProjects" + sep + "Applic_Gare" + sep + "ressources" + sep + "ApplicGareProperties.properties");
            PropertyFileGare.setProperty("PortNSSIN", tfPortEcouteSenderIN.getText());
            PropertyFileGare.setProperty("PortTARIN", tfPortEcouteReceiverIN.getText());
            PropertyFileGare.setProperty("PortNSSOUT", tfPortEcouteSenderOUT.getText());
            PropertyFileGare.setProperty("PortTAROUT", tfPortEcouteReceiverOUT.getText());
            PropertyFileGare.setProperty("PortNSSHG", tfPortEcouteSenderHG.getText());
            PropertyFileGare.setProperty("PortTARHG", tfPortEcouteReceiverHG.getText());
            
            PropertyFileGare.store(FOS, "Port d'écoutes");
            
            FOS.flush();
            FOS.close();
        }
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(FenParametresReseaux.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex)
        {
            Logger.getLogger(FenParametresReseaux.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void EnregistrementPropertyFilePoste()
    {
        try
        {
            FileOutputStream FOS = new FileOutputStream(repHome + sep + "Documents" + sep + "NetBeansProjects" + sep + "Applic_Poste" + sep + "ressources" + sep + "ApplicPosteProperties.properties");
            PropertyFilePoste.setProperty("PortNSRIN", tfPortEcouteSenderIN.getText());
            PropertyFilePoste.setProperty("PortNSSIN", tfPortEcouteReceiverIN.getText());
            PropertyFilePoste.setProperty("PortNSROUT", tfPortEcouteSenderOUT.getText());
            PropertyFilePoste.setProperty("PortNSSOUT", tfPortEcouteReceiverOUT.getText());
            
            PropertyFilePoste.store(FOS, "Port d'écoutes");
            
            FOS.flush();
            FOS.close();
        }
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(FenParametresReseaux.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex)
        {
            Logger.getLogger(FenParametresReseaux.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void EnregistrementPropertyFileDepot()
    {
        try
        {
            FileOutputStream FOS = new FileOutputStream(repHome + sep + "Documents" + sep + "NetBeansProjects" + sep + "Applic_Depot" + sep + "ressources" + sep + "ApplicDepotProperties.properties");
            PropertyFileDepot.setProperty("PortNSR", tfPortEcouteSenderHG.getText());
            PropertyFileDepot.setProperty("PortNSS", tfPortEcouteReceiverHG.getText());
            
            PropertyFileDepot.store(FOS, "Port d'écoutes");
            
            FOS.flush();
            FOS.close();
        }
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(FenParametresReseaux.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex)
        {
            Logger.getLogger(FenParametresReseaux.class.getName()).log(Level.SEVERE, null, ex);
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

        lbControleIn = new javax.swing.JLabel();
        lbControleOut = new javax.swing.JLabel();
        lbDepot = new javax.swing.JLabel();
        btValider = new javax.swing.JButton();
        lbPortEcouteSenderIN = new javax.swing.JLabel();
        tfPortEcouteSenderIN = new javax.swing.JTextField();
        lbPortEcouteReceiverIN = new javax.swing.JLabel();
        tfPortEcouteReceiverIN = new javax.swing.JTextField();
        lbPortEcouteSenderOUT = new javax.swing.JLabel();
        lbPortEcouteReceiverOUT = new javax.swing.JLabel();
        tfPortEcouteSenderOUT = new javax.swing.JTextField();
        tfPortEcouteReceiverOUT = new javax.swing.JTextField();
        lbPortEcouteSenderHG = new javax.swing.JLabel();
        lbPortEcouteReceiverHG = new javax.swing.JLabel();
        tfPortEcouteSenderHG = new javax.swing.JTextField();
        tfPortEcouteReceiverHG = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbControleIn.setFont(new java.awt.Font("Dialog", 0, 30)); // NOI18N
        lbControleIn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbControleIn.setText("Poste de contrôle IN");
        lbControleIn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbControleOut.setFont(new java.awt.Font("Dialog", 0, 30)); // NOI18N
        lbControleOut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbControleOut.setText("Poste de contrôle OUT");
        lbControleOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lbDepot.setFont(new java.awt.Font("Dialog", 0, 30)); // NOI18N
        lbDepot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDepot.setText("Dépôt");

        btValider.setText("Valider");
        btValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btValiderActionPerformed(evt);
            }
        });

        lbPortEcouteSenderIN.setText("Port d'écoute Sender: ");

        tfPortEcouteSenderIN.setText("jTextField1");

        lbPortEcouteReceiverIN.setText("Port d'écoute Receiver: ");

        tfPortEcouteReceiverIN.setText("jTextField2");

        lbPortEcouteSenderOUT.setText("Port d'écoute Sender: ");

        lbPortEcouteReceiverOUT.setText("Port d'écoute Receiver: ");

        tfPortEcouteSenderOUT.setText("jTextField1");

        tfPortEcouteReceiverOUT.setText("jTextField1");

        lbPortEcouteSenderHG.setText("Port d'écoute Sender: ");

        lbPortEcouteReceiverHG.setText("Port d'écoute Receiver: ");

        tfPortEcouteSenderHG.setText("jTextField1");

        tfPortEcouteReceiverHG.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbControleIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDepot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btValider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbControleOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbPortEcouteSenderOUT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbPortEcouteReceiverOUT))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbPortEcouteSenderHG)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbPortEcouteReceiverHG))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbPortEcouteSenderIN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfPortEcouteSenderIN))
                                .addGap(146, 146, 146)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbPortEcouteReceiverIN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfPortEcouteReceiverIN)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(tfPortEcouteSenderOUT)
                                .addGap(146, 146, 146)
                                .addComponent(tfPortEcouteReceiverOUT))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(tfPortEcouteSenderHG)
                                .addGap(146, 146, 146)
                                .addComponent(tfPortEcouteReceiverHG)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbControleIn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPortEcouteSenderIN)
                    .addComponent(lbPortEcouteReceiverIN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPortEcouteSenderIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPortEcouteReceiverIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbControleOut)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPortEcouteSenderOUT)
                    .addComponent(lbPortEcouteReceiverOUT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPortEcouteSenderOUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPortEcouteReceiverOUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbDepot)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPortEcouteSenderHG)
                    .addComponent(lbPortEcouteReceiverHG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPortEcouteSenderHG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPortEcouteReceiverHG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(btValider)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btValiderActionPerformed
        try
        {
            VerificationTextFields();
            
            if (VerificationDonnees() > 0) // Si plus grand que 0, il y a des champs à réenregistrer
            {
                EnregistrementPropertyFileGare();
                EnregistrementPropertyFilePoste();
                EnregistrementPropertyFileDepot();
            }
            
            this.dispose();
        }
        catch(NullOrEmptyFieldException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        catch (NotANumberException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            //Logger.getLogger(FenParametresReseaux.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btValiderActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenParametresReseaux.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenParametresReseaux.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenParametresReseaux.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenParametresReseaux.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FenParametresReseaux dialog = new FenParametresReseaux(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btValider;
    private javax.swing.JLabel lbControleIn;
    private javax.swing.JLabel lbControleOut;
    private javax.swing.JLabel lbDepot;
    private javax.swing.JLabel lbPortEcouteReceiverHG;
    private javax.swing.JLabel lbPortEcouteReceiverIN;
    private javax.swing.JLabel lbPortEcouteReceiverOUT;
    private javax.swing.JLabel lbPortEcouteSenderHG;
    private javax.swing.JLabel lbPortEcouteSenderIN;
    private javax.swing.JLabel lbPortEcouteSenderOUT;
    private javax.swing.JTextField tfPortEcouteReceiverHG;
    private javax.swing.JTextField tfPortEcouteReceiverIN;
    private javax.swing.JTextField tfPortEcouteReceiverOUT;
    private javax.swing.JTextField tfPortEcouteSenderHG;
    private javax.swing.JTextField tfPortEcouteSenderIN;
    private javax.swing.JTextField tfPortEcouteSenderOUT;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the PF
     */
    public Properties getPropertyFileGare() {
        return PropertyFileGare;
    }

    /**
     * @param PF the PF to set
     */
    public void setPropertyFileGare(Properties PF) {
        PropertyFileGare = PF;
    }

    /**
     * @return the PropertyFileDepot
     */
    public Properties getPropertyFileDepot() {
        return PropertyFileDepot;
    }

    /**
     * @return the PropertyFilePoste
     */
    public Properties getPropertyFilePoste() {
        return PropertyFilePoste;
    }

    /**
     * @param PropertyFileDepot the PropertyFileDepot to set
     */
    public void setPropertyFileDepot(Properties PropertyFileDepot) {
        this.PropertyFileDepot = PropertyFileDepot;
    }

    /**
     * @param PropertyFilePoste the PropertyFilePoste to set
     */
    public void setPropertyFilePoste(Properties PropertyFilePoste) {
        this.PropertyFilePoste = PropertyFilePoste;
    }
}
