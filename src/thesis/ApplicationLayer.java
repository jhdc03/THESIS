
package thesis;
import java.util.NoSuchElementException;
import java.util.Random; 
import javax.swing.JDialog;



public class ApplicationLayer  extends Node {

    public ApplicationLayer(NodeAttributes atts) {
        super(atts);
    }
    
    public Message generateData() {
           
           Message mess = new Message("A", this.att.id, "Generated Data");

        return mess;
        //InputHandler.dispatch(EventManager.inInsertMessage(mess));
    }

    @Override
    public Message messageToNetwork() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void messageToNode(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void newNarrativeMessage(String sourceID, String destinationID, String messageText) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clockTick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JDialog getNodeDialog() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateNodeDialog(JDialog dialog) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
 
    
    }

    