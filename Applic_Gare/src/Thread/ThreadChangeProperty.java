/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Thread;

import ClassesVehicule.HoraireTrain;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julien
 */
public class ThreadChangeProperty extends Thread
{
    private JTextField tfIn;
    private JButton btOut;
    private JButton btHangar;
    private JTable Table;
    private HoraireTrain HT;
    private String Retard;
    
    public ThreadChangeProperty()
    {
        
    }
    
    public ThreadChangeProperty(JTextField tf, JButton btH, JButton btO, JTable tbl, HoraireTrain ht, String R)
    {
        tfIn = tf;
        btOut = btO;
        btHangar = btH;
        Table = tbl;
        HT = ht;
        Retard = R;
    }
    
    public void run()
    {
        int i = 0;
        
        do
        {
            System.out.println(tfIn.getText()); // Plantage si pas de println ... Mais pourquoi ????
            if (tfIn.getText().equals("ACK"))
                i = 1;
            else
            {
                if (i == 1)
                {
                    if (tfIn.getText().equals("Train passé!"))
                    {
                        DefaultTableModel md = (DefaultTableModel)Table.getModel();
                        
                        /* Si il n'y a pas d'identifiant OU si le train n'est pas présent dans la gare, on entre */
                        if (md.getValueAt(HT.getQuai() - 1, 1) == null || md.getValueAt(HT.getQuai() - 1, 4).equals(false))
                        {
                            // Objet à placer dans la table - N° ligne ("quai - 1" car ligne commence à 0) - N° colonne
                            md.setValueAt(HT.getQuai(), HT.getQuai() - 1, 0);
                            md.setValueAt(HT.getTrain().getIdentifiant(), HT.getQuai() - 1, 1);
                            md.setValueAt(HT.getHeureArrivee(), HT.getQuai() - 1, 2);
                            md.setValueAt(HT.getHeureDepart(), HT.getQuai() - 1, 3);
                            md.setValueAt(true, HT.getQuai() - 1, 4);
                            if (Retard == null) // Va permettre d'affecter (ou non) les minutes de retard aux trains qui arrivent en gare
                                md.setValueAt(0 ,HT.getQuai() - 1, 5);
                            else
                                md.setValueAt(Retard, HT.getQuai() - 1, 5);

                            btOut.setEnabled(true);
                            btHangar.setEnabled(true);
                        }
                        else
                            System.out.println("Quai non disponnible");
                        i = 2;
                    }
                }
            }
        }
        while(i < 2);
    }
}
