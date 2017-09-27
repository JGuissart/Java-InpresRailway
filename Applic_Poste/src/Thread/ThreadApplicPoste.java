/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Thread;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import network.NetworkStringReceiver;

/**
 *
 * @author Julien
 */
public class ThreadApplicPoste extends Thread
{
    private boolean ControleIn; // Si le choix de la comboBox est in (true) ou out (false)
    private JTextField tfAnnonce;
    private JTextField tfTrainConsidere;
    private int Port;
    
    public ThreadApplicPoste()
    {
        ControleIn = true;
    }
    
    public ThreadApplicPoste(JTextField tfA, JTextField tfTC)
    {
        tfAnnonce = tfA;
        tfTrainConsidere = tfTC;
    }
    
    public void run()
    {
        String sMessage;
        NetworkStringReceiver NSR;
        
        if (isControleIn())
            NSR = new NetworkStringReceiver(Port);
        else
            NSR = new NetworkStringReceiver(Port);
        
        do
        {
            try 
            {
                Thread.sleep(1000);
                sMessage = NSR.getMessage();
                
                if (!sMessage.equals("RIEN"))
                {
                    tfAnnonce.setText(sMessage.substring(0, 7));
                    tfTrainConsidere.setText(sMessage);
                }
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(ThreadApplicPoste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        while (true);
    }

    /**
     * @return the ControleIn
     */
    public boolean isControleIn() {
        return ControleIn;
    }

    /**
     * @param ControleIn the ControleIn to set
     */
    public void setControleIn(boolean ControleIn) {
        this.ControleIn = ControleIn;
    }

    /**
     * @return the Port
     */
    public int getPort() {
        return Port;
    }

    /**
     * @param Port the Port to set
     */
    public void setPort(int Port) {
        this.Port = Port;
    }
}
