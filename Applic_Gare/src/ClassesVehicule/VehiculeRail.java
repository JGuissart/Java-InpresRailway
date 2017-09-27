/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesVehicule;

import java.io.Serializable;

/**
 *
 * @author Julien
 */
public class VehiculeRail implements Serializable
{
    /**
     * L'identifiant du VehiculeRail. Unique.
     * @see VehiculeRail#getNumeroIdentifiant() 
     * @see VehiculeRail#setNumeroIdentifiant(java.lang.String) 
     * 
     */
    private String numeroIdentifiant;
    
    /**
     * L'année de mise en service du VehiculeRail.
     * @see VehiculeRail#getAnneeMiseEnService() 
     * @see VehiculeRail#setAnneeMiseEnService(int) 
     * 
     */
    private int anneeMiseEnService;
    
    /**
     * Constructeur par défaut de VehiculeRail.
     * 
     */
    public VehiculeRail()
    {
        numeroIdentifiant = "Default";
        anneeMiseEnService = 1970;
    }
    
    /**
     * Constructeur d'initialisation de VehiculeRail
     * 
     * @param numId
     *          Numéro d'identifiant du VehiculeRail
     * @param annee 
     *          Année de mise en service du VehiculeRail
     */
    public VehiculeRail(String numId, int annee)
    {
        numeroIdentifiant = numId;
        anneeMiseEnService = annee;
    }

    /**
     * @return the numeroIdentifiant
     */
    public String getNumeroIdentifiant() {
        return numeroIdentifiant;
    }

    /**
     * @return the anneeMiseEnService
     */
    public int getAnneeMiseEnService() {
        return anneeMiseEnService;
    }
    
    @Override
    public String toString()
    {
        return "ID: " + numeroIdentifiant + " | Annee: " + anneeMiseEnService + " | ";
    }

    /**
     * @param numeroIdentifiant the numeroIdentifiant to set
     */
    public void setNumeroIdentifiant(String numeroIdentifiant) {
        this.numeroIdentifiant = numeroIdentifiant;
    }

    /**
     * @param anneeMiseEnService the anneeMiseEnService to set
     */
    public void setAnneeMiseEnService(int anneeMiseEnService) {
        this.anneeMiseEnService = anneeMiseEnService;
    }
}
