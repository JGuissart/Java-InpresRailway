/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesVehicule;

/**
 *
 * @author Julien
 */
public class Wagon extends VehiculeRail
{
    private int Longueur;
    private String Type;
    
    public Wagon()
    {
        super();
        Longueur = 0;
        Type = "default";
    }
    
    public Wagon(String id, int ames, int l, String t)
    {
        super(id, ames);
        Longueur = l;
        if (t.equals("MA") || t.equals("VO"))
            Type = t;
        
        // Exception mauvais type de wagon
    }
    
    @Override
    public String toString()
    {
        return super.toString() + "Longueur: " + Longueur + " | Type: " + Type;
    }

    /**
     * @return the Longueur
     */
    public int getLongueur() {
        return Longueur;
    }

    /**
     * @return the Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Longueur the Longueur to set
     */
    public void setLongueur(int Longueur) {
        this.Longueur = Longueur;
    }

    /**
     * @param Type the Type to set
     */
    public void setType(String Type) {
        if (Type.equals("MA") || Type.equals("VO"))
            this.Type = Type;
    }
}
