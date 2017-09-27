/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesVehicule;

/**
 *
 * @author Julien
 */
public class Locomotive extends VehiculeRail
{
    private int Puissance;
    
    public Locomotive()
    {
        super();
        Puissance = 0;
    }
    
    public Locomotive(String numId, int annee, int p)
    {
        super(numId, annee);
        Puissance = p;
    }
    
    @Override
    public String toString() 
    {
        return super.toString() + Puissance;
    }

    /**
     * @return the Puissance
     */
    public int getPuissance() {
        return Puissance;
    }

    /**
     * @param Puissance the Puissance to set
     */
    public void setPuissance(int Puissance) {
        this.Puissance = Puissance;
    }
}
