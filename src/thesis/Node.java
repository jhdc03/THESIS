
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
    this.att = new NodeAttributes(att.id, x, y, att.range, att.energy, att.isPromiscuous);
  }
  public void setRange(int range) {
    this.att = new NodeAttributes(att.id, att.x, att.y, range, att.energy, att.isPromiscuous);
  }
  public boolean isPromiscuous() {
    return this.att.isPromiscuous;
  }

  public void setPromiscuity(boolean value) {
    this.att = new NodeAttributes(this.att.id, this.att.x, this.att.y, this.att.range, this.att.energy, value);
  }

}