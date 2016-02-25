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
    //addMouseListener(new PopClickListener());
    setVisible(true);
    
  }

  public void setNodeInspector(NodeInspector nodeInspector) {
    this.nodeInspector = nodeInspector;
  }

  public NodeInspector getNodeInspector() {
    return nodeInspector;
  }

  private NodeInspector nodeInspector;





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


    public EditNodePopup() {
      delete_item = new JMenuItem("Delete node");
      delete_item.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
         // deleteNodeReq(gnode.getId());
        }
      });

      msg_item = new JMenuItem("Send Message");
      msg_item.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          //new SendNodeMessageDialog(null, gnode.getId(), nodeAttributesArea
          //    .getNodeList());
        }
      });

      view_item = new JMenuItem("View attributes");
      view_item.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          if (nodeAttributesArea != null) {
            //nodeAttributesArea.openNodeDialog(/*gnode.getId()*/ "A");
          }
        }
      });

}
}


  public String getSimType() {
    return "AODV";
  }



  public void setNodeAttributesArea(NodeAttributesArea nodeAttributesArea) {
    this.nodeAttributesArea = nodeAttributesArea;
  }



 

}