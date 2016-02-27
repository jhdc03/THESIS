
package thesis;
import java.util.HashMap;
import java.util.Iterator;

public class NodeStore {
   

// Hash map of nodes.
private HashMap<String, Node> store = new HashMap<String, Node>();


public NodeAttributes getNodeAttributes(String nodeId) {
   Node node = store.get(nodeId);
   
   if(node == null) {
     return null;
   }
   return node.getAttributes();
}

public void clear() {
   store.clear();
}


public Node getNode(String nodeId) {
  return store.get(nodeId);
  
}

public void addNode(Node node) {
      store.put(node.getAttributes().id, node);
}

public boolean deleteNode(String id) {
   return (store.remove(id) != null);
}

// Returns an iterator for all of the nodes in the store.
public Iterator<Node> getNodes() {
   return store.values().iterator();
}



}