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
        //ImageFactory.checkInit();
        SimEngine s = new SimEngine();
        s.start();
        GUIStarter gs = new GUIStarter();
        GUI g = new GUI();
        g.setNodeInspector(s);
        OutputHandler.addOutputConsumer(g);
        g.setVisible(true);
        
    }
    

     
}
