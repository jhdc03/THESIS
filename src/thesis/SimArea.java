/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis;


import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;
import java.util.*;

public class SimArea extends JLayeredPane {

  /**
   * 
   */
  private static final long  serialVersionUID = 1L;

  private boolean            locked           = true;
  private NodeAttributesArea nodeAttributesArea;

  // /////////////////////////////Constructor
  public SimArea() {
    setLayout(null);
    addMouseListener(new PopClickListener());
    setVisible(true);
    
  }

  public void setNodeInspector(NodeInspector nodeInspector) {
    this.nodeInspector = nodeInspector;
  }

  public NodeInspector getNodeInspector() {
    return nodeInspector;
  }

  private NodeInspector nodeInspector;




  class NodeActionHandler implements GNodeListener {

    public void nodeMoved(GNode n, int x, int y) {
      // enforce simArea's bound restrictions
     // Point boundedPoint = getBoundedNodePoint(new Point(x, y));

      // drop all connection animations associated with this node
      //animations.dropConns(n);
      
      // issue a move node request
     // moveNodeReq(n.getId(), boundedPoint.x, boundedPoint.y);

      // propagate the signal to other listeners
      for (GNodeListener g : nodeListeners) {
        g.nodeMoved(n, x, y);
      }
    }

    public void nodeEntered(GNode n) {

      // propagate the signal to other listeners
      for (GNodeListener g : nodeListeners) {
        g.nodeEntered(n);
      }
    }

    public void nodeExited(GNode n) {

      // propagate the signal to other listeners
      for (GNodeListener g : nodeListeners) {
        g.nodeExited(n);
      }
    }

    public void nodeSelected(GNode n) {

      // propagate the signal to other listeners
      for (GNodeListener g : nodeListeners) {
        g.nodeSelected(n);
      }
    }

    public void nodePopupEvent(GNode n, int x, int y) {
      EditNodePopup edit_menu = new EditNodePopup();
      edit_menu.gnode = n;
      edit_menu.gnodemap = gnodemap;
      edit_menu.show(n, x, y);

    }

  }

  public String getSelectedNodeID() {
    if (GNode.SelectedNode != null) {
      return GNode.SelectedNode.getId();
    } else
      return null;
  }

  public boolean setSelectedNode(String id) {
    // Get the node by id
    GNode n = getGNode(id);

    // If n is null, node not found. return false.
    if (n == null) {
      return false;
    }

    // Otherwise, select the node, return true.
    n.select();
    return true;
  }

  private ArrayList<GNodeListener> nodeListeners = new ArrayList<GNodeListener>();

  public void addNodeListener(GNodeListener gl) {
    nodeListeners.add(gl);
  }

  public void removeNodeSelectedListener(GNodeListener gl) {
    nodeListeners.remove(gl);
  }

  private GNode getGNode(String id) {
    // return a null reference if we don't find it
    if (!gnodemap.containsKey(id)) {
      return null;
    }

    return gnodemap.get(id);
  }

  private void addNewNodeReq(int x, int y) {
    InputHandler.dispatch(EventManager.inAddNode(x,y, Defaults.RANGE, Defaults.IS_PROMISCUOUS));
  }

  private void deleteNodeReq(String id) {
    // Dispatch
    InputHandler.dispatch(EventManager.inDeleteNode(id));
  }

  // This function will send a request to move a node to the input handler
  // eventually.
  private void moveNodeReq(String id, int x, int y) {
    InputHandler.dispatch(EventManager.inMoveNode(id, x, y));
  }


  // This function adds a node to the GUI. It's assumed that the node now exists
  // in the simulator.
  public void addNewNode(int x, int y, int range, String id) {
    // instantiate a new GNode
    GNode node = new GNode(id, x, y, range, this);

    // add it to the gnode map
    gnodemap.put(id, node);

    // add it to the canvas
    this.add(node, JLayeredPane.PALETTE_LAYER);

    // add our node listener
    node.addListener(new NodeActionHandler());
    
    // set the locked replay status
    node.setLockedReplayMode(lockedReplayMode);

    // Add a range indicator
    //animations.addRangeIndicator(node);

    reassessFPS();
    nodeAttributesArea.openNodeDialog(/*gnode.getId()*/ "A");
  }

  private boolean graphicsEnabled = true;

  public void setGraphicsEnabled(boolean isEnabled) {
    this.graphicsEnabled = isEnabled;
    if(isEnabled) {
      //animations.start();
    }
    else {
      //animations.stop();
    }
  }

