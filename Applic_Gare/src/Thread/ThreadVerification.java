/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Thread;

import ClassesVehicule.HoraireTrain;
import ClassesVehicule.Train;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julien
 */
public class ThreadVerification extends Thread
{
    private JTextField tfOut;
    private JButton btTrainSuivant;
    private JComboBox cbTrainsArrives;
    private HoraireTrain HT;
    private JTable Table;
    private LinkedList<Train> llTrainParti;
    
    public ThreadVerification()
    {
        
    }
    
    public ThreadVerification(JTextField tf, JButton bt, JComboBox cb, HoraireTrain ht, JTable tbl, LinkedList<Train> llTrParti)
    {
        tfOut = tf;
        btTrainSuivant = bt;
        cbTrainsArrives = cb;
        HT = ht;
        Table = tbl;
        llTrainParti = llTrParti;
    }
    
    @Override
    public void run()
    {
        int i = 0;
        
        do
        {
            System.out.println(tfOut.getText()); // Plantage sinon
            if (tfOut.getText().equals("ACK"))
                i = 1;
            else
            {
                if (i == 1)
                {
                    if (tfOut.getText().equals("Train passé!"))
                    {
                        btTrainSuivant.setEnabled(true);
                        cbTrainsArrives.addItem(HT.toString());
                        i = 2;
                        DefaultTableModel md = (DefaultTableModel)Table.getModel();
                        md.setValueAt(false, HT.getQuai() - 1, 4); // Train partis
                        llTrainParti.add(HT.getTrain());
                    }
                }
            }
        }
        while(i < 2);
    }

    /**
     * @return the llTrainParti
     */
    public LinkedList<Train> getLlTrainParti() {
        return llTrainParti;
    }

    /**
     * @param llTrainParti the llTrainParti to set
     */
    public void setLlTrainParti(LinkedList<Train> llTrainParti) {
        this.llTrainParti = llTrainParti;
    }
}
