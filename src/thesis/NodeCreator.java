
package thesis;

import thesis.Node;


public class NodeCreator {
    
  // Add the protocol Name to the Enumeration
  public enum NodeType { AODV };
  public static Node makeNewNode(NodeType nt, NodeAttributes na) {
    if(nt == null || na == null) {
      return null;
    }
  
    switch(nt) {
    case AODV : return new AODV(na);
    // Create nodes of the new NodeType.
    // case NewProtoName : return NewProto(na); 
    default   : return null;
    }
  }
}
