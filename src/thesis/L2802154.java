/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis;
import java.lang.Math;
import java.util.Random;
import javax.swing.JDialog;
import java.util.HashMap;
/**
 *
 * @author Arik
 */
public class L2802154 extends Node{
    int MinBE= 3;
    int MaxBE= 5;
    int waittime = 320;
    int MaxBackoff=4;
    int MaxRetry = 5;
    int BE=3; //backoff exponent
    int NB=0; //number of successive backoffs before the current transmission. 
    int timeslot=0;
    Random random = new Random();
    public L2802154(NodeAttributes atts) {
    super(atts);
  }
  
  private L2802154() { super(null); };
  
  
    public int getBackoffTime(){
        
        if(this.getBackoff()<=MaxBE && this.getNB()<=MaxBackoff){
        int maxVal = (int)(Math.pow(2,(double)this.getBackoff()) - 1);
        timeslot= (random.nextInt(maxVal - 0 + 1) + 0)*10; // to interpret float values to integer due to time integer 
        return timeslot;
        }
        else{
        return 0;
        }
    };
    
    public boolean CSMAallow(Medium medium){
    if(medium.isUsed()==false){
            medium.setUsed(true);
            this.setWaitACK(waittime);
            this.setStatACK(false);
            initialize();
            return true;
        }
    else{
    this.setNB(this.getNB()+1);
    this.setBackoff(this.getBackoff()+1);
        if(this.getNB()>MaxBackoff){
        initialize();
        }
    return false;
    }
    };

    void sendToMedium(Medium medium, Message message){
    medium.setMessage(message);
    
    
    }
    void initialize(){
    this.setBackoff(MinBE);
    this.setNB(0);
    this.setRetries(1);
    //if node reached max number of retries
    //any timers
    };

    public Message recieveData(Medium medium, Message message){
        return message;
        
    };
    public boolean waitAck(){
    int wait = this.getWaitACK();
    wait--;
    this.setWaitACK(wait);
    if(this.getWaitACK()==0){
        this.setWaitACK(waittime);
        this.setStatACK(false);
        return true;
    }
    else
        return false;
    };
    
    void sendACK(Medium medium){
         if(this.getRetries()<=MaxRetry && CSMAallow(medium)==true){
         medium.setStatACK(this.getStatACK());
         initialize();
         this.setStatACK(false);
         }
         else{
        this.setRetries(this.getRetries()+1);
         }
 
        }
    
    void recieveAck(){
    this.setWaitACK(waittime);
    this.setStatACK(false);
    };
    

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


