/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis;

/**
 *
 * @author harve
 */
public class THESIS {
    
    public THESIS(){}
    /**
     * @param args the command line arguments
     */
   
    
    public static void main(String[] args) {
        //InputHandler.addInputConsumer(Logger.getInstance());
        //OutputHandler.addOutputConsumer(Logger.getInstance());
        // Instantiate the simulator engine
        SimEngine s = new SimEngine();
        
        // Make the time keeping component of the simulator engine viewable to EventManager
        EventManager.setSimTime( (SimTime) s);
        
        // Name the simulator engine as an input consumer
        InputHandler.addInputConsumer(s);
        
        GUI g = new GUI();
        
        // Setup the node inpsector for the gui. This gives the gui a backdoor into the 
        // simulation, where it can view node attributes
        g.setNodeInspector(s);
    
        OutputHandler.addOutputConsumer(g);
        s.start();
        g.setVisible(true);
        
    }
    

     
}
