/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import Authenticate.CritereLP;
import Authenticate.User;
import java.util.HashMap;

/**
 *
 * @author Julien
 */
public class CritereLPArray extends CritereLP
{
    private HashMap HM;
    
    public CritereLPArray()
    {
        super();
    }
    
    public CritereLPArray(String l, String mdp)
    {
        super(l, mdp);
    }
    
    public CritereLPArray(String l, String mdp, HashMap HMUsers)
    {
        super(l, mdp);
        HM = new HashMap(HMUsers);
    }

    @Override
    public String findMdp(String log)
    {
        User u = new User();
        
        u = (User)HM.get(log);
        
        if (u != null)
            return u.getMotDePasse();
        else
            return "MDP pas trouve ...";
    }
    
    public String findLog(String log)
    {
        User u = new User();
        
        u = (User)HM.get(log);
        
        if (u != null)
            return u.getIdentifiant();
        else
            return "Login pas trouve ...";
    }
}
