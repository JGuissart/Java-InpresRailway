/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Outils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Julien
 */

@SuppressWarnings("FieldMayBeFinal")
public class FichierLog 
{
    private String nomFichier;
    private String sep;
    private String cheminFichier;

    public FichierLog(String str)
    {
        nomFichier = str;
        sep = System.getProperty("file.separator");
        cheminFichier = System.getProperty("user.home") + sep + "Documents" + sep + "NetBeansProjects" + sep + "Applic_Gare" + sep + "ressources" + sep + str;
    }

    public void ecritLigne(String line)
    {
        String Date;
        Date Now = new Date();
        Date = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.FRANCE).format(Now);

        try
        {
            FileWriter fW = new FileWriter(cheminFichier, true); // true => écriture à la fin du fichier
            BufferedWriter buffW = new BufferedWriter(fW);

            buffW.write("[" + Date + "] : " + line);
            buffW.newLine();
            buffW.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public String litFichier()
    {   
        String sContent = null;
        
        try
        {
            FileReader fR = new FileReader(cheminFichier);
            BufferedReader buffR = new BufferedReader(fR);

            String temp = buffR.readLine();
            
            int i = 0;
            while (temp != null)
            {
                if (i == 0)
                    sContent = temp + "\n";
                else
                    sContent += temp + "\n";
                
                temp = buffR.readLine();
                i++;
            }
                
            buffR.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        
        return sContent;
    }

    /**
     * @return the nomFichier
     */
    public String getNomFichier() {
        return nomFichier;
    }

    /**
     * @return the sep
     */
    public String getSep() {
        return sep;
    }

    /**
     * @return the cheminFichier
     */
    public String getCheminFichier() {
        return cheminFichier;
    }

    /**
     * @param nomFichier the nomFichier to set
     */
    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    /**
     * @param sep the sep to set
     */
    public void setSep(String sep) {
        this.sep = sep;
    }

    /**
     * @param cheminFichier the cheminFichier to set
     */
    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }
}
