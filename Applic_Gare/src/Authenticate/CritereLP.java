/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Authenticate;

/**
 *
 * @author Julien
 */
public abstract class CritereLP implements Critere
{
    String login;
    String mdpIn;
    
    public CritereLP()
    {
        login = "default";
        mdpIn = "Default";
    }
    
    public CritereLP(String log, String mdp)
    {
        login = log;
        mdpIn = mdp;
    }
    
    public abstract String findMdp(String log);

    @Override
    public boolean isOk()
    {
        if (mdpIn.equals(findMdp(login)))
            return true;
        else
            return false;
    }
}
