/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Authenticate;

import java.io.Serializable;

/**
 *
 * @author Julien
 */
public class User implements Serializable
{
    private String identifiant;
    private String motDePasse;
    private String role;
    
    public User()
    {
        identifiant = "Admin";
        motDePasse = "Admin";
        role = "Admin";
    }
    
    public User(String id, String mdp, String r)
    {
        identifiant = id;
        motDePasse = mdp;
        role = r;
    }

    public String getIdentifiant()
    {
        return identifiant;
    }

    public void setIdentifiant(String id)
    {
        identifiant = id;
    }
    
    public void setRole(String r)
    {
        role = r;
    }

    public String getMotDePasse()
    {
        return motDePasse;
    }

    public void setMotDePasse(String mdp)
    {
        motDePasse = mdp;
    }
    
    public String getRole()
    {
        return role;
    }
    
    /* Méthodes */
    
    public String toString()
    {
        String r;
        
        if (role.equals("Admin"))
            r = "Administrateur";
        else
            r = "Chef de gare";
        
        return "Login: " + identifiant + " | Mot de passe: " + motDePasse + " | Role: " + r;
    }
}
