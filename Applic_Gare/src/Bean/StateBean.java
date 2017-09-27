/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

import Outils.FichierLog;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.DateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import threadsutils.ThreadRandomGenerator;
import threadsutils.UtilisateurNombre;

/**
 *
 * @author Julien
 */

@SuppressWarnings("FieldMayBeFinal")
public class StateBean implements UtilisateurNombre
{
    private String info;
    private String identifiant;
    private ArrayDeque FIFO;
    private Properties PropertyFile;
    private int TempsAttente;
    private int Retard;
    private int Manifestation;
    private int Greve;
    private ThreadRandomGenerator TRD;
    protected PropertyChangeSupport GestProp = new PropertyChangeSupport(this);
    private FichierLog FL;
    
    public StateBean()
    {
        TempsAttente = 15;
        Retard = 301;         //
        Manifestation = 301;  // Valeurs abérantes pour ne pas avoir d'incidents dés le lancement du StateBean (setPropertyFile)
        Greve = 301;          //
        
        TRD = new ThreadRandomGenerator(this, 1, 300, 3, TempsAttente);
        FIFO = new ArrayDeque();
    }
    
    public void run()
    {
        if (TRD != null)
            TRD.start();
    }
    
    @Override
    public String getIdentifiant() 
    {
        return identifiant;
    }

    @Override
    public void traiteNombre(int n)
    {
        Date Now = new Date();
        String Date = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.FRANCE).format(Now);
        int dr = (int) (1 + Math.random() * (200 - 1));
        System.out.println("TraiteNombre : " + dr);
        
        if (dr % Retard == 0) // Retard
        {
            int dr2 = (int) (5 + Math.random() * (30 - 5));
            setInfo("RETARD - " + dr2 + " - " + Date);
            FL.ecritLigne("Un retard de " + dr2 + " minutes est à prévoir");
            FIFO.add(Integer.toString(dr2));
        }
        else
        {
            if (dr % Manifestation == 0) // Manifestation
            {
                setInfo("MANIFESTATION - " + Date);
                FL.ecritLigne("Une manifestation a lieu dans la gare");
            }
            else
            {
                if (dr % Greve == 0) // Grève sauvage
                {
                    setInfo("GREVE - " + Date);
                    FL.ecritLigne("Une greve a lieu dans la gare");
                }
            }
        }
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l) // Ajouter un listener sur la propriété liée (reçoit une notif quand changement de valeur)s
    {
        GestProp.addPropertyChangeListener(l);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener l)
    {
        GestProp.removePropertyChangeListener(l);
    }

    /**
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(String newInfo) {
        GestProp.firePropertyChange("info", info, newInfo);
        this.info = newInfo;
    }

    /**
     * @return the FIFO
     */
    public ArrayDeque getFIFO() {
        return FIFO;
    }

    /**
     * @return the TempsAttente
     */
    public int getTempsAttente() {
        return TempsAttente;
    }

    /**
     * @return the Retard
     */
    public int getRetard() {
        return Retard;
    }

    /**
     * @return the Manifestation
     */
    public int getManifestation() {
        return Manifestation;
    }

    /**
     * @return the Greve
     */
    public int getGreve() {
        return Greve;
    }

    /**
     * @return the FichierLog
     */
    public FichierLog getFichierLog() {
        return FL;
    }

    /**
     * @param FIFO the FIFO to set
     */
    public void setFIFO(ArrayDeque FIFO) {
        this.FIFO = FIFO;
    }

    /**
     * @param TempsAttente the TempsAttente to set
     */
    public void setTempsAttente(int TempsAttente) {
        this.TempsAttente = TempsAttente;
        TRD.setTempsPause(TempsAttente);
    }

    /**
     * @param Retard the Retard to set
     */
    public void setRetard(int Retard) {
        this.Retard = Retard;
    }

    /**
     * @param Manifestation the Manifestation to set
     */
    public void setManifestation(int Manifestation) {
        this.Manifestation = Manifestation;
    }

    /**
     * @param Greve the Greve to set
     */
    public void setGreve(int Greve) {
        this.Greve = Greve;
    }

    /**
     * @param FichierLog the FichierLog to set
     */
    public void setFichierLog(FichierLog FichierLog) {
        this.FL = FichierLog;
    }

    /**
     * @return the PropertyFile
     */
    public Properties getPropertyFile() {
        return PropertyFile;
    }

    /**
     * @param PropertyFile the PropertyFile to set
     */
    public void setPropertyFile(Properties PropertyFile) {
        this.PropertyFile = PropertyFile;
        Retard = Integer.valueOf((String)PropertyFile.get("NumRetard"));
        Manifestation = Integer.valueOf((String)PropertyFile.get("NumManifestation"));
        Greve = Integer.valueOf((String)PropertyFile.get("NumGreve"));
    }
}
