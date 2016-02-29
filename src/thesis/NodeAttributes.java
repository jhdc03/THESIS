
package thesis;


public class NodeAttributes {
    
  public final String id;
  public final int x;
  public final int y;
  public final int range;
  public final double energy;
  public final boolean isPromiscuous;

  public NodeAttributes(String id, int x, int y, int range, double energy, boolean isPromiscuous) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.range = range;
    this.energy= energy;
    this.isPromiscuous = isPromiscuous;
  }
 
  //Copy constructor
  public NodeAttributes(NodeAttributes ni) {
    this(ni.id, ni.x, ni.y, ni.range, ni.energy, ni.isPromiscuous);
  }

  // Hide the no arg constructor.
  @SuppressWarnings("unused")
  private NodeAttributes() {
    //Put values in so compiler doesn't complain
    this.id = "";
    this.x = 0;
    this.y = 0;
    this.range = 0;
    this.energy=0;
    this.isPromiscuous = false;
  }
    
}
