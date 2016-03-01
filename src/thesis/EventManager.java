
package thesis;

import java.lang.reflect.Field;
import thesis.NodeCreator.NodeType;

public class EventManager implements Comparable{               
    
    /*public void CreateEvent(long time) {
        this.time = time;
    }*/
    
    public boolean lessThan(Comparable y) {
        EventManager e = (EventManager) y;  // Will throw an exception if y is not an Event
        return this.time < e.time;
    }
    
    public void execute(SimEngine simulator, EventManager e){
        simulator.consumeInput(e);
        //System.out.println("Event : " + e.eventType + "The time is "+ time + "\n" );
        
    };
    
    
    private static String newline = System.getProperty("line.separator");

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  public enum EventType {
    // Input event types
    IN_ADD_NODE,IN_DEL_NODE,IN_SET_NODE_PROMISCUITY, IN_INSERT_MESSAGE,
    
    // Output event types
    OUT_ADD_NODE,OUT_MSG_TRANSMITTED, OUT_DEL_NODE, OUT_DEBUG, OUT_ERROR,OUT_INSERT_MESSAGE, OUT_NARRMSG_RECEIVED, 
    OUT_CONTROLMSG_RECEIVED, OUT_NARRMSG_TRANSMITTED, OUT_CONTROLMSG_TRANSMITTED, 
    OUT_QUANTUM_ELAPSED, OUT_MSG_RECEIVED, OUT_NODE_INFO, OUT_DISPLAY_NODE, OUT_PACKET_DROPPED, OUT_SIM_RESULTS,
    
    //Protocol Events
    NEW_NARRATIVE_MESSAGE, MESSAGE_TO_NODE, CLOCK_TICK,
    
    //AppLayer
    GENERATE_DATA
    
  };
  
  public EventType            eventType;
  public String               nodeId;
  public String               sourceId;
  public String               destinationId;
  public String               informationalMessage;
  public String               transmittedMessage;
  public Message              message;
  public int                  nodeX;
  public int                  nodeY;
  public int                  nodeRange;
  public int                  totalSent;
  public int                  totalReceived;
  public double               energy;
  public int                  packetDrop;
  public NodeCreator.NodeType nodeType;
  public long                 time;
  public boolean              isPromiscuous;
  public int                  retries;
  public int                  NB;
  public int                  backoff;
  public int                  state;
  public int                  role;
  public String               macadd;
  public int                  waitACK;
  public boolean              ACK;
  public NodeAttributes getNodeAttributes() {
    return new NodeAttributes(nodeId, nodeX, nodeY, nodeRange, energy, packetDrop, totalSent, totalReceived, isPromiscuous, retries, NB, backoff, state, role, macadd, waitACK, ACK);
  }
  
  public void setNodeAttributes(NodeAttributes n) {
    nodeX = n.x;
    nodeY = n.y;
    nodeRange = n.range;
    nodeId = n.id;
    isPromiscuous = n.isPromiscuous;
  }
  
    private static SimTime simTime;
  public static void setSimTime(SimTime s) {
    simTime = s;
  }
  
  public EventManager() {
    //If the time keeper is set, view the current time from it.
    if(simTime != null) {
      time = simTime.getTime();
    }
  }
  
  public static EventManager inInsertMessage(Message message) {
    EventManager e = new EventManager();
    e.transmittedMessage = message.message;
    e.sourceId = message.originId;
    e.destinationId = message.destinationId;
    e.eventType = EventType.IN_INSERT_MESSAGE;
    e.time = simTime.getTime();
    return e;
  }
  
  public static EventManager outInsertMessage(String sourceID, String destID, String message) {
    EventManager e = new EventManager();
    e.eventType = EventType.OUT_INSERT_MESSAGE;
    e.informationalMessage = "Data Generated. Source ID: " + sourceID + " Dest ID: " + destID + " Message: " + message;
    e.time = simTime.getTime();
    return e;
  }
  
