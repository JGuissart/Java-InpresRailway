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
public class ThreadAttenteReponse extends Thread
{
    private int Port;
    private JTextField tfIn;
    
    public ThreadAttenteReponse()
    {
        Port = 0;
    }
    
    public ThreadAttenteReponse(int P, JTextField tf)
    {
        Port = P;
        tfIn = tf;
    }
    
    public void run()
    {
        NetworkStringReceiver NSR;
        String sMessage;
        
        NSR = new NetworkStringReceiver(Port);
        
        do
        {
            try 
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(ThreadAttenteReponse.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            sMessage = NSR.getMessage();
            
            if (!sMessage.equals("RIEN"))
                tfIn.setText(sMessage);
        }
        while(true);
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
