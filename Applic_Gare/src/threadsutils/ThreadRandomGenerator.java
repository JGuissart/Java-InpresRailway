package threadsutils;
/**
* @author Vilvens
*/

@SuppressWarnings("FieldMayBeFinal")
public class ThreadRandomGenerator extends java.lang.Thread
{
    private UtilisateurNombre utilisateurThread;
    private int borneInferieure, borneSuperieure, multipleDeclenchement, tempsPause;
    private int nombreProduit;
    public ThreadRandomGenerator(UtilisateurNombre un, int bi, int bs, int md, int tp)
    {
        utilisateurThread = un; 
        borneInferieure=bi; 
        borneSuperieure=bs;
        multipleDeclenchement = md; 
        tempsPause = tp; 
        nombreProduit = -1;
    }
    public void run()
    {
        Double dr;
        while (true)
        {
            dr = new Double(getBorneInferieure() + Math.random()*(getBorneSuperieure() - getBorneInferieure()));
            nombreProduit = dr.intValue();
            System.out.println(utilisateurThread.getIdentifiant() + "> nombreProduit = " + nombreProduit);
            if (nombreProduit % getMultipleDeclenchement() == 0)
            {
                System.out.println(utilisateurThread.getIdentifiant() + "> -------------- !!!!!!! " + nombreProduit + "!!!!");
                utilisateurThread.traiteNombre(nombreProduit);
            }
            try
            {
                Thread.sleep(getTempsPause()*1000);
            }
            catch (InterruptedException e)
            {
                System.out.println("Erreur de thread interrompu : " + e.getMessage());
            }
        }
    }

    /**
     * @return the borneInferieure
     */
    public int getBorneInferieure() {
        return borneInferieure;
    }

    /**
     * @return the borneSuperieure
     */
    public int getBorneSuperieure() {
        return borneSuperieure;
    }

    /**
     * @return the multipleDeclenchement
     */
    public int getMultipleDeclenchement() {
        return multipleDeclenchement;
    }

    /**
     * @return the tempsPause
     */
    public int getTempsPause() {
        return tempsPause;
    }

    /**
     * @param borneInferieure the borneInferieure to set
     */
    public void setBorneInferieure(int borneInferieure) {
        this.borneInferieure = borneInferieure;
    }

    /**
     * @param borneSuperieure the borneSuperieure to set
     */
    public void setBorneSuperieure(int borneSuperieure) {
        this.borneSuperieure = borneSuperieure;
    }

    /**
     * @param multipleDeclenchement the multipleDeclenchement to set
     */
    public void setMultipleDeclenchement(int multipleDeclenchement) {
        this.multipleDeclenchement = multipleDeclenchement;
    }

    /**
     * @param tempsPause the tempsPause to set
     */
    public void setTempsPause(int tempsPause) {
        this.tempsPause = tempsPause;
    }
}