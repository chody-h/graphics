/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355.solution;

import cs355.GUIFunctions;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cody Heffner
 */
public class CS355 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
    	// Fill in the parameters below with your controller, view, 
    	//   mouse listener, and mouse motion listener
    	
    	MyCS355Controller contr = new MyCS355Controller();
    	MyViewRefresher vr = new MyViewRefresher();
    	MyMouseListener ml = new MyMouseListener();
    	MyMouseMotionListener mml = new MyMouseMotionListener();
    	
        GUIFunctions.createCS355Frame(contr,vr,ml,mml);
        
        GUIFunctions.refresh();        
    }
}