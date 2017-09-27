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
public class HoraireTrain implements Serializable
{
    /**
     * Le train qui va être géré
     * 
     * @see Train
     * @see HoraireTrain#getTrain()
     * @see HoraireTrain#setTrain(ClassesVehicule.Train)
     * 
     */
    private Train train;
    
    /**
     * L'heure à laquelle le train arrive en gare
     * @see HoraireTrain#getHeureArrivee() 
     * @see HoraireTrain#setHeureArrivee(java.lang.String) 
     * 
     */
    private String heureArrivee;
    
    /**
     * L'heure à laquelle le train quitte la gare
     * @see HoraireTrain#getHeureDepart() 
     * @see HoraireTrain#setHeureArrivee(java.lang.String) 
     * 
     */
    private String heureDepart;
    
    /**
     * Type de train
     *  -Voyageur (VO)
     *  -Marchandise (MA)
     * @see HoraireTrain#getType() 
     * @see HoraireTrain#setType(java.lang.String) 
     * 
     */
    private String type;
    
    /**
     * Ville de départ du train
     * @see HoraireTrain#getVilleDepart() 
     * @see HoraireTrain#setVilleDepart(java.lang.String) 
     * 
     */
    private String villeDepart;
    
    /**
     * Ville d'arrivée du train
     * @see HoraireTrain#getVilleDestination() 
     * @see HoraireTrain#setVilleDestination(java.lang.String) 
     * 
     */
    private String villeDestination;
    
    /**
     * Numéro de quai du train
     * @see HoraireTrain#getQuai() 
     * @see HoraireTrain#setQuai(int) 
     * 
     */
    private int quai;
    
    /**
     * Constructeurs par défaut de HoraireTrain.
     * 
     */
    
    public HoraireTrain() 
    {
        train = new Train();
        heureArrivee = "default";
        heureDepart = "default";
        type = "default";
        villeDepart = "default";
        villeDestination = "default";
        quai = 0;
    }
    
    /**
     * Constructeur d'initialisation de HoraireTrain.
     * 
     * @param Tr
     *          Le train que l'on affecte à HoraireTrain
     * @param HA
     *          L'heure d'arrivée
     * @param HD
     *          L'heure de départ
     * @param Ty
     *          Le type de train
     * @param VDep
     *          La ville de départ
     * @param VDest
     *          La ville d'arrivée
     * @param q 
     *          Le quai
     */
    
    public HoraireTrain(Train Tr, String HA, String HD, String Ty, String VDep, String VDest, int q)
    {
        train = Tr;
        heureArrivee = HA;
        heureDepart = HD;
        type = Ty;
        villeDepart = VDep;
        villeDestination = VDest;
        quai = q;
    }
    
    /**
     * Formate l'affichage d'un HoraireTrain
     * @return l'affichage formatée d'un HoraireTrain sous forme de chaine de caractères
     * 
     */
    @Override
    public String toString()
    {
        return train.getIdentifiant() + " : " + getHeureArrivee() + " - " + getHeureDepart() + " : " + getType() + " " + getVilleDepart() + " -> " + getVilleDestination() + " = " + getQuai();
    }

    /**
     * Retourne le train spécifié
     * @return Le train affecté à cet HoraireTrain
     * 
     */
    public Train getTrain() {
        return train;
    }

    /**
     * Retourne l'heure d'arrivée du train
     * @return L'heure d'arrivée du train affecté à cet HoraireTrain
     * 
     */
    public String getHeureArrivee() {
        return heureArrivee;
    }

    /**
     * Retourne l'heure de départ du train
     * @return L'heure de départ du train affecté à cet HoraireTrain
     * 
     */
    public String getHeureDepart() {
        return heureDepart;
    }

    /**
     * Retourne le type du train
     * @return Le type du train affecté à cet HoraireTrain
     * 
     */
    public String getType() {
        return type;
    }

    /**
     * Retourne la ville de départ du train
     * @return La ville de départ du train affecté à cet HoraireTrain
     */
    public String getVilleDepart() {
        return villeDepart;
    }

    /**
     * Retourne la ville d'arrivée du train
     * @return La ville d'arrivée du train affecté à cet HoraireTrain
     * 
     */
    public String getVilleDestination() {
        return villeDestination;
    }

    /**
     * Retourne le quai où sera stationné le train
     * @return Le quai où sera stationné le train affecté à cet HoraireTrain
     */
    public int getQuai() {
        return quai;
    }

    /**
     * Met à jour le train affecté
     * @param train Le nouveau train à affecter
     */
    public void setTrain(Train train) {
        this.train = train;
    }

    /**
     * Met à jour l'heure d'arrivée
     * @param heureArrivee La nouvelle heure d'arrivée
     */
    public void setHeureArrivee(String heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    /**
     * Met à jour l'heure de départ
     * @param heureDepart La nouvelle heure de départ
     */
    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    /**
     * Met à jour le type du train
     * @param type Le nouveau type du train
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Met à jour la ville de départ du train
     * @param villeDepart La nouvelle ville de départ du train
     */
    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    /**
     * Met à jour la ville d'arrivée du train
     * @param villeDestination La nouvelle ville d'arrivée du train
     */
    public void setVilleDestination(String villeDestination) {
        this.villeDestination = villeDestination;
    }

    /**
     * Met à jour le numéro de quai du train
     * @param quai Le nouveau numéro de quai du train
     */
    public void setQuai(int quai) {
        this.quai = quai;
    }    
}
