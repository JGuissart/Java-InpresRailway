/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfaceGraphique;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Julien
 */
public class FenCreationRapportIncident extends javax.swing.JDialog
{
    private String nomFichier;
    private Boolean Save;
    private String sep;

    /**
     * Creates new form FenCreationRapportIncident
     */
    public FenCreationRapportIncident(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        sep = System.getProperty("file.separator");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        taRapportIncident = new javax.swing.JTextArea();
        menuBarRapportIncident = new javax.swing.JMenuBar();
        menuFichier = new javax.swing.JMenu();
        sMenuNouveau = new javax.swing.JMenuItem();
        sMenuEnregistrer = new javax.swing.JMenuItem();
        sMenuQuitter = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        taRapportIncident.setColumns(20);
        taRapportIncident.setRows(5);
        jScrollPane1.setViewportView(taRapportIncident);

        menuFichier.setText("Fichier");

        sMenuNouveau.setText("Nouveau");
        sMenuNouveau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMenuNouveauActionPerformed(evt);
            }
        });
        menuFichier.add(sMenuNouveau);

        sMenuEnregistrer.setText("Enregistrer");
        sMenuEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMenuEnregistrerActionPerformed(evt);
            }
        });
        menuFichier.add(sMenuEnregistrer);

        sMenuQuitter.setText("Quitter");
        sMenuQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMenuQuitterActionPerformed(evt);
            }
        });
        menuFichier.add(sMenuQuitter);

        menuBarRapportIncident.add(menuFichier);

        setJMenuBar(menuBarRapportIncident);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sMenuNouveauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMenuNouveauActionPerformed
        taRapportIncident.setText(null);
    }//GEN-LAST:event_sMenuNouveauActionPerformed

    private void sMenuEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMenuEnregistrerActionPerformed
        try 
        {
            String repHome = System.getProperty("user.home");
            String chemin = repHome + sep + "Documents" + sep + "NetBeansProjects" + sep + "Applic_gare" + sep + "Incidents" + sep + nomFichier;
            BufferedWriter BW = new BufferedWriter(new FileWriter(chemin, true)); // true pour append
            taRapportIncident.write(BW);
            BW.close();
            Save = true;  
        } 
        catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_sMenuEnregistrerActionPerformed

    private void sMenuQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMenuQuitterActionPerformed
        this.dispose();
    }//GEN-LAST:event_sMenuQuitterActionPerformed

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
            java.util.logging.Logger.getLogger(FenCreationRapportIncident.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenCreationRapportIncident.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenCreationRapportIncident.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenCreationRapportIncident.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FenCreationRapportIncident dialog = new FenCreationRapportIncident(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBarRapportIncident;
    private javax.swing.JMenu menuFichier;
    private javax.swing.JMenuItem sMenuEnregistrer;
    private javax.swing.JMenuItem sMenuNouveau;
    private javax.swing.JMenuItem sMenuQuitter;
    private javax.swing.JTextArea taRapportIncident;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the nomFichier
     */
    public String getNomFichier() {
        return nomFichier;
    }

    /**
     * @return the Save
     */
    public Boolean isSave() {
        return Save;
    }

    /**
     * @param nomFichier the nomFichier to set
     */
    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
        taRapportIncident.setText("        Rapport de l'incident : " + nomFichier + "\n");
    }

    /**
     * @param Save the Save to set
     */
    public void setSave(Boolean Save) {
        this.Save = Save;
    }
}