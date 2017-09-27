/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Exceptions;

/**
 *
 * @author Julien
 */
public class NullOrEmptyFieldException extends Exception
{    
    public NullOrEmptyFieldException()
    {
        super("Un ou plusieurs champs du formulaire sont vides !");
    }
    
    public NullOrEmptyFieldException(String m)
    {
        super(m);
    }
}
