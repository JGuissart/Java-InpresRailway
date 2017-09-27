/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Outils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;

/**
 *
 * @author Julien
 */
public class Calendrier 
{
    private int Jour;
    private int Mois;
    private int Annee;
    private int Heure;
    private int Minute;

    /** Constructeur par defaut de Calendrier
     *
     */
    public Calendrier()
    {
        Calendar c = Calendar.getInstance();
        Jour = c.get(Calendar.DATE);
        Mois = c.get(Calendar.MONTH);
        Annee = c.get(Calendar.YEAR);
        Heure = c.get(Calendar.HOUR);
        Minute = c.get(Calendar.MINUTE);
    }

    /**
     *
     * @return La date actuelle sous forme Année-Mois-Jour
     */
    public String formaterDate()
    {
        String dateFormatee = + getAnnee() + "-" + getMois() + "-" + getJour();
        return dateFormatee;
    }

    /**
     *
     * @return La date formatee 
     */
    public String getDateHeure()
    {
        String date = null;
        try
        {
            String sep = System.getProperty("file.separator");
            String sPathFichier = System.getProperty("user.dir") + sep + "ressources" + sep + "Calendrier.properties";
            FileInputStream FIS = new FileInputStream(sPathFichier);
            Properties propLogFile = new Properties();
            propLogFile.load(FIS);
            String sLangue = propLogFile.getProperty("Langue");
            String sPays = propLogFile.getProperty("Pays");
            if(sLangue == null || sPays == null)
            {
                date = getJour() + "/" + getMois() + "/" + getAnnee() + " " + getHeure() + ":" + getMinute();
                return date;
            }
            else
            {
                Locale loc = new Locale(sLangue, sPays);
                String sDateStyle = propLogFile.getProperty("dateStyle");
                int iDateStyle;
                if(sDateStyle.equals("FULL"))
                    iDateStyle = DateFormat.FULL;
                else if(sDateStyle.equals("SHORT"))
                    iDateStyle = DateFormat.SHORT;
                else if(sDateStyle.equals("LONG"))
                    iDateStyle = DateFormat.LONG;
                else
                    iDateStyle = DateFormat.MEDIUM;

                String sTimeStyle = propLogFile.getProperty("timeStyle");
                int iTimeStyle;
                if(sTimeStyle.equals("FULL"))
                    iTimeStyle = DateFormat.FULL;
                else if(sTimeStyle.equals("SHORT"))
                    iTimeStyle = DateFormat.SHORT;
                else if(sTimeStyle.equals("LONG"))
                    iTimeStyle = DateFormat.LONG;
                else
                    iTimeStyle = DateFormat.MEDIUM;

                Date Now = new Date();
                date = DateFormat.getDateTimeInstance(iDateStyle, iTimeStyle, loc).format(Now);
                return date;
            }
        }
        catch (IOException ex)
        {
            date = getJour() + "/" + getMois() + "/" + getAnnee() + " " + getHeure() + ":" + getMinute();
            return date;
        }      
    }

    /**
     * @return the Jour
     */
    public int getJour() {
        return Jour;
    }

    /**
     * @return the Mois
     */
    public int getMois() {
        return Mois;
    }

    /**
     * @return the Annee
     */
    public int getAnnee() {
        return Annee;
    }

    /**
     * @return the Heure
     */
    public int getHeure() {
        return Heure;
    }

    /**
     * @return the Minute
     */
    public int getMinute() {
        return Minute;
    }

    /**
     * @param Jour the Jour to set
     */
    public void setJour(int Jour) {
        this.Jour = Jour;
    }

    /**
     * @param Mois the Mois to set
     */
    public void setMois(int Mois) {
        this.Mois = Mois;
    }

    /**
     * @param Annee the Annee to set
     */
    public void setAnnee(int Annee) {
        this.Annee = Annee;
    }

    /**
     * @param Heure the Heure to set
     */
    public void setHeure(int Heure) {
        this.Heure = Heure;
    }

    /**
     * @param Minute the Minute to set
     */
    public void setMinute(int Minute) {
        this.Minute = Minute;
    }
}
