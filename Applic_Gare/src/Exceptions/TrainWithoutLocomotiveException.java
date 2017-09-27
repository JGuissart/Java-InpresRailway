/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Julien
 */
public class TrainWithoutLocomotiveException extends Exception
{
    //private String Message;
    
    public TrainWithoutLocomotiveException()
    {
        super("Vous essayez de construire un train sans locomotive ...");
        //Message = "Vous essayez de construire un train sans locomotive ...";
    }
    
    public TrainWithoutLocomotiveException(String m)
    {
        super(m);
        //Message = m;
    }

//    /**
//     * @return the Message
//     */
//    public String getMessage() {
//        return Message;
//    }
//
//    /**
//     * @param Message the Message to set
//     */
//    public void setMessage(String Message) {
//        this.Message = Message;
//    }
}
