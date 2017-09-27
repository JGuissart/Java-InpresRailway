/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesVehicule;
import Exceptions.TrainWithoutLocomotiveException;
import java.util.Vector;
import java.io.Serializable;

/**
 *
 * @author Julien
 */
public class Train implements Serializable
{
    /**
     * L'identifiant du train. Cet identifiant est unique.
     * @see Train#getIdentifiant() 
     * @see Train#setIdentifiant(java.lang.String) 
     * 
     */
    private String Identifiant;
    
    /**
     * La locomotive affectée à ce train
     * @see Locomotive
     * @see Train#getLoco() 
     * @see Train#setLoco(ClassesVehicule.Locomotive) 
     * 
     */
    private Locomotive Loco;
    
    /**
     * Le(s) wagon(s) affecté(s) à ce train
     * @see Wagon
     * @see Train#getVecWagon() 
     * @see Train#setVecWagon(java.util.Vector) 
     * 
     */
    private Vector<Wagon> vecWagon;
    
    /**
     * Constructeur par défaut de Train.
     * 
     */
    
    public Train()
    {
        Identifiant = "default";
        Loco = new Locomotive();
        vecWagon = new Vector<Wagon>();
    }
    
    /**
     * Constructeur d'initialisation de Train.
     * 
     * @param id
     *          L'identifiant du train construit.
     * @param locTrain
     *          La locomotive affectée à ce train.
     * @param vctWag
     *          Le vecteur de Wagon affecté à ce train.
     * @throws TrainWithoutLocomotiveException 
     *          Exception lancée si la Locomotive passée en paramètre est null
     */
    
    public Train(String id, Locomotive locTrain, Vector<Wagon> vctWag) throws TrainWithoutLocomotiveException
    {
        Identifiant = id;
        if (locTrain == null)
            throw new TrainWithoutLocomotiveException();
        else
            Loco = locTrain;
        vecWagon = vctWag;
    }
    
    /**
        * Formate l'affichage d'un Train
     * @return l'affichage formatée d'un Train sous forme de chaine de caractères
     * 
     */
    @Override
    public String toString()
    {
        return "Identifiant: " + Identifiant + " | Locomotive: " + Loco.getNumeroIdentifiant()+ " | Nombbre de wagons: " + vecWagon.size();
    }

    /**
     * Retourne l'identifiant du train
     * @return l'identifiant de ce Train
     */
    public String getIdentifiant() {
        return Identifiant;
    }

    /**
     * Retourne la locomotive du train
     * @return La locomotive affectée à ce train
     */
    public Locomotive getLoco() {
        return Loco;
    }

    /**
     * Retourne le vecteur de Wagons de ce train
     * @return Le vecteur de Wagon affecté à ce train
     */
    public Vector<Wagon> getVecWagon() {
        return vecWagon;
    }

    /**
     * Met à jour l'identifiant du train
     * @param Identifiant Le nouvel identifiant du train
     */
    public void setIdentifiant(String Identifiant) {
        this.Identifiant = Identifiant;
    }

    /**
     * Met à jour la locomotive du train
     * @param Loco La nouvelle locomotive du train
     */
    public void setLoco(Locomotive Loco) {
        this.Loco = Loco;
    }

    /**
     * Met à jour le vecteur de Wagon du train
     * @param vecWagon Le nouveau vecteur de Wagon du train
     */
    public void setVecWagon(Vector<Wagon> vecWagon) {
        this.vecWagon = vecWagon;
    }
}