  public static EventManager outPacketDropped(String sourceID, String destID) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_PACKET_DROPPED;
    d.informationalMessage = "Packet Dropped  : Source ID: " + sourceID + " Dest ID: " + destID +".";
    d.time = simTime.getTime();
    return d;
  }
  
  public static EventManager inAddNode(int x, int y, int range, double energy, double packetDrop, int totalSent, int totalReceived ,boolean isPromiscuous) {
    EventManager e = new EventManager();
    e.eventType = EventType.IN_ADD_NODE;
    e.nodeX = x;
    e.nodeY = y;
    e.nodeRange = range;
    e.energy = energy;
    e.totalSent=totalSent;
    e.totalReceived=totalReceived;
    e.isPromiscuous = isPromiscuous;
    e.time = simTime.getTime();
    return e;
  }

  public static EventManager inDeleteNode(String id) {
    EventManager e = new EventManager();
    e.eventType = EventType.IN_DEL_NODE;
    e.nodeId = id;
    e.time = simTime.getTime();
    return e;
  }

  public static EventManager inSetNodePromiscuity(String id, boolean isPromiscuous) {
    EventManager e = new EventManager();
    e.eventType = EventType.IN_SET_NODE_PROMISCUITY;
    e.isPromiscuous = isPromiscuous;
    e.nodeId = id;
    e.time = simTime.getTime();
    return e;
  }
  
  public static EventManager outAddNode(NodeAttributes n) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_ADD_NODE;
    d.setNodeAttributes(n);
    d.informationalMessage = "Added Node : " + n.id + ".";
    d.time = simTime.getTime();
    return d;
  }
  
  public static EventManager outDisplayNode(NodeAttributes n) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_DISPLAY_NODE;
    d.setNodeAttributes(n);
    if (n.totalReceived==0){
        d.informationalMessage = "Displayed Routing Table of Node : " + n.id + ". Node Energy: " + Math.floor(n.energy * 1000) / 1000+ "%"
            + ". No packet Received";}
    else{
        d.informationalMessage = "Displayed Routing Table of Node : " + n.id + ". Node Energy: " + Math.floor(n.energy * 1000) / 1000+ "%"
            + ". Node packetDrop: " + String.format("%.2f", (n.packetDrop/(float)n.totalReceived) * 100) + "%";}
    d.time = simTime.getTime();
    return d;
  }
  
  public static EventManager outSimResults(String message) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_SIM_RESULTS;
    d.informationalMessage = message ;
    d.time = simTime.getTime();
    return d;
  }
  
  public static EventManager outDeleteNode(String id) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_DEL_NODE;
    d.nodeId = id;
    d.informationalMessage = "Node Out of Energy: " + id  + "%";
    d.time = simTime.getTime();
    return d;
  }

  public static EventManager outMsgRecieved(String sourceId, String destId, String message) {
    EventManager e = new EventManager();
    e.eventType = EventType.OUT_MSG_RECEIVED;
    e.sourceId = sourceId;
    e.destinationId = destId;
    e.transmittedMessage = message;
    e.informationalMessage = "Node " + sourceId + " successfuly sent a message to Node " + destId + " Message: " + e.transmittedMessage;
    e.time = simTime.getTime();
    return e;
  }
  
  public static EventManager outMsgTransmitted(String sourceId, String destId, String message) {
    EventManager e = new EventManager();
    e.eventType = EventType.OUT_MSG_TRANSMITTED;
    e.sourceId = sourceId;
    e.destinationId = destId;
    e.transmittedMessage = message;
    e.informationalMessage = "Node " + sourceId + " transmitted a message to Node " + destId;
    e.time = simTime.getTime();
    return e;
  }
  
  public static EventManager outError(String informationalMessage) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_ERROR;
    d.informationalMessage = informationalMessage;
    d.time = simTime.getTime();
    return d;
  }
  
  public static EventManager outDebug(String informationalMessage) {
    
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_DEBUG;
    d.informationalMessage = informationalMessage;
    d.time = simTime.getTime();
    return d;
  }

  static EventManager outInformation(String informationalMessage) {
    // stub
    return new EventManager();
  }

  public static EventManager outControlMsgTransmitted(String sourceId, Message msg) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_CONTROLMSG_TRANSMITTED;
    d.sourceId = msg.originId;
    d.destinationId = msg.destinationId;
    d.transmittedMessage = msg.message;
    d.informationalMessage = d.sourceId + " transmitted control message to " + d.destinationId + " : " + msg.message;
    d.time = simTime.getTime();
    return d;
  }

  public static EventManager outNarrMsgTransmitted(String sourceId, Message msg) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_NARRMSG_TRANSMITTED;
    d.sourceId = msg.originId;
    d.destinationId = msg.destinationId;
    d.transmittedMessage = msg.message;
    d.informationalMessage = d.sourceId + " transmitted narrative message to " + d.destinationId + " : " + msg.message;
    d.time = simTime.getTime();
    return d;
  }
  
  public static EventManager outControlMsgReceived(String sourceId, Message msg) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_CONTROLMSG_RECEIVED;
    d.sourceId = msg.originId;
    d.destinationId = sourceId;
    d.transmittedMessage = msg.message;
    d.informationalMessage = d.sourceId + " received control message from " + d.destinationId + " : " + msg.message;
    d.time = simTime.getTime();
    return d;
  }

  public static EventManager outNarrMsgReceived(String sourceId, Message msg) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_NARRMSG_RECEIVED;
    d.sourceId = msg.originId;
    d.destinationId = sourceId;
    d.transmittedMessage = msg.message;
    d.informationalMessage = d.sourceId + " received narrative message from " + d.destinationId + " : " + msg.message;
    d.time = simTime.getTime();
    return d;
  }
   
  public static EventManager outNodeInfo(String infoMsg) {
    EventManager e = new EventManager();
    e.eventType = EventType.OUT_NODE_INFO;
    e.informationalMessage = infoMsg;
    e.time = simTime.getTime();
    return e;
  }

  public static EventManager outQuantumElapsed() {
    EventManager e = new EventManager();
    e.eventType = EventType.OUT_QUANTUM_ELAPSED;
    e.time= simTime.getTime();
    return e;
  }
  
  public static EventManager newNarrativeMessage(String nodeID, String sourceId, String destId, String message, int delay) {
    EventManager e = new EventManager();
    e.eventType = EventType.NEW_NARRATIVE_MESSAGE;
    e.nodeId=nodeID;
    e.sourceId = sourceId;
    e.destinationId = destId;
    e.transmittedMessage = message;
    e.time= simTime.getTime() + delay;
    return e;
  }
  
  public static EventManager messageToNode(String nodeID, Message msg, int delay ) {
    EventManager e = new EventManager();
    e.eventType = EventType.MESSAGE_TO_NODE;
    e.message=msg;
    e.nodeId=nodeID;
    e.time= simTime.getTime() + delay;
    return e;
  }
  /////////////////////////////////////////////////////////////////////////////////////
  //Node Events
  /*public static EventManager Recive_Message(Message msg, int delay ) {
    EventManager e = new EventManager();
    e.eventType = EventType.RECEIVE_MESSAGE;
    e.message=msg;
    e.time= simTime.getTime() + delay;
    return e;
  }*/
  
  public static EventManager clocktick(String nodeID) {
    EventManager e = new EventManager();
    e.eventType = EventType.CLOCK_TICK;
    e.nodeId=nodeID;
    e.time= simTime.getTime();
    return e;
  }
  
    public static EventManager generateData(String nodeID, int interval) {
    EventManager e = new EventManager();
    e.eventType = EventType.GENERATE_DATA;
    e.nodeId=nodeID;
    e.time= simTime.getTime() + interval;
    return e;
  }
  
  /////////////////////////////////////////////////////////////////////////////////////
 
   private static final NodeType[] nodeTypes = NodeType.values();
  public static NodeType[] getNodeTypes() {
    return nodeTypes;
  }
  
  public static NodeType parseNodeType(String str) {
    //Get each possible node type
    NodeType[] nTypes =  getNodeTypes();
    
    //For each nType..
    for(NodeType nt : nTypes){
      if(str.equals(nt.toString())) {
        return nt;
      }
    }
    
    //No match
    return null;
  }
  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
  static final Class<EventManager> c = EventManager.class;
  static final Field[] fields = c.getFields();
  public String getLogString() {
    StringBuilder sb = new StringBuilder();
    // proposed format of log string is:
    // comma separated values, with public fields of EventManager printed out in
    // order
 
    //Trunc the string builder
    sb.setLength(0);
    
    for (Field f : fields) {
      Object obj;
      try {
        obj = f.get(this);
        if (obj != null) {
          sb.append(obj.toString() + ",");
        } else {
          sb.append(",");
        }
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
        System.exit(1);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
        System.exit(1);
      }

    }
    // remove trailing comma
    sb.deleteCharAt(sb.length()-1);
    
    // add a newline char
    sb.append(newline);
    return sb.toString();
  }

  public static String getLogHeader() {
    // use reflection to get each field name
    Class<EventManager> c = EventManager.class;
    Field[] fields = c.getFields();
    String ret = "";
    for (Field f : fields) {
      ret += f.getName() + ",";
    }
    // remove trailing comma
    ret = ret.substring(0, ret.length() - 1);
    return ret;
  }

  private static EventType getEventTypeFromString(String str) {
    // use reflection to get each field
    for(EventType e : EventType.values()) {
      if(e.toString().equals(str)) return e;  
    }
    return null;
  }

  public static EventManager parseLogString(String lineEvent) {
    EventManager e = new EventManager();
    try
    {
      String[] details = lineEvent.split(",");
      
      e.eventType = getEventTypeFromString(details[0]);
      if(e.eventType == null) {
        //Must have event type field.
        return null;
      }
      
      e.nodeId = details[1];
      e.sourceId = details[2];
      e.destinationId = details[3];
      e.informationalMessage = details[4];
      e.transmittedMessage = details[5];
      e.nodeX = Integer.parseInt(details[7]);
      e.nodeY = Integer.parseInt(details[8]);
      e.nodeRange = Integer.parseInt(details[9]);
      e.nodeType = parseNodeType(details[10]);
      e.time = Long.parseLong(details[11]);
      e.isPromiscuous = Boolean.parseBoolean(details[12]);
      
    }
    catch (Exception ex){
      ex.printStackTrace();
      return null;
    }

    return e;
  }
  
}
