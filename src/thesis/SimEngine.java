
package thesis;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javax.swing.JDialog;
import thesis.NodeCreator.NodeType;


public class SimEngine implements InputConsumer, SimTime, NodeInspector {
    private long time = 0;
    NodeStore store = new NodeStore();
    Queue<Message> messageQueue = new LinkedList<Message>();
    Queue<Message> newMessages = new LinkedList<Message>();
    OrderedSet EventQueue;

    @Override
    public long getTime() {
        return time;
    }

    void doAllEvents() {
        Event e;
        while ((e = (Event) EventQueue.removeFirst()) != null) {
            time = e.time;
            e.execute(this);
        }
    }

    void insert(Event e) {
        EventQueue.insert(e);
    }

    Event cancel(Event e) {
        throw new java.lang.RuntimeException("Method not implemented");
    }

   
      public void AddMultipleNodes( ) {
        Random r = new Random();
        double X = 200;
        double Y = 200;
        int numberOfNodes = 5;


        for (int i = 1; i <= numberOfNodes; i++){
          int range = r.nextInt(400) + 50; // Min range of 50
          int x = r.nextInt((int)X);
          int y = r.nextInt((int)Y);
          InputHandler.dispatch(EventManager.inAddNode(x,y,range, Defaults.IS_PROMISCUOUS));
        }
       }
      

    /////////////////////////////////////////////////////////////
 
    
    
    public void start() {
        AddMultipleNodes();
        
        EventQueue = new ListQueue();
        
        
        doAllEvents();
        
        for (int i = 0; i < 100; i++) {
            runSimulation();
        }
                Node node = null;
                Iterator<Node> i;
                i = store.getNodes();
                while (i.hasNext()) {
                    node = i.next();
                    OutputHandler.dispatch(EventManager.outDisplayNode(node.getAttributes()));
                }
                
        
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void runSimulation() {
        
            //Increment sim time
            time++;
            
            //Begin a new quantum
            OutputHandler.dispatch(EventManager.outQuantumElapsed());
            System.out.println(time);
            
            // Enter the critical area for the simulation
            //////////////////////////////////////////////////////
              MainLoop();
      }
 

  public void MainLoop() {
    Node node = null;
    Message message = null;
    Iterator<Node> i;
    Iterator<Message> mi;
   
    // If there are any messages in the newMessage Q, introduce them
    // into the network.
    mi = newMessages.iterator();
    while(mi.hasNext()) {
      //Get the ref to the message
      Message m = mi.next();
      
      //Delete it from the Q
      mi.remove();
      
      //Get the node
      Node n = store.getNode(m.originId);
      if(n == null) {
        continue;
        
      }
      //Introduce the message into the network
      n.newNarrativeMessage(m.originId, m.destinationId, m.message);
    }
    
    
    // If there are messages in the messageQueue try to attempt delivery.
    while (messageQueue.isEmpty() == false) {
      message = messageQueue.poll();

      // If the message is a broadcast then try to send to everyone
      if (message.destinationId == Message.BCAST_STRING) {  
        
        i = store.getNodes();
        while(i.hasNext()) {
          node = i.next();
          //if the node is null, it was deleted, continue
          if (node == null)
            continue;
          
          // Only allow the nodes in range to hear the broadcast.
          if (canCommunicate(message.originId, node.getAttributes().id) && message.originId != node.getAttributes().id) {
            node.messageToNode(message);
          }
        }
        // Else if the messageQueue is not a broadcast try to send it to the
        // destination id.
      } else {
        if (canCommunicate(message.originId, message.destinationId)) {
          // SAK - Send the message to the destination node.
          store.getNode(message.destinationId).messageToNode(message);
        }
      }
    }

    // Issue a clock tick to each node so that they can make algorithmic
    // decisions.
    i = store.getNodes();
    while(i.hasNext()) {
      // / Issue a clock tick to each node
      node = i.next();
      if (node == null)
        continue;
      node.clockTick();
    }

    // Check each node for messages waiting to be sent and gather them up
    // to be stored in our message queue.
    i = store.getNodes();
    while(i.hasNext()) {
      node = i.next();
      // Gather all the messages from each node.
      while ((message = node.messageToNetwork()) != null) {
        messageQueue.add(message);
      }
    }
    
  }
  

    public void consumeInput(EventManager e) {
        Node n;

        // Enter critical area
        switch (e.eventType) {

            case IN_ADD_NODE:
                // Get the node attributes for this input event
                NodeAttributes ni = e.getNodeAttributes();

                // Assign an ID to the node
                String id = assignNodeId();

                // Make a new network node with these attributes
                ni = new NodeAttributes(id, ni.x, ni.y, ni.range, ni.isPromiscuous);
                n = NodeCreator.makeNewNode(getNodeType(), ni);
                OutputHandler.dispatch(EventManager.outAddNode(ni));
                // Add it to the node store
                store.addNode(n);
                // Dispatch an output event indicating a new node has entered
                // the network.
                
                break;

            case IN_DEL_NODE:
                if (store.deleteNode(e.nodeId)) {
                    OutputHandler.dispatch(EventManager.outDeleteNode(e.nodeId));
                } else {
                    OutputHandler.dispatch(EventManager.outError("Could not delete node " + e.nodeId + ", node does not exist"));
                }
                break;
            //////////////////////////////////////////////////////////////////////    
            case OUT_NODE_INFO:

                System.out.println("test print node");
                Node node = null;
                Iterator<Node> i;
                i = store.getNodes();
                while (i.hasNext()) {
                    node = i.next();
                    System.out.println("Node count:" + node.toString());
                    //if the node is null, it was deleted, continue
                    System.out.println("ID :" + node.getAttributes().id + "\n");
                    System.out.println("X :" + node.getAttributes().x + "\n");
                    System.out.println("Y :" + node.getAttributes().y + "\n");
                    System.out.println("Promiscuous :" + node.getAttributes().isPromiscuous + "\n");

                    //node.get
                }
                break;

            case IN_INSERT_MESSAGE:
                // Check if the source node exists
                if (store.getNode(e.sourceId) == null) {
                    OutputHandler.dispatch(EventManager.outError("Could not insert a new message into the network, originating node " + e.nodeId + " does not exist"));
                }

                // Add the message to the newMessages Q
                Message m = new Message(e.destinationId, e.sourceId, e.transmittedMessage);
                newMessages.add(m);

                // Dispatch the insert message event
                OutputHandler.dispatch(EventManager.outInsertMessage(e.sourceId, e.destinationId, e.transmittedMessage));

                break;
        }
    }

    private NodeType nodeType = NodeType.AODV;

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nt) {
        nodeType = nt;
    }

