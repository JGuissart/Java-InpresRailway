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
public class NotANumberException extends Exception
{
    public NotANumberException()
    {
        super("Le champs doit être un numéro ...");
    }
    
    public NotANumberException(String m)
    {
        super(m);
    }
}
