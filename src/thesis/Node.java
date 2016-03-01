
package thesis;

import javax.swing.JDialog;


public abstract class Node{


  protected NodeAttributes att;

  public Node(NodeAttributes atts) {
    this.setAttributes(atts);
  }

  /**
   * **************************************************************************
   * *** Abstract methods that must be implemented to define a new protocol.
   * **************************************************************************
   */

  public abstract Message messageToNetwork();
  
  public abstract void messageToNode(Message message);
  
  public abstract void newNarrativeMessage(String sourceID,String destinationID, String messageText);
  
  public abstract void clockTick();
  
  public abstract JDialog getNodeDialog();
  
  public abstract void updateNodeDialog(JDialog dialog);
  
  public NodeAttributes getAttributes() {
    return att;
  }
  public void setAttributes(NodeAttributes atts) {
    this.att = atts;
  }
  public void setXY(int x, int y) {
    this.att = new NodeAttributes(this.att.id, x, y, this.att.range, this.att.energy, att.packetDrop, 
att.totalSent, att.totalReceived, att.isPromiscuous, att.retries, att.NB, att.backoff, att.state, att.role
, att.macadd, att.waitACK, att.ACK);
  }
  public void setRange(int range) {
    this.att = new NodeAttributes(this.att.id, this.att.x, this.att.y, range, this.att.energy, att.packetDrop, 
att.totalSent, att.totalReceived, att.isPromiscuous, att.retries, att.NB, att.backoff, att.state, att.role
, att.macadd, att.waitACK, att.ACK);
  }
  public boolean isPromiscuous() {
    return this.att.isPromiscuous;
  }
  public void setEnergy(double energy) {
    this.att = new NodeAttributes(this.att.id, this.att.x, this.att.y, this.att.range, energy, att.packetDrop, 
att.totalSent, att.totalReceived, att.isPromiscuous, att.retries, att.NB, att.backoff, att.state, att.role
, att.macadd, att.waitACK, att.ACK);
  }

  public void setPromiscuity(boolean value) {
    this.att = new NodeAttributes(this.att.id, this.att.x, this.att.y, this.att.range, this.att.energy, att.packetDrop, 
att.totalSent, att.totalReceived, value, att.retries, att.NB, att.backoff, att.state, att.role
, att.macadd, att.waitACK, att.ACK);
  }
  
   public void setpacketDrop(int packetDrop) {
    this.att = new NodeAttributes(this.att.id, this.att.x, this.att.y, this.att.range, this.att.energy, packetDrop, 
att.totalSent, att.totalReceived, att.isPromiscuous, att.retries, att.NB, att.backoff, att.state, att.role
, att.macadd, att.waitACK, att.ACK);
  }
   
   public void setTotalSent(int totalSent) {
    this.att = new NodeAttributes(this.att.id, this.att.x, this.att.y, this.att.range, this.att.energy, att.packetDrop, 
totalSent, att.totalReceived, att.isPromiscuous, att.retries, att.NB, att.backoff, att.state, att.role
, att.macadd, att.waitACK, att.ACK);
  }
   
   public void setTotalReceived(int totalReceived) {
    this.att = new NodeAttributes(this.att.id, this.att.x, this.att.y, this.att.range, this.att.energy, att.packetDrop, 
att.totalSent, totalReceived, att.isPromiscuous, att.retries, att.NB, att.backoff, att.state, att.role
, att.macadd, att.waitACK, att.ACK);
  }
   
   public void setRetries(int retries){
       this.att = new NodeAttributes(this.att.id, this.att.x, this.att.y, this.att.range, this.att.energy, att.packetDrop, 
att.totalSent, att.totalReceived, att.isPromiscuous, retries, att.NB, att.backoff, att.state, att.role
, att.macadd, att.waitACK, att.ACK);
   }
   
   public void setNB(int NB){
       this.att = new NodeAttributes(this.att.id, this.att.x, this.att.y, this.att.range, this.att.energy, att.packetDrop, 
att.totalSent, att.totalReceived, att.isPromiscuous, att.retries, NB, att.backoff, att.state, att.role
, att.macadd, att.waitACK, att.ACK);
   }
   
   
   public void setBackoff(int backoff){
       this.att = new NodeAttributes(this.att.id, this.att.x, this.att.y, this.att.range, this.att.energy, att.packetDrop, 
att.totalSent, att.totalReceived, att.isPromiscuous, att.retries, att.NB, backoff, att.state, att.role
, att.macadd, att.waitACK, att.ACK);
   }
   
   public void setState(int state){
       this.att = new NodeAttributes(this.att.id, this.att.x, this.att.y, this.att.range, this.att.energy, att.packetDrop, 
att.totalSent, att.totalReceived, att.isPromiscuous, att.retries, att.NB, att.backoff, state, att.role
, att.macadd, att.waitACK, att.ACK);
   }
   
   public void setRole(int role){
       this.att = new NodeAttributes(this.att.id, this.att.x, this.att.y, this.att.range, this.att.energy, att.packetDrop, 
att.totalSent, att.totalReceived, att.isPromiscuous, att.retries, att.NB, att.backoff, att.state, role
, att.macadd, att.waitACK, att.ACK);
   }
   
   public void setMacadd(String macadd){
       this.att = new NodeAttributes(this.att.id, this.att.x, this.att.y, this.att.range, this.att.energy, att.packetDrop, 
att.totalSent, att.totalReceived, att.isPromiscuous, att.retries, att.NB, att.backoff, att.state, att.role
, macadd, att.waitACK, att.ACK);
   }
   
   public void setWaitACK(int waitACK){
       this.att = new NodeAttributes(this.att.id, this.att.x, this.att.y, this.att.range, this.att.energy, att.packetDrop, 
att.totalSent, att.totalReceived, att.isPromiscuous, att.retries, att.NB, att.backoff, att.state, att.role
, att.macadd, waitACK, att.ACK);
   }
   
   public void setStatACK(boolean ACK){
       this.att = new NodeAttributes(this.att.id, this.att.x, this.att.y, this.att.range, this.att.energy, att.packetDrop, 
att.totalSent, att.totalReceived, att.isPromiscuous, att.retries, att.NB, att.backoff, att.state, att.role
, att.macadd, att.waitACK, ACK);
   }
   
   public int getRetries(){
   return this.att.retries;
   }
   
   public int getNB(){
   return this.att.NB;
   }
   
   
   public int getBackoff(){
   return this.att.backoff;
   }
   
   public int getState(){
   return this.att.state;
   }
   
   public int getRole(){
   return this.att.role;
   }
   
   public String getMacadd(){
   return this.att.macadd;
   }
   
   public int getWaitACK(){
   return this.att.waitACK;
   }
   
   public boolean getStatACK(){
   return this.att.ACK;
   }
}