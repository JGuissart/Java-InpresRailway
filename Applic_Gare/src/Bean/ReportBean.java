/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import InterfaceGraphique.FenIncident;
import Outils.FichierLog;
import java.io.Serializable;

/**
 *
 * @author Julien
 */
public class ReportBean implements EventEventListener, Serializable
{
    private EventEvent EvEv;
    private FichierLog FL;

    public ReportBean() 
    {
        
    }
    
    public void run()
    {
        FenIncident FI = new FenIncident(null, true);
        FI.setEE(getEvEv());
        FI.setFL(FL);
        FI.setVisible(true);
    }

    @Override
    public String toString() 
    {
        if (getEvEv().getNature().equals("RETARD"))
            return getEvEv().getNature() + " le " + getEvEv().getDate() + " à " + getEvEv().getHeure() + " avec " + getEvEv().getMinRetard() + " min de retard";
        else
            return getEvEv().getNature() + " le " + getEvEv().getDate() + " à " + getEvEv().getHeure();
    }
    
    @Override
    public void EventEventReceive(EventEvent Ev)
    {
        setEvEv(Ev);
        this.run();
    }

    /**
     * @return the EvEv
     */
    public EventEvent getEvEv() {
        return EvEv;
    }

    /**
     * @return the FL
     */
    public FichierLog getFL() {
        return FL;
    }

    /**
     * @param EvEv the EvEv to set
     */
    public void setEvEv(EventEvent EvEv) {
        this.EvEv = EvEv;
    }

    /**
     * @param FL the FL to set
     */
    public void setFL(FichierLog FL) {
        this.FL = FL;
    }
}
