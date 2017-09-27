/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julien
 */

@SuppressWarnings("FieldMayBeFinal")
public class AlarmBeanSerializable 
{
    private EventEvent EvEv;
    private Vector<EventEvent> vecEvEv;
    private FileOutputStream FOS;
    private String sep;
    private String cheminFichier;
    
    public AlarmBeanSerializable()
    {
        sep = System.getProperty("file.separator");
        cheminFichier = System.getProperty("user.home") + sep + "Documents" + sep + "NetBeansProjects" + sep + "Applic_Gare" + sep + "AlarmBeanVector.dat";
        
        vecEvEv = new Vector<EventEvent>();
        LoadEventEvent();
    }
    
    private void LoadEventEvent()
    {
        try 
        {
            FileInputStream FIS = new FileInputStream(cheminFichier);
            ObjectInputStream OIS;
            try 
            {
                OIS = new ObjectInputStream(FIS);
                try 
                {
                    vecEvEv = (Vector<EventEvent>)OIS.readObject();
                } 
                catch (ClassNotFoundException ex) 
                {
                    System.out.println("ABSerialize Error ClassNot Found > " + ex.getMessage());
                }
                OIS.close();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(AlarmBeanSerializable.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) 
        {
            System.out.println("Aucun incident à charger");
        }
    }
    
    public void Serialize()
    {
        try
        {
            FOS = new FileOutputStream(cheminFichier);
            ObjectOutputStream OOS = new ObjectOutputStream(FOS);
            OOS.writeObject(getVecEvEv());
            OOS.flush();
            OOS.close();
        }
        catch(NotSerializableException e)
        {
            System.out.println("Composante non Serializable ! : " + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return the EvEv
     */
    public EventEvent getEvEv() {
        return EvEv;
    }

    /**
     * @return the vecEvEv
     */
    public Vector<EventEvent> getVecEvEv() {
        return vecEvEv;
    }

    /**
     * @return the FOS
     */
    public FileOutputStream getFOS() {
        return FOS;
    }

    /**
     * @param EvEv the EvEv to set
     */
    public void setEvEv(EventEvent EvEv) {
        this.EvEv = EvEv;
        vecEvEv.add(EvEv);
    }

    /**
     * @param vecEvEv the vecEvEv to set
     */
    public void setVecEvEv(Vector<EventEvent> vecEvEv) {
        this.vecEvEv = vecEvEv;
    }

    /**
     * @param FOS the FOS to set
     */
    public void setFOS(FileOutputStream FOS) {
        this.FOS = FOS;
    }
}
