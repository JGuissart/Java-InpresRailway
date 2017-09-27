/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Julien
 */

@SuppressWarnings("FieldMayBeFinal")
public class AlarmBean implements PropertyChangeListener, Serializable // Permet d'être prévenu du changement de propriété d'un bean
{
    private String InfoReceptionnee;
    private Vector<EventEventListener> EvEvL;
    private AlarmBeanSerializable ABS;
    
    public AlarmBean()
    {
        InfoReceptionnee = "Default";
        EvEvL = new Vector<EventEventListener>();
        ABS = new AlarmBeanSerializable(); // Permet la sérialisation
    } 
    
    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        EventEvent EV;
        System.out.println("AlarmBean > Nom de la propriété modifiée = " + evt.getPropertyName());
        
        if (evt.getNewValue().toString().contains("RETARD"))
        {
            String[] tab = evt.getNewValue().toString().split(" - ");
            EV = new EventEvent(tab[2], "RETARD", tab[1]);
        }
        else
        {
            if (evt.getNewValue().toString().contains("MANIFESTATION"))
            {
                String[] tab = evt.getNewValue().toString().split(" - ");
                EV = new EventEvent(tab[1], "MANIFESTATION");
            }
            else
            {
                String[] tab = evt.getNewValue().toString().split(" - ");
                EV = new EventEvent(tab[1], "GREVE");
            }
        }
        
        for (EventEventListener eel : EvEvL) // Prévenir tous les beans implémentant EventEventListener (dans ce cas-ci, 1 seul)
            eel.EventEventReceive(EV);
        
        ABS.setEvEv(EV);
        ABS.Serialize();
    }
    
    public void addListener(EventEventListener eel)
    {
        EvEvL.add(eel);
    }
    
    public Vector<EventEvent> getVecteurEvent()
    {
        System.out.println("getVec > " + ABS.getVecEvEv().size());
        return ABS.getVecEvEv();
    }

    /**
     * @return the InfoReceptionnee
     */
    public String getInfoReceptionnee() {
        return InfoReceptionnee;
    }

    /**
     * @return the EvEvL
     */
    public Vector<EventEventListener> getEvEvL() {
        return EvEvL;
    }

    /**
     * @param InfoReceptionnee the InfoReceptionnee to set
     */
    public void setInfoReceptionnee(String InfoReceptionnee) {
        this.InfoReceptionnee = InfoReceptionnee;
    }

    /**
     * @param EvEvL the EvEvL to set
     */
    public void setEvEvL(Vector<EventEventListener> EvEvL) {
        this.EvEvL = EvEvL;
    }
}
