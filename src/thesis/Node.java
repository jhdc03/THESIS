/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis;

import javax.swing.JDialog;


/**
 *
 * @author harve
 */
public abstract class Node{

  /**
   * Attributes member
   */
  protected NodeAttributes att;

  /**
   * Node Constructor.
   * 
   * All nodes must have node attributes set.
   * 
   * @author kresss
   * 
   * @param atts
   *          Node Attributes
   */
  public Node(NodeAttributes atts) {
    this.setAttributes(atts);
  }

  /**
   * **************************************************************************
   * *** Abstract methods that must be implemented to define a new protocol.
   * **************************************************************************
   */

  /**
   * Pop a message of the node's transmit queue and return it.
   * 
   * This function is used to return a message off the transmit queue of a node
   * and return it for the simulation engine to consume. Effectively this is
   * used to simulate the transmittal of a message into the network.
   * 
   * When a protocol implements this function it should call the appropriate
   * DARSEvent for the message that is being sent. A message is either a Control
   * message or Narrative. Example follows:
   * 
   * OutputHandler.dispatch(DARSEvent.outControlMsgTransmitted(this.att.id,
   * message));
   * 
   * OutputHandler.dispatch(DARSEvent.outNarrMsgTransmitted(this.att.id,
   * message));
   * 
   * @author kresss
   * 
   * @return Message Message that is being sent into the network.
   */
  
  //test
  
  public abstract void addroute();
  //public abstract RouteEntry getroute();
  //
  public abstract Message messageToNetwork();

  /**
   * Push a message into the node's receive queue.
   * 
   * This function is used to deliver a message to a node. The message will be
   * placed into the nodes receive queue effectively the node is receiving the
   * message.
   * 
   * When a protocol implements this function it should call the appropriate
   * DARSEvent for the message that is being received. A message is either a
   * Control message or Narrative. Example follows:
   * 
   * OutputHandler.dispatch(DARSEvent.outControlMsgReceived(this.att.id,
   * message));
   * 
   * OutputHandler.dispatch(DARSEvent.outNarrMsgReceived(this.att.id, message));
   * 
   * @author kresss
   * 
   * @param message
   *          Message to be delivered to the node.
   * 
   */
  public abstract void messageToNode(Message message);

  /**
   * Send a narrative message from one node to another.
   * 
   * Narrative messages are messages that the user initiates. This method will
   * be called by the simulation engine to notify the node that the user
   * requests it send a message.
   * 
   * This method should build a new narrative message and place it on the
   * transmit message queue.
   * 
   * Upon successful processing of a narrative message if the node receiving the
   * message is the destination for the message an addition DARSEvent should be
   * sent.
   * 
   * OutputHandler.dispatch(DARSEvent.outMsgRecieved(MsgOrigID, MsgDestID,
   * MsgText));
   * 
   * @author kresss
   * 
   * @param sourceID
   * @param destinationID
   * @param messageText
   */
  public abstract void newNarrativeMessage(String sourceID,
      String destinationID, String messageText);
  
  /**
   * Process an iteration of this node.
   * 
   * This will do all the processing for a node's time interval.
   * 
   * @author kresss
   */
  public abstract void clockTick();
  
   /**
   * Return a JDialog that will be displayed by the GUI.
   * 
   * Each protocol must define this function so that the GUI can inspect the
   * nodes information.
   * 
   * @author kennylmay
   */
  public abstract JDialog getNodeDialog();

  /**
   * Update the previously returned JDialog with the latest information for a
   * node that will be showed to the GUI.
   * 
   * 
   * Each protocol must define this function so that the GUI can inspect the
   * nodes information.
   * 
   * @author kennylmay
   */
  public abstract void updateNodeDialog(JDialog dialog);

  /**
   * **************************************************************************
   * *** Standard Node methods. Additional protocols should not modify these.
   * **************************************************************************
   */

  /**
   * This function will return the attributes that are defined in the Node
   * class.
   * 
   * Note, this returns a copy of the node attributes. Not a reference to the
   * attributes object itself.
   * 
   * @return NodeAttributes
   */
  
  public NodeAttributes getAttributes() {
    return att;
  }

  /**
   * Sets the Node's attributes
   * 
   * @author mayk
   * 
   * @param atts
   *          The new attributes for the node.
   */
  public void setAttributes(NodeAttributes atts) {
    this.att = atts;
  }

  /**
   * Sets the X and Y coordinates of the node.
   * 
   * @param x
   *          The new x coordinate.
   * @param y
   *          The new y coordinate.
   */
  public void setXY(int x, int y) {
    this.att = new NodeAttributes(att.id, x, y, att.range, att.isPromiscuous);
  }

  /**
   * Sets the range of the node.
   * 
   * @param newRange
   */
  public void setRange(int range) {
    this.att = new NodeAttributes(att.id, att.x, att.y, range,
        att.isPromiscuous);
  }

  /**
   * Return true if the nodes is listen only.
   * 
   * @author kresss
   * 
   * @return True/False based on the nodes Promiscuity
   */
  public boolean isPromiscuous() {
    return this.att.isPromiscuous;
  }

  /**
   * Set whether or not a node is listen only.
   * 
   * @author kresss
   * 
   * @param value
   */
  public void setPromiscuity(boolean value) {
    this.att = new NodeAttributes(this.att.id, this.att.x, this.att.y,
        this.att.range, value);
  }

}