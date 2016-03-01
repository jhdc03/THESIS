
package thesis;


public class NodeAttributes {
    
  public final String id;
  public final int x;
  public final int y;
  public final int range;
  public final double energy;
  public final int packetDrop;
  public final int totalSent ;
  public final int totalReceived;
  public final boolean isPromiscuous;

  public int retries;
    public int NB;
    public int backoff;
    public int state; //0-sleep, 1-active
    public int role;  //0-gateway, 1-FFD, 2-RFD
    public String macadd;
    public int waitACK;
    public boolean ACK;
  public NodeAttributes(String id, int x, int y, int range, double energy, int packetDrop, int totalSent, int totalReceived, boolean isPromiscuous,
          int retries, int NB, int backoff, int state, int role, String macadd, int waitACK, boolean ACK) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.range = range;
    this.energy = energy;
    this.packetDrop = packetDrop;
    this.totalSent = totalSent;
    this.totalReceived = totalReceived;
    this.isPromiscuous = isPromiscuous;
    this.retries = retries;
    this.NB = NB;
    this.backoff = backoff;
    this.state = state;
    this.role = role;
    this.macadd = macadd;
    this.waitACK = waitACK;
    this.ACK = ACK;
  }
 
  //Copy constructor
  public NodeAttributes(NodeAttributes ni) {
    this(ni.id, ni.x, ni.y, ni.range, ni.energy, ni.packetDrop, ni.totalSent, ni.totalReceived,ni.isPromiscuous, ni.retries, ni.NB
            , ni.backoff, ni.state, ni.role, ni.macadd, ni.waitACK, ni.ACK);
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
    this.packetDrop=0;
    this.totalSent=0;
    this.totalReceived=0;
    this.isPromiscuous = false;
  }
    
}
