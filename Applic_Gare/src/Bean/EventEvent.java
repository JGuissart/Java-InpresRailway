/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import java.io.Serializable;

/**
 *
 * @author Julien
 */

@SuppressWarnings("FieldMayBeFinal")
public class EventEvent implements Serializable
{
    private String date;
    private String heure;
    private String nature;
    private String minRetard;
    
    public EventEvent()
    {
        date = "Default";
        heure = "Default";
        nature = "Default";
        minRetard = "0";
    }
    
    public EventEvent(String D, String N) // D contient date + heure (19 mai 2014 02:34:17 par exemple)
    {
        date = D.substring(0, D.length() - 8);
        heure = D.substring(D.length() - 8, D.length());
        nature = N;
    }
    
    public EventEvent(String D, String N, String M)
    {
        date = D.substring(0, D.length() - 8);
        heure = D.substring(D.length() - 8, D.length());
        nature = N;
        minRetard = M;
    }
    
    @Override
    public String toString() 
    {
        if (getNature().equals("RETARD"))
            return getNature() + " le " + getDate() + " à " + getHeure() + " avec " + getMinRetard() + " min de retard";
        else
            return getNature() + " le " + getDate() + " à " + getHeure();
    }

    /**
     * @return the Date
     */
    public String getDate() {
        return date;
    }

    /**
     * @return the Heure
     */
    public String getHeure() {
        return heure;
    }

    /**
     * @return the nature
     */
    public String getNature() {
        return nature;
    }

    /**
     * @return the MinRetard
     */
    public String getMinRetard() {
        return minRetard;
    }

    /**
     * @param Date the Date to set
     */
    public void setDate(String Date) {
        this.date = Date;
    }

    /**
     * @param Heure the Heure to set
     */
    public void setHeure(String Heure) {
        this.heure = Heure;
    }

    /**
     * @param nature the nature to set
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /**
     * @param MinRetard the MinRetard to set
     */
    public void setMinRetard(String MinRetard) {
        this.minRetard = MinRetard;
    }
}