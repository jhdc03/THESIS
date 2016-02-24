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
public class NodeAttributes {
    
  public final String id;
  public final int x;
  public final int y;
  public final int range;
  public final boolean isPromiscuous;

  public NodeAttributes(String id, int x, int y, int range, boolean isPromiscuous) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.range = range;
    this.isPromiscuous = isPromiscuous;
  }
 
  //Copy constructor
  public NodeAttributes(NodeAttributes ni) {
    this(ni.id, ni.x, ni.y, ni.range, ni.isPromiscuous);
  }

  // Hide the no arg constructor.
  @SuppressWarnings("unused")
  private NodeAttributes() {
    //Put values in so compiler doesn't complain
    this.id = "";
    this.x = 0;
    this.y = 0;
    this.range = 0;
    this.isPromiscuous = false;
  }
    
}
