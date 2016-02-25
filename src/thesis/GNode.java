/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis;

/**
 *
 * @author harve
 */

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GNode extends JPanel {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public static String      newline          = System
                                                 .getProperty("line.separator");

  static public GNode       SelectedNode;

  // /Constructor
  public GNode(String id, int x, int y, int range, JLayeredPane layeredPane) {
    // Copy in the id, coordinates, range
    id_ = id;
    this.layeredPane = layeredPane;
    this.range = range;


  }

 

  // /Functions
  private boolean locked = false;
  public void setLocked(boolean locked) {
    this.locked = locked;  
  }
  
  public void addListener(GNodeListener l) {
    this.listeners.add(l);
  }

  public void removeListener(GNodeListener l) {
    this.listeners.remove(l);
  }


  // Select
  public void select() {
    // unselect the currently selected node
    GNode tmp = GNode.SelectedNode;
    if (GNode.SelectedNode != null) {
      GNode.SelectedNode.unselect();
    }

    isSelected = true;
    GNode.SelectedNode = this;
    // System.out.println("selecting a node..");
   // this.img_ = ImageFactory.getSelectedNodeImg();

    if (tmp != null) {
      tmp.repaint();
    }

    this.repaint();
  }

  // Unselect
  public void unselect() {
    // System.out.println("unselecting a node..");
    isSelected = false;
    GNode.SelectedNode = null;
   // this.img_ = ImageFactory.getNodeImg();
//
    this.repaint();

  }

  public boolean isSelected() {
    return (GNode.SelectedNode == this);
  }
  
  
  public void setXY(int x, int y) {
    // Set the new location of the canvas
    setLocation(new Point(x, y));
  }

  public int getRange() {
    return range;
  }
  public void setRange(int range) {
    this.range = range;
  }

  public String getId() {
    return this.id_;
  }

  private static Point p = new Point();
  public Point getCenter() {
    p.x = getX() + img_.getWidth() / 2;
    p.y =  getY() + img_.getHeight() / 2;
    return p;
  }


  // ID of the node
  private String                      id_;

  private boolean                     isSelected;

  // DraggedNode boolean. Will be set true if the node is currently being
  // dragged
  boolean                             isDragged;

  private JLayeredPane                layeredPane;


  private BufferedImage               img_      = null;

  private final ArrayList<GNodeListener> listeners = new ArrayList<GNodeListener>();

  private boolean                     isClicked = false;

  private class GNodeMouseListener extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
      if(locked) {
        return;
      }
      
      // Invalidate any other drag notions initially
      isClicked = false;

      // If this is a popup event, notify the handlers and return
      if (e.isPopupTrigger()) {
        // System.out.println("Popup event");
        for (GNodeListener l : listeners) {
          l.nodePopupEvent((GNode) e.getSource(), e.getX(), e.getY());
        }
        return;
      }

      // If it's a non popup event right click, just return out
      if (e.getButton() == MouseEvent.BUTTON3) {
        return;
      }

      // Mark this node as selected
      select();

      // Notify the handlers
      for (GNodeListener l : listeners) {
        l.nodeSelected((GNode) e.getSource());
      }

      isClicked = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      if(locked) {
        return;
      }
      if (e.isPopupTrigger()) {
        // System.out.println("Popup event");
        for (GNodeListener l : listeners) {
          l.nodePopupEvent((GNode) e.getSource(), e.getX(), e.getY());
        }

        return;
      }

      // If its a right click, or in locked replay mode return
      if (e.getButton() == MouseEvent.BUTTON3 || lockedReplayMode) {
        return;
      }


      isClicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      if(locked) {
        return;
      }
      // Set state to entered
      //setEntered(true);

      // Notify the handlers
      for (GNodeListener l : listeners) {
        l.nodeEntered((GNode) e.getSource());
      }
    }

    @Override
    public void mouseExited(MouseEvent e) {
      if(locked) {
        return;
      }
      // Set state to not entered
      //setEntered(false);

      // Notify the handler
      for (GNodeListener l : listeners) {
        l.nodeExited((GNode) e.getSource());
      }
    }
  }

  private class GNodeMouseMotionListener extends MouseMotionAdapter {
    @Override
    public void mouseDragged(MouseEvent e) {
      if(locked || lockedReplayMode) {
        return;
      }

      // If its a right click, return
      if (!isClicked) {
        return;
      }

    }
  }


  private int range;

  private boolean lockedReplayMode = false;
  public void setLockedReplayMode(boolean b) {
    lockedReplayMode = b;   
  }

}