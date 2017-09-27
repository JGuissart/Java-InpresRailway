/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Thread;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import network.NetworkStringReceiver;

/**
 *
 * @author Julien
 */

@SuppressWarnings("FieldMayBeFinal")
public class ThreadApplicDepot extends Thread
{
    private JTextField tfTrainConsidere;
    private JTextField tfAnnonce;
    private JTable tableTrain;
    private JComboBox cbVoie;
    private int Port;

    public ThreadApplicDepot(JTextField tfA, JTextField tfTC, JTable tbl, JComboBox cb, int p)
    {
        tfAnnonce = tfA;
        tfTrainConsidere = tfTC;
        tableTrain = tbl;
        cbVoie = cb;
        Port = p;
    }

    public void run()
    {
        String sMessage;
        NetworkStringReceiver NSR;

        NSR = new NetworkStringReceiver(Port);
        
        while (true)
        {
            try 
            {
                Thread.sleep(1000);
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(ThreadApplicDepot.class.getName()).log(Level.SEVERE, null, ex);
            }

            sMessage = NSR.getMessage();
            if(!sMessage.equals("RIEN"))
            {
                if (sMessage.charAt(0) == '*') // Entete * signifie qu'on envoie un numéro de train à supprimer
                {
                    System.out.println("* Message *");
                    
                    sMessage = sMessage.substring(1, sMessage.length());
                    
                    DefaultTableModel md = (DefaultTableModel)tableTrain.getModel();
                    
                    for (int i = 0; i < 6; i++)
                    {
                        if (md.getValueAt(i, 1) != null)
                        {
                            if (md.getValueAt(i, 1).equals(sMessage))
                            {
                                md.setValueAt(" ", i, 1);
                                md.setValueAt(" ", i, 2);
                                md.setValueAt(" ", i, 3);
                                cbVoie.addItem(i + 1);
                            }
                        }
                    }
                }
                else
                {
                    tfAnnonce.setText(sMessage.substring(0, 7));
                    tfTrainConsidere.setText(sMessage);
                }
            }
        }
    }
}