  private void reassessFPS() {
    // reassess the FPS
    int newFPS = 0;
    if (gnodemap.size() != 0) {
      newFPS = (1000 / gnodemap.size());
    }
    int usedFPS;
    if (newFPS < Defaults.MAXFPS && newFPS > Defaults.MINFPS)
      usedFPS = newFPS;
    else if (newFPS <= Defaults.MINFPS) {
      usedFPS = Defaults.MINFPS;
    } else
      usedFPS = Defaults.MAXFPS;

    //animations.setFPS(usedFPS);
  }

 // private Animations animations = new Animations(this);

  public void nodeBroadcast(String nodeId) {
    if (!graphicsEnabled) {
      return;
    }
    GNode n = getGNode(nodeId);
    if (n == null)
      return;
    //animations.nodeBroadcast(n);
  }

  public void traceMessage(String fromId, String toId, Color color,
      int longevityFactor, int fatness, int priority) {

    if (!graphicsEnabled) {
      return;
    }
    GNode a = getGNode(fromId);
    GNode b = getGNode(toId);

    if (a == null || b == null)
      return;

    //animations.traceMessage(a, b, color, longevityFactor, fatness, priority);

  }

  // ////////////////////////Data

  private TreeMap<String, GNode> gnodemap = new TreeMap<String, GNode>();

  // Inner classes

  // Pop up menu for adding nodes
  class AddNodePopup extends JPopupMenu implements ActionListener {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JMenuItem                 anItem1;
    JMenuItem                 anItem2;
    int                       x, y;

    public AddNodePopup() {
      anItem1 = new JMenuItem("Add a new node");
      anItem1.addActionListener(this);
      add(anItem1);
    }

    public void actionPerformed(ActionEvent e) {
      //Enforce boundaries
      //Point p = getBoundedNodePoint(new Point(this.x,this.y));
      //addNewNodeReq(p.x,p.y);
    }
  }

  // Pop up menu for editing/deleting nodes
  class EditNodePopup extends JPopupMenu {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JMenuItem                 delete_item;
    JMenuItem                 msg_item;
    JMenuItem                 view_item;
    GNode                     gnode;
    TreeMap<String, GNode>    gnodemap;

    public EditNodePopup() {
      delete_item = new JMenuItem("Delete node");
      delete_item.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          deleteNodeReq(gnode.getId());
        }
      });

      msg_item = new JMenuItem("Send Message");
      msg_item.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          new SendNodeMessageDialog(null, gnode.getId(), nodeAttributesArea
              .getNodeList());
        }
      });

      view_item = new JMenuItem("View attributes");
      view_item.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          if (nodeAttributesArea != null) {
              Iterator<Node> i;
             // i=store.get
            nodeAttributesArea.openNodeDialog(/*gnode.getId()*/ "A");
          }
        }
      });

      if(!lockedReplayMode){
        add(delete_item);
        add(msg_item);
      }
      add(view_item);

    }

  }

  // Listener for spawning new pop menus
  class PopClickListener extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
      if (e.isPopupTrigger()) {
        // If the Sim Area is locked just return.
        if (locked == true)
          return;

        doPop(e);
      }
    }

    // override
    @Override
    public void mouseReleased(MouseEvent e) {
      if (e.isPopupTrigger()) {
        // If the Sim Area is locked just return.
        if (locked == true)
          return;

        doPop(e);
      }
    }

    private void doPop(MouseEvent e) {
      // If the Sim Area is locked just return.
      if (locked || lockedReplayMode)
        return;

      // Show the "Add Node" menu.
      AddNodePopup menu = new AddNodePopup();
      menu.x = e.getX();
      menu.y = e.getY();
      menu.show(e.getComponent(), e.getX(), e.getY());
    }
  }


  public String getSimType() {
    return "AODV";
  }

  public void selectNode(String nodeId) {
    GNode g = getGNode(nodeId);
    if (g == null) {
      return;
    } else {
      g.select();
    }

  }

  /**
   * This function will allow the GUI to sort of "deactivate" the the signal
   * handlers.
   * 
   * @author kennlmay
   * 
   * @param locked
   *          the state of the simulation
   */



  /**
   * You can use this function to determine the state of the sim area.
   * 
   * @author kennylmay
   * 
   * @return the locked
   */
  public boolean isLocked() {
    return locked;
  }

  public void clear() {
    ArrayList<String> nodeIds = new ArrayList<String>();
    for (GNode n : gnodemap.values()) {
      // Accumulate every node id
      nodeIds.add(n.getId());
    }
    for (String id : nodeIds) {
    }
  }



  public void setNodeAttributesArea(NodeAttributesArea nodeAttributesArea) {
    this.nodeAttributesArea = nodeAttributesArea;
  }



  private boolean lockedReplayMode = false;
  public void setLockedReplayMode(boolean b) {
    lockedReplayMode=b;
    for(GNode n : gnodemap.values()) {
      n.setLockedReplayMode(b);
    }
  }


}