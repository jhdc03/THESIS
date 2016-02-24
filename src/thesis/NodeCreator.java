/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis;

import thesis.Node;

/**
 *
 * @author harve
 */
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
