
package thesis;

import java.lang.reflect.Field;
import thesis.NodeCreator.NodeType;

public class EventManager {
    private static String newline = System.getProperty("line.separator");
  public enum EventType {
    // Input event types
    IN_ADD_NODE,IN_DEL_NODE,IN_SET_NODE_PROMISCUITY, IN_INSERT_MESSAGE,
    
    // Output event types
    OUT_ADD_NODE,OUT_MSG_TRANSMITTED, OUT_DEL_NODE, OUT_DEBUG, OUT_ERROR,OUT_INSERT_MESSAGE, OUT_NARRMSG_RECEIVED, 
    OUT_CONTROLMSG_RECEIVED, OUT_NARRMSG_TRANSMITTED, OUT_CONTROLMSG_TRANSMITTED, 
    OUT_QUANTUM_ELAPSED, OUT_MSG_RECEIVED, OUT_NODE_INFO, OUT_DISPLAY_NODE, OUT_PACKET_DROPPED
  };
  
  public EventType            eventType;
  public String               nodeId;
  public String               sourceId;
  public String               destinationId;
  public String               informationalMessage;
  public String               transmittedMessage;
  public int                  nodeX;
  public int                  nodeY;
  public int                  nodeRange;
  public float                energy;
  public NodeCreator.NodeType nodeType;
  public long                 time;
  public boolean              isPromiscuous;
  
  
  public NodeAttributes getNodeAttributes() {
    return new NodeAttributes(nodeId, nodeX, nodeY, nodeRange, energy, isPromiscuous);
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
    return e;
  }
  
  public static EventManager outInsertMessage(String sourceID, String destID, String message) {
    EventManager e = new EventManager();
    e.eventType = EventType.OUT_INSERT_MESSAGE;
    e.informationalMessage = "Data Generated. Source ID: " + sourceID + " Dest ID: " + destID + " Message: " + message;
    return e;
  }
  
  public static EventManager outPacketDropped(String sourceID, String destID) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_PACKET_DROPPED;
    d.informationalMessage = "Packet Dropped  : Source ID: " + sourceID + " Dest ID: " + destID +".";
    return d;
  }
  
  public static EventManager inAddNode(int x, int y, int range, boolean isPromiscuous) {
    EventManager e = new EventManager();
    e.eventType = EventType.IN_ADD_NODE;
    e.nodeX = x;
    e.nodeY = y;
    e.nodeRange = range;
    e.isPromiscuous = isPromiscuous;
    return e;
  }

  public static EventManager inDeleteNode(String id) {
    EventManager e = new EventManager();
    e.eventType = EventType.IN_DEL_NODE;
    e.nodeId = id;
    return e;
  }

  public static EventManager inSetNodePromiscuity(String id, boolean isPromiscuous) {
    EventManager e = new EventManager();
    e.eventType = EventType.IN_SET_NODE_PROMISCUITY;
    e.isPromiscuous = isPromiscuous;
    e.nodeId = id;
    return e;
  }
  
  public static EventManager outAddNode(NodeAttributes n) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_ADD_NODE;
    d.setNodeAttributes(n);
    d.informationalMessage = "Added Node : " + n.id + ".";
    return d;
  }
  
  public static EventManager outDisplayNode(NodeAttributes n) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_DISPLAY_NODE;
    d.setNodeAttributes(n);
    d.informationalMessage = "Displayed Routing Table of Node : " + n.id + ".";
    return d;
  }
  
  
  
  public static EventManager outDeleteNode(String id) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_DEL_NODE;
    d.nodeId = id;
    d.informationalMessage = "Node Deleted: " + id;
    return d;
  }

  public static EventManager outMsgRecieved(String sourceId, String destId, String message) {
    EventManager e = new EventManager();
    e.eventType = EventType.OUT_MSG_RECEIVED;
    e.sourceId = sourceId;
    e.destinationId = destId;
    e.transmittedMessage = message;
    e.informationalMessage = "Node " + sourceId + " successfuly sent a message to Node " + destId + " Message: " + e.transmittedMessage;
    return e;
  }
  
  public static EventManager outMsgTransmitted(String sourceId, String destId, String message) {
    EventManager e = new EventManager();
    e.eventType = EventType.OUT_MSG_TRANSMITTED;
    e.sourceId = sourceId;
    e.destinationId = destId;
    e.transmittedMessage = message;
    e.informationalMessage = "Node " + sourceId + " transmitted a message to Node " + destId;
    return e;
  }
  
  public static EventManager outError(String informationalMessage) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_ERROR;
    d.informationalMessage = informationalMessage;
    return d;
  }
  
  public static EventManager outDebug(String informationalMessage) {
    
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_DEBUG;
    d.informationalMessage = informationalMessage;
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
    return d;
  }

  public static EventManager outNarrMsgTransmitted(String sourceId, Message msg) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_NARRMSG_TRANSMITTED;
    d.sourceId = msg.originId;
    d.destinationId = msg.destinationId;
    d.transmittedMessage = msg.message;
    d.informationalMessage = d.sourceId + " transmitted narrative message to " + d.destinationId + " : " + msg.message;
    return d;
  }
  
  public static EventManager outControlMsgReceived(String sourceId, Message msg) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_CONTROLMSG_RECEIVED;
    d.sourceId = msg.originId;
    d.destinationId = sourceId;
    d.transmittedMessage = msg.message;
    d.informationalMessage = d.sourceId + " received control message from " + d.destinationId + " : " + msg.message;
    return d;
  }

  public static EventManager outNarrMsgReceived(String sourceId, Message msg) {
    EventManager d = new EventManager();
    d.eventType = EventType.OUT_NARRMSG_RECEIVED;
    d.sourceId = msg.originId;
    d.destinationId = sourceId;
    d.transmittedMessage = msg.message;
    d.informationalMessage = d.sourceId + " received narrative message from " + d.destinationId + " : " + msg.message;
    return d;
  }
   
  public static EventManager outNodeInfo(String infoMsg) {
    EventManager e = new EventManager();
    e.eventType = EventType.OUT_NODE_INFO;
    e.informationalMessage = infoMsg;
    return e;
  }

  public static EventManager outQuantumElapsed() {
    EventManager e = new EventManager();
    e.eventType = EventType.OUT_QUANTUM_ELAPSED;
    return e;
  }
  
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