    /**
     * assignNodeId method.
     *
     * Assigns a new node id. It uses the private variable currId to keep track
     * of the next id. The assignment sequence is as follows:
     *
     * A......Z AA....AZ BA....BZ ........ AAA..AAZ ABA..ABZ ........
     *
     * The algorithm used is a modified version of the convert decimal to hex
     * algorithm (or any other digit). It cheats a bit because there is no
     * "zero" digit in the ID assigning scheme (Just A-Z).
     *
     *
     */
    private String assignNodeId() {
        // Assign a three character ID from A-Z
        //

        // increment the id
        currId++;

        int charA = (int) 'A';
        int totalChars = 26;
        String ret = "";

        int remainder;
        int quotient = currId;
        int count = 0;

        while (quotient != 0) {
            // Divide the digit by our alphabet size. The remainder is the digit for
            // this place.
            remainder = quotient % totalChars;
            quotient = quotient / totalChars;

            // Convert the digit to its representation (A-Z)
            char c;
            c = (char) (remainder + charA);

            count++;
            // Prepend the return string
            ret = c + ret;
        }

        //decrement the first char of the ID
        char f = ret.charAt(0);
        f--;
        ret = f + ret.substring(1);

        return ret;
    }

    private int currId = 0;

    /**
     * This method is used for determining if a can send a message to antoher
     * node
     *
     * @param node1
     * @param node2
     *
     * @return boolean
     */
    private Point point1 = new Point();

    private boolean canCommunicate(String OriginID, String DestinationID) {
        NodeAttributes originAtt = store.getNodeAttributes(OriginID);
        NodeAttributes destinationAtt = store.getNodeAttributes(DestinationID);

        //Nodes that don't exist can't communicate; return false.
        if (originAtt == null || destinationAtt == null) {
            return false;
        }
        point1.x = originAtt.x;
        point1.y = originAtt.y;

        double distance = point1.distance(destinationAtt.x, destinationAtt.y);
        if (distance > originAtt.range || distance > destinationAtt.range) {
            return false;
        } else {
            return true;
        }
    }

    // Fulfills the "Node Inspector" contract.
    public JDialog getNodeDialog(String nodeId) {
        Node node = store.getNode(nodeId);
        if (node == null) {
            return null;
        }
        return node.getNodeDialog();
    }
    // Fulfills the "Node Inspector" contract.

    public void updateNodeDialog(String nodeId, JDialog dialog) {

        Node node = store.getNode(nodeId);
        if (node == null) {
            return;
        }
        node.updateNodeDialog(dialog);

    }

    // Fulfills the "Node Inspector" contract.
    public NodeAttributes getNodeAttributes(String nodeId) {
        Node node = store.getNode(nodeId);

        if (node == null) {
            return null;
        }
        return node.getAttributes();

    }

}
