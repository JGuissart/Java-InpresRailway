/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfaceGraphique;

import Bean.AlarmBeanSerializable;
import Bean.EventEvent;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Julien
 */
public class FenRechercheIncident extends javax.swing.JDialog
{
    private Vector<EventEvent> vecEvEv;
    private Vector<EventEvent> vecFound;
    /**
     * Creates new form FenRechercheIncident
     */
    public FenRechercheIncident(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        setTitle("Recherche d'un incident");
    }
    
    public FenRechercheIncident(java.awt.Frame parent, boolean modal, Vector<EventEvent> vctEvEv)
    {
        super(parent, modal);
        initComponents();
        setTitle("Recherche d'un incident");
        
        for (int i = 1; i <= 31; i++)
            cbJour.addItem(i);
        
        cbMois.addItem("janvier");
        cbMois.addItem("fevrier");
        cbMois.addItem("mars");
        cbMois.addItem("avril");
        cbMois.addItem("mai");
        cbMois.addItem("juin");
        cbMois.addItem("juillet");
        cbMois.addItem("aout");
        cbMois.addItem("septembre");
        cbMois.addItem("octobre");
        cbMois.addItem("novembre");
        cbMois.addItem("decembre");
        
        vecEvEv = vctEvEv;
        vecFound = new Vector<EventEvent>();
        
        Vector v = new Vector();
        
        for (int i = 0; i < vecEvEv.size(); i++)
        {
            EventEvent ev = vecEvEv.elementAt(i);
            
            String sAnnee = ev.getDate().substring(ev.getDate().length() - 5, ev.getDate().length() - 1); // -1 Car un espace sauvage traine en fin de chaine
            int iAnnee = Integer.valueOf(sAnnee);
            
            if (!v.contains(iAnnee))
                v.add(iAnnee);
        }
        
        Enumeration e = v.elements();
        
        while (e.hasMoreElements())
            cbAnnee.addItem((Integer)e.nextElement());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbChoisirDateIncident = new javax.swing.JLabel();
        cbJour = new javax.swing.JComboBox();
        cbMois = new javax.swing.JComboBox();
        cbAnnee = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        listIncidents = new javax.swing.JList();
        btCreationRapport = new javax.swing.JButton();
        btAnnuler = new javax.swing.JButton();
        btValider = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbChoisirDateIncident.setText("Veuillez choisir la date de l'incident: ");

        listIncidents.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listIncidentsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listIncidents);

        btCreationRapport.setText("Créer un rapport");
        btCreationRapport.setEnabled(false);
        btCreationRapport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreationRapportActionPerformed(evt);
            }
        });

        btAnnuler.setText("Annuler");
        btAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnnulerActionPerformed(evt);
            }
        });

        btValider.setText("Valider la date");
        btValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btValiderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btCreationRapport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btAnnuler))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbChoisirDateIncident)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbJour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbMois, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btValider)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbChoisirDateIncident)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbJour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMois, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btValider))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCreationRapport)
                    .addComponent(btAnnuler))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btValiderActionPerformed
        vecFound.removeAllElements();
        String Date = " " + cbJour.getSelectedItem().toString() + " " + cbMois.getSelectedItem().toString() + " " + cbAnnee.getSelectedItem().toString();
        System.out.println("Date : " + Date);
        for (int i = 0; i < vecEvEv.size(); i++)
        {
            if (vecEvEv.get(i).toString().contains(Date))
                vecFound.add(vecEvEv.get(i));
        }
        
        if (vecFound.isEmpty())
            JOptionPane.showMessageDialog(null, "Aucun incident n'a eu lieu à cette date !");
        else
        {
            listIncidents.removeAll();
            DefaultListModel DLM = new DefaultListModel();
            Enumeration e = vecFound.elements();
            
            while (e.hasMoreElements())
                DLM.addElement((EventEvent)e.nextElement());
            
            listIncidents.setModel(DLM);
        }
    }//GEN-LAST:event_btValiderActionPerformed

    private void btCreationRapportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreationRapportActionPerformed
        FenCreationRapportIncident FCRI = new FenCreationRapportIncident(null, true);
        
        /**************************************************************************/
        
        // Cette partie va permettre de récupérer et affecter une heure "correcte".
        // Le format de l'heure est HH:MM:SS, mais impossible d'enregistrer sur Windows les : dans un nom de fichier/dossier
        // Le "nouveau" format de l'heure sera HH-MM-SS
        String Heure = vecFound.get(listIncidents.getSelectedIndex()).getHeure();
        String[] tab = Heure.split(":");
        String newHeure = tab[0] + "-" + tab[1] + "-" + tab[2];
        /**************************************************************************/
        
        FCRI.setNomFichier(vecFound.get(listIncidents.getSelectedIndex()).getNature() + "-" + vecFound.get(listIncidents.getSelectedIndex()).getDate() + newHeure + ".txt");
        FCRI.setVisible(true);
        
        if (FCRI.isSave() == true)
        {
            System.out.println("size vecEvent(avant) > "+vecEvEv.size()); // ?? Quand on enlèves les 2println, ça ne fonctionne plus
            vecEvEv.remove(vecFound.get(listIncidents.getSelectedIndex()));
            System.out.println("size vecEvent(Apres) > "+vecEvEv.size());
            AlarmBeanSerializable ABS = new AlarmBeanSerializable();
            ABS.setVecEvEv(vecEvEv);
            ABS.Serialize(); // On supprime l'incident du fichier, car le rapport a été fait.
            this.dispose();
        }
    }//GEN-LAST:event_btCreationRapportActionPerformed

    private void btAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnnulerActionPerformed
        this.dispose();
    }//GEN-LAST:event_btAnnulerActionPerformed

    private void listIncidentsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listIncidentsValueChanged
        btCreationRapport.setEnabled(true);
    }//GEN-LAST:event_listIncidentsValueChanged

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
            java.util.logging.Logger.getLogger(FenRechercheIncident.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenRechercheIncident.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenRechercheIncident.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenRechercheIncident.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FenRechercheIncident dialog = new FenRechercheIncident(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btAnnuler;
    private javax.swing.JButton btCreationRapport;
    private javax.swing.JButton btValider;
    private javax.swing.JComboBox cbAnnee;
    private javax.swing.JComboBox cbJour;
    private javax.swing.JComboBox cbMois;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbChoisirDateIncident;
    private javax.swing.JList listIncidents;
    // End of variables declaration//GEN-END:variables
}
